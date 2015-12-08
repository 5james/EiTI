package proz.asteroids.view;

import java.awt.Color;
import java.awt.Graphics;

import proz.asteroids.controller.LaserController;
import proz.asteroids.model.Laser;
/** View of Laser */
public class LaserView {
	/** Model of laser */
	Laser laser;
	/** Controller of laser */
	LaserController laserController;
	
	/** Assigning model
	 * 
	 * @param laser - reference
	 */
	public void setModel (Laser laser) {
		this.laser = laser;
	}
	/** Assigning controller
	 * 
	 * @param laserController - reference
	 */
	public void setController (LaserController laserController) {
		this.laserController = laserController;
	}
	/** drawing on graphic g
	 * 
	 * @param g - Graphic
	 */
	public void updateView (Graphics g) {
		final double x[] = laser.getLaserPointsX();
		final double y[] = laser.getLaserPointsY();
		final int points = laser.getLaserPointsX().length;
		int a[] = new int[points];
		int b[] = new int[points];
		double angle = laser.getAngle();
		for(int i=0 ; i<points ; i++) {
			a[i] = helpFormulaX(x[i], y[i], angle, laser.getX());
			b[i] = helpFormulaY(x[i], y[i], angle, laser.getY());
		}
		g.setColor(Color.BLUE);
		g.fillPolygon(a, b, points);
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
}
