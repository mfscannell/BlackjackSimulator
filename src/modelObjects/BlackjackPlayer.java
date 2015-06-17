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
	public void setTakesInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
	/**
	 * Checks whether or not the player is counting cards.
	 * @return  True if the player counts cards.
	 */
	public boolean doesCountsCards() {
		return countsCards;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < hands.size(); i++) {
			stringBuilder.append(hands.get(i).toString());
			stringBuilder.append("\n");
		}
		
		stringBuilder.append("Cash:" + getCashTotal() + "\n");
		stringBuilder.append("Current bet:" + getBetAmount() + "\n");
		
		if (countsCards) {
			stringBuilder.append(COUNTS_CARDS);
		} else {
			stringBuilder.append(DOESNT_COUNTS_CARDS);
		}
		stringBuilder.append("\n");
		
		if (insurance) {
			stringBuilder.append("Does Takes Insurance\n");
		} else {
			stringBuilder.append("Doesn't Takes Insurance\n");
		}
		
		return stringBuilder.toString();
	}
}
