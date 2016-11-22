package player;

import java.util.ArrayList;

import cards.Family;
import cards.districts.District;
import game.Bank;
import game.Game;
import game.Pick;

/**
 * Abstraction of a player. This is the class which link all the class
 * like cards, pick and bank by actions.
 * @author bpotiron
 */
public class Player {

	private boolean protectionFromCondottiere;
	
	private String name;
	
	private int coins;
	
	private ArrayList<District> city;
	
	private ArrayList<District> deck;
	
	private Pick pick; 
	private Bank bank;
	private Game game;
	
	/**
	 * Constructor for a player.
	 * @param name name of the player
	 * @param pick the pick where the player can draw cards and make other actions
	 * @param bank the bank where the player get and put his coins.
	 * @param game the game where the player is involved
	 */
	public Player(String name, Pick pick, Bank bank, Game game) {
		this.protectionFromCondottiere = false;
		this.name = name;
		
		this.pick = pick;
		this.bank = bank;
		this.game = game;
		
		this.city = new ArrayList<District>();
		this.deck = new ArrayList<District>();
	}
	
	/**
	 * Constructor used only for non human players (like the trash player)
	 * @param name correspond to the name of the player
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * The player draw a card in the pick. The card is in his hand.
	 * @return District the district add in the deck of the player
	 */
	public District drawCard() {
		District district = this.pick.drawCard();
		this.deck.add(district);
		return district;
	}
	
	/**
	 * The player throw a card of his deck to the pick
	 * @param d the card to throw
	 */
	public void throwCard(District d) {
		try {
			this.pick.addCardInPick(d);
			this.deck.remove(d);
		} catch(Exception e) {
			System.out.println("ERROR : No card \"" + d.toString() + "\" in the deck of the player.");
		}
	}

	/**
	 * The player throw a card of his deck to the pick
	 * @param i corresponding to the rank of the card in the arrayList representing the deck
	 */
	public void throwCard(int i) {
		this.pick.addCardInPick((District) this.deck.remove(i));
	}

	/**
	 * The player build a district. The district is removed from is hand
	 * and goes to his city (visible by the other player). His coins are
	 * credited by the price of the district and the money goes to the bank.
	 * Pre condition : the district to add doesn't alreasy exist in the city
	 * of the player. 
	 * @param c correspond to the card of the district to build
	 * @throws Exception when the player has not enough money or when he tries to build a district that he already have in his city
	 */
	public void buildDistrict(District c) throws Exception {
		if(districtNotInCity(c)) {
			if(c.getPrice() > this.coins) 
					throw new Exception("Not enought money to build this district.");
			else {
				removeCoinsFromPlayer(c.getPrice());
				if(this.deck.remove(c))
					this.city.add(c);
			}
		} else
			throw new Exception("You can't build a district that you already have in your city.");
	}
	
	/**
	 * This method verify the pre-condition to build a district
	 * in the city of the player. The district should not exist
	 * already in the city.
	 * @param d the district
	 * @return true if the district in parameter is not in the city of the player. Else return false.
	 */
	private boolean districtNotInCity(District d) {
		for(District di : this.city) {
			if(di.equals(d))
				return false;
		}
		return true;
	}
	
	/**
	 * Method used by the condottiere to destroy the district of the city of another player
	 * @param d the district to destroy in the city of the player
	 */
	public void destroyDistrict(District d) {
		try {
			this.city.remove(d);
			this.pick.addCardInPick(d);
		} catch(Error e) {
			System.out.println("ERROR : " + e.getMessage());
		}
	}
	
	/**
	 * return the arrayList containing all the city builds by the player
	 * @return the city of the player
	 */
	public ArrayList<District> getCity() {
		return this.city;
	}
	
