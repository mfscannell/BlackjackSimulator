package modelObjects;

import java.util.ArrayList;

public class BlackjackPlayer extends Gambler {
	private boolean countsCards;
	private ArrayList<BlackjackHand> hands;
	private boolean insurance;
	
	private static final String COUNTS_CARDS = "Counts cards";
	private static final String DOESNT_COUNTS_CARDS = "Doesn't counts cards";

	/**
	 * Constructor.
	 * @param cashTotal  The total amount of cash a player has.
	 * @param countsCards  True if the player counts cards.
	 */
	public BlackjackPlayer(double cashTotal, boolean countsCards) {
		super(cashTotal);
		this.countsCards = countsCards;
		hands = null;
		insurance = false;
	}
	
	/**
	 * Checks if a player takes insurance.
	 * @return  True if a player takes insurance.
	 */
	public boolean takesInsurance() {
		return insurance;
	}
	
	public void setCountsCards(boolean countsCards) {
		this.countsCards = countsCards;
	}
	
	/**
	 * Associates the hands with the player.
	 * @param hands The hands to be associated with the player.
	 */
	public void setHands(final ArrayList<BlackjackHand> hands) {
		this.hands = hands;
	}
	
	/**
	 * Sets whether or not a player takes insurance when the dealer offers insurance.
	 * @param insurance  True if the player takes insurance.
	 */
	public void setsTakesInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
	/**
	 * Checks whether or not the player is counting cards.
	 * @return  True if the player counts cards.
	 */
	public boolean doesCountsCards() {
		return countsCards;
	}
	
	/**
	 * Converts the player to a string
	 */
	public String toString() {
		String string = "";
		
		for (int i = 0; i < hands.size(); i++) {
			string = string.concat(hands.get(i).toString());
			string = string.concat("\n");
		}
		
		string = string.concat("Cash:" + getCashTotal() + "\n");
		string = string.concat("Current bet:" + getBetAmount() + "\n");
		
		if (countsCards) {
			string = string.concat(COUNTS_CARDS);
		} else {
			string = string.concat(DOESNT_COUNTS_CARDS);
		}
		string = string.concat("\n");
		
		if (insurance) {
			string = string.concat("Does Takes Insurance\n");
		} else {
			string = string.concat("Doesn't Takes Insurance\n");
		}
		
		return string;
	}//end method toString

}
