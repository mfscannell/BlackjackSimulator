package rules;

import modelObjects.BlackjackHand;
import enumerations.BlackjackMove;

/**
 * This class contains the rules of Blackjack.
 * @author mscannell
 *
 */
public class BlackjackRules {
	/**
	 * the pay out if the player takes insurance and the dealer's hole card is a ten
	 */
	public static final double INSURANCE_PAYOUT = 2.0;
	
	public static final double INSURANCE_BET_SIZE = 0.5;
	
	/**
	 * The pay out if the player double downs and wins
	 */
	public static final double DOUBLE_DOWN_WIN = 2.0;
	/**
	 * The pay out if the player double downs and loses
	 */
	public static final double DOUBLE_DOWN_LOSE = -2.0;
	/**
	 * The pay out if the player loses his hand
	 */
	public static final double HAND_LOSE = -1.0;
	/**
	 * The payout if the player and dealer push
	 */
	public static final double HAND_PUSH = 0;
	/**
	 * The largest hand total before a player busts
	 */
	public static final int MAX_TOTAL = 21;
	
	/**
	 * The number of cards dealt per player in the initial deal.
	 */
	public static final int NUM_CARDS_PER_INITIAL_DEAL = 2;
	
	public static final int DEALER_MIN_HARD_COUNT = 17;
	
	private int maxHands;
	private double blackjackPayout;
	private boolean doubleAfterSplit;
	private boolean dealerHitsSoft17;
	private boolean resplitAces;
	
	/**
	 * Gets the maximum number of hands a player can have after splitting his hands.
	 * @return The maximum number of hands a player can after splits.
	 */
	public int getMaxHands() {
		return maxHands;
	}
	
	/**
	 * Gets the payout factor when a player has blackjack.
	 * @return
	 */
	public double getBlackjackPayout() {
		return blackjackPayout;
	}
	
	/**
	 * Check if a player can double-down after splitting a hand.
	 * @return
	 */
	public boolean canDoubleAfterSplit() {
		return doubleAfterSplit;
	}
	
	/**
	 * Check if the dealer must hit soft 17s.
	 * @return  True if the dealer must hit soft 17.
	 */
	public boolean canDealerHitSoft17() {
		return dealerHitsSoft17;
	}
	
	/**
	 * Check if a player can re-split aces.
	 * @return  True if a player can re-split aces.
	 */
	public boolean canResplitAces() {
		return resplitAces;
	}
	
	/**
	 * Gets the required move the dealer must make depending on her hand.
	 * @param dealersHand  The dealer's hand.
	 * @return  The required move the dealer must make.
	 */
	public BlackjackMove getDealersMove(final BlackjackHand dealersHand) {
		BlackjackMove dealersMove = BlackjackMove.STAND;
		
		if (dealersHand.getTotal() == 17 && dealersHand.isSoft() && dealerHitsSoft17) {
			dealersMove = BlackjackMove.HIT;
		} else if (dealersHand.getTotal() < DEALER_MIN_HARD_COUNT) {
			dealersMove = BlackjackMove.HIT;
		}
		
		return dealersMove;
	}
	
	/**
	 * Builder class to build a set of rules for blackjack.
	 * @author mscannell
	 *
	 */
	public static class Builder {
		private static int maxHands;
		private static double blackjackPayout = 1.5;
		private static boolean doubleAfterSplit;
		private static boolean dealerHitsSoft17;
		private static boolean resplitAces;
		
		/**
		 * Constructor to create a Rules.Builder instance to create the rules.
		 */
		public Builder() {
		}
		
		/**
		 * Sets the maximum number of hands a player can have after splitting.  Specifying a
		 * maxHands of 4 indicates a player can split 3 times.
		 * @param maxHands The maximum number of hands a player can have after splitting his cards.
		 * @return Rules.Builder
		 */
		public Builder setMaxHands(int maxHands) {
			this.maxHands = maxHands;
			return this;
		}
		
		/**
		 * Sets the payout multiplier if a player has blackjack.  If a player has blackjack, 
		 * he will receive blackjackPayout * his bet size.
		 * @param blackjackPayout  The payout factor a player receives when he has blackjack.
		 * @return Rules.Builder
		 */
		public Builder setBlackjackPayout(double blackjackPayout) {
			this.blackjackPayout = blackjackPayout;
			return this;
		}
		
		/**
		 * Sets the boolean value if a player can double-down on a hand if that hand was split.
		 * @param doubleAfterSplit True if a player can double down on a hand that was split.
		 * @return Rules.Builder
		 */
		public Builder setDoubleAfterSplit(boolean doubleAfterSplit) {
			this.doubleAfterSplit = doubleAfterSplit;
			return this;
		}
		
		/**
		 * Sets the boolean value if the dealer must hit soft 17s.  Soft 17s are hands where the
		 * total is exactly 17, the hand contains an ace, and the value of the ace is 11.
		 * @param dealerHitsSoft17 True if the dealer must hit soft 17.
		 * @return Rules.Builder
		 */
		public Builder setDealerHitsSoft17(boolean dealerHitsSoft17) {
			this.dealerHitsSoft17 = dealerHitsSoft17;
			return this;
		}
		
		/**
		 * Sets the boolean value if a player can re-split aces.  Resplitting aces is when a player
		 * was initially dealt two aces, the player splits the aces, and then one of those split
		 * aces receives an ace as a second card.
		 * @param resplitAces  True if a previously split ace can be resplit if its second card
		 * is an ace.
		 * @return Rules.Builder
		 */
		public Builder setResplitAces(boolean resplitAces) {
			this.resplitAces = resplitAces;
			return this;
		}
		
		/**
		 * Build a Rules instance.
		 * @return Rules instance.
		 */
		public BlackjackRules build() {
			return new BlackjackRules(this);
		}
	}//end Builder class
	
	/**
	 * Constructor using the builder object to return a Rules object.
	 * @param builder
	 */
	private BlackjackRules(Builder builder) {
		this.maxHands = builder.maxHands;
		this.blackjackPayout = builder.blackjackPayout;
		this.doubleAfterSplit = builder.doubleAfterSplit;
		this.dealerHitsSoft17 = builder.dealerHitsSoft17;
		this.resplitAces = builder.resplitAces;
	}

}
