package cards;

/**
 * Basic abstraction of a card in the game.
 * @author bpotiron
 */
public abstract class Card {
	
	protected String name;
	protected Family family;
	
	/**
	 * Constructor for the card.
	 * @param name name of the card
	 * @param family family affiliate to the card (can be null)
	 */
	public Card(String name, Family family) {
		this.name = name;
		this.family = family;
	}
	
	/**
	 * Getter for the family of a card
	 * @return the family affiliate to the card
	 */
	public Family getFamily() {
		return this.family;
	}
	
	@Override
	public String toString() {
		return(this.name);
	}

}
