package blackjackStrategies;

import rules.BlackjackRules;
import modelObjects.BlackjackHand;
import modelObjects.PlayingCard;
import modelObjects.Shoe;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import exceptions.InvalidNumDecksException;
import exceptions.InvalidShoeException;

public class KISSIStrategy extends BlackjackStrategyDecorator {
	BlackjackStrategy blackjackStrategy;
	/**Initial Card Counts at the start of a new shoe based upon the number of decks used. */
	public static final int[] INITIAL_COUNTS = {20, 18, 17, 15, 14, 12, 10, 8, 6};
	public static final int[] WALK_AWAY = {14, 14, 14, 12, 10, 8, 6, 4, 2};
	private int initialCount;
	private int count;
	private BlackjackRules rules;
	private int numDecks;
	public static int KISS_COUNT = 20;

	/**
	 * Constructor
	 * @param rules  The specific rules at the table.
	 * @param numDecks  The number of decks used in the shoe.
	 * @throws InvalidNumDecksException  The number of decks specified must be between
	 * 1 and 8.
	 */
	public KISSIStrategy(BlackjackStrategy blackjackStrategy, BlackjackRules rules, int numDecks) {
		this.blackjackStrategy = blackjackStrategy;
		this.rules = rules;
		this.numDecks = numDecks;
		
		initialCount = INITIAL_COUNTS[numDecks];
		count = initialCount;
	}
	
	/**
	 * Resets the card-counting count to the initial count.
	 */
	public void resetCount() {
		count = initialCount;
	}
	
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
	}
	
	public int getCount() {
		return count;
	}
	
	/**
	 * Refer to the KISS I strategy chart to select a move based upon the table conditions.
	 * @param dealerUpCard  The dealer's exposed card.
	 * @param hand  The player's hand.
	 * @param numHands  The number of hands the player has based upon the number of splits 
	 * and resplits.
	 * @return  The recommended blackjack move.
	 */
	public BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand hand, int numHands) {
		BlackjackMove move = BlackjackMove.STAND;
		
		if (count >= KISS_COUNT) {
			if (hand.getBlackjackTotal() == 9 && !hand.isSoft() && dealerUpCard.getValue() == 2) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.getBlackjackTotal() == 11 && dealerUpCard.isAce()) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.getBlackjackTotal() == 16 && dealerUpCard.getValue() == 10) {
				move = BlackjackMove.STAND;
			} else if (hand.isHand(CardRank.ACE, CardRank.SEVEN) && dealerUpCard.getValue() == 2) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.isHand(CardRank.ACE, CardRank.EIGHT) && dealerUpCard.getValue() == 5) {
				move = BlackjackMove.DOUBLE;
			} else if (hand.isHand(CardRank.ACE, CardRank.EIGHT) && dealerUpCard.getValue() == 6) {
				move = BlackjackMove.DOUBLE;
			} else {
				move = blackjackStrategy.getAction(dealerUpCard, hand, numHands);
			}
		} else {
			move = blackjackStrategy.getAction(dealerUpCard, hand, numHands);
		}
		
		if (move == BlackjackMove.DOUBLE && 
			hand.wasFromSplit() && 
			!rules.isDoubleAfterSplitAllowed()) {
			move = BlackjackMove.HIT;
		}
		
		return move;
	}
	
	/**
	 * Checks KISS I if it recommends the player to take insurance.
	 * @return  True if KISS I recommends to take insurance.
	 */
	public boolean getInsuranceAction() {
		boolean insurance = false;
		
		if (numDecks == 1 && count >= 21) {
			insurance = true;
		} else if (numDecks == 2 && count >= 22) {
			insurance = true;
		} else if (4 <= numDecks && numDecks <= 8 && count >= 25) {
			insurance = true;
		} else {
			insurance = blackjackStrategy.getInsuranceAction();
		}
		
		return insurance;
	}
	
	/**
	 * Get the bet size based upon the KISS I strategy.
	 * @return  The ratio of the recommended bet size to the minimum bet size.
	 */
	public int getBetSize() {
		int betSize = 1;
		
		if (numDecks <= 2) {
			if (count <= 19) {
				betSize = 1;
			} else if (count == 20) {
				betSize = 2;
			} else if (count == 21) {
				betSize = 4;
			} else if (count >= 22) {
				betSize = 4;
			}
		} else if (4 <= numDecks && numDecks <= 8) {
			if (count <= 19) {
				betSize = 1;
			} else if (count == 20) {
				betSize = 2;
			} else if (count == 21) {
				betSize = 4;
			} else if (count >= 22) {
				betSize = 6;
			}
		}
		
		return betSize;
	}
	
	/**
	 * Check to see if the card counter should walk away from an unfavorable table.
	 * @return  True if the player should walk away.
	 */
	public boolean shouldWalkAway() {
		boolean walk = false;
		
		if (count <= WALK_AWAY[numDecks]) {
			walk = true;
		}
		
		return walk;
	}

}
