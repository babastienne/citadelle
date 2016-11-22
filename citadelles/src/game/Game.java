package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cards.character.Character;
import cards.districts.District;
import player.Player;

/**
 * This class is the heart of the program. This is the class that
 * link all the others classes and run the game round by round with
 * an implementation of the powers of the characters. 
 * @author bpotiron
 */
public class Game {
	
	private Player player1, player2;
	private final Pick pioche;
	private final Bank banque;
	
	private Player winner;
	private Player crowned;
	
	private Scanner sc; // for read the keyboard entries

	/**
	 * Constructor of a Game. It initialize the game.
	 * After the construction of the Game you need to run
	 * the method "launchGame".
	 * @param player1Name the name of the first player
	 * @param player2Name the name of the second player
	 */
	public Game(String player1Name, String player2Name) {
		this.pioche = new Pick();
		this.banque = new Bank();
		
		this.player1 = new Player(player1Name, this.pioche, this.banque, this);
		this.player2 = new Player(player2Name, this.pioche, this.banque, this);
		
		setWinner(null); // by default the winner is null
		if(1 == 1 + (int)(Math.random() * 2)) // the player who have the crowned by default is randomly selected
			setCrowned(this.player1);
		else
			setCrowned(this.player2);
		
		initializeGame();
	}
	
	/**
	 * If the game is not construct with the names of
	 * the two players, then this constructor calls the 
	 * constructor with the default names (Player1 and Player2)
	 */
	public Game() {
		this("Player1", "Player2");
	}
	
	/**
	 * Compare the player in parameter to the winner
	 * @param player the player compared to the winner
	 * @return true if the player in parameter is the winner
	 */
	public boolean isWinner(Player player) {
		return (this.winner.equals(player));
	}
	
	/**
	 * Set the winner of the game
	 * @param player became the winner of the game
	 */
	private void setWinner(Player player) {
		this.winner = player;
	}

	/**
	 * Set the crowned player
	 * @param player became the crowned player
	 */
	private void setCrowned(Player player) {
		this.crowned = player;
	}
	
	/**
	 * Compare the player in param to the crowned player
	 * @param player the player comparated to the crowned player
	 * @return true if the player in param is the crowned player
	 */
	private boolean isCrowned(Player player) {
		return (this.crowned.equals(player));
	}

