package proz.asteroids.model;

import java.awt.geom.Point2D;

/** Model of Laser */
public class Laser implements BasicModel {
	/** X and Y - Coordinates of the laser */
	double x, y;
	/** Speed of the laser in X and Y coordinated */
	double speedX, speedY;
	/** Angle of the laser */
	double angle;
	/** Speed of the laser without X and Y coordinated. Also known as conversion rate */
	final double speed = 10;
	/** Radius of laser (for collision purposes) */
	final double radius = 10;
	/** This value tells how far will the laser shoot */
	int distance = 25;
	/** Defines if the laser is still existing or not. If lifeLeft is less or equal 0 then Laser stop existing */
	boolean existing;
	/** Shape of laser - X points */
	final double[] laserPointsX = {-2,-2,2,2};
	/** Shape of laser - Y points */
	final double[] laserPointsY = {-6,6,6,-6};
	/** How long does it take to reload laser - shooting speed */
	public static double shootingSpeed = 10;
	
	
	/** Constructor of Laser Model
	 * @param position x of the laser
	 * @param position y of the laser
	 * @param angle of the laser
	 * @param x speed of the ship
	 * @param y speed of the ship*/
	public Laser(double x, double y, double angle, double shipXSpeed, double shipYSpeed) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		speedX = -speed*Math.sin(angle) + shipXSpeed;
		speedY = speed*Math.cos(angle) + shipYSpeed;
		existing = true;
	}

	public double[] getLaserPointsX() {
		return laserPointsX;
	}

	public double[] getLaserPointsY() {
		return laserPointsY;
	}

	public double getAngle() {
		return angle;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isExisting() {
		return existing;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setExisting(boolean existing) {
		this.existing = existing;
	}

	public double getRadius() {
		return radius;
	}
	
	public Point2D getCenter() {
		return new Point2D.Double(x, y);
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
