package proz.asteroids.controller;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import proz.asteroids.model.Asteroid;
import proz.asteroids.model.BasicModel;
import proz.asteroids.view.AsteroidView;

public class AsteroidControllerTest {
	Asteroid asteroid;
	AsteroidController asteroidController;
	AsteroidView AsteroidView;
	
	private class TestModel implements BasicModel {

		@Override
		public double getX() {
			return 100;
		}

		@Override
		public double getY() {
			return 100;
		}

		@Override
		public double getRadius() {
			return 10;
		}

		@Override
		public Point2D getCenter() {
			return new Point2D.Double(getX(), getY());
		}
		
	}

	@Before
	public void setUp() {
		asteroid = new Asteroid(1, 0, 0, 0, 640, 480);
		asteroidController = new AsteroidController(asteroid);
	}

	@Test
	public void afterMovingUpAsteroidPositionUpdated() {
		double yBeforeMoving =  asteroid.getY();
		asteroid.setSpeedY(1);
		asteroidController.move(640, 480);
		double yAfterMoving =  asteroid.getY();
		assertEquals(yBeforeMoving, yAfterMoving-1, 0.0001);
	}	
	
	@Test
	public void afterMovingDownAsteroidPositionUpdated() {
		double yBeforeMoving =  asteroid.getY();
		asteroid.setSpeedY(-1);
		asteroidController.move(640, 480);
		double yAfterMoving =  asteroid.getY();
		assertEquals(yBeforeMoving, yAfterMoving+1, 0.0001);
	}
	
	@Test
	public void afterMovingLeftAsteroidPositionUpdated() {
		double xBeforeMoving =  asteroid.getX();
		asteroid.setSpeedX(1);
		asteroidController.move(640, 480);
		double xAfterMoving =  asteroid.getX();
		assertEquals(xBeforeMoving, xAfterMoving-1, 0.0001);
	}
	
	@Test
	public void afterMovingViewIsRefreshed() {
		double xBeforeMoving =  asteroid.getX();
		asteroid.setSpeedX(1);
		asteroidController.move(640, 480);
		double xAfterMoving =  asteroid.getX();
		assertEquals(xBeforeMoving, xAfterMoving-1, 0.0001);
	}
	
	@Test
	public void afterMovingOnRightBorderMovesToLeftBorder() {
		int width = 640;
		int height = 480;
		asteroid.setX(width);
		asteroid.setSpeedX(1);
		asteroidController.move(width, height);
		double xAfterMoving =  asteroid.getX();
		assertEquals(xAfterMoving, 1, 0.0001);
	}	
	@Test
	public void afterMovingOnLeftBorderMovesToRightBorder() {
		int width = 640;
		int height = 480;
		asteroid.setX(0);
		asteroid.setSpeedX(-1);
		asteroidController.move(width, height);
		double xAfterMoving =  asteroid.getX();
		assertEquals(xAfterMoving, width-1, 0.0001);
	}	
	@Test
	public void afterMovingOnTopBorderMovesToBottomBorder() {
		int width = 640;
		int height = 480;
		asteroid.setY(height);
		asteroid.setSpeedY(1);
		asteroidController.move(width, height);
		double yAfterMoving =  asteroid.getY();
		assertEquals(yAfterMoving, 1, 0.0001);
	}	
	@Test
	public void afterMovingOnBottomBorderMovesToTopBorder() {
		int width = 640;
		int height = 480;
		asteroid.setY(0);
		asteroid.setSpeedY(-1);
		asteroidController.move(width, height);
		double yAfterMoving =  asteroid.getY();
		assertEquals(yAfterMoving, height-1, 0.0001);
	}	
	@Test
	public void isActualTurningRight() {
		int width = 640;
		int height = 480;
		asteroid.setAngle(100);
		asteroid.setTurningSpeed(1);
		asteroidController.move(width, height);
		double angleAfter = asteroid.getAngle();
		double changingAngle = asteroid.getTurningSpeed();
		assertEquals(100d, angleAfter-changingAngle, 0.0001);
	}
	@Test
	public void isActualTurningLeft() {
		int width = 640;
		int height = 480;
		asteroid.setAngle(100);
		asteroid.setTurningSpeed(-1);
		asteroidController.move(width, height);
		double angleAfter = asteroid.getAngle();
		boolean result = (angleAfter < 100) ? true : false;
		assertEquals(result, true);
	}

	
	@Test
	public void collisionTestTrueRight() {
		asteroid.setX(120);
		asteroid.setY(100);
		asteroid.setRadius(11);
		TestModel testModel = new TestModel();
		boolean result = asteroidController.collision(testModel);
		assertEquals(result, true);
	}
	@Test
	public void collisionTestTrueLeft() {
		asteroid.setX(80);
		asteroid.setY(100);
		asteroid.setRadius(11);
		TestModel testModel = new TestModel();
		boolean result = asteroidController.collision(testModel);
		assertEquals(result, true);
	}
	@Test
	public void collisionTestTrueUp() {
		asteroid.setX(100);
		asteroid.setY(120);
		asteroid.setRadius(11);
		TestModel testModel = new TestModel();
		boolean result = asteroidController.collision(testModel);
		assertEquals(result, true);
	}
	@Test
	public void collisionTestTrueDown() {
		asteroid.setX(100);
		asteroid.setY(80);
		asteroid.setRadius(11);
		TestModel testModel = new TestModel();
		boolean result = asteroidController.collision(testModel);
		assertEquals(result, true);
	}
	@Test
	public void collisionTestFalseRight() {
		asteroid.setX(120);
		asteroid.setY(100);
		asteroid.setRadius(9.99);
		TestModel testModel = new TestModel();
		boolean result = asteroidController.collision(testModel);
		assertEquals(result, false);
	}
	@Test
	public void collisionTestFalseDown() {
		asteroid.setX(100);
		asteroid.setY(80);
		asteroid.setRadius(9.99);
		TestModel testModel = new TestModel();
		boolean result = asteroidController.collision(testModel);
		assertEquals(result, false);
	}
	
	@Test
	public void notMovingXIfNotExisting() {
		int width = 640;
		int height = 480;
		asteroid.setSpeedX(10);
		asteroid.setExisting(false);
		double xBefore =  asteroid.getX();
		asteroidController.move(width, height);
		double xAfter =  asteroid.getX();
		assertEquals(xBefore, xAfter, 0.0001);
	}
	@Test
	public void notMovingYIfNotExisting() {
		int width = 640;
		int height = 480;
		asteroid.setSpeedY(10);
		asteroid.setExisting(false);
		double yBefore =  asteroid.getY();
		asteroidController.move(width, height);
		double yAfter =  asteroid.getY();
		assertEquals(yBefore, yAfter, 0.0001);
	}
	
}
