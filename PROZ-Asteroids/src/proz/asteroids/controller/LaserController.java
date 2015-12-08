package proz.asteroids.controller;

import proz.asteroids.model.Laser;

/** Controller of Laser */
public class LaserController {
	/** Model */
	Laser laser;
	/** Constructor of Controller (assigning model)
	 * @param Model of Laser */
	public LaserController (Laser laser) {
		this.laser = laser;
	}

	/** Moving (one cycle)
	 * @param width of the frame/screen
	 * @param height of the frame/screen */
	public void move (int x, int y) {
		/** Proper moving if the asteroid exists */
		if (laser.isExisting()) {
			laser.setX(laser.getX() + laser.getSpeedX());
			laser.setY(laser.getY() + laser.getSpeedY());
		}
		/** Check Borders */
		//if gets out of boundary x
		if ( laser.getX() < 0)
			laser.setX(laser.getX() + x);
		else if (laser.getX() > x)
			laser.setX(laser.getX() - x);
		//if gets out of boundary y
		if ( laser.getY() < 0)
			laser.setY(laser.getY() + y);
		else if (laser.getY() > y)
			laser.setY(laser.getY() - y);
		
		/** Update distance of laser to make + setting Existing as false if needed */
		laser.setDistance(laser.getDistance()-1);
		if (laser.getDistance() <= 0)
			laser.setExisting(false);
	}
	
	
}
