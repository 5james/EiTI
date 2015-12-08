package proz.asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	
	Ship ship;
	
	@Before
	public void setUp() {
		ship = new Ship(320, 240, 90);
	}
	
	@Test
	public void hasProperPositionX() {
		assertEquals(ship.x, 320, 0.0001);
	}	
	@Test
	public void hasProperPositionY() {
		assertEquals(ship.y, 240, 0.0001);
	}
	@Test
	public void hasProperAngle() {
		assertEquals(ship.angle, 90, 0.0001);
	}
	@Test
	public void hasProperSpeedUp() {
		assertEquals(ship.speedup, 0.5, 0.0001);
	}
	@Test
	public void hasProperRadius() {
		double x =  ship.radius;
		assertEquals(x, 12, 0.0001);
	}
	@Test
	public void hasProperShootingSpeed() {
		assertEquals(ship.shootingSpeed, 10, 0.0001);
	}
	@Test
	public void hasProperShootingMissingTime() {
		assertEquals(ship.shootingMissingTime, 10, 0.0001);
	}
	@Test
	public void notTurningRight() {
		assertEquals(ship.turningRight, false);
	}
	@Test
	public void notTurningLeft() {
		assertEquals(ship.turningLeft, false);
	}
	@Test
	public void notAccelerating() {
		assertEquals(ship.acceleration, false);
	}
	@Test
	public void notReversing() {
		assertEquals(ship.reversing, false);
	}
	@Test
	public void notShooting() {
		assertEquals(ship.shoot, false);
	}
}
