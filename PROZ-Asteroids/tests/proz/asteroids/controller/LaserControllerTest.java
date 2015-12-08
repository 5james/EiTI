package proz.asteroids.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proz.asteroids.model.Laser;
import proz.asteroids.view.LaserView;

public class LaserControllerTest {
	Laser laser;
	LaserController laserController;
	LaserView laserView;

	@Before
	public void setUp() {
		laser = new Laser(100, 100, 0, 5, 5);
		laserController = new LaserController(laser);
		laserView = new LaserView();
		laserView.setController(laserController);
		laserView.setModel(laser);
	}

	@Test
	public void afterMovingUpLaserPositionUpdated() {
		double yBeforeMoving =  laser.getY();
		laser.setSpeedY(1);
		laserController.move(640, 480);
		double yAfterMoving =  laser.getY();
		assertEquals(yBeforeMoving, yAfterMoving-1, 0.0001);
	}	
	
	@Test
	public void afterMovingDownLaserPositionUpdated() {
		double yBeforeMoving =  laser.getY();
		laser.setSpeedY(-1);
		laserController.move(640, 480);
		double yAfterMoving =  laser.getY();
		assertEquals(yBeforeMoving, yAfterMoving+1, 0.0001);
	}
	
	@Test
	public void afterMovingLeftLaserPositionUpdated() {
		double xBeforeMoving =  laser.getX();
		laser.setSpeedX(1);
		laserController.move(640, 480);
		double xAfterMoving =  laser.getX();
		assertEquals(xBeforeMoving, xAfterMoving-1, 0.0001);
	}
	
	@Test
	public void afterMovingViewIsRefreshed() {
		double xBeforeMoving =  laser.getX();
		laser.setSpeedX(1);
		laserController.move(640, 480);
		double xAfterMoving =  laser.getX();
		assertEquals(xBeforeMoving, xAfterMoving-1, 0.0001);
	}
	
	@Test
	public void afterMovingOnRightBorderMovesToLeftBorder() {
		int width = 640;
		int height = 480;
		laser.setX(width);
		laser.setSpeedX(1);
		laserController.move(width, height);
		double xAfterMoving =  laser.getX();
		assertEquals(xAfterMoving, 1, 0.0001);
	}	
	@Test
	public void afterMovingOnLeftBorderMovesToRightBorder() {
		int width = 640;
		int height = 480;
		laser.setX(0);
		laser.setSpeedX(-1);
		laserController.move(width, height);
		double xAfterMoving =  laser.getX();
		assertEquals(xAfterMoving, width-1, 0.0001);
	}	
	@Test
	public void afterMovingOnTopBorderMovesToBottomBorder() {
		int width = 640;
		int height = 480;
		laser.setY(height);
		laser.setSpeedY(1);
		laserController.move(width, height);
		double yAfterMoving =  laser.getY();
		assertEquals(yAfterMoving, 1, 0.0001);
	}	
	@Test
	public void afterMovingOnBottomBorderMovesToTopBorder() {
		int width = 640;
		int height = 480;
		laser.setY(0);
		laser.setSpeedY(-1);
		laserController.move(width, height);
		double yAfterMoving =  laser.getY();
		assertEquals(yAfterMoving, height-1, 0.0001);
	}

	@Test
	public void notMovingYIfNotExisting() {
		int width = 640;
		int height = 480;
		laser.setSpeedY(10);
		laser.setExisting(false);
		double yBefore =  laser.getY();
		laserController.move(width, height);
		double yAfter =  laser.getY();
		assertEquals(yBefore, yAfter, 0.0001);
	}
	@Test
	public void notMovingXIfNotExisting() {
		int width = 640;
		int height = 480;
		laser.setSpeedX(10);
		laser.setExisting(false);
		double xBefore =  laser.getX();
		laserController.move(width, height);
		double xAfter =  laser.getX();
		assertEquals(xBefore, xAfter, 0.0001);
	}
	
	@Test
	public void afterMovingMinusDistance() {
		int width = 640;
		int height = 480;
		int before = laser.getDistance();
		laserController.move(width, height);
		int after = laser.getDistance();
		assertEquals(before, after+1);
	}

}
