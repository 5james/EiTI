package proz.asteroids.view;

import java.awt.Color;
import java.awt.Graphics;

import proz.asteroids.model.Asteroid;

/** View of the Asteroid */
public class AsteroidView {
	/** Model */
	Asteroid asteroid;
	
	/** Constructor
	 * 
	 * @param existing reference to asteroid
	 */
	public AsteroidView(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
	/** drawing on graphic g
	 * 
	 * @param g - Graphic
	 */
	public void updateView (Graphics g) {
		final double x[] = asteroid.getAsteroidXPoints();
		final double y[] = asteroid.getAsteroidYPoints();
		final int points = asteroid.getAsteroidXPoints().length;
		int a[] = new int[points];
		int b[] = new int[points];
		double angle = asteroid.getAngle();
		for(int i=0 ; i<points ; i++) {
			a[i] = helpFormulaX(x[i], y[i], angle, asteroid.getX());
			b[i] = helpFormulaY(x[i], y[i], angle, asteroid.getY());
		}
		g.setColor(Color.ORANGE);
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
