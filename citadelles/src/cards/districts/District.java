package cards.districts;

import cards.Card;
import cards.Family;

/**
 * Abstraction of a district. In the game the districts are builds
 * and makes a city. The player with 8 districts in his city win
 * the game. Districts are cards so they extends the Card class.
 * @author bpotiron
 */
public class District extends Card {

	private int price;
	
	/**
	 * Constructor for districts.
	 * @param name the name of the district
	 * @param family the family corresponding to the districts (nobility, bishop, military, religious)
	 * @param price the price to pay for the construction of the district
	 */
	public District(String name, Family family, int price) {
		super(name, family);
		this.price = price;
	}
	
	/**
	 * Getter for the price of the district.
	 * @return the price to pay to build the district.
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * Getter for the value of the district (used in the final score). Re-implementation of this method in the Wonder class.
	 * @return the value of the district (different than the price with some wonder districts). 
	 */
	public int getValue() {
		return this.price;
	}
	
	/**
	 * Getter for the name of the district
	 * @return the name of the district
	 */
	public String getName() {
		return super.name;
	}
	
	@Override
	public String toString() {
		return super.name + "[" + price + ", " + super.family + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		District other = (District) obj;
		if (other.toString().equals(this.toString()))
			return true;
		return false;
	}
	
}
