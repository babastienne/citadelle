package cards.districts;

import java.awt.Color;

import cards.Family;

/**
 * Abstraction of the Wonder cards. Those cards have different powers.
 * Wonders are specials districts so the class extends the District class.
 * @author bpotiron
 */
public class Wonder extends District {
	
	protected String capacity;
	protected int supplement; // When a wonder has a better value than the real cost of fabrication
	
	/**
	 * Constructor for the Wonder class.
	 * This is the same as a district but a wonder have also a special capacity
	 * and sometimes a supplement.
	 * @param name The name of the wonder
	 * @param price the price to build a wonder
	 * @param capacity the special power affiliate to the wonder
	 * @param supplement the supplement of the wonder (sometimes a wonder have a better value than his real cost of construction)
	 */
	public Wonder(String name, int price, String capacity, int supplement) {
		super(name, new Family("Merveille", Color.magenta), price);
		this.capacity = capacity;
		this.supplement = supplement;
	}
	
	/**
	 * Getter for the special capacity of the wonder
	 * @return the capacity of the wonder
	 */
	public String getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Getter for the value of the wonder (his price and the supplement)
	 * @return the value of the wonder
	 */
	public int getValue() {
		return super.getValue() + supplement;
	}

}
