package com.scannell.mark.casino.blackjack;

import java.util.HashMap;

import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;

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
    
    /**
     * Get the ratio of the payout when a player has blackjack.
     * @return  The payout ratio when a player has blackjack.
     */
    public double getBlackjackPayoutMultiple() {
        return this.blackjackPayoutMultiple;
    }
    
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
     * Refer to the rules regarding the move the dealer is required to make.  The dealer is required to hit all hands with a 
     * total less than 17.  The dealer must hit soft 17 if the rules state he must.
     * @param dealersHand  The dealer's hand.
     * @return  The move the dealer is required to make.
     */
    public BlackjackMove getDealersMove(final BlackjackHand dealersHand) {
        BlackjackMove dealersMove = BlackjackMove.STAND;
        
        if (dealersHand.getBlackjackTotal() == 17 && dealersHand.isSoft() && this.dealerHitsSoft17) {
            dealersMove = BlackjackMove.HIT;
        } else if (dealersHand.getBlackjackTotal() < DEALER_MIN_HARD_COUNT) {
            dealersMove = BlackjackMove.HIT;
        }
        
        return dealersMove;
    }
    
    /**
     * Get the maximum number of hands allowed after splits and re-splits of a hand or hands.
     * @return  The maximum number of hands allowed after splits.
     */
    public int getMaxHandsAfterSplits() {
        return this.maxHands;
    }
    
    /**
     * Get the ratio of how much the player's chip stack will be adjusted based upon his hand and the dealer's hand.  The player
     * will either push (not have his chips adjusted), lose (lose his bet), win (win an amount equal to his bet), win a double 
     * down (win an amount equal to twice his bet), lose a double down (lose twice his bet), or get a blackjack (win an amount
     * equal to the blackjack payout multiple).
     * @param hand  The player's hand.
     * @param dealerHand  The dealer's hand.
     * @param numPlayerHands  The number of hands the player has.
     * @return  The ratio of how much the player's chip stack will be adjusted.
     */
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
     * Refer to the rules as to whether or not double down of a hand after it has been split is allowed.
     * @return  True if double down of a hand after it has been split is allowed.
     */
    public boolean isDoubleAfterSplitAllowed() {
        return this.doubleAfterSplitAllowed;
    }
    
    /**
     * Checks if the maximum number of hands after splitting and re-splitting a player's hand has been reached.
     * @param numHands The number of hands the player has after splitting and re-splitting his hands.
     * @return True if the maximum number of hands has been reached.
     */
    public boolean isMaxHandsAfterSplitsReached(int numHands) {
        boolean maxHandsReached = false;
        
        if (numHands >= maxHands) {
            maxHandsReached = true;
        }
        
        return maxHandsReached;
    }
    
    /**
     * Refer to the rules as to whether or not re-splitting a pair of aces is allowed.
     * @return  True if the player can resplit aces.
     */
    public boolean isResplitAcesAllowed() {
        return this.resplitAces;
    }
    
    /**
     * Refer to the rules as to whether or not the dealer must hit when he has a soft 17.  An example of soft 17 is Ace-6.
     * @return  True if the dealer must hit when he has a soft 17.
     */
    public boolean mustDealerHitSoft17() {
        return this.dealerHitsSoft17;
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
    
    
}
