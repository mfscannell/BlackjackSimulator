package rules;

import java.util.HashMap;

import casino.blackjack.BlackjackHand;
import enumerations.BlackjackMove;
import enumerations.CardRank;

/**
 * This class contains the rules of Blackjack.
 * @author mscannell
 *
 */
public class BlackjackRules {
    public static final double INSURANCE_BET_SIZE = 0.5;
    public static final double PAYOUT_INSURANCE = 2.0;
    public static final double PAYOUT_DOUBLE_DOWN_WIN = 2.0;
    public static final double PAYOUT_DOUBLE_DOWN_LOSE = -2.0;
    public static final double PAYOUT_HAND_LOSE = -1.0;
    public static final double PAYOUT_HAND_PUSH = 0;
    public static final double PAYOUT_HAND_WIN = 1;
    public static final int MAX_TOTAL_BEFORE_BUST = 21;
    public static final int NUM_CARDS_PER_INITIAL_DEAL = 2;
    public static final int DEALER_MIN_HARD_COUNT = 17;
    
    private int maxHands;
    private double blackjackPayoutMultiple;
    private boolean doubleAfterSplitAllowed;
    private boolean dealerHitsSoft17;
    private boolean resplitAces;
    private static HashMap<CardRank, Integer> blackjackCardValues;
    
    /**
     * Get the value of a card for a specific rank of a card.  The rank of a card is either clubs, diamonds, hearts, or
     * spades.
     * @param rank  The rank of a card to get its value.
     * @return  The value of a card according to the rules of blackjack.
     */
    public static int getCardValue(CardRank rank) {
        int value = blackjackCardValues.get(rank);
        
        return value;
    }
    
    /**
     * Get the maximum number of hands allowed after splits and re-splits of a hand or hands.
     * @return  The maximum number of hands allowed after splits.
     */
    public int getMaxHandsAfterSplits() {
        return this.maxHands;
    }
    
    /**
     * Get the ratio of the payout when a player has blackjack.
     * @return  The payout ratio when a player has blackjack.
     */
    public double getBlackjackPayoutMultiple() {
        return this.blackjackPayoutMultiple;
    }
    
    /**
     * Refer to the rules as to whether or not double down of a hand after it has been split is allowed.
     * @return  True if double down of a hand after it has been split is allowed.
     */
    public boolean isDoubleAfterSplitAllowed() {
        return this.doubleAfterSplitAllowed;
    }
    
    /**
     * Refer to the rules as to whether or not the dealer must hit when he has a soft 17.  An example of soft 17 is Ace-6.
     * @return  True if the dealer must hit when he has a soft 17.
     */
    public boolean mustDealerHitSoft17() {
        return this.dealerHitsSoft17;
    }
    
    public boolean isResplitAcesAllowed() {
        return this.resplitAces;
    }
    
    public BlackjackMove getDealersMove(final BlackjackHand dealersHand) {
        BlackjackMove dealersMove = BlackjackMove.STAND;
        
        if (dealersHand.getBlackjackTotal() == 17 && dealersHand.isSoft() && this.dealerHitsSoft17) {
            dealersMove = BlackjackMove.HIT;
        } else if (dealersHand.getBlackjackTotal() < DEALER_MIN_HARD_COUNT) {
            dealersMove = BlackjackMove.HIT;
        }
        
        return dealersMove;
    }
    
