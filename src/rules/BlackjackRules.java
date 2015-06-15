package rules;

import java.util.HashMap;

import modelObjects.BlackjackHand;
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
	
	public static int getCardValue(CardRank rank) {
		int value = blackjackCardValues.get(rank);
		
		return value;
	}
	
	public int getMaxHandsAfterSplits() {
		return maxHands;
	}
	
	public double getBlackjackPayoutMultiple() {
		return blackjackPayoutMultiple;
	}
	
	public boolean isDoubleAfterSplitAllowed() {
		return doubleAfterSplitAllowed;
	}
	
	public boolean mustDealerHitSoft17() {
		return dealerHitsSoft17;
	}
	
	public boolean isResplitAcesAllowed() {
		return resplitAces;
	}
	
	public BlackjackMove getDealersMove(final BlackjackHand dealersHand) {
		BlackjackMove dealersMove = BlackjackMove.STAND;
		
		if (dealersHand.getBlackjackTotal() == 17 && dealersHand.isSoft() && dealerHitsSoft17) {
			dealersMove = BlackjackMove.HIT;
		} else if (dealersHand.getBlackjackTotal() < DEALER_MIN_HARD_COUNT) {
			dealersMove = BlackjackMove.HIT;
		}
		
		return dealersMove;
	}
	
	public double getPayoutAdjustment(BlackjackHand hand, BlackjackHand dealerHand) {
		double payoutAdjustment = 1.0;
		
		if (hand.wasDoubleDown() && !hand.isBust() && hand.getBlackjackTotal() > dealerHand.getBlackjackTotal()) {
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
		}
		
		return payoutAdjustment;
	}
	
	/**
	 * Builder class to build a set of rules for blackjack.
	 * @author mscannell
	 *
	 */
	public static class Builder {
		private static int maxHands;
		private static double blackjackPayoutMultiple = 1.5;
		private static boolean doubleAfterSplitAllowed;
		private static boolean dealerHitsSoft17;
		private static boolean resplitAces;
		
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
		
		blackjackCardValues = new HashMap<CardRank, Integer>();
		blackjackCardValues.put(CardRank.ACE, 1);
		blackjackCardValues.put(CardRank.TWO, 2);
		blackjackCardValues.put(CardRank.THREE, 3);
		blackjackCardValues.put(CardRank.FOUR, 4);
		blackjackCardValues.put(CardRank.FIVE, 5);
		blackjackCardValues.put(CardRank.SIX, 6);
		blackjackCardValues.put(CardRank.SEVEN, 7);
		blackjackCardValues.put(CardRank.EIGHT, 8);
		blackjackCardValues.put(CardRank.NINE, 9);
		blackjackCardValues.put(CardRank.TEN, 10);
		blackjackCardValues.put(CardRank.JACK, 10);
		blackjackCardValues.put(CardRank.QUEEN, 10);
		blackjackCardValues.put(CardRank.KING, 10);
	}
}
