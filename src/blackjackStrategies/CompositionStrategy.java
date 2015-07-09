package blackjackStrategies;

import modelObjects.BlackjackHand;
import modelObjects.PlayingCard;
import enumerations.BlackjackMove;
import enumerations.CardRank;

public class CompositionStrategy extends BlackjackStrategyDecorator {
	BlackjackStrategy blackjackStrategy;

	/**
	 * Constructor
	 * @param rules  The specific rules at the table.
	 * @param numDecks  The number of decks used in the shoe.
	 * @throws InvalidNumDecksException  The number of decks specified must be between
	 * 1 and 8.
	 */
	public CompositionStrategy(BlackjackStrategy blackjackStrategy) {
		this.blackjackStrategy = blackjackStrategy;
	}
	
	/**
	 * Refer to the basic strategy chart to select a move based upon the table conditions.
	 * @param dealerUpCard  The dealer's exposed card.
	 * @param hand  The player's hand.
	 * @param numHands  The number of hands the player has based upon the number of splits 
	 * and resplits.
	 * @return  The recommended blackjack move.
	 */
	public BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand hand, int numHands) {
		BlackjackMove move = BlackjackMove.HIT;
		
		if (hand.getBlackjackTotal() == 16 && 
			(hand.hasCardOfRank(CardRank.FOUR) || hand.hasCardOfRank(CardRank.FIVE)) &&
			dealerUpCard.getValue() == 10) {
			move = BlackjackMove.STAND;
		} else if (hand.isHand(CardRank.TEN, CardRank.TWO) && dealerUpCard.getValue() == 4) {
			move = BlackjackMove.HIT;
		} else {
			move = blackjackStrategy.getAction(dealerUpCard, hand, numHands);
		}
		
		return move;
	}
	
	public int getBetSize() {
		return 1;
	}
	
	public void resetCount() {
		//do nothing
	}
	
	public int getCount() {
		return blackjackStrategy.getCount();
	}
	
	public boolean getInsuranceAction() {
		return blackjackStrategy.getInsuranceAction();
	}
	
	public void adjustCount(PlayingCard dealtCard) {
		blackjackStrategy.adjustCount(dealtCard);
	}

}
