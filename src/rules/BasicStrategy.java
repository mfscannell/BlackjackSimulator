package rules;

import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;
import modelObjects.BlackjackTable;
import modelObjects.PlayingCard;
import modelObjects.Shoe;

public class BasicStrategy {
	private BlackjackMove[][] pairChart;
	private BlackjackMove[][] totalChart;
	private BlackjackMove[][] softChart;
	
	/**
	 * Constructor
	 * @param rules  The rules at the table.
	 * @param numDecks  The number of decks used in the shoe.
	 * @throws InvalidNumDecksException  The number of decks specified must be between
	 * 1 and 8.
	 */
	public BasicStrategy(final BlackjackRules rules, int numDecks) throws InvalidNumDecksException {
		
		if (Shoe.MIN_NUM_DECKS <= numDecks && numDecks <= Shoe.MAX_NUM_DECKS) {
			pairChart = new BlackjackMove[11][11];
			totalChart = new BlackjackMove[22][11];
			softChart = new BlackjackMove[11][11];
		
			updatePairChart(rules, numDecks);
			updateTotalChart(rules, numDecks);
			updateSoftChart(rules, numDecks);
		} else {
			throw new InvalidNumDecksException("" + numDecks + " is invalid number of decks");
		}
	}//end constructor
	
	/**
	 * Updates the basic strategy chart for hands that are pairs.
	 * @param rules  The table rules to make adjustments to the basic strategy.
	 * @param numDecks  The number of decks used in the blackjack shoe.
	 */
	private void updatePairChart(final BlackjackRules rules, int numDecks) {
		for (int i = 0; i < 11; i++) {//loop on pair
			for (int j = 0; j < 11; j++) {//loop on dealer's up card value
				switch (i) {//switch on the value of the paired card
				case 2:		if (4 <= j && j <= 7) {
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else if ((j == 2 || j == 3) && (rules.canDoubleAfterSplit())){
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else if (j == 3 && numDecks == 1) {
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else {
								pairChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 3:		if (4 <= j && j <= 7) {
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else if ((j == 2 || j == 3) && (rules.canDoubleAfterSplit())){
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else {
								pairChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 4:		if ((rules.canDoubleAfterSplit() || numDecks == 1) && (5 <= j && j <= 6)) {
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else {
								pairChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 5:		if (j == 10 || j == 1) {
								pairChart[i][j] = BlackjackMove.HIT;
							} else {
								pairChart[i][j] = BlackjackMove.DOUBLE;
							}
							break;
				case 6:		if (3 <= j && j <= 6) {
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else if (j == 2 && (rules.canDoubleAfterSplit() || numDecks <= 2)) {
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else {
								pairChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 7:		if (j == 10 && numDecks == 1) {
								pairChart[i][j] = BlackjackMove.STAND;
							} else if (2 <= j && j <= 7) {
								pairChart[i][j] = BlackjackMove.SPLIT;
							} else {
								pairChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 8:		pairChart[i][j] = BlackjackMove.SPLIT;
							break;
				case 9:		if (j == 7 || j == 10 || j == 1) {
								pairChart[i][j] = BlackjackMove.STAND;
							} else {
								pairChart[i][j] = BlackjackMove.SPLIT;
							}
							break;
				case 10:	pairChart[i][j] = BlackjackMove.STAND;
							break;
				case 1:		pairChart[i][j] = BlackjackMove.DOUBLE;
							break;
				default:	pairChart[i][j] = BlackjackMove.STAND;
							break;
				}
			}
		}
	}//end method updatePairChart
	
	/**
	 * Updates the basic strategy chart for hands based on the hand total.
	 * @param rules  The table rules to make adjustments to the basic strategy.
	 * @param numDecks  The number of decks used in the blackjack shoe.
	 */
	private void updateTotalChart(final BlackjackRules rules, int numDecks) {
		for (int i = 0; i < 22; i++) {//loop on Total
			for (int j = 0; j < 11; j++) {//loop on dealer's up card
				switch (i) {//switch on the hand total
				case 1:		totalChart[i][j] = BlackjackMove.HIT;
							break;
				case 2:		totalChart[i][j] = BlackjackMove.HIT;
							break;
				case 3:		totalChart[i][j] = BlackjackMove.HIT;
							break;
				case 4:		totalChart[i][j] = BlackjackMove.HIT;
							break;
				case 5:		totalChart[i][j] = BlackjackMove.HIT;
							break;
				case 6:		totalChart[i][j] = BlackjackMove.HIT;
							break;
				case 7:		totalChart[i][j] = BlackjackMove.HIT;
							break;
				case 8:		if (numDecks == 1 && j == 5) {
								totalChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 9:		if (3 <= j && j <= 6) {
								totalChart[i][j] = BlackjackMove.DOUBLE;
							} else if (j == 2 && (numDecks <= 2)) {
								totalChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 10:	if (2 <= j && j <= 9) {
								totalChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 11:	if (rules.canDealerHitSoft17() || numDecks <= 2) {
								totalChart[i][j] = BlackjackMove.DOUBLE;
							} else if (j != 1) {
								totalChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 12:	if (4 <= j && j <= 6) {
								totalChart[i][j] = BlackjackMove.STAND;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 13:	if (2 <= j && j <= 6) {
								totalChart[i][j] = BlackjackMove.STAND;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 14:	if (2 <= j && j <= 6) {
								totalChart[i][j] = BlackjackMove.STAND;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 15:	if (2 <= j && j <= 6) {
								totalChart[i][j] = BlackjackMove.STAND;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 16:	if (2 <= j && j <= 6) {
								totalChart[i][j] = BlackjackMove.STAND;
							} else {
								totalChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 17:	totalChart[i][j] = BlackjackMove.STAND;
							break;
				case 18:	totalChart[i][j] = BlackjackMove.STAND;
							break;
				case 19:	totalChart[i][j] = BlackjackMove.STAND;
							break;
				case 20:	totalChart[i][j] = BlackjackMove.STAND;
							break;
				case 21:	totalChart[i][j] = BlackjackMove.STAND;
							break;
				default:	totalChart[i][j] = BlackjackMove.STAND;
							break;
				}
			}
		}
	}//end method updateTotalChart
	
	/**
	 * Updates the basic strategy chart for hands that are soft totals.
	 * @param rules  The table rules to make adjustments to the basic strategy.
	 * @param numDecks  The number of decks used in the blackjack shoe.
	 */
	private void updateSoftChart(final BlackjackRules rules, int numDecks) {
		for (int i = 0; i < 11; i++) {//loop on Non-ace
			for (int j = 0; j < 11; j++) {//loop on dealer's up card
				switch (i) {//switch on the value of the non-ace card.
				case 1:		softChart[i][j] = BlackjackMove.SPLIT;
							break;
				case 2:		if (5 <= j && j <= 6) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else if (j == 4 && numDecks == 1) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								softChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 3:		if (5 <= j && j <= 6) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else if (j == 4 && numDecks <= 2) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								softChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 4:		if (4 <= j && j <= 6) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								softChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 5:		if (4 <= j && j <= 6) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								softChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 6:		if (3 <= j && j <= 6) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								softChart[i][j] = BlackjackMove.HIT;
							}
							break;
				case 7:		if (3 <= j && j <= 6) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else if (j == 2 || j == 7 || j == 8) {
								softChart[i][j] = BlackjackMove.STAND;
							} else {
								softChart[i][j] = BlackjackMove.HIT;
							}
							break;	
				case 8:		if ((rules.canDealerHitSoft17() || numDecks == 1) && j == 6) {
								softChart[i][j] = BlackjackMove.DOUBLE;
							} else {
								softChart[i][j] = BlackjackMove.STAND;
							}
							break;
				case 9:		softChart[i][j] = BlackjackMove.STAND;
							break;
				case 10:	softChart[i][j] = BlackjackMove.STAND;
							break;
				default:	softChart[i][j] = BlackjackMove.STAND;
							break;
				}
			}
		}
	}//end method updateSoftChart
	
	/**
	 * Refer to the basic strategy chart to select a moinsuranceve based upon the table conditions.
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
		BlackjackMove move;
		int handTotal = hand.getTotal();
		int dealerCardValue = dealerUpCard.getValue();
		
		//acquire the recommended move
		if (hand.isMultiCard()) {
			if (hand.isSoft()) {
				if (hand.getTotal() <= 17) {
					move = BlackjackMove.HIT;
				} else {
					move = BlackjackMove.STAND;
				}
			} else {
				move = totalChart[handTotal][dealerCardValue];
			}
		} else {
			if (hand.isPair()) {
				
				move = pairChart[hand.getFirstCardValue()][dealerCardValue];
			} else if (hand.isSoft()) {
				int nonAceValue;
				
				if (hand.isFirstCardAce()) {
					nonAceValue = hand.getSecondCardValue();
				} else {
					nonAceValue = hand.getFirstCardValue();
				}
				
				move = softChart[nonAceValue][dealerCardValue];
			} else {
				move = totalChart[handTotal][dealerCardValue];
			}
		}
		
		//correct the recommended move based upon special cases
		if (hand.isMultiCard() && move == BlackjackMove.DOUBLE) {
			move = BlackjackMove.HIT;
		}
		
		if (move == BlackjackMove.SPLIT && numHands >= rules.getMaxHands()) {
			move = totalChart[handTotal][dealerCardValue];
		}
		
		if (move == BlackjackMove.DOUBLE && 
			hand.getNumCards() == 2 && 
			hand.wasFromSplit() && 
			!rules.canDoubleAfterSplit()) {
			move = BlackjackMove.HIT;
		}
		
		if (hand.isPairAces() && 
			hand.wasFromSplit() && 
			(!rules.canResplitAces() || numHands >= rules.getMaxHands())) {
			move = BlackjackMove.STAND;
		}
		
		if (hand.isFirstCardAce() && hand.wasFromSplit()) {
			move = BlackjackMove.STAND;
		}
		
		return move;
	}//end method getAction
	
	/**
	 * Get the recommend insurance move when it is offered.
	 * @return  True if the player should take insurance.
	 */
	public boolean getInsuranceMove() {
		return false;
	}
	
	public void printPairChart() {
		for (int i = 0; i < pairChart.length; i++) {
			System.out.printf("%2d: ", i);
			for (int j = 1; j < pairChart[i].length; j++) {
				if (i == 0) {
					System.out.printf("%3d", j);
				} else {
					System.out.printf("%3s", pairChart[i][j].toString());
				}
			}
			System.out.println("");
		}
	}
	
	public void printTotalChart() {
		for (int i = 0; i < totalChart.length; i++) {
			System.out.printf("%2d: ", i);
			for (int j = 1; j < totalChart[i].length; j++) {
				if (i == 0) {
					System.out.printf("%3d", j);
				} else {
					System.out.printf("%3s", totalChart[i][j].toString());
				}
			}
			System.out.println("");
		}
	}
	
	public void printSoftChart() {
		for (int i = 0; i < softChart.length; i++) {
			System.out.printf("%2d: ", i);
			for (int j = 1; j < softChart[i].length; j++) {
				if (i == 0) {
					System.out.printf("%3d", j);
				} else {
					System.out.printf("%3s", softChart[i][j].toString());
				}
			}
			System.out.println("");
		}
	}

}
