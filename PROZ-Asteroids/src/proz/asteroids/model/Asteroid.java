package proz.asteroids.model;

import java.awt.geom.Point2D;

public class Asteroid implements BasicModel {
	/** X and Y - Coordinates of the Asteroid */
	double x, y;
	/** Speed of the Asteroid in X and Y coordinated */
	double speedX, speedY;
	/** How fast does the Asteroid turns */
	double turningSpeed;
	/** Angle of the Asteroid */
	double angle;
	/** Defines if the Asteroid is still existing or not.*/
	boolean existing;
	/** Size of existing Asteroid */
	int size;

	/** x points of asteroid (for polygon) */
	final double[] asteroidXPoints = {0,5,20,10,30,10,20,5,0,-5,-20,-10,-30,-10,-20,-5};
	/** y points of asteroid (for polygon) */
	final double[] asteroidYPoints = {30,10,20,5,0,-5,-20,-10,-30,-10,-20,-5,0,5,20,10};
	/** radius of asteroid */
	double radius;

	/** Constructor of Asteroid Model
	 * @param size of the Asteroid (0 - small; 1 - normal size; 2 - big)
	 * @param position x of the ship
	 * @param position y of the ship
	 * @param angle of the asteroid
	 * @param width of the screen
	 * @param height of the screen */
	public Asteroid (int size, double xShip, double yShip, double angle, int x, int y) {
		this.size = size;
		this.existing = true;
		this.turningSpeed = Math.random()/4;
		this.radius = 30;
		Point2D shipCenter;
		Point2D asteroidCenter;
		do {
			this.x = Math.random()*x;
			this.y = Math.random()*y;
			shipCenter = new Point2D.Double(xShip, yShip);
			asteroidCenter = new Point2D.Double(this.x, this.y);
		} while (shipCenter.distance(asteroidCenter) <= 80);
		this.speedX = (Math.random()+1)*randomMinusPlus();
		this.speedY = (Math.random()+1)*randomMinusPlus();
		if (size >= 2) {
			this.radius *= 2;
			this.speedX /=2;
			this.speedY /= 2;
			this.turningSpeed /= 2;
			for (int i = 0; i < asteroidXPoints.length; i++) {
				asteroidXPoints[i] *= 2;
				asteroidYPoints[i] *= 2;
			}
		}
		else if (size < 1) {
			this.radius /= 2;
			this.speedX *=2;
			this.speedY *= 2;
			this.turningSpeed *= 2;
			for (int i = 0; i < asteroidXPoints.length; i++) {
				asteroidXPoints[i] /= 2;
				asteroidYPoints[i] /= 2;
			}
		}
	}
	
	/** Constructor of Asteroid Model
	 * @param size of the Asteroid (0 - small; 1 - normal size; 2 - big)
	 * @param position x of the ship
	 * @param position y of the ship
	 * @param angle of the asteroid
	 * @param width of the screen
	 * @param height of the screen
	 * @param x position of the asteroid
	 * @param y position of the asteroid*/
	public Asteroid (int size, double xShip, double yShip, double angle, int x, int y, double xAsteroid, double yAsteroid) {
		this.size = size;
		this.existing = true;
		this.turningSpeed = Math.random()/4;
		this.radius = 30;
		this.x = xAsteroid;
		this.y = yAsteroid;
		this.angle = angle;
		this.speedX = (Math.random()+1)*randomMinusPlus();
		this.speedY = (Math.random()+1)*randomMinusPlus();
		if (size >= 2) {
			this.radius *= 2;
			this.speedX /=2;
			this.speedY /= 2;
			this.turningSpeed /= 2;
			for (int i = 0; i < asteroidXPoints.length; i++) {
				asteroidXPoints[i] *= 2;
				asteroidYPoints[i] *= 2;
			}
		}
		else if (size < 1) {
			this.radius /= 2;
			this.speedX *=2;
			this.speedY *= 2;
			this.turningSpeed *= 2;
			for (int i = 0; i < asteroidXPoints.length; i++) {
				asteroidXPoints[i] /= 2;
				asteroidYPoints[i] /= 2;
			}
		}
	}
	
	/** Constructor of Asteroid Model
	 * @param existing instance of asteroid
	 * @param size of the Asteroid (0 - small; 1 - normal size; 2 - big)d*/
	public Asteroid (Asteroid asteroid, int size) {
		this.size = size;
		this.existing = true;
		this.turningSpeed = Math.random()/4;
		this.radius = 30;
		this.x = asteroid.getX();
		this.y = asteroid.getY();
		this.angle = asteroid.getAngle();
		this.speedX = (Math.random()+1)*randomMinusPlus();
		this.speedY = (Math.random()+1)*randomMinusPlus();
		if (size >= 2) {
			this.radius *= 2;
			this.speedX /=2;
			this.speedY /= 2;
			this.turningSpeed /= 2;
			for (int i = 0; i < asteroidXPoints.length; i++) {
				asteroidXPoints[i] *= 2;
				asteroidYPoints[i] *= 2;
			}
		}
		else if (size < 1) {
			this.radius /= 2;
			this.speedX *=2;
			this.speedY *= 2;
			this.turningSpeed *= 2;
			for (int i = 0; i < asteroidXPoints.length; i++) {
				asteroidXPoints[i] /= 2;
				asteroidYPoints[i] /= 2;
			}
		}
	}

	
	/** @return returns 1 or -1*/
	private int randomMinusPlus() {
		double result = (Math.random()*1);
		if ((int) Math.round(result) == 1) {
			return 1;
		}
		return -1;
	}

	public boolean isExisting() {
		return existing;
	}

	public void setExisting(boolean existing) {
		this.existing = existing;
	}

	public double[] getAsteroidXPoints() {
		return asteroidXPoints;
	}

	public double[] getAsteroidYPoints() {
		return asteroidYPoints;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Point2D getCenter() {
		return new Point2D.Double(x, y);
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getTurningSpeed() {
		return turningSpeed;
	}

	public void setTurningSpeed(double turningSpeed) {
		this.turningSpeed = turningSpeed;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}
