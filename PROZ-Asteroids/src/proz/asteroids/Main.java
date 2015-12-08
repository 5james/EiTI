/** @author Jakub Guzek
 *  @version 1.0.0 */
package proz.asteroids;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import proz.asteroids.controller.AsteroidController;
import proz.asteroids.controller.LaserController;
import proz.asteroids.controller.ShipController;
import proz.asteroids.model.Asteroid;
import proz.asteroids.model.Laser;
import proz.asteroids.model.Ship;
import proz.asteroids.view.AsteroidView;
import proz.asteroids.view.LaserView;
import proz.asteroids.view.ShipView;

public class Main extends Applet implements Runnable, KeyListener {
	private static final long serialVersionUID = 3959266314210039342L;
	/** Thread */
	Thread thread;
	Image offscreen;
	Dimension dim;
	/** Width and height of gaming screen/frame */
	int width, height;
	Graphics g;

	/** Used to calculations for fps */
	long timeA, timeB;
	/** Used to determine the pace of the game */
	long fps;
	/** if pause then game-actions and drawing stops */
	boolean pause = false;

	/** Statistics of ingame play */
	int score, lives, topScore;

	/** Model of the ship */
	Ship ship;
	/** View of the ship */
	ShipView shipView;
	/** Controller of the ship */
	ShipController shipController;
	
	/** Class that contains Model, View and Controller of one example of Laser */
	class Lasers {
		/** Model */
		Laser laser;
		/** View */
		LaserView laserView;
		/** Controller */
		LaserController laserController;

		/** Creating Model, View and Controller of Laser and connects them */
		Lasers () {
			laser = shipController.shooting();
			laserView = new LaserView();
			laserController = new LaserController(laser);
			laserView.setController(laserController);
			laserView.setModel(laser);
		}
	}

	/** List of lasers (active or not) */
	ArrayList<Lasers> lasers = new ArrayList<Lasers>();

	/** Class that contains Model, View and Controller of one example of Asteroid */
	class Asteroids {
		/** Model */
		Asteroid asteroid;
		/** View */
		AsteroidView asteroidView;
		/** Controller */
		AsteroidController asteroidController;

		/** Creating Model, View and Controller of Laser and connects them
		 * @param size of the Asteroid (0 - small; 1 - normal size; 2 - big) */
		Asteroids (int size) {
			asteroid = new Asteroid(size, ship.getX(), ship.getY(), ship.getAngle(), width, height);
			asteroidView = new AsteroidView(asteroid);
			asteroidController = new AsteroidController(asteroid);
		}

		/** Creating Model, View and Controller of Laser and connects them
		 * @param size of the Asteroid (0 - small; 1 - normal size; 2 - big)
		 * @param x position of asteroid
		 * @param y position of asteroid */
		Asteroids (int size, double xAsteroids, double yAsteroids) {
			asteroid = new Asteroid(size, ship.getX(), ship.getY(), ship.getAngle(), width, height, xAsteroids, yAsteroids);
			asteroidView = new AsteroidView(asteroid);
			asteroidController = new AsteroidController(asteroid);
		}
	}
	/** Number of proper existing (with destroyed ones counted in) Asteroids*/
	int numberOfExistingAsteroids;
	/** List of the asteroids (active or not) */
	ArrayList<Asteroids> asteroids = new ArrayList<Asteroids>();