    public double getPayoutAdjustment(BlackjackHand hand, BlackjackHand dealerHand, int numPlayerHands) {
        double payoutAdjustment = PAYOUT_HAND_PUSH;
        
        if (hand.isBlackjack() && !dealerHand.isBlackjack() && numPlayerHands == 1) {
            payoutAdjustment = this.blackjackPayoutMultiple;
        } else if (hand.isBlackjack() && dealerHand.isBlackjack() && numPlayerHands == 1) {
            payoutAdjustment = PAYOUT_HAND_PUSH;
        } else if (!hand.isBlackjack() && dealerHand.isBlackjack() && numPlayerHands == 1) {
            payoutAdjustment = PAYOUT_HAND_LOSE;
        } else if (hand.wasDoubleDown() && !hand.isBust() && hand.getBlackjackTotal() > dealerHand.getBlackjackTotal()) {
            payoutAdjustment = PAYOUT_DOUBLE_DOWN_WIN;
        } else if (hand.wasDoubleDown() && !hand.isBust() && dealerHand.isBust()) {
            payoutAdjustment = PAYOUT_DOUBLE_DOWN_WIN;
        } else if (hand.wasDoubleDown() && hand.isBust()) {
            payoutAdjustment = PAYOUT_DOUBLE_DOWN_LOSE;
        } else if (hand.wasDoubleDown() && hand.getBlackjackTotal() < dealerHand.getBlackjackTotal() && !dealerHand.isBust()) {
            payoutAdjustment = PAYOUT_DOUBLE_DOWN_LOSE;
        } else if (hand.isBust()) {
            payoutAdjustment = PAYOUT_HAND_LOSE;
        } else if (hand.getBlackjackTotal() < dealerHand.getBlackjackTotal() && !dealerHand.isBust()) {
            payoutAdjustment = PAYOUT_HAND_LOSE;
        } else if (!hand.isBust() && dealerHand.isBust()) {
            payoutAdjustment = PAYOUT_HAND_WIN;
        } else if (hand.getBlackjackTotal() > dealerHand.getBlackjackTotal() && !hand.isBust()) {
            payoutAdjustment = PAYOUT_HAND_WIN;
        } else if (hand.getBlackjackTotal() == dealerHand.getBlackjackTotal() && !hand.isBust() && !hand.isBlackjack()) {
            payoutAdjustment = PAYOUT_HAND_PUSH;
        }
        
        return payoutAdjustment;
    }
    
    /**
     * Builder class to build a set of rules for blackjack.
     * @author mscannell
     *
     */
    public static class Builder {
        private static int maxHands = 4;
        private static double blackjackPayoutMultiple = 1.5;
        private static boolean doubleAfterSplitAllowed = true;
        private static boolean dealerHitsSoft17 = true;
        private static boolean resplitAces = true;
        
        public Builder() {
        }
        
        public Builder setMaxHandsAfterSplits(int maxHands) {
            this.maxHands = maxHands;
            return this;
        }
        
        public Builder setBlackjackPayoutMultiple(double blackjackPayout) {
            this.blackjackPayoutMultiple = blackjackPayout;
            return this;
        }
        
        public Builder setDoubleAfterSplitAllowed(boolean doubleAfterSplitAllowed) {
            this.doubleAfterSplitAllowed = doubleAfterSplitAllowed;
            return this;
        }
        
        public Builder setDealerHitsSoft17(boolean dealerHitsSoft17) {
            this.dealerHitsSoft17 = dealerHitsSoft17;
            return this;
        }
        
        public Builder setCanResplitAces(boolean resplitAces) {
            this.resplitAces = resplitAces;
            return this;
        }
        
        public BlackjackRules build() {
            return new BlackjackRules(this);
        }
    }//end Builder class
    
    private BlackjackRules(Builder builder) {
        this.maxHands = builder.maxHands;
        this.blackjackPayoutMultiple = builder.blackjackPayoutMultiple;
        this.doubleAfterSplitAllowed = builder.doubleAfterSplitAllowed;
        this.dealerHitsSoft17 = builder.dealerHitsSoft17;
        this.resplitAces = builder.resplitAces;
        
        this.blackjackCardValues = new HashMap<CardRank, Integer>();
        this.blackjackCardValues.put(CardRank.ACE, 1);
        this.blackjackCardValues.put(CardRank.TWO, 2);
        this.blackjackCardValues.put(CardRank.THREE, 3);
        this.blackjackCardValues.put(CardRank.FOUR, 4);
        this.blackjackCardValues.put(CardRank.FIVE, 5);
        this.blackjackCardValues.put(CardRank.SIX, 6);
        this.blackjackCardValues.put(CardRank.SEVEN, 7);
        this.blackjackCardValues.put(CardRank.EIGHT, 8);
        this.blackjackCardValues.put(CardRank.NINE, 9);
        this.blackjackCardValues.put(CardRank.TEN, 10);
        this.blackjackCardValues.put(CardRank.JACK, 10);
        this.blackjackCardValues.put(CardRank.QUEEN, 10);
        this.blackjackCardValues.put(CardRank.KING, 10);
    }
}
