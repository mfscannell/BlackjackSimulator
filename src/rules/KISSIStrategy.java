package rules;

import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;
import modelObjects.PlayingCard;
import modelObjects.Shoe;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import exceptions.InvalidNumDecksException;
import exceptions.InvalidShoeException;

public class KISSIStrategy extends CompositionStrategy {
	/**Initial Card Counts at the start of a new shoe based upon the number of decks used. */
	public static final int[] INITIAL_COUNTS = {20, 18, 17, 15, 14, 12, 10, 8, 6};
	public static final int[] WALK_AWAY = {14, 14, 14, 12, 10, 8, 6, 4, 2};
	private int initialCount;
	private int count;
	public static int KISS_COUNT = 20;

	/**
	 * Constructor
	 * @param rules  The specific rules at the table.
	 * @param numDecks  The number of decks used in the shoe.
	 * @throws InvalidNumDecksException  The number of decks specified must be between
	 * 1 and 8.
	 */
	public KISSIStrategy(BlackjackRules rules, int numDecks) throws InvalidNumDecksException {
		super(rules, numDecks);
		
		initialCount = INITIAL_COUNTS[numDecks];
		count = initialCount;
	}
	
	/**
	 * Resets the card-counting count to the initial count.
	 */
	public void resetCount() {
		count = initialCount;
	}
	
	/**
	 * Adjusts the card count based upon the dealt card.
	 * @param card  The dealt card.
	 */
	public void adjustCount(PlayingCard card) {
		int cardValue = card.getValue();
		
		if ((cardValue == 2 && !card.isRedCard()) || 
			cardValue == 4 || 
			cardValue == 5 || 
			cardValue == 6) {
			count++;
		} else if (card.isFaceCard()) {
			count--;
		}
	}//end method adjustCount
	
	public int getCount() {
		return count;
	}
	
	/**
	 * Refer to the KISS I strategy chart to select a move based upon the table conditions.
	 * @param dealerUpCard  The dealer's exposed card.
	 * @param hand  The player's hand.
	 * @param rules  The specific rules at the table.
	 * @param numHands  The number of hands the player has based upon the number of splits 
	 * and resplits.
	 * @return  The recommended blackjack move.
	 */
	public BlackjackMove getAction(final PlayingCard dealerUpCard, 
								   final BlackjackHand hand,
								   final BlackjackRules rules,
								   int numHands) {
		BlackjackMove move = BlackjackMove.STAND;
		
		if (count >= KISS_COUNT) {
			if (hand.getTotal() == 9 && !hand.isSoft() && dealerUpCard.getValue() == 2) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.getTotal() == 11 && dealerUpCard.isAce()) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.getTotal() == 16 && dealerUpCard.getValue() == 10) {
				move = BlackjackMove.STAND;
			} else if (hand.isHand(CardRank.ACE, CardRank.SEVEN) && dealerUpCard.getValue() == 2) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.isHand(CardRank.ACE, CardRank.EIGHT) && dealerUpCard.getValue() == 5) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.isHand(CardRank.ACE, CardRank.EIGHT) && dealerUpCard.getValue() == 6) {
				move = BlackjackMove.DOUBLE;
			} else {
				move = super.getAction(dealerUpCard, hand, rules, numHands);
			}
		} else {
			move = super.getAction(dealerUpCard, hand, rules, numHands);
		}
		
		if (move == BlackjackMove.DOUBLE && 
			hand.wasFromSplit() && 
			!rules.isDoubleAfterSplitAllowed()) {
			move = BlackjackMove.HIT;
		}
		
		return move;
	}//end method getAction
	
	/**
	 * Checks KISS I if it recommends the player to take insurance.
	 * @param numDecks  The number of decks in the shoe.
	 * @return  True if KISS I recommends to take insurance.
	 * @throws InvalidShoeException  Thrown if the number of decks specified is less than
	 * the minimum number of decks in a shoe or greater than the maximum number of decks in the shoe.
	 */
	public boolean getInsuranceMove(int numDecks) throws InvalidShoeException {
		boolean insurance = false;
		
		if (numDecks == 1 && count >= 21) {
			insurance = true;
		} else if (numDecks == 2 && count >= 22) {
			insurance = true;
		} else if (4 <= numDecks && numDecks <= 8 && count >= 25) {
			insurance = true;
		} else if (numDecks < Shoe.MIN_NUM_DECKS || numDecks > Shoe.MAX_NUM_DECKS) {
			throw new InvalidShoeException("Invalid number of decks specified");
		} else {
			insurance = super.getInsuranceMove();
		}
		
		return insurance;
	}//end method getInsuranceMove
	
	/**
	 * Get the bet size based upon the KISS I strategy.
	 * @param numDecks  The number of decks in the shoe.
	 * @return  The ratio of the recommended bet size to the minimum bet size.
	 */
	public int getBetSize(int numDecks) {
		int betSize = 1;
		
		if (numDecks <= 2) {
			if (count <= 19) {
				betSize = 1;
			} else if (count == 20) {
				betSize = 2;
			} else if (count == 21) {
				betSize = 4;
			} else if (count >= 22) {
				betSize = 6;
			}
		} else if (4 <= numDecks && numDecks <= 8) {
			if (count <= 19) {
				betSize = 1;
			} else if (count == 20) {
				betSize = 3;
			} else if (count == 21) {
				betSize = 6;
			} else if (count == 22) {
				betSize = 8;
			} else if (count >= 23) {
				betSize = 12;
			}
		}
		
		return betSize;
	}//end method getBetSize
	
	/**
	 * Check to see if the card counter should walk away from an unfavorable table.
	 * @param numDecks  The number of decks used in the shoe.
	 * @return  True if the player should walk away.
	 */
	public boolean walkAway(final int numDecks) {
		boolean walk = false;
		
		if (count <= WALK_AWAY[numDecks]) {
			walk = true;
		}
		
		return walk;
	}

}
