package proz.asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AsteroidTest {
	Asteroid asteroid;

	@Before
	public void setUp() {
		asteroid = new Asteroid(1, 123, 321, 515, 640, 480, 456, 678);
	}

	@Test
	public void hasProperPositionX() {
		double x =  asteroid.x;
		assertEquals(x, 456, 0.0001);
	}	
	@Test
	public void hasProperPositionY() {
		double y =  asteroid.y;
		assertEquals(y, 678, 0.0001);
	}
	@Test
	public void hasProperAngle() {
		double x =  asteroid.angle;
		assertEquals(x, 515, 0.0001);
	}
	@Test
	public void hasProperSize() {
		double x =  asteroid.size;
		assertEquals(x, 1, 0.0001);
	}
	@Test
	public void hasProperRadius() {
		double x =  asteroid.radius;
		assertEquals(x, 30, 0.0001);
	}
	@Test
	public void hasProperBiggerRadius() {
		asteroid = new Asteroid(asteroid, 2);
		double x =  asteroid.radius;
		assertEquals(x, 60, 0.0001);
	}
	@Test
	public void hasProperSmallerRadius() {
		asteroid = new Asteroid(asteroid, 0);
		double x =  asteroid.radius;
		assertEquals(x, 15, 0.0001);
	}
	@Test
	public void existingAfterCreation() {
		assertEquals(asteroid.existing, true);
	}
}
