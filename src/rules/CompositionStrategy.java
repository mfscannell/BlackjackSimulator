package rules;

import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import exceptions.InvalidNumDecksException;

public class CompositionStrategy extends BasicStrategy {

	/**
	 * Constructor
	 * @param rules  The specific rules at the table.
	 * @param numDecks  The number of decks used in the shoe.
	 * @throws InvalidNumDecksException  The number of decks specified must be between
	 * 1 and 8.
	 */
	public CompositionStrategy(BlackjackRules rules, int numDecks) throws InvalidNumDecksException {
		super(rules, numDecks);
	}
	
	/**
	 * Refer to the basic strategy chart to select a move based upon the table conditions.
	 * @param dealerUpCard  The dealer's exposed card.
	 * @param hand  The player's hand.
	 * @param rules  The specific rules at the table.
	 * @param numHands  The number of hands the player has based upon the number of splits 
	 * and resplits.
	 * @return  The recommended blackjack move.
	 */
	public BlackjackMove getAction(final BlackjackCard dealerUpCard, 
								   final BlackjackHand hand,
								   final BlackjackRules rules,
								   int numHands) {
		BlackjackMove move = BlackjackMove.HIT;
		
		if (hand.getTotal() == 16 && 
			(hand.hasCard(CardRank.FOUR) || hand.hasCard(CardRank.FIVE)) &&
			dealerUpCard.getValue() == 10) {
			move = BlackjackMove.STAND;
		} else if (hand.getTotal() == 12 && 
				   ((hand.getFirstCardValue() == 10 && hand.getSecondCardValue() == 2) || 
					(hand.getFirstCardValue() == 2 && hand.getSecondCardValue() == 10)) && 
					dealerUpCard.getValue() == 4) {
			move = BlackjackMove.HIT;
		} else {
			move = super.getAction(dealerUpCard, hand, rules, numHands);
		}
		
		return move;
	}//end method getAction

}
