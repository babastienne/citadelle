package game;

import java.util.ArrayList;
import java.util.Collections;

import cards.character.Architect;
import cards.character.Assassin;
import cards.character.Bishop;
import cards.character.Character;
import cards.character.Condottiere;
import cards.character.King;
import cards.character.Magician;
import cards.character.Thief;
import cards.character.Trader;

/**
 * Abstraction of the pick of characters. This class create a deck used by the
 * players at the beginning of each round to choose their characters. The class
 * also create deck for iteration which is used at each round to iterate in
 * the order of apparition of each character. The deck and the deck for iteration
 * are the same except that the first deck is shuffled.
 * @author bpotiron
 */
public class PickCharacters {
	
	private ArrayList<Character> deckChar;
	private ArrayList<Character> copyDeckForIteration;
	
	/**
	 * Creation of the class. At this point no deck is created.
	 */
	public PickCharacters() {
	}
	
	/**
	 * Initialize the two decks.
	 */
	public void initializeDeck() {
		this.deckChar = null;
		this.deckChar = new ArrayList<Character>();
		this.deckChar.add(new Assassin());
		this.deckChar.add(new Thief());
		this.deckChar.add(new Magician());
		this.deckChar.add(new King());
		this.deckChar.add(new Bishop());
		this.deckChar.add(new Trader());
		this.deckChar.add(new Architect());
		this.deckChar.add(new Condottiere());
		
		this.copyDeckForIteration = new ArrayList<Character>();
		this.copyDeckForIteration.addAll(deckChar);
		
		Collections.shuffle(deckChar); // the deck is shuffled
	}
	
	/**
	 * Used by the game to iterate on each character in the good order.
	 * Note : this method re-initialize the deckChar.
	 * @return the list of the characters of the game in the good order
	 */
	public ArrayList<Character> iteratorDeck() {
		this.deckChar = new ArrayList<>();
		this.deckChar.addAll(copyDeckForIteration);
		return this.copyDeckForIteration;
	}
	
	/**
	 * Remove a character to the list of characters created at each round of the game
	 * @param i the rank of the character to remove
	 * @return the character removed
	 */
	public Character remove(int i) {
		try {
			return this.deckChar.remove(i);
		} catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Getter for the character in the list.
	 * @param i the rank of the character to return
	 * @return the character of the rank i in the list
	 */
	public Character getChar(int i) {
		try {
			return this.deckChar.get(i);
		} catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		return null;
	}
	
	@Override
	public String toString() {
		String result = "";
		int i = 0;
		for(Character c : this.deckChar) {
			result += i + " : " + c.toString() + " / ";
			i++;
		}
			
		return result;
	}

}
