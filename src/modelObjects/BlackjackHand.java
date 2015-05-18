package modelObjects;

import java.util.ArrayList;

import enumerations.CardRank;
import exceptions.InvalidMoveException;

public class BlackjackHand {
	private ArrayList<PlayingCard> cards;
	private boolean containsAce;
	private int total;
	private boolean fromSplit;
	private boolean doubleDown;
	
	/**
	 * Constructor.  Creates a new blackjack hand that consists of no cards.
	 */
	public BlackjackHand() {
		containsAce = false;
		total = 0;
		cards = new ArrayList<PlayingCard>();	/**
		 * Checks if the hand contains at least one ace.
		 * @return   True if at least one of the cards is an ace.
		 */
		fromSplit = false;
		doubleDown = false;
	}
	
	/**
	 * Constructor.  Creates a new blackjack hand that consists of the card.
	 * @param card  The first card of the new hand.
	 * @param fromSplit  True if the hand was created from another hand that was split.
	 */
	public BlackjackHand(final PlayingCard card, boolean fromSplit) {
		cards = new ArrayList<PlayingCard>();
		cards.add(card);
		total = card.getValue();
		
		if (card.isAce()) {
			containsAce = true;
		} else {
			containsAce = false;
		}
		this.fromSplit = fromSplit;
		doubleDown = false;
	}
	
	public void addCard(final PlayingCard card) {
		total = total + card.getValue();
		
		if (card.isAce()) {
			containsAce = true;
		} else if (cards.size() == 0) {
			containsAce = false;
		}
		
		cards.add(card);
	}
	
	/**
	 * Remove the last card from the hand.
	 * @return  The last card in the hand.
	 */
	public PlayingCard removeCard() {
		PlayingCard card = null;
		
		if (cards.size() > 0) {
			card = cards.remove(cards.size() - 1);
			resetTotal();
		}
		
		if (cards.size() == 0) {
			containsAce = false;
		}
		
		return card;
	}
	
	public int getNumCards() {
		return cards.size();
	}
	
	/**
	 * Gets the total value of the hand.
	 * @return The total value of the hand.
	 */
	public int getBlackjackTotal() {
		int total = this.total;
		
		if (total <= 11 && containsAce) {
			total = total + 10;
		}
		
		return total;
	}
	
	public boolean isBlackjack() {
		boolean blackjack = false;
		
		if (getNumCards() == 2 && getBlackjackTotal() == 21 && !wasFromSplit()) {
			blackjack = true;
		}
		
		return blackjack;
	}
	
	public boolean isBust() {
		boolean bust = false;
		
		if (total > 21) {
			bust = true;
		}
		
		return bust;
	}
	
	/**
	 * Checks if the hand is a pair.  A pair consists of only two cards.
	 * @return  True if the hand is a pair.
	 */
	public boolean isPair() {
		boolean pair = false;
		if (cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank()) {
			pair = true;
		}
		
		return pair;
	}
	
	/**
	 * Checks if the hand is a pair of aces.  A pair of aces consists of only two cards
	 * and both the cards are aces.
	 * @return  True if the hand is a pair of aces.
	 */
	public boolean isPairAces() {
		boolean pairAces = false;
		
		if (cards.size() == 2 && 
			cards.get(0).getRank() == CardRank.ACE && 
			cards.get(1).getRank() == CardRank.ACE) {
			pairAces = true;
		}
		
		return pairAces;
	}
	
	public boolean hasExactlyTwoCards() {
		boolean twoCards = false;
		
		if (cards.size() == 2) {
			twoCards = true;
		}
		
		return twoCards;
	}
	
	/**
	 * Checks if the hand has more than two cards in it.
	 * @return  True if the hand consists of more than two cards.
	 */
	public boolean isMultiCard() {
		boolean multiCard = false;
		
		if (cards.size() > 2) {
			multiCard = true;
		}
		
		return multiCard;
	}
	
	/**
	 * Checks if the hand contains only two cards and one card is rank1 and the other card
	 * is rank2.
	 * @param rank1  The rank of the first card.
	 * @param rank2  The rank of the second card.
	 * @return  True if the hand contains only two cards and they are rank1 and rank2.
	 */
	public boolean isHand(CardRank rank1, CardRank rank2) {
		boolean handTrue = false;
		
		if (getNumCards() == 2 && 
			((cards.get(0).getRank() == rank1 && cards.get(1).getRank() == rank2) || 
			 (cards.get(0).getRank() == rank2 && cards.get(1).getRank() == rank1))) {
			handTrue = true;
		}
		
		return handTrue;
	}
	
