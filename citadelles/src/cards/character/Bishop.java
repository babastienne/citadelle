package cards.character;

import java.awt.Color;

import cards.Family;

/**
 * Bishop character which extends the character class
 * @author bpotiron
 */
public class Bishop extends Character {

	/**
	 * Constructor of the Bishop character
	 */
	public Bishop() {
		super("Eveque", new Family("Religion", Color.blue), "Est protege par le condottiere", 5);
	}
}
