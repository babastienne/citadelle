package test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Test;

import cards.Card;
import cards.Family;
import cards.districts.District;
import game.Pick;

public class PickTest {

	@Test
	public void testDrawCard() {
		Pick pick = new Pick();
		Card card = pick.drawCard();
		assertNotEquals(card, pick.drawCard());
	}

	@Test
	public void testAddCardInPick() {
		Pick pick = new Pick();
		int size = pick.getPickSize();
		for(int i = 0; i <= size-1; i++) 
			pick.drawCard(); // on retire toutes les autres cartes de la pioche
		District card = new District("Test", new Family("Noblesse", Color.YELLOW), 500);
		pick.addCardInPick(card);
		assertEquals(card, pick.drawCard()); // la dernière carte restante dans la pioche est la dernière ajoutée (celle créée ci-dessus)
	}

	@Test
	public void testGetPickSize() {
		Pick pick = new Pick();
		District card = new District("Test", new Family("Noblesse", Color.YELLOW), 500);
		pick.addCardInPick(card);
		assertEquals(58, pick.getPickSize());
	}

}