	/**
	 * Checks if the hand is a soft hand.  Soft hands are those that contain an ace and the 
	 * hand total is 21 or less when the ace is counted as a value of 11.
	 * @return  True if the hand is a soft hand.
	 */
	public boolean isSoft() {
		boolean softTotal = false;
		
		if (total <= 11 && containsAce) {
			softTotal = true;
		}
		
		return softTotal;
	}
	
	/**
	 * Checks if the hand is splitable.  A hand is splitable if it is a pair, and the number
	 * of hands a player has after splits/resplits is less than the maximum number of hands
	 * a player can have after splits/resplits.
	 * @param maxHands  The maximum number of hands a player can have after splits/resplits.
	 * @param numHands  The number of hands a player currently has based upon the number 
	 * of splits/resplits.
	 * @param resplitAces  True if resplitting aces is allowed.
	 * @return  True if the hand can be split based upon the rules.
	 */
	public boolean isSplitable(int maxHands, int numHands, boolean resplitAces) {
		boolean splitable = false;
		
		if (isPairAces() && resplitAces && numHands < maxHands) {
			splitable = true;
		} else if (isPair() && numHands < maxHands) {
			splitable = true;
		}
		
		return splitable;
	}
	
	/**
	 * Checks if the first card dealt in the hand is an ace.
	 * @return  True if the first card dealt in the hand is an ace.
	 */
	public boolean isFirstCardAce() {
		boolean upCardAce = false;
		
		if (cards.get(0).isAce()) {
			upCardAce = true;
		}
		
		return upCardAce;
	}
	
	/**
	 * Get the value of the first card dealt in the hand.  The value ranges from 2 to 10
	 * for numbered cards, 10 for face cards, and 1 for aces.
	 * @return  The value of the first card dealt in the hand.
	 */
	public int getFirstCardValue() {
		return cards.get(0).getValue();
	}
	
	/**
	 * Get the value of the second card dealt in the hand.  The value ranges from 2 to 10
	 * for numbered cards, 10 for face cards, and 1 for aces.
	 * @return  The value of the second card dealt in the hand.
	 */
	public int getSecondCardValue() {
		return cards.get(1).getValue();
	}
	
	/**
	 * Inquire the first card dealt in the hand.
	 * @return
	 */
	public final PlayingCard getFirstCard() {
		return cards.get(0);
	}
	
	/**
	 * Inquire the second card dealt in the hand.
	 * @return
	 */
	public final PlayingCard getSecondCard() {
		return cards.get(1);
	}
	
	public boolean hasAce() {
		return containsAce;
	}
	
	public boolean hasCardOfRank(CardRank rank) {
		boolean hasRank = false;
		
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getRank() == rank) {
				hasRank = true;
				break;
			}
		}
		
		return hasRank;
	}//end method hasCard
	
	/**
	 * Splits the hand by removing one of the cards and returns it to the caller.
	 * 
	 * @return  The second card in the hand after splitting the hand.
	 * @precondition The method call BlackjackHand.isSplitable() returns true.
	 */
	public PlayingCard split() {
		PlayingCard returnCard = null;
		
		returnCard = cards.remove(1);
		fromSplit = true;
		
		resetTotal();
		
		return returnCard;
	}
	
	/**
	 * Sets whether or not the had was from a split hand.
	 * @param fromSplit  True if the hand originated from a split hand.
	 */
	public void setFromSplit(boolean fromSplit) {
		this.fromSplit = fromSplit;
	}
	
	/**
	 * Checks if the hand was created from a previously split hand.
	 * @return  True if the hand was not the original hand dealt to the player.
	 */
	public boolean wasFromSplit() {
		return fromSplit;
	}
	
	/**
	 * Query if the hand was double downed.
	 * @return  True if the player double downed on the hand.
	 */
	public boolean wasDoubleDown() {
		return doubleDown;
	}
	
	/**
	 * Set whether or not the hand was double downed upon.
	 * @param doubleDown  True if the hand was double downed.
	 */
	public void setWasDoubleDown(boolean doubleDown) {
		this.doubleDown = doubleDown;
	}
	
	private void resetTotal() {
		total = 0;
		
		for (int i = 0; i < cards.size(); i++) {
			total = total + cards.get(i).getValue();
		}
	}
	
	/**
	 * Converts the hand to a string.
	 */
	public String toString() {
		String string = "";
		
		for (int i = 0; i < cards.size(); i++) {
			string = string.concat(cards.get(i).toString());
		}
		
		return string;
	}

}
