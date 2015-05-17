package modelObjects;

import rules.BlackjackRules;
import enumerations.CardSuit;
import enumerations.CardRank;

public class BlackjackCard extends PlayingCard {
	private int value;

	/**
	 * Constructor
	 * @param rank The rank of the card.
	 * @param suit The suit of the card.
	 */
	public BlackjackCard(CardRank rank, CardSuit suit) {
		super(rank, suit);
		value = BlackjackRules.getCardValue(rank);
	}
	
	/**
	 * Get the value of the card.  
	 * Aces have value 1.
	 * 2s through 10s have their rank as their value.
	 * Face cards have value 10.
	 * @return The value of the card.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Checks if the card is a ten value.
	 * @return  True if the card is ten, Jack, Queen, or King; otherwise false.
	 */
	public boolean isTenValue() {
		boolean tenValue = false;
		
		if (getRank() == CardRank.TEN || getRank() == CardRank.JACK || 
			getRank() == CardRank.QUEEN || getRank() == CardRank.KING) {
			tenValue = true;
		}
		
		return tenValue;
	}

}
