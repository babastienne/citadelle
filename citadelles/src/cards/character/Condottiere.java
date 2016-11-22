package cards.character;

import java.awt.Color;

import cards.Family;

/**
 * Condottiere character which extends the character class
 * @author bpotiron
 */
public class Condottiere extends Character {

	/**
	 * Constructor of the Condottiere character
	 */
	public Condottiere() {
		super("Condottiere", new Family("Militaire", Color.red), "D�truit un quartier", 8);
	}
}