	@Override
	public void run() {
		while(true) {
			timeA = System.currentTimeMillis(); /** Starting time of one cycle */
			if (!pause)  {
				shipController.move(width, height);	/** One move of the Ship */
				if (ship.isShoot()) /** If ship i shooting then create new laser */
					lasers.add(new Lasers());
				
				for (int i = 0; i<lasers.size(); i++) {
					if (!lasers.get(i).laser.isExisting()) /** If lasers are inactive then remove from list */
						lasers.remove(i);
				}
				
				for (Lasers a : lasers) {
					a.laserController.move(width, height); /** Normal lasers moving */
				}
				for (int i = 0; i < asteroids.size(); i++) {
					if (asteroids.get(i).asteroidController.collision(ship)) {
						lives--;
						restartShip(); /** collision between ship and asteroid */
					}
					for (int j = 0; j < lasers.size(); j++) /** collision between laser and asteroid */ {
						if (asteroids.get(i).asteroidController.collision(lasers.get(j).laser)) {
							score += asteroids.get(i).asteroid.getSize()*50+50;
							if (score > topScore) /** Assign top score */
								topScore = score;
							/** Creating two smaller asteroids */
							if (asteroids.get(i).asteroid.getSize() > 0) {
								double xAsteroids = asteroids.get(i).asteroid.getX(); 
								double yAsteroids = asteroids.get(i).asteroid.getY();
								asteroids.add(new Asteroids(asteroids.get(i).asteroid.getSize()-1, xAsteroids, yAsteroids));
								asteroids.add(new Asteroids(asteroids.get(i).asteroid.getSize()-1, xAsteroids, yAsteroids));
							}
							asteroids.remove(i);
							lasers.remove(j);
						}
					}
				}
				if (asteroids.size() == 0) {
					for(int i = 0; i<numberOfExistingAsteroids; i++) {
						if (i>2)
							asteroids.add(new Asteroids(2));
						else
							asteroids.add(new Asteroids(1));
					}
					numberOfExistingAsteroids++;
				}
				for (Asteroids x : asteroids)
					x.asteroidController.move(width, height);
				if (lives < 0)
					restartGame();
				repaint();
			}
			try {
				timeB = System.currentTimeMillis(); 
				if(fps - (timeB-timeA) > 0) 				
					Thread.sleep(fps - (timeB - timeA)); 	//timer
			} catch(InterruptedException e) {}

		}

	}

	public void paint(Graphics graphics) {
		update(graphics);
	}
	public void update(Graphics graphics) {
		Graphics2D g2d = (Graphics2D) g;
		
		/** ANTIALIASING */
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		
		/** filling background */
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height); 
		
		/** Drawing ship */
		shipView.updateView(g2d);
		
		/** Drawing all of lasers */
		for (Lasers l : lasers) {
			l.laserView.updateView(g2d);
		}
		
		/** Drawing all of asteroids */
		for (Asteroids x : asteroids)
			x.asteroidView.updateView(g2d);
		
		/** Drawing statistics of the game */
		g2d.drawString("lives: " + lives, 10, 470);
		g2d.drawString("score: " + score, 60, 470);
		g2d.drawString("topscore: " + topScore, 150, 470);

		graphics.drawImage(offscreen, 0, 0, this); //double - buffering
	}

	public void init() {
		resize(640,480);
		dim = getSize(); 
		width = dim.width;
		height = dim.height;
		fps = 25;

		numberOfExistingAsteroids = 1;
		score = 0;
		lives = 3; 
		topScore = 0;

		setFocusable(true); 

		ship = createShipModel();
		shipView = createShipView();
		shipController = createShipController(ship);
		shipView.setController(shipController);
		shipView.setModel(ship);

		addKeyListener(shipView);
		addKeyListener(this);

		offscreen = createImage(width, height); // double-buffering
		g = offscreen.getGraphics();
		thread = new Thread(this);
		thread.start();

	}
	/** Create and return one instance of the Ship Model */
	private Ship createShipModel () {
		return new Ship(width/2, height/2, 0);
	}
	/** Create and return one instance of the Ship View */
	private ShipView createShipView() {
		return new ShipView();
	}
	/** Create and return one instance of the Ship Controller */
	private ShipController createShipController(Ship s) {
		return new ShipController(s);
	}

	/** Restart position of ship, removes all of existing objects and restarts statistics beyond top score */
	private void restartGame() {
		removeAllObjects();
		restartShip();
		numberOfExistingAsteroids = 1;
		lives = 3;
		score = 0;
	}
	
	/** Restart the position ship */
	private void restartShip() {
		ship = createShipModel();
		shipView = createShipView();
		shipController = createShipController(ship);
		shipView.setController(shipController);
		shipView.setModel(ship);
		addKeyListener(shipView);
	}
	
	/** Remove all of existing objects (asteroids and lasers) */
	private void removeAllObjects() {
		while (asteroids.size() != 0) {
			asteroids.remove(0);
		}
		while (lasers.size() != 0) {
			lasers.remove(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {


	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_P) {
			pause = (pause==false) ? true : false;
		}
		if (key == KeyEvent.VK_R) {
			pause = true;
			restartGame();
			pause = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

}