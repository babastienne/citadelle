package cards.character;

import java.awt.Color;

import cards.Family;

/**
 * King character which extends the character class
 * @author bpotiron
 */
public class King extends Character {
	
	/**
	 * Constructor of the King character
	 */
	public King() {
		super("Roi", new Family("Noblesse", Color.yellow), "Devient le premier joueur", 4);
	}
	
}
