package proz.asteroids.controller;

import proz.asteroids.model.Asteroid;
import proz.asteroids.model.BasicModel;

/** Controller of Asteroid */
public class AsteroidController {
	/** Model */
	Asteroid asteroid;

	/** Constructor
	 * @param Model of the Asteroid */
	public AsteroidController(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
	/** Moving (one cycle)
	 * @param width of the frame/screen
	 * @param height of the frame/screen */
	public void move (int x, int y) {
		/** Proper moving if the asteroid exists */
		if (asteroid.isExisting()) {
			asteroid.setAngle(asteroid.getAngle() + asteroid.getTurningSpeed());
			asteroid.setX(asteroid.getX() + asteroid.getSpeedX());
			asteroid.setY(asteroid.getY() + asteroid.getSpeedY());
		}

		/** Check Borders */
		if (asteroid.getX() < 0)
			asteroid.setX(asteroid.getX() + x);
		else if (asteroid.getX() > x) 
			asteroid.setX(asteroid.getX() - x);
		if (asteroid.getY() < 0)
			asteroid.setY(asteroid.getY() + y);
		else if (asteroid.getY() > y) 
			asteroid.setY(asteroid.getY() - y);

	}
	/** Check collision of asteroid and other basic object
	 * @param Model, which implements BasicModel */
	public boolean collision(BasicModel a) {
		if (a.getCenter().distance(asteroid.getCenter()) <= asteroid.getRadius()+a.getRadius())
			return true;
		return false;
	}

}
