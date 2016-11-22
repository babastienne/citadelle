package test.cards.districts;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import cards.Family;
import cards.districts.District;

public class DistrictTest {
	
	private static final Family religion = new Family("Religion", Color.blue);
	private static final Family noblesse = new Family("Noblesse", Color.yellow);

	@Test
	public void testGetPrice() {
		District district = new District("Temple", religion, 1);
		assertEquals(1, district.getPrice());
	}

	@Test
	public void testGetValue() {
		District district = new District("Temple", religion, 1);
		assertEquals(1, district.getValue());
	}

	@Test
	public void testGetName() {
		District district = new District("Temple", religion, 1);
		assertEquals("Temple", district.getName());
	}

	@Test
	public void testGetFamily() {
		District district = new District("Temple", religion, 1);
		assertEquals(religion, district.getFamily());
	}

	@Test
	public void testToString() {
		District district = new District("Temple", religion, 1);
		assertEquals("Temple[1, Religion]", district.toString());
	}

	@Test
	public void testEqualsObject() {
		District district = new District("Temple", religion, 1);
		District district2 = new District("Temple", religion, 1);
		assertEquals(true, district.equals(district2));
		district2 = new District("Temple", noblesse, 1);
		assertEquals(false, district.equals(district2));
	}

}
