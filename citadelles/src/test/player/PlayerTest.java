/**
 * 
 */
package test.player;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import cards.districts.District;
import game.Bank;
import game.Pick;
import player.Player;

/**
 * @author bpotiron
 */
public class PlayerTest {

	/**
	 * Test method for {@link player.Player#Player(java.lang.String, game.Pick, game.Bank, game.Game)}.
	 */
	@Test
	public void testPlayerStringPickBankGame() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		assertEquals("Joueur", player1.getName());
	}

	/**
	 * Test method for {@link player.Player#Player(java.lang.String)}.
	 */
	@Test
	public void testPlayerString() {
		Player player1 = new Player("Joueur");
		assertEquals("Joueur", player1.getName());
	}

	/**
	 * Test method for {@link player.Player#drawCard()}.
	 */
	@Test
	public void testDrawCard() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		player1.drawCard(); // the player will have one card in his deck now
		assertEquals(1, player1.getDeck().size());
	}

	/**
	 * Test method for {@link player.Player#throwCard(cards.districts.District)}.
	 */
	@Test
	public void testThrowCardDistrict() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		District d = player1.drawCard(); // the player will have one card in his deck
		player1.throwCard(d); // if the method works, the player will have no dsitrict anymore in his deck
		assertEquals(0, player1.getDeck().size()); 
	}

	/**
	 * Test method for {@link player.Player#throwCard(int)}.
	 */
	@Test
	public void testThrowCardInt() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		player1.drawCard(); // the player will have one card in his deck
		player1.drawCard(); // the player will have two cards in his deck. 
		District d = player1.drawCard(); // the player will have three cards in his deck. We save the third card.
		player1.throwCard(1); // We throw the second card
		assertEquals(d, player1.getDeck().get(1)); // the third card became the second.
	}

	/**
	 * Test method for {@link player.Player#buildDistrict(cards.districts.District)}.
	 */
	@Test
	public void testBuildDistrict() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		District d = player1.drawCard(); // the player will have one card in his deck
		player1.getCoinsFromBank(20);
		try {
			player1.buildDistrict(d); // we build the district
		} catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		assertEquals(d, player1.getCity().get(0)); // the district build is now in the city of the player
	}

	/**
	 * Test method for {@link player.Player#destroyDistrict(cards.districts.District)}.
	 */
	@Test
	public void testDestroyDistrict() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		District d = player1.drawCard();
		player1.getCoinsFromBank(20);
		try {
			player1.buildDistrict(d); // we build the district
		} catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		player1.destroyDistrict(d); // we destroy the district built
		assertEquals(0, player1.getCity().size()); // the player have no district in his city anymore
	}

	/**
	 * Test method for {@link player.Player#getCity()}.
	 */
	@Test
	public void testGetCity() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		District d = player1.drawCard(); 
		District d1 = player1.drawCard(); 
		while(d.equals(d1))
			d1 = player1.drawCard();
		player1.getCoinsFromBank(20);
		try {
			player1.buildDistrict(d);
			player1.buildDistrict(d1);
		} catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		ArrayList<District> list = new ArrayList<District>(); // we create a list with the two districts that the player draw previously
		list.add(d);
		list.add(d1);
		assertEquals(list, player1.getCity()); // the two list are equals. The method getCity works
	}

	/**
	 * Test method for {@link player.Player#NbOfFamilyDistrictsInCity(cards.Family)}.
	 */
	@Test
	public void testNbOfFamilyDistrictsInCity() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		District d = player1.drawCard(); // we stock the card draw so we can have the family affiliated after
		player1.getCoinsFromBank(20);
		try {
			player1.buildDistrict(d); // we build the city
		} catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		assertEquals(1, player1.NbOfFamilyDistrictsInCity(d.getFamily())); // we build one district of the family f
	}

	/**
	 * Test method for {@link player.Player#getDeck()}.
	 */
	@Test
	public void testGetDeck() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		District d = player1.drawCard(); 
		District d1 = player1.drawCard(); 
		ArrayList<District> list = new ArrayList<District>(); // we create a list with the two districts that the player draw previously
		list.add(d);
		list.add(d1);
		assertEquals(list, player1.getDeck()); // the lists should be equals
	}

	/**
	 * Test method for {@link player.Player#replaceDeck(java.util.ArrayList)}.
	 */
	@Test
	public void testReplaceDeck() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		Player player2 = new Player("Joueur", new Pick(), new Bank(), null);
		District d = player2.drawCard(); 
		District d1 = player2.drawCard(); // the player2 have a deck
		ArrayList<District> list = new ArrayList<District>(); // we create the same deck as player2
		list.add(d);
		list.add(d1);
		player1.replaceDeck(player2.getDeck()); // the player 1 replace his own deck with the deck of the player 2.
		assertEquals(list, player1.getDeck());
	}

	/**
	 * Test method for {@link player.Player#nbCard()}.
	 */
	@Test
	public void testNbCard() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		player1.drawCard();
		player1.drawCard();
		player1.drawCard();
		player1.drawCard();
		assertEquals(4, player1.nbCard());
	}

	/**
	 * Test method for {@link player.Player#setProtectionFromCondottiere(boolean)}.
	 */
	@Test
	public void testSetProtectionFromCondottiere() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		player1.setProtectionFromCondottiere(true);
		assertEquals(true, player1.isProtectedFromCondottiere());
	}

	/**
	 * Test method for {@link player.Player#isProtectedFromCondottiere()}.
	 */
	@Test
	public void testIsProtectedFromCondottiere() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		assertEquals(false, player1.isProtectedFromCondottiere()); // by default the player is not protected from the condottiere
	}

	/**
	 * Test method for {@link player.Player#getName()}.
	 */
	@Test
	public void testGetName() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		assertEquals("Joueur", player1.getName());
	}

	/**
	 * Test method for {@link player.Player#getCoins()}.
	 */
	@Test
	public void testGetCoins() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		assertEquals(0, player1.getCoins()); // by default a player has no coins before the initialization of the game
	}

	/**
	 * Test method for {@link player.Player#getCoinsFromBank(int)}.
	 */
	@Test
	public void testGetCoinsFromBank() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		player1.getCoinsFromBank(15);
		assertEquals(15, player1.getCoins());
	}

	/**
	 * Test method for {@link player.Player#removeCoinsFromPlayer(int)}.
	 */
	@Test
	public void testRemoveCoinsFromPlayer() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		player1.getCoinsFromBank(15);
		player1.removeCoinsFromPlayer(10);
		assertEquals(5, player1.getCoins());
	}

	/**
	 * Test method for {@link player.Player#removeCoinsToOtherPlayer(int, player.Player)}.
	 */
	@Test
	public void testRemoveCoinsToOtherPlayer() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		Player player2 = new Player("Joueur", new Pick(), new Bank(), null);
		player2.getCoinsFromBank(15);
		player1.removeCoinsToOtherPlayer(5, player2);
		assertEquals(10, player2.getCoins());
		assertEquals(5, player1.getCoins());
	}

	/**
	 * Test method for {@link player.Player#hasBuild8Districts()}.
	 */
	@Test
	public void testHasBuild8Districts() {
		Player player1 = new Player("Joueur", new Pick(), new Bank(), null);
		assertEquals(false, player1.hasBuild8Districts());
		player1.getCoinsFromBank(30);
		int i = 0;
		while(!player1.hasBuild8Districts()) { // while we havn't build 8 districts we try to build a district
			player1.drawCard();
			if(player1.getCoins() < 8)
				player1.getCoinsFromBank(15);
			if(player1.getCity().contains(player1.getDeck().get(i)))
				i++;
			else {
				try {
					player1.buildDistrict(player1.getDeck().get(i));
				} catch(Exception e) {
					System.out.println("ERROR : " + e.getMessage());
				}
			}
		}
		assertEquals(true, player1.hasBuild8Districts());
	}

}
