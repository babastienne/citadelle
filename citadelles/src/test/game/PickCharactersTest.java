package test.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import game.PickCharacters;

public class PickCharactersTest {
	
	@Test
	public void testIteratorDeck() {
		PickCharacters pickChar = new PickCharacters();
		pickChar.initializeDeck();
		assertEquals(8, pickChar.iteratorDeck().size());
	}
	
	@Test
	public void testRemove() {
		PickCharacters pickChar = new PickCharacters();
		pickChar.initializeDeck();
		ArrayList<cards.character.Character> charList = pickChar.iteratorDeck();
		assertEquals(charList.get(6), pickChar.remove(6));
	}
	
	@Test
	public void testGetChar() {
		PickCharacters pickChar = new PickCharacters();
		pickChar.initializeDeck();
		ArrayList<cards.character.Character> charList = pickChar.iteratorDeck();
		assertEquals(charList.get(6), pickChar.getChar(6));
	}
}
