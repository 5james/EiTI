/** Package model of game Asteroids */
package proz.asteroids.model;

import java.awt.geom.Point2D;

/** Model - Ship, basic model - triangle.*/
public class Ship implements BasicModel {
	/** X and Y - Coordinates of the ship */
	double x, y;
	/** Speed of the ship in X and Y coordinated */
	double speedX, speedY;
	/** value of acceleration and deceleration ~ how fast does the ship accelerate and decelerate */
	final double speedup = 0.5;
	final double speeddown = 0.15;
	/** Stopping force of the ship */
	double gravitation = 0.98;
	/** How fast does the ship turns */
	double turningSpeed = 0.15;
	/** Angle of the ship */
	double angle;
	/** How fast can the ship shoot*/
	final double shootingSpeed = 10;
	/** How long to shoot ( under zero )*/
	double shootingMissingTime;
	/** Variable tell whether ship is accelerating or not*/
	boolean acceleration;
	/** Variable tell whether ship is reversing or not*/
	boolean reversing;
	/** Variable tell whether ship is turning right or not*/
	boolean turningRight;
	/** Variable tell whether ship is turning left or not*/
	boolean turningLeft;
	/** Variable tell whether ship is shooting or not*/
	boolean shoot;
	/** Radius of the Ship*/
	double radius = 12;
	/** Shape of ship - X points */
	final double shipPointsX[] =  {-10,10,0};
	/** Shape of ship - Ypoints */
	final double shipPointsY[] = {-15,-15,15};
	/** Shape of flames - X points */
	final double flamesPointsX[] = {-5,5,0};
	/** Shape of flames - Y points */
	final double flamesPointsY[] = {-15,-15,-25};
	
	/** Constructor of the Model Ship
	 * 
	 * @param x position of the ship
	 * @param y position of the ship
	 * @param angle of the ship
	 */
	public Ship (double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		acceleration = false;
		reversing = false;
		turningLeft = false;
		turningRight = false;
		shoot = false;
		shootingMissingTime = shootingSpeed;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
		
	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setAcceleration(boolean acceleration) {
		this.acceleration = acceleration;
	}

	public void setReversing(boolean reversing) {
		this.reversing = reversing;
	}

	public void setTurningRight(boolean turningRight) {
		this.turningRight = turningRight;
	}

	public void setTurningLeft(boolean turningLeft) {
		this.turningLeft = turningLeft;
	}
	
	public boolean isTurningRight() {
		return turningRight;
	}

	public boolean isTurningLeft() {
		return turningLeft;
	}

	public Point2D getCenter() {
		return new Point2D.Double(x, y);
	}

	public boolean isAcceleration() {
		return acceleration;
	}

	public boolean isReversing() {
		return reversing;
	}

	public double[] getShipPointsX() {
		return shipPointsX;
	}

	public double[] getShipPointsY() {
		return shipPointsY;
	}

	public double[] getFlamesPointsX() {
		return flamesPointsX;
	}

	public double[] getFlamesPointsY() {
		return flamesPointsY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
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

	public double getSpeedup() {
		return speedup;
	}
	public double getSpeeddown() {
		return speeddown;
	}

	public double getTurningSpeed() {
		return turningSpeed;
	}

	public void setTurningSpeed(double turningSpeed) {
		this.turningSpeed = turningSpeed;
	}

	public double getGravitation() {
		return gravitation;
	}

	public void setGravitation(double gravitation) {
		this.gravitation = gravitation;
	}

	public double getShootingMissingTime() {
		return shootingMissingTime;
	}

	public void setShootingMissingTime(double shootingMissingTime) {
		this.shootingMissingTime = shootingMissingTime;
	}

	public double getShootingSpeed() {
		return shootingSpeed;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	
	
}
