package cards;

import java.awt.Color;

/**
 * Abstraction of a family in the game. Each district is affiliate to a family.
 * Sometimes the powers of the characters are dependent to the family.
 * @author bpotiron
 */
public class Family {

	private String name;
	private Color color;
	
	/**
	 * Constructor of a family.
	 * @param name the name of the family
	 * @param color the color affiliate to the family (in the game each family have a color [blue, red, yellow, purple and green])
	 */
	public Family(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	/**
	 * Getter for the name of the family
	 * @return the name of the family
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the color of the family
	 * @return the color affiliate to the family
	 */
	public Color getColor() {
		return color;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(this.getClass() == o.getClass()) {
			Family obj = (Family) o;
			if(obj.getColor() == getColor() && obj.getName() == getName())
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
