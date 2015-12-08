package proz.asteroids.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import proz.asteroids.controller.ShipController;
import proz.asteroids.model.Ship;

/** View of the Ship */
public class ShipView implements KeyListener {
	/** Model */
	Ship ship;
	/** Controller */
	ShipController shipcontroller;

	/** Assing model
	 * 
	 * @param ship - reference
	 */
	public void setModel (Ship ship) {
		this.ship = ship;
	}
	/** Assing controller
	 * 
	 * @param shipcontroller - reference
	 */
	public void setController (ShipController shipcontroller) {
		this.shipcontroller = shipcontroller;
	}

	/** drawing on graphic g
	 * 
	 * @param g - Graphic
	 */
	public void updateView (Graphics g) {
		if(ship.isReversing() && !ship.isAcceleration()) {
			helpDrawingPolygon(ship.getFlamesPointsX(), ship.getFlamesPointsY(), ship.getFlamesPointsX().length, g, Color.WHITE);
		}

		if(ship.isAcceleration() && !ship.isReversing()) {
			helpDrawingPolygon(ship.getFlamesPointsX(), ship.getFlamesPointsY(), ship.getFlamesPointsX().length, g, Color.RED);
		}

		helpDrawingPolygon(ship.getShipPointsX(), ship.getShipPointsY(), ship.getShipPointsX().length, g, Color.WHITE);

		Double xx = new Double(ship.getX());
		Double yy = new Double(ship.getY());
		g.drawString("Ship position:", 550, 20);
		g.drawString(xx.toString(), 550, 30);
		g.drawString(yy.toString(), 550, 40);
	}
	/** x = a*cos(angle) - b*sin(angle)
	 * 
	 * @param a
	 * @param b
	 * @param angle
	 * @param x
	 * @return
	 */
	private int helpFormulaX(double a, double b, double angle, double x) {
		return (int)Math.ceil(a * Math.cos(angle) - b * Math.sin(angle) + x);
	}
	/** x = a*cos(angle) + b*sin(angle)
	 * 
	 * @param a
	 * @param b
	 * @param angle
	 * @param x
	 * @return
	 */
	private int helpFormulaY(double a, double b, double angle, double y) {
		return (int)Math.ceil(a*Math.sin(angle) + b*Math.cos(angle) + y);
	}
	/** fuction calculates points of the moved (in angle) object
	 * 
	 * @param x - points of proper object
	 * @param y - points of proper object
	 * @param points - number of points of proper object
	 * @param g  - Graphic
	 * @param color - Color of drawing
	 */
	private void helpDrawingPolygon (double[] x, double[] y, int points, Graphics g, Color color) {
		int a[] = new int[points];
		int b[] = new int[points];
		for(int i=0 ; i<points ; i++) {
			double angle = ship.getAngle();
			a[i] = helpFormulaX(x[i], y[i], angle, ship.getX());
			b[i] = helpFormulaY(x[i], y[i], angle, ship.getY());
		}
		g.setColor(color);
		g.fillPolygon(a, b, points);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_S) {
			if(ship.getShootingMissingTime() <= 0)
				shipcontroller.setShooting(true);
		}
		if(key == KeyEvent.VK_UP) 
			shipcontroller.setAcceleration(true);
		if(key == KeyEvent.VK_LEFT)
			shipcontroller.setTurningLeft(true);
		if(key == KeyEvent.VK_DOWN)
			shipcontroller.setReversing(true);
		if(key == KeyEvent.VK_RIGHT)
			shipcontroller.setTurningRight(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) 
			shipcontroller.setAcceleration(false);
		if(key == KeyEvent.VK_LEFT)
			shipcontroller.setTurningLeft(false);
		if(key == KeyEvent.VK_DOWN)
			shipcontroller.setReversing(false);
		if(key == KeyEvent.VK_RIGHT)
			shipcontroller.setTurningRight(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
