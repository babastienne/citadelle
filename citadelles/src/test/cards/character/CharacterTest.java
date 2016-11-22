package test.cards.character;

import org.junit.Test;

import cards.character.Architect;
import cards.character.Assassin;
import cards.character.Character;
import cards.character.King;
import junit.framework.TestCase;

public class CharacterTest extends TestCase {

	@Test
	public void testGetDescription() throws Exception {
		Character test = new Assassin();
		assertEquals("Assassine un autre personnage", test.getDescription());
	}

	@Test
	public void testSetDescription() {
		Character test = new Assassin();
		test.setDescription("Vole un autre personnage");
		assertEquals("Vole un autre personnage", test.getDescription());
	}

	@Test
	public void testGetRank() {
		Character test = new Architect();
		assertEquals(7, test.getRank());
	}
	
	@Test
	public void testCompareTo() {
		Character test = new Architect();
		Character test2= new Architect();
		assertEquals(0, test.compareTo(test2));
		test = new King();
		assertEquals(-3, test.compareTo(test2));
	}

}
