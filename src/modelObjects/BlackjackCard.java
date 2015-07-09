package modelObjects;

import rules.BlackjackRules;
import enumerations.CardRank;
import enumerations.CardSuit;

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
	
	public int getValue() {
		return value;
	}
	
	public boolean isTenValue() {
		boolean tenValue = false;
		
		if (getRank() == CardRank.TEN || getRank() == CardRank.JACK || 
			getRank() == CardRank.QUEEN || getRank() == CardRank.KING) {
			tenValue = true;
		}
		
		return tenValue;
	}

}
