package cards.character;

import cards.Card;
import cards.Family;

/**
 * Abstraction of a character and his attributes.
 * A character is a card so the class extends the Card class.
 * @author bpotiron
 */
public abstract class Character extends Card implements Comparable<Character> {

	private String description;
	private int rank;
	
	/**
	 * Constructor of a character which call the constructor of a card.
	 * @param name the name of the character
	 * @param family the family affiliate to the character (null if the character is not affiliate to a family)
	 * @param description Description of the power of the character
	 * @param rank the rank in the game of the character. The rank is used to know the position of the character in the game. Two characters can't have the same rank.
	 */
	Character(String name, Family family, String description, int rank) {
		super(name, family);
		this.description = description;
		this.rank = rank;
	}
	
	/**
	 * Getter for the description of the character
	 * @return the description of the character
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * The setter of the description of the character
	 * @param description corresponding to the description of the powers of the character
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for the rank of the character
	 * @return the rank of the character in the game (for example the assassin is number 1 and the condottiere is number 8)
	 */
	public int getRank() {
		return rank;
	}

	@Override
	public int compareTo(Character o) {
		return getRank() - o.getRank();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
