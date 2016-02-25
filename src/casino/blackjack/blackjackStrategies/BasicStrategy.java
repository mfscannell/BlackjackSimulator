package casino.blackjack.blackjackStrategies;

import casino.blackjack.BlackjackHand;
import casino.playingCard.PlayingCard;
import casino.blackjack.rules.BlackjackRules;
import casino.playingCard.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;

/**
 * This class is used to represent the basic strategy in Blackjack that consists of referring to a chart to obtain
 * the recommended move.
 * @author mscannell
 *
 */
public class BasicStrategy extends BlackjackStrategy {
    private BlackjackMove[][] pairChart;
    private BlackjackMove[][] totalChart;
    private BlackjackMove[][] softChart;
    private BlackjackRules rules;
    
    public BasicStrategy() {
        
    }
    
    /**
     * Constructor
     * @param rules  The rules at the table.
     * @param numDecks  The number of decks used in the shoe.
     * @throws InvalidNumDecksException  The number of decks specified must be between
     * 1 and 8.
     */
    public BasicStrategy(final BlackjackRules rules, int numDecks) {
        initialize(rules, numDecks);
    }
    
    @Override
    public void adjustCount(PlayingCard dealtCard) {
        //do nothing
    }
    
    @Override
    public BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand playerHand, int numPlayerHands) {
        BlackjackMove move;
        int dealerCardValue = dealerUpCard.getValue();
        
        if (playerHand.hasMoreThanTwoCards()) {
            move = getMultiCardAction(playerHand, dealerCardValue);
        } else {
            move = getTwoCardAction(playerHand, dealerCardValue);
        }
        
        move = correctActionForSpecialCases(move, playerHand, dealerCardValue, numPlayerHands);
        
        return move;
    }
    
    @Override
    public int getBetSize() {
        return 1;
    }
    
    @Override
    public int getCount() {
        return 0;
    }
    
    @Override
    public boolean getInsuranceAction() {
        return false;
    }
    
    @Override
    public void initialize(BlackjackRules rules, int numDecks) {
        this.pairChart = new BlackjackMove[11][11];
        this.totalChart = new BlackjackMove[22][11];
        this.softChart = new BlackjackMove[11][11];
        this.rules = rules;
        
        updatePairChart(rules, numDecks);
        updateTotalChart(rules, numDecks);
        updateSoftChart(rules, numDecks);
    }
    
    @Override
    public void resetCount() {
        //do nothing
    }
    
    /**
     * Take an initial recommended move and modify it for special cases and scenarios.  These scenarios include but are not
     * limited to trying to double after split when the rules do not permit it, trying to resplit a hand when the maximum number
     * of resplits has been reached.
     * @param move  The original recommended move.
     * @param hand  The player's hand the recommended move is for.
     * @param dealerCardValue  The value of the dealer's exposed card.
     * @param numHands  The number of hands the player has after splits and re-splits.
     * @return  The blackjack move corrected based upon the parameters.
     */
    private BlackjackMove correctActionForSpecialCases(BlackjackMove move, final BlackjackHand hand, int dealerCardValue, int numHands) {
        BlackjackMove correctedMove = move;
        int handTotal = hand.getBlackjackTotal();
        
        if (move == BlackjackMove.SPLIT && numHands >= rules.getMaxHandsAfterSplits()) {
            correctedMove = this.totalChart[handTotal][dealerCardValue];
        }
        
        if (move == BlackjackMove.DOUBLE && hand.wasFromSplit() && !this.rules.isDoubleAfterSplitAllowed()) {
            correctedMove = BlackjackMove.HIT;
        }
        
        if (hand.isPairAces() && hand.wasFromSplit() && !this.rules.isResplitAcesAllowed()) {
            correctedMove = BlackjackMove.STAND;
        }
        
        if (hand.hasCardOfRank(CardRank.ACE) && !hand.isPair() && hand.wasFromSplit()) {
            correctedMove = BlackjackMove.STAND;
        }
        
        return correctedMove;
    }
    
    /**
     * Get the recommended action when the hand has more than two cards.
     * @param hand  The player's multi-card hand to get the recommended action for.
     * @param dealerCardValue  The blackjack value of the dealer's exposed card.
     * @return  The recommended move for a hand that has more than two cards.
     */
    private BlackjackMove getMultiCardAction(final BlackjackHand hand, int dealerCardValue) {
        BlackjackMove move = BlackjackMove.STAND;
        int handTotal = hand.getBlackjackTotal();
        
        if (hand.isSoft()) {
            if (hand.getBlackjackTotal() <= 17) {
                move = BlackjackMove.HIT;
            } else if (hand.getBlackjackTotal() == 18 && 
                       (dealerCardValue == 9 || dealerCardValue == 10 || dealerCardValue == 1)) {
                move = BlackjackMove.HIT;
            }
        } else {
            move = this.totalChart[handTotal][dealerCardValue];
            
            if (move == BlackjackMove.DOUBLE) {
                move = BlackjackMove.HIT;
            }
        }
        
        return move;
    }
    
    /**
     * Get the recommended action when the hand has exactly two cards.
     * @param hand  The player's hand to get the recommended action for.
     * @param dealerCardValue  The blackjack value of the dealer's exposed card.
     * @return  The recommended move for a hand that has exactly two cards.
     */
    private BlackjackMove getTwoCardAction(final BlackjackHand hand, int dealerCardValue) {
        BlackjackMove move = BlackjackMove.STAND;
        int firstCardValue = hand.getFirstCardValue();
        int handTotal = hand.getBlackjackTotal();
        
        if (hand.isPair()) {
            move = this.pairChart[firstCardValue][dealerCardValue];
        } else if (hand.isSoft()) {
            int nonAceValue;
            
            if (hand.isFirstCardAce()) {
                nonAceValue = hand.getSecondCardValue();
            } else {
                nonAceValue = hand.getFirstCardValue();
            }
            
            move = this.softChart[nonAceValue][dealerCardValue];
        } else {
            move = this.totalChart[handTotal][dealerCardValue];
        }
        
        return move;
    }
    
    /**
     * Updates the basic strategy chart for hands that are pairs.
     * @param rules  The table rules to make adjustments to the basic strategy.
     * @param numDecks  The number of decks used in the blackjack shoe.
     */
    private void updatePairChart(final BlackjackRules rules, int numDecks) {
        for (int i = 0; i < 11; i++) {//loop on pair
            for (int j = 0; j < 11; j++) {//loop on dealer's up card value
                switch (i) {//switch on the value of the paired card
                    case 2:
                        if (4 <= j && j <= 7) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else if ((j == 2 || j == 3) && (rules.isDoubleAfterSplitAllowed())) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else if (j == 3 && numDecks == 1) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else {
                            this.pairChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 3:
                        if (4 <= j && j <= 7) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else if ((j == 2 || j == 3) && (rules.isDoubleAfterSplitAllowed())) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else {
                            this.pairChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 4:
                        if ((rules.isDoubleAfterSplitAllowed() || numDecks == 1) && (5 <= j && j <= 6)) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else {
                            this.pairChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 5:
                        if (j == 10 || j == 1) {
                            this.pairChart[i][j] = BlackjackMove.HIT;
                        } else {
                            this.pairChart[i][j] = BlackjackMove.DOUBLE;
                        }
                        break;
                    case 6:
                        if (3 <= j && j <= 6) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else if (j == 2 && rules.isDoubleAfterSplitAllowed()) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else if (j == 2 && numDecks <= 2) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else {
                            this.pairChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 7:
                        if (j == 10 && numDecks == 1) {
                            this.pairChart[i][j] = BlackjackMove.STAND;
                        } else if (2 <= j && j <= 7) {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        } else {
                            this.pairChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 8:
                        pairChart[i][j] = BlackjackMove.SPLIT;
                        break;
                    case 9:
                        if (j == 7 || j == 10 || j == 1) {
                            this.pairChart[i][j] = BlackjackMove.STAND;
                        } else {
                            this.pairChart[i][j] = BlackjackMove.SPLIT;
                        }
                        break;
                    case 10:
                        this.pairChart[i][j] = BlackjackMove.STAND;
                        break;
                    case 1:
                        this.pairChart[i][j] = BlackjackMove.SPLIT;
                        break;
                    default:
                        this.pairChart[i][j] = BlackjackMove.STAND;
                        break;
                }
            }
        }
    }//end method updatePairChart
    
    /**
     * Updates the basic strategy chart for hands that are soft totals.
     * @param rules  The table rules to make adjustments to the basic strategy.
     * @param numDecks  The number of decks used in the blackjack shoe.
     */
    private void updateSoftChart(final BlackjackRules rules, int numDecks) {
        for (int i = 0; i < 11; i++) {//loop on Non-ace
            for (int j = 0; j < 11; j++) {//loop on dealer's up card
                switch (i) {//switch on the value of the non-ace card.
                case 1:
                    this.softChart[i][j] = BlackjackMove.SPLIT;
                    break;
                case 2:
                    if (5 <= j && j <= 6) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else if (j == 4 && numDecks == 1) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else {
                        this.softChart[i][j] = BlackjackMove.HIT;
                    }
                    break;
                case 3:
                    if (5 <= j && j <= 6) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else if (j == 4 && numDecks <= 2) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else {
                        this.softChart[i][j] = BlackjackMove.HIT;
                    }
                    break;
                case 4:
                    if (4 <= j && j <= 6) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else {
                        this.softChart[i][j] = BlackjackMove.HIT;
                    }
                    break;
                case 5:
                    if (4 <= j && j <= 6) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else {
                        this.softChart[i][j] = BlackjackMove.HIT;
                    }
                    break;
                case 6:
                    if (j == 2 && numDecks == 1) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else if (3 <= j && j <= 6) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else {
                        this.softChart[i][j] = BlackjackMove.HIT;
                    }
                    break;
                case 7:
                    if (3 <= j && j <= 6) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else if (j == 2 || j == 7 || j == 8) {
                        this.softChart[i][j] = BlackjackMove.STAND;
                    } else {
                        this.softChart[i][j] = BlackjackMove.HIT;
                    }
                    break;    
                case 8:
                    if ((rules.mustDealerHitSoft17() || numDecks == 1) && j == 6) {
                        this.softChart[i][j] = BlackjackMove.DOUBLE;
                    } else {
                        this.softChart[i][j] = BlackjackMove.STAND;
                    }
                    break;
                case 9:
                    this.softChart[i][j] = BlackjackMove.STAND;
                    break;
                case 10:
                    this.softChart[i][j] = BlackjackMove.STAND;
                    break;
                default:
                    this.softChart[i][j] = BlackjackMove.STAND;
                    break;
                }
            }
        }
    }//end method updateSoftChart
    
    /**
     * Updates the basic strategy chart for hands based on the hand total.
     * @param rules  The table rules to make adjustments to the basic strategy.
     * @param numDecks  The number of decks used in the blackjack shoe.
     */
    private void updateTotalChart(final BlackjackRules rules, int numDecks) {
        for (int i = 0; i < 22; i++) {//loop on Total
            for (int j = 0; j < 11; j++) {//loop on dealer's up card
                switch (i) {//switch on the hand total
                    case 1:
                        this.totalChart[i][j] = BlackjackMove.HIT;
                        break;
                    case 2:
                        this.totalChart[i][j] = BlackjackMove.HIT;
                        break;
                    case 3:
                        this.totalChart[i][j] = BlackjackMove.HIT;
                        break;
                    case 4:
                        this.totalChart[i][j] = BlackjackMove.HIT;
                        break;
                    case 5:
                        this.totalChart[i][j] = BlackjackMove.HIT;
                        break;
                    case 6:
                        this.totalChart[i][j] = BlackjackMove.HIT;
                        break;
                    case 7:
                        this.totalChart[i][j] = BlackjackMove.HIT;
                        break;
                    case 8:
                        if (numDecks == 1 && j == 5) {
                            this.totalChart[i][j] = BlackjackMove.DOUBLE;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 9:
                        if (3 <= j && j <= 6) {
                            this.totalChart[i][j] = BlackjackMove.DOUBLE;
                        } else if (j == 2 && (numDecks <= 2)) {
                            this.totalChart[i][j] = BlackjackMove.DOUBLE;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 10:
                        if (2 <= j && j <= 9) {
                            this.totalChart[i][j] = BlackjackMove.DOUBLE;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 11:
                        if (rules.mustDealerHitSoft17() || numDecks <= 2) {
                            this.totalChart[i][j] = BlackjackMove.DOUBLE;
                        } else if (j != 1) {
                            this.totalChart[i][j] = BlackjackMove.DOUBLE;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 12:
                        if (4 <= j && j <= 6) {
                            this.totalChart[i][j] = BlackjackMove.STAND;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 13:
                        if (2 <= j && j <= 6) {
                            this.totalChart[i][j] = BlackjackMove.STAND;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 14:
                        if (2 <= j && j <= 6) {
                            this.totalChart[i][j] = BlackjackMove.STAND;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 15:
                        if (2 <= j && j <= 6) {
                            this.totalChart[i][j] = BlackjackMove.STAND;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 16:
                        if (2 <= j && j <= 6) {
                            this.totalChart[i][j] = BlackjackMove.STAND;
                        } else {
                            this.totalChart[i][j] = BlackjackMove.HIT;
                        }
                        break;
                    case 17:
                        this.totalChart[i][j] = BlackjackMove.STAND;
                        break;
                    case 18:
                        this.totalChart[i][j] = BlackjackMove.STAND;
                        break;
                    case 19:
                        this.totalChart[i][j] = BlackjackMove.STAND;
                        break;
                    case 20:
                        this.totalChart[i][j] = BlackjackMove.STAND;
                        break;
                    case 21:
                        this.totalChart[i][j] = BlackjackMove.STAND;
                        break;
                    default:
                        this.totalChart[i][j] = BlackjackMove.STAND;
                        break;
                }
            }
        }
    }//end method updateTotalChart
}
