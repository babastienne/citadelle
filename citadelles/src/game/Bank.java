package game;

/**
 * Abstraction of the bank in the game. There is only one bank by game.
 * @author bpotiron
 */
public class Bank {

	private static final int NB_TOTAL_GOLD = 30;
	
	private int totalGoldBank;
	
	/**
	 * Constructor of the bank. It initialize the number of gold coins into the bank to 30.
	 */
	public Bank() {
		this.totalGoldBank = NB_TOTAL_GOLD;
	}
	
	/**
	 * Method to add gold into the bank. The number of gold into the bank shouldn't 
	 * be superior to the limit (initialize to 30).
	 * @param i correspond to the number of coins to add
	 * @return true if the operation is success. Else false (if there is too much money in the bank for example).
	 */
	public boolean addGoldInBank(int i) {
		if(this.totalGoldBank + i <= NB_TOTAL_GOLD) {
			this.totalGoldBank += i;
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Method to get gold from the bank. 
	 * @param i the number of coins to get in the bank.
	 * @return true of the operation is success. Else false (if there is not enought money into the bank for example).
	 */
	public int getGoldInBank(int i) {
		if(this.totalGoldBank - i > 0) {
			this.totalGoldBank -= i;
			return i;
		} else
			return 0;
	}
	
}


