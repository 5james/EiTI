package proz.asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LaserTest {
	Laser laser;

	@Before
	public void setUp() {
		laser = new Laser(123, 345, 151, 196, 122);
	}

	@Test
	public void hasProperPositionX() {
		assertEquals(laser.x, 123, 0.0001);
	}	
	@Test
	public void hasProperPositionY() {
		assertEquals(laser.y, 345, 0.0001);
	}
	@Test
	public void hasProperAngle() {
		assertEquals(laser.angle, 151, 0.0001);
	}
	@Test
	public void hasProperSpeedX() {
		laser = new Laser(100, 100, 0, 0, 0);
		assertEquals(laser.speedX, 0, 0.0001);
	}
	@Test
	public void hasProperSpeedY() {
		laser = new Laser(100, 100, Math.sinh(0), 0, 0);
		assertEquals(laser.speedY, 10, 0.0001);
	}
	@Test
	public void hasProperRadius() {
		assertEquals(laser.radius, 10, 0.0001);
	}
	@Test
	public void hasProperSpeed() {
		assertEquals(laser.speed, 10, 0.0001);
	}
	@Test
	public void hasProperShootingSpeed() {
		assertEquals(Laser.shootingSpeed, 10, 0.0001);
	}
	@Test
	public void hasProperDistance() {
		double x =  laser.distance;
		assertEquals(x, 25, 0.0001);
	}
	@Test
	public void existingAfterCreation() {
		assertEquals(laser.existing, true);
	}


}
