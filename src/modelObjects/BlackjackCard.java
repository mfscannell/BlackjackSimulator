package modelObjects;

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
		
		switch (rank) {
		case ACE:	value = 1;
					break;
		case TWO:	value = 2;
					break;
		case THREE:	value = 3;
					break;
		case FOUR:	value = 4;
					break;
		case FIVE:	value = 5;
					break;
		case SIX:	value = 6;
					break;
		case SEVEN:	value = 7;
					break;
		case EIGHT:	value = 8;
					break;
		case NINE:	value = 9;
					break;
		case TEN:	value = 10;
					break;
		case JACK:	value = 10;
					break;
		case QUEEN:	value = 10;
					break;
		case KING:	value = 10;
					break;
		default:	value = 0;
					break;
		}
	}//end constructor
	
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