	/**
	 * This method initialize the game by giving to the two players 4 cards
	 * and two coins.
	 */
	private void initializeGame() {
		for(int i = 0; i < 4; i++) { // the players begins the game with 4 districts
			this.player1.drawCard();
			this.player2.drawCard();
		}
		this.player1.getCoinsFromBank(2);
		this.player2.getCoinsFromBank(2);
	}
	
	
	/**
	 * This method launch all the game (round by round) of Citadelle.
	 */
	public void launchGame() {
		this.sc = new Scanner(System.in);
		Player currentPlayer = null;
		PickCharacters PC = new PickCharacters();
		HashMap<cards.character.Character, Player> mapChoices = null;
		Player trash = new Player("Trash");
		this.setWinner(trash); // by default the trash player is the winner
		while(this.isWinner(trash)) {
			PC.initializeDeck();
			currentPlayer = (this.isCrowned(player1)) ? this.player1 : this.player2;
			
			// initialization of the choose part
			mapChoices = new HashMap<cards.character.Character, Player>();
			mapChoices.put(PC.remove(0 + (int)(Math.random() * 7)), trash); // the first card is removed automatically and randomly
			
			System.out.println(PC);
			System.out.println("Current player : " + currentPlayer);
			System.out.println("Choisissez un personnage : ");
			mapChoices.put(PC.remove(sc.nextInt()), currentPlayer); // the current player choose his first character
			
			currentPlayer = (currentPlayer == this.player1) ? this.player2 : this.player1; // inversion of the currentPlayer
			System.out.println(PC);
			System.out.println("Current player : " + currentPlayer);
			System.out.println("Choisissez un personnage : ");
			mapChoices.put(PC.remove(sc.nextInt()), currentPlayer); // the current player choose his first character
			
			System.out.println(PC);
			System.out.println("Current player : " + currentPlayer);
			System.out.println("Choisissez un personnage à jeter: ");
			mapChoices.put(PC.remove(sc.nextInt()), trash); // the current player choose the char to remove
			
			currentPlayer = (currentPlayer == this.player1) ? this.player2 : this.player1; // inversion of the currentPlayer
			System.out.println(PC);
			System.out.println("Current player : " + currentPlayer);
			System.out.println("Choisissez un deuxième personnage : ");
			mapChoices.put(PC.remove(sc.nextInt()), currentPlayer); // the current player choose his second character
			
			System.out.println(PC);
			System.out.println("Current player : " + currentPlayer);
			System.out.println("Choisissez un personnage à jeter: ");
			mapChoices.put(PC.remove(sc.nextInt()), trash); // the current player choose the char to remove
			
			currentPlayer = (currentPlayer == this.player1) ? this.player2 : this.player1; // inversion of the currentPlayer
			System.out.println(PC);
			System.out.println("Current player : " + currentPlayer);
			System.out.println("Choisissez un deuxième personnage : ");
			mapChoices.put(PC.remove(sc.nextInt()), currentPlayer); // the current player choose his second character
			
			System.out.println(PC);
			mapChoices.put(PC.remove(0), trash); // the last char is removed
			
			// end of the choose part
			
			// Beginning of the round char by char.
			Character killedChar = null, robedChar = null;
			Player robber = null;
			this.player1.setProtectionFromCondottiere(false);
			this.player2.setProtectionFromCondottiere(false);
			
			
			ArrayList<Character> iteratorDeck = PC.iteratorDeck();
			for(Character c : iteratorDeck) {
				PC.remove(0); // we remove the current character to the list at each round because a character cannot act on himself or on characters that are already play.
				if(mapChoices.get(c) != trash) {
					currentPlayer = mapChoices.get(c);
					if(c.equals(killedChar)) { // if the actual character is dead
						System.out.println("Votre personnage a été assassiné.");
						if(killedChar.toString() == "Roi")
							this.setCrowned(currentPlayer); // even if the king is killed, the correspondent player is crowned for the next round 
					} 
					else {
						if(c.equals(robedChar)) { // if the actual character was robbed then the player loose his money
							System.out.println("Votre personnage a été volé par le voleur.");
							robber.removeCoinsToOtherPlayer(currentPlayer.getCoins(), currentPlayer);
						}
						
						
						switch(c.toString()) {
							case "Assassin":
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								// the assassin begins by killing another character
								System.out.println("Choisissez un personnage à assassiner : " + PC); // the player can't kills his own character
								killedChar = PC.getChar(sc.nextInt());
								
								// then he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								// then he can finish his round by building (or not) a district
								buildDistrict(currentPlayer);
								
								break;
								
							case "Voleur":
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								// the thief choose the character to rob 
								System.out.println("Choisissez un personnage à voler : " + PC); // the player can't rob his own character and the assassin because he already play this round
								robedChar = PC.getChar(sc.nextInt());
								robber = currentPlayer;
								
								// then he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								// then he can finish his round by building (or not) a district
								buildDistrict(currentPlayer);
								
								break;
								
							case "Magicien":
								boolean magicianActed = false;
								
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								if(proposeToActivePowerOfCharacter("Echange de cartes")) { // If the player didn't active the power of his character he can
									magicianActed = true;
									actionMagician(currentPlayer);
								}
								
								// he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								if(!magicianActed) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									magicianActed = true;
									actionMagician(currentPlayer);
								}
								
								// then he can finish his round by building (or not) a district
								buildDistrict(currentPlayer);
								
								if(!magicianActed) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									magicianActed = true;
									actionMagician(currentPlayer);
								}
								
								break;
								
							case "Roi":
								// the king is crowned
								this.setCrowned(currentPlayer);
								
								boolean getTheGold = false;
								
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								if(proposeToActivePowerOfCharacter("Gagne une pièce d'or par quartier noblesse")) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
									
								// he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								if(!getTheGold) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								
								// then he can finish his round by building (or not) a district
								buildDistrict(currentPlayer);

								
								if(!getTheGold) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								
								break;
								
							case "Eveque":
								
								getTheGold = false;
								
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								if(proposeToActivePowerOfCharacter("Gagne une pièce d'or par quartier religieux")) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
									
								// he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								if(!getTheGold) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								
								// then he can finish his round by building (or not) a district
								buildDistrict(currentPlayer);
								
								if(!getTheGold) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
							
								currentPlayer.setProtectionFromCondottiere(true); // the player who own the bishop is protected from the condottiere during the all round			
								
								break;
								
							case "Marchand":
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								System.out.println("Vous gagnez une pièce d'or en raison de votre status de marchand");
								currentPlayer.getCoinsFromBank(1);
								
								getTheGold = false;
								
								if(proposeToActivePowerOfCharacter("Gagner une pièce d'or par quartier marchand")) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								
								// he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								if(!getTheGold) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								
								// then he can finish his round by building (or not) a district
								buildDistrict(currentPlayer);
								
								if(!getTheGold) if(proposeToActivePowerOfCharacter()) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								
								break;
								
							case "Architecte":
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								// the architect get automatically two cards from the pick
								System.out.println("Vous etes architecte, vous obtenez deux cartes");
								currentPlayer.drawCard();
								currentPlayer.drawCard();
								
								// he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								// then he can finish his round by building (or not) a district (up to 3 districts because he is architect)
								int i = 0;
								while(buildDistrict(currentPlayer) && i < 3)
									i++;
								
								break;
								
							case "Condottiere":
								getTheGold = false;
								boolean actionCondottiere = false;
								
								// description of the status of the player
								descriptionStatePlayer(currentPlayer, c);
								
								if(proposeToActivePowerOfCharacter("Gagner une pièce d'or par quartier militaire")) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								if(proposeToActivePowerOfCharacter("Détruire un quartier")) { // If the player didn't active the power of his character he can
									actionCondottiere(currentPlayer);
									actionCondottiere = true;
								}
								
								// he can choose to pick card of get two coins of gold
								chooseBetweenPickCardOrTakeGold(currentPlayer);
								
								if(!getTheGold) if(proposeToActivePowerOfCharacter("Gagner une pièce d'or par quartier militaire")) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								if(!actionCondottiere) if(proposeToActivePowerOfCharacter("Détruire un quartier")) { // If the player didn't active the power of his character he can
									actionCondottiere(currentPlayer);
									actionCondottiere = true;
								}
								
								// then he can finish his round by building (or not) a district
								buildDistrict(currentPlayer);
								
								if(!getTheGold) if(proposeToActivePowerOfCharacter("Gagner une pièce d'or par quartier militaire")) { // If the player didn't active the power of his character he can
									characterGetGoldFromHisFamily(currentPlayer, c);
									getTheGold = true;
								}
								if(!actionCondottiere) if(proposeToActivePowerOfCharacter("Détruire un quartier")) { // If the player didn't active the power of his character he can
									actionCondottiere(currentPlayer);
									actionCondottiere = true;
								}
								
								break;
								
							default: // no option can use the default switch
								break;
						}
					
						
						if(this.isWinner(trash) && currentPlayer.hasBuild8Districts()) // if the current player has build his 8 districts, he win the game
							this.setWinner(currentPlayer);
					}
					
				}
			}
			
		}
		
		
		System.out.println(this.winner.getName() + " gagne la partie !!!");
		System.out.println("Score final : \n" + this.player1.getName() + " = " + this.player1.calculateScore() + "\n" + this.player2.getName() + " = " + this.player2.calculateScore());
		
		sc.close(); // we close the scanner
	}
	
	/**
	 * This method describe the status of a player during the game.
	 * the status includes : the name of the player, his money, the character he's playing, his deck and his city.
	 * @param currentPlayer the player that will be describe
	 * @param c the character the the player is playing
	 */
	private void descriptionStatePlayer(Player currentPlayer, Character c) {
		System.out.println("Joueur : " + currentPlayer + "(or : " + currentPlayer.getCoins() + "). Personnage : " + c);
		System.out.println("Votre deck : " + currentPlayer.getDeck() + "\nVos quartiers construits : " + currentPlayer.getCity());
	}

	/**
	 * This method implement the action of a character when he had to choose between
	 * take two coins of gold in the bank of draw two cards in the pick and keep only one.
	 * @param currentPlayer the player involved in the action
	 */
	private void chooseBetweenPickCardOrTakeGold(Player currentPlayer) {
		System.out.println("Choix 1 : deux pièces d'or.\nChoix 2 : piocher deux cartes quartiers et en garder une.");
		if(sc.nextInt() == 1) {
			currentPlayer.getCoinsFromBank(2);
			System.out.println("Vous possédez à présent : " + currentPlayer.getCoins());
		} else {
			currentPlayer.drawCard();
			currentPlayer.drawCard();
			System.out.println("Les deux cartes piochées sont : "
					+ currentPlayer.getDeck().get(currentPlayer.getDeck().size()-1) 
					+ "\n"
					+ currentPlayer.getDeck().get(currentPlayer.getDeck().size()-2)
					+ "\n"
					+ "Choix 1 : Garder la première carte.\nChoix 2 : Garder la deuxième carte");
			if(sc.nextInt() == 1)
				currentPlayer.throwCard((District) currentPlayer.getDeck().get(currentPlayer.getDeck().size()-2));
			else
				currentPlayer.throwCard((District) currentPlayer.getDeck().get(currentPlayer.getDeck().size()-1));
			System.out.println("Votre deck actuel : " + currentPlayer.getDeck());
		}
	}

	/**
	 * This method implement the action : build a district.
	 * When a player play a character, he can choose to build a district or no.
	 * @param currentPlayer the player involved in the action
	 * @return true if the player built a district. Else return false if the player decided to not build a district or if there is an exception (for example if the player hasn't enough money to build a district or if the player already have the district that he decided to build in his city).
	 */
	private boolean buildDistrict(Player currentPlayer) {
		int continu;
		System.out.println("Choix 1 : Je construit un quartier.\nChoix 2 : Je termine mon tour.");
		continu = sc.nextInt();
		
		if(continu == 1) {
			System.out.println("Quel quartier voulez-vous construire (-1 pour annuler) ?\n" + currentPlayer.getDeck());
			int indexQuartierToDestroy = sc.nextInt();
			if(indexQuartierToDestroy != -1) {
				try {
					currentPlayer.buildDistrict((District) currentPlayer.getDeck().get(indexQuartierToDestroy));
				} catch (Exception e) {
					System.out.println("ERROR : " + e.getMessage());
					return false;
				}
			}
		}
		return(continu == 1) ? true : false;
	}
	
	/**
	 * This method calls ProposeToActivePowerOfCharacter(string s) with an empty argument
	 * @return true if the player decided to active the power of his character. Else false.
	 */
	private boolean proposeToActivePowerOfCharacter() {
		return proposeToActivePowerOfCharacter("");
	}
	
	/**
	 * This method propose to active the power of a character
	 * @param s description of the power of the character (if empty string no description of the power)
	 * @return true if the player accept to active the power of his character. Else false.
	 */
	private boolean proposeToActivePowerOfCharacter(String s) {
		System.out.println("Voulez-vous activer le pouvoir de votre personnage ?");
		if(!s.isEmpty())
			System.out.println("Pouvoir : " + s);
		System.out.println("\nChoix 1 : oui \nChoix 2 : plus tard peut-être");
		return(sc.nextInt() == 1) ? true : false;
	}
	
	/**
	 * This method is called when a character decided to active his power and the 
	 * power includes that the character get gold from his affiliated family.
	 * @param currentPlayer the player playing the character will received the gold
	 * @param c the character affiliate to the power (used to know the family)
	 */
	private void characterGetGoldFromHisFamily(Player currentPlayer, Character c) {
		int nbDistrictFamily = currentPlayer.NbOfFamilyDistrictsInCity(c.getFamily());
		currentPlayer.getCoinsFromBank(nbDistrictFamily);
		System.out.println("Vous gagnez " + nbDistrictFamily + " en raison de vos quartiers '" + c.getFamily().toString() + "' construits");
	}
	
	/**
	 * This method is specific for activate the power of the magician.
	 * The magician can exchange his deck with the deck of the adverse player
	 * or he can exchange some cards of his deck with new ones in the pick
	 * (he can't choose the cards in the pick).
	 * @param currentPlayer the player who active the power of the magician
	 */
	private void actionMagician(Player currentPlayer) {
		System.out.println("Choix 1 : Defausser une/des carte(s) contre le même nombre de la pioche.\nChoix 2 : Echanger ses cartes contre celles d'un autre joueur.");
		if(sc.nextInt() == 1) { // if the player decided to exchange cards in his deck with cards in the pick
			System.out.println("Entrez les chiffres correspondants aux cartes à défausser (validez par entrée à chaque carte). Pour arreter tapez -1.\n" + currentPlayer.getDeck().toString());
			int value;
			do {
				value = sc.nextInt();
				if(value != -1) {
					currentPlayer.throwCard(value);
					currentPlayer.drawCard();
				}
			} while(value != -1 && currentPlayer.nbCard() > 0);
		} else { // if the player decided to exchange his deck with the deck of the adverse player
			System.out.println("Vous échangez votre deck avec celui du joueur adverse.");
			ArrayList<District> tmp = currentPlayer.getDeck();
			if(currentPlayer == this.player1) {
				this.player1.replaceDeck(this.player2.getDeck());
				this.player2.replaceDeck(tmp);
			} else {
				this.player2.replaceDeck(this.player1.getDeck());
				this.player1.replaceDeck(tmp);
			}
			System.out.println("Votre nouveau deck : " + currentPlayer.getDeck());
		}
	}

	/**
	 * This method is specifically called when the power of the condottiere is activated.
	 * The condottiere can destroy one district of the adverse player by paying the price
	 * of the district to destroy - 1. 
	 * @param currentPlayer the player which is actually the condottiere
	 */
	private void actionCondottiere(Player currentPlayer) {
		Player otherPlayer;
		otherPlayer = (currentPlayer == this.player1) ? this.player2 : this.player1;
		
		if(otherPlayer.isProtectedFromCondottiere())
			System.out.println("Le joueur adverse est protégé du pouvoir du condottiere puisqu'il incarne l'évèque.");
		else {
			System.out.println("Choix 1 : J'utilise mon pouvoir de condottiere.\nChoix 2 : Je n'utilise pas mon pouvoir.");
			if(sc.nextInt() == 1) {
				System.out.println("Quartiers adverses : " + otherPlayer.getCity() + "\nVous devez payer le prix de la construction -1 pour détruire un quartier.");
				System.out.println("Entrez le numéro du quartier à détruire (-1 pour annuler) :");
				int numDis = sc.nextInt();
				if(numDis != -1) {
					try {
						District toDestroy = otherPlayer.getCity().get(numDis);
						currentPlayer.removeCoinsFromPlayer(toDestroy.getPrice()-1);
						otherPlayer.destroyDistrict(toDestroy);
					} catch(Error e) {
						System.out.println("ERROR : " + e.getMessage());
					}
				}
			}
		}
	}
	
}