	/**
	 * When a character (king for example) begin a round, 
	 * he can have 1 gold for each district of the king family (nobility).
	 * This method return the number of district of the f family in the city of the player
	 * @param f the family of the character played
	 * @return the number of districts of the f family in the city of the player
	 */
	public int NbOfFamilyDistrictsInCity(Family f) {
		int total = 0;
		for(District d : this.city) {
			if(d.getFamily().equals(f))
				total++;
		}
		return total;
	}

	/**
	 * return the arrayList containing all the city builds by the player
	 * @return the city of the player
	 */
	public ArrayList<District> getDeck() {
		return this.deck;
	}

	/**
	 * Method used when a magician want to replace his deck with another player
	 * @param deckOther the new deck of the player
	 */
	public void replaceDeck(ArrayList<District> deckOther) {
		this.deck= deckOther;
	}

	/**
	 * @return the number of cards in the deck of the player
	 */
	public int nbCard() {
		return this.deck.size();
	}
	
	/**
	 * Set the attribute corresponding to the protection from condottiere
	 * to true or false, depends of the param.
	 * @param b set the attribute to true or false respectively if the player is protected or not from the condottiere.
	 */
	public void setProtectionFromCondottiere(boolean b) {
		this.protectionFromCondottiere = b;
	}

	/**
	 * @return true if the player is protected from the condottiere 
	 * (if the player played the bishop character during the round). 
	 * Else return false.
	 */
	public boolean isProtectedFromCondottiere() {
		return this.protectionFromCondottiere;
	}
	
	/**
	 * Return the name of the player
	 * @return the name/pseudo of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the number of coins of the player
	 * @return integer correspond to the number of coins of the player
	 */
	public int getCoins() {
		return coins;
	}
	
	/**
	 * Give coins to the player (coins are removed from the bank)
	 * @param i number of coins to add
	 */
	public void getCoinsFromBank(int i) {
		addCoins(i);
		this.bank.getGoldInBank(i);
	}

	/**
	 * Add coins to the account of the player
	 * @param i number of coin to add
	 * @return true if the operation is successful
	 */
	private boolean addCoins(int i) {
		if(this.coins + i > 30 || i < 0)
			return false;
		this.coins += i;
		return true;
	}
	
	/**
	 * Remove coins to the account of the player
	 * @param i number of coin to remove
	 * @return true if the operation is successful
	 */
	private boolean removeCoins(int i) {
		if(this.coins - i < 0 || i < 0)
			return false;
		this.coins -= i;
		return true;
	}

	/**
	 * Remove coins to the player and put them in the bank
	 * @param i number of coins to remove
	 */
	public void removeCoinsFromPlayer(int i) {
		removeCoins(i);
		this.bank.addGoldInBank(i);
	}

	/**
	 * This method remove coins to the player specified in parameter
	 * and give them to the current player (this)
	 * @param i correspond to the number of coins to take
	 * @param p the player which will loose money
	 */
	public void removeCoinsToOtherPlayer(int i, Player p) {
		try {
			if(p.getCoins() < i || i > 30 || i < 1)
				throw new Exception();
			else
				if ((p.removeCoins(i) && addCoins(i)) != true) 
					throw new Exception();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method returning a boolean which correspond to the status of the game.
	 * @return true if the player have finish to build 8 districts
	 */
	public boolean hasBuild8Districts() {
		return (this.city.size() >= 8) ? true : false;
	}
	
	/**
	 * Calculate the score of the player (at the end of the game)
	 * @return the score of the player
	 */
	public int calculateScore() {
		int score = 0;
		
		ArrayList<Family> listFamily = new ArrayList<Family>();
		
		for(District d : this.city) {
			if(!listFamily.contains(d.getFamily())) {
				listFamily.add(d.getFamily());
			}
			score += d.getValue();
		}
		if(listFamily.size() == 5) // if the player have build districts from the five family
			score += 3;
		if(hasBuild8Districts()) // if the player finish to build 8 districts
			score += 2;
		if(this.game.isWinner(this)) // if the player is the first to finish the game
			score += 2;
		
		return score;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if(other.getName() == this.getName() && other.getCoins() == this.getCoins())
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
