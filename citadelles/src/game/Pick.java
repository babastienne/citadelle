package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cards.Family;
import cards.districts.District;
import cards.districts.Wonder;

/**
 * This class create a pick which contains all the districts and wonders for the game.
 * There is only one pick by game.
 * @author bpotiron
 */
public class Pick {
	
	private List<District> pick;
	
	private int listLimit;
	
	/**
	 * Constructor for the pick. The constructor initialize the pick with all the districts
	 * and also initialize the limit of the list.
	 */
	public Pick() {
		pick = new ArrayList<District>();
		listLimit = 0;
		creationOfCards();
	}
	
	/**
	 * This method draw a card on the top of the pick (correspond to the first card in the list)
	 * If there's not enough card to the pick, then an exception is throw.
	 * @return the card picked by the player
	 */
	public District drawCard() {
		try {
			if(getPickSize() > 0) {
				listLimit--;
				return pick.remove(0);
			} else {
				throw new Exception("Not enough card in the pick to draw a card.");
			}
			
		} catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * This method add a district at the end of the pick (correspond to the last card in the list)
	 * @param district which is the card to add in the pick
	 */
	public void addCardInPick(District district) {
		pick.add(district);
		listLimit++;
	}
	
	/**
	 * Getter of the listLimit variable
	 * @return the number of cards in the pick
	 */
	public int getPickSize() {
		return listLimit;
	}

	/**
	 * This method create the cards objects and put them in the pick
	 */
	private void creationOfCards() {
		
		Family religion = new Family("Religion", Color.blue);
		Family noblesse = new Family("Noblesse", Color.yellow);
		Family commerce = new Family("Commerce", Color.green);
		Family militaire = new Family("Militaire", Color.red);
		
		// Bishop
		this.addCardInPick(new District("Temple", religion, 1));
		this.addCardInPick(new District("Temple", religion, 1));
		this.addCardInPick(new District("Temple", religion, 1));
		this.addCardInPick(new District("Eglise", religion, 3));
		this.addCardInPick(new District("Eglise", religion, 3));
		this.addCardInPick(new District("Eglise", religion, 3));
		this.addCardInPick(new District("Monastere", religion, 4));
		this.addCardInPick(new District("Monastere", religion, 4));
		this.addCardInPick(new District("Monastere", religion, 4));
		this.addCardInPick(new District("Monastere", religion, 4));
		this.addCardInPick(new District("Cathedrale", religion, 2));
		this.addCardInPick(new District("Cathedrale", religion, 2));
		
		// Condottiere
		this.addCardInPick(new District("Tour de guet", militaire, 1));
		this.addCardInPick(new District("Tour de guet", militaire, 1));
		this.addCardInPick(new District("Tour de guet", militaire, 1));
		this.addCardInPick(new District("Prison", militaire, 2));
		this.addCardInPick(new District("Prison", militaire, 2));
		this.addCardInPick(new District("Prison", militaire, 2));
		this.addCardInPick(new District("Caserne", militaire, 3));
		this.addCardInPick(new District("Caserne", militaire, 3));
		this.addCardInPick(new District("Caserne", militaire, 3));
		this.addCardInPick(new District("Forteresse", militaire, 5));
		this.addCardInPick(new District("Forteresse", militaire, 5));
		this.addCardInPick(new District("Forteresse", militaire, 5));
		
		// Trade
		this.addCardInPick(new District("Taverne", commerce, 1));
		this.addCardInPick(new District("Taverne", commerce, 1));
		this.addCardInPick(new District("Taverne", commerce, 1));
		this.addCardInPick(new District("Taverne", commerce, 1));
		this.addCardInPick(new District("Taverne", commerce, 1));
		this.addCardInPick(new District("Echoppe", commerce, 2));
		this.addCardInPick(new District("Echoppe", commerce, 2));
		this.addCardInPick(new District("Echoppe", commerce, 2));
		this.addCardInPick(new District("Echoppe", commerce, 2));
		this.addCardInPick(new District("Marche", commerce, 2));
		this.addCardInPick(new District("Marche", commerce, 2));
		this.addCardInPick(new District("Marche", commerce, 2));
		this.addCardInPick(new District("Marche", commerce, 2));
		this.addCardInPick(new District("Comptoir", commerce, 3));
		this.addCardInPick(new District("Comptoir", commerce, 3));
		this.addCardInPick(new District("Comptoir", commerce, 3));
		this.addCardInPick(new District("Port", commerce, 4));
		this.addCardInPick(new District("Port", commerce, 4));
		this.addCardInPick(new District("Port", commerce, 4));
		this.addCardInPick(new District("Hotel de ville", commerce, 5));
		this.addCardInPick(new District("Hotel de ville", commerce, 5));
		
		// Nobility
		this.addCardInPick(new District("Manoir", noblesse, 3));
		this.addCardInPick(new District("Manoir", noblesse, 3));
		this.addCardInPick(new District("Manoir", noblesse, 3));
		this.addCardInPick(new District("Manoir", noblesse, 3));
		this.addCardInPick(new District("Manoir", noblesse, 3));
		this.addCardInPick(new District("Palais", noblesse, 5));
		this.addCardInPick(new District("Palais", noblesse, 5));
		this.addCardInPick(new District("Chateau", noblesse, 4));
		this.addCardInPick(new District("Chateau", noblesse, 4));
		this.addCardInPick(new District("Chateau", noblesse, 4));
		this.addCardInPick(new District("Chateau", noblesse, 4));
		this.addCardInPick(new District("Chateau", noblesse, 4));
		
		// Wonder
//		this.addCardInPick(new Wonder("Cour des miracles", 2, "Pour le décompte final des points, la cour des miracles est considérée comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacité si vous avez construit la cour des miracles au dernier tour de jeu.", 0));
//		this.addCardInPick(new Wonder("Donjon", 3, "Le Donjon ne peut pas être détruit par le Condottiere.", 0));
//		this.addCardInPick(new Wonder("Laboratoire", 5, "Une fois par tour, vous pouvez vous défausser d'une carte quartier de votre main et recevoir une pièce d'or en contrepartie", 0));
//		this.addCardInPick(new Wonder("Manufacture", 5, "Une fois par tour, vous pouvez payer trois pièces d'or pour piocher trois cartes.", 0));
//		this.addCardInPick(new Wonder("Observatoire", 3, "Si vous choisissez de piocher des cartes au début de votre tour, vous en piochez trois, en choisissez une et défaussez les deux autres.", 0));
//		this.addCardInPick(new Wonder("Cimetière", 5, "Lorsque le Condottiere détruit un quartier, vous pouvez payer une pièce d'or pour le reprendre dans votre main. Vous ne pouvez pas faire cela si vous êtes vous-même Condottiere.", 0));
//		this.addCardInPick(new Wonder("Bibliothèque", 6, "Si vous choisissez de piocher des cartes au début de votre tour, vous en piochez deux et les conservez toutes les deux.", 0));
//		this.addCardInPick(new Wonder("Ecole de Magie", 6, "Pour la perception des revenus, l'école de magie est considérée comme un quartier de la couleur de votre choix, elle vous rapporte donc si vous êtes, Roi, Evêque, Marchand ou Condottiere", 0));
//		this.addCardInPick(new Wonder("Université", 6, "Cette réalisation de prestige (nul n'a jamais compris à quoi pouvait bien servir une université) coûte six pièces d'or à bâtir mais vaut huit points dans le décompte de fin de partie.", 2));
//		this.addCardInPick(new Wonder("Dracoport", 6, "Cette réalisation de prestige (on n'a pas vu de dragon dans le Royaume depuis bientôt mille ans) coûte six pièces d'or à bâtir mais vaut huit points dans le décompte de fin de partie.", 2));
//		
		this.shufflePick();
		
	}

	/**
	 * This method shuffle all the pick. Shouldn't be called before the initialization of the pick.
	 */
	private void shufflePick() {
		Collections.shuffle(pick);
	}

}
