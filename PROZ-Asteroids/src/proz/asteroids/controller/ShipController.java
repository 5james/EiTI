package proz.asteroids.controller;

import proz.asteroids.model.Laser;
import proz.asteroids.model.Ship;

/** Controller of the Ship */
public class ShipController {
	/** Model */
	Ship ship;
	/** Constructor of Controller (assigning model)
	 * @param Model of Ship */
	public ShipController(Ship ship) {
		this.ship=ship;
	}
	/** set shooting of the ship
	 * @param shoot or not (true or false) */
	public void setShooting (boolean a) {
		ship.setShoot(a);
	}

	/** reset shooting of the ship */
	public void resetShooting() {
		ship.setShoot(false);
		ship.setShootingMissingTime(ship.getShootingSpeed());
	}
	
	public void setTurningRight (boolean a) {
		ship.setTurningRight(a);
	}
	public void setTurningLeft (boolean a) {
		ship.setTurningLeft(a);
	}
	public void setAcceleration (boolean a) {
		ship.setAcceleration(a);
	}
	public void setReversing (boolean a) {
		ship.setReversing(a);
	}

	/** shooting of the ship
	 * @return new Laser ( x and y assigned from ship ) */
	public Laser shooting() {
		resetShooting();
		return new Laser(ship.getX(), ship.getY(), ship.getAngle(), ship.getSpeedX(), ship.getSpeedY());
	}

	/** Moving (one cycle)
	 * @param width of the frame/screen
	 * @param height of the frame/screen */
	public void move(int x, int y) {
		/** Proper moving  */
		if(ship.isTurningRight())
			ship.setAngle(ship.getAngle()+ship.getTurningSpeed());
		if(ship.isTurningLeft())
			ship.setAngle(ship.getAngle()-ship.getTurningSpeed());
		
		if(ship.isAcceleration()) {
			ship.setSpeedX(ship.getSpeedX()-ship.getSpeedup()*Math.sin(ship.getAngle()));
			ship.setSpeedY(ship.getSpeedY()+ship.getSpeedup()*Math.cos(ship.getAngle()));
		}
		
		if(ship.isReversing()) {
			ship.setSpeedX(ship.getSpeedX()+ship.getSpeedup()*Math.sin(ship.getAngle()));
			ship.setSpeedY(ship.getSpeedY()-ship.getSpeedup()*Math.cos(ship.getAngle()));
		}
		
		/** update x and y position */
		ship.setX(ship.getX() + ship.getSpeedX());
		ship.setY(ship.getY() + ship.getSpeedY());
		ship.setSpeedX(ship.getSpeedX()*ship.getGravitation());
		ship.setSpeedY(ship.getSpeedY()*ship.getGravitation());
		
		/** Check borders */
		if(ship.getX()<0)
			ship.setX((int) (ship.getX() + x));
		else if(ship.getX()>x)
			ship.setX((int) (ship.getX() - x));
		
		if(ship.getY()<0) {
			ship.setY((int) (ship.getY() + y));
		}
		else if(ship.getY()>y)
			ship.setY((int) (ship.getY() - y));
		
		/** update shooting reloading time */
		ship.setShootingMissingTime(ship.getShootingMissingTime()-1);
	}
}
