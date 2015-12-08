package proz.asteroids.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import proz.asteroids.model.Ship;
import proz.asteroids.view.ShipView;

public class ShipControllerTest {
	Ship ship ;
	ShipController shipController;
	ShipView shipView;

	@Before
	public void setUp() {
		ship = new Ship(100, 100, 0);
		shipController = new ShipController(ship);
		shipView = new ShipView();
		shipView.setController(shipController);
		shipView.setModel(ship);
	}

	@Test
	public void afterMovingUpShipPositionUpdated() {
		double yBeforeMoving =  ship.getY();
		ship.setSpeedY(1);
		shipController.move(640, 480);
		double yAfterMoving =  ship.getY();
		assertEquals(yBeforeMoving, yAfterMoving-1, 0.0001);
	}	

	@Test
	public void afterMovingDownShipPositionUpdated() {
		double yBeforeMoving =  ship.getY();
		ship.setSpeedY(-1);
		shipController.move(640, 480);
		double yAfterMoving =  ship.getY();
		assertEquals(yBeforeMoving, yAfterMoving+1, 0.0001);
	}

	@Test
	public void afterMovingLeftShipPositionUpdated() {
		double xBeforeMoving =  ship.getX();
		ship.setSpeedX(1);
		shipController.move(640, 480);
		double xAfterMoving =  ship.getX();
		assertEquals(xBeforeMoving, xAfterMoving-1, 0.0001);
	}

	@Test
	public void afterMovingViewIsRefreshed() {
		double xBeforeMoving =  ship.getX();
		ship.setSpeedX(1);
		shipController.move(640, 480);
		double xAfterMoving =  ship.getX();
		assertEquals(xBeforeMoving, xAfterMoving-1, 0.0001);
	}

	@Test
	public void afterMovingOnRightBorderMovesToLeftBorder() {
		int width = 640;
		int height = 480;
		ship.setX(width);
		ship.setSpeedX(1);
		shipController.move(width, height);
		double xAfterMoving =  ship.getX();
		assertEquals(xAfterMoving, 1, 0.0001);
	}	
	@Test
	public void afterMovingOnLeftBorderMovesToRightBorder() {
		int width = 640;
		int height = 480;
		ship.setX(0);
		ship.setSpeedX(-1);
		shipController.move(width, height);
		double xAfterMoving =  ship.getX();
		assertEquals(xAfterMoving, width-1, 0.0001);
	}	
	@Test
	public void afterMovingOnTopBorderMovesToBottomBorder() {
		int width = 640;
		int height = 480;
		ship.setY(height);
		ship.setSpeedY(1);
		shipController.move(width, height);
		double yAfterMoving =  ship.getY();
		assertEquals(yAfterMoving, 1, 0.0001);
	}	
	@Test
	public void afterMovingOnBottomBorderMovesToTopBorder() {
		int width = 640;
		int height = 480;
		ship.setY(0);
		ship.setSpeedY(-1);
		shipController.move(width, height);
		double yAfterMoving =  ship.getY();
		assertEquals(yAfterMoving, height-1, 0.0001);
	}	
	
	@Test
	public void turningShipRight() {
		shipController.setTurningRight(true);
		boolean result = ship.isTurningRight();
		assertEquals(result, true);
	}	
	@Test
	public void turningShipLeft() {
		shipController.setTurningLeft(true);
		boolean result = ship.isTurningLeft();
		assertEquals(result, true);
	}	
	@Test
	public void notTurningShipRight() {
		shipController.setTurningRight(false);
		boolean result = ship.isTurningRight();
		assertEquals(result, false
				);
	}	
	@Test
	public void notTurningShipLeft() {
		shipController.setTurningLeft(false);
		boolean result = ship.isTurningLeft();
		assertEquals(result, false);
	}
	@Test
	public void isActualTurningRight() {
		int width = 640;
		int height = 480;
		ship.setAngle(100);
		shipController.setTurningRight(true);
		shipController.move(width, height);
		double angleAfter = ship.getAngle();
		boolean result = (angleAfter > 100) ? true : false;
		assertEquals(result, true);
	}
	@Test
	public void isActualTurningLeft() {
		int width = 640;
		int height = 480;
		ship.setAngle(100);
		shipController.setTurningLeft(true);
		shipController.move(width, height);
		double angleAfter = ship.getAngle();
		boolean result = (angleAfter < 100) ? true : false;
		assertEquals(result, true);
	}
	
	@Test
	public void setShipShooting() {
		shipController.setShooting(true);
		boolean result = ship.isShoot();
		assertEquals(result, true);
	}
	@Test
	public void setShipAccelerating() {
		shipController.setAcceleration(true);
		boolean result = ship.isAcceleration();
		assertEquals(result, true);
	}
	@Test
	public void setShipReversing() {
		shipController.setReversing(true);
		boolean result = ship.isReversing();
		assertEquals(result, true);
	}
	
	@Test
	public void shootingMissingTimeMinusOne() {
		int width = 640;
		int height = 480;
		ship.setY(0);
		ship.setSpeedY(-1);
		double before =  ship.getShootingMissingTime();
		shipController.move(width, height);
		double after =  ship.getShootingMissingTime();
		assertEquals(before, after+1, 0.0001);
	}
}
