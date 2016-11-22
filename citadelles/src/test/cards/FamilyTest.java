package test.cards;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import cards.Family;

public class FamilyTest {

	@Test
	public void testGetName() {
		Family religion = new Family("Religion", Color.blue);
		assertEquals("Religion", religion.getName());
	}

	@Test
	public void testGetColor() {
		Family religion = new Family("Religion", Color.blue);
		assertEquals(Color.blue, religion.getColor());
	}

	@Test
	public void testEqualsObject() {
		Family religion = new Family("Religion", Color.blue);
		Family religion2 = new Family("Religion", Color.red);
		assertEquals(false, religion.equals(religion2));
	}

}
