package cards.character;

import java.awt.Color;

import cards.Family;

/**
 * Trader character which extends the character class
 * @author bpotiron
 */
public class Trader extends Character {
	
	/**
	 * Constructor of the Trader character
	 */
	public Trader() {
		super("Marchand", new Family("Commerce", Color.green), "Gagne une pièce d'or", 6);
	}

}
