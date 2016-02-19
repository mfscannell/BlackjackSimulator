package casino.blackjack;

import java.util.ArrayList;

import casino.playingCard.PlayingCard;
import enumerations.CardRank;

/**
 * A hand owned by a blackjack player at a game of blackjack.
 * @author mscannell
 *
 */
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
        this.containsAce = false;
        this.total = 0;
        this.cards = new ArrayList<PlayingCard>();
        this.fromSplit = false;
        this.doubleDown = false;
    }
    
    /**
     * Constructor.  Creates a new blackjack hand that consists of the card.  This hand was
     * split from a hand containing the card being added to the hand.
     * @param card  The first card of the new hand.
     */
    private BlackjackHand(final PlayingCard card) {
        this.cards = new ArrayList<PlayingCard>();
        this.cards.add(card);
        this.total = card.getValue();
        this.containsAce = false;
        this.fromSplit = true;
        this.doubleDown = false;
        
        if (card.isAce()) {
            this.containsAce = true;
        }
    }
    
    /**
     * Add a card to the hand.
     * @param card
     */
    public void addCard(final PlayingCard card) {
        this.total = this.total + card.getValue();
        
        if (card.isAce()) {
            this.containsAce = true;
        } else if (cards.size() == 0) {
            this.containsAce = false;
        }
        
        cards.add(card);
    }
    
    /**
     * Gets the total value of the hand.
     * @return The total value of the hand.
     */
    public int getBlackjackTotal() {
        int total = this.total;
        
        if (total <= 11 && this.containsAce) {
            total = total + 10;
        }
        
        return total;
    }
    
    /**
     * Inquire the first card dealt in the hand.
     * @return
     */
    public final PlayingCard getFirstCard() {
        return this.cards.get(0);
    }
    
    /**
     * Get the value of the first card dealt in the hand.  The value ranges from 2 to 10
     * for numbered cards, 10 for face cards, and 1 for aces.
     * @return  The value of the first card dealt in the hand.
     */
    public int getFirstCardValue() {
        return this.cards.get(0).getValue();
    }
    
    /**
     * Get the number of cards found in the hand.
     * @return  The number of cards in the hand.
     */
    public int getNumCards() {
        return this.cards.size();
    }
    
    /**
     * Inquire the second card dealt in the hand.
     * @return
     */
    public final PlayingCard getSecondCard() {
        return this.cards.get(1);
    }
    
    /**
     * Get the value of the second card dealt in the hand.  The value ranges from 2 to 10
     * for numbered cards, 10 for face cards, and 1 for aces.
     * @return  The value of the second card dealt in the hand.
     */
    public int getSecondCardValue() {
        return this.cards.get(1).getValue();
    }
    
    /**
     * Checks if the hand has at least one card of a specified rank (Ace, King, 9, etc.).
     * @param rank  The specified rank.
     * @return  True if the hand has at least one card of a specified rank.
     */
    public boolean hasCardOfRank(CardRank rank) {
        boolean hasRank = false;
        
        for (int i = 0; i < this.cards.size(); i++) {
            if (this.cards.get(i).getRank() == rank) {
                hasRank = true;
                break;
            }
        }
        
        return hasRank;
    }
    
    /**
     * Checks if the hand has exactly two cards.
     * @return  True if the hand has exactly two cards.
     */
    public boolean hasExactlyTwoCards() {
        boolean twoCards = false;
        
        if (this.cards.size() == 2) {
            twoCards = true;
        }
        
        return twoCards;
    }
    
    /**
     * Checks if the hand has more than two cards.
     * @return  True if the hand has more than two cards.
     */
    public boolean hasMoreThanTwoCards() {
        boolean multiCard = false;
        
        if (this.cards.size() > 2) {
            multiCard = true;
        }
        
        return multiCard;
    }
    
    /**
     * Checks if the hand is a blackjack.  A blackjack is a hand of exactly two cards whose total is exactly 21 and
     * the hand was not split from another hand.
     * @return  True if the hand is a blackjack.
     */
    public boolean isBlackjack() {
        boolean blackjack = false;
        
        if (getNumCards() == 2 && getBlackjackTotal() == 21 && !wasFromSplit()) {
            blackjack = true;
        }
        
        return blackjack;
    }
    
    /**
     * Checks if the hand is a bust in blackjack.  A bust is when the hand's total is greater than 21.
     * @return
     */
    public boolean isBust() {
        boolean bust = false;
        
        if (this.total > 21) {
            bust = true;
        }
        
        return bust;
    }
    
    /**
     * Checks if the first card dealt in the hand is an ace.
     * @return  True if the first card dealt in the hand is an ace.
     */
    public boolean isFirstCardAce() {
        boolean upCardAce = false;
        
        if (this.cards.get(0).isAce()) {
            upCardAce = true;
        }
        
        return upCardAce;
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
            ((this.cards.get(0).getRank() == rank1 && this.cards.get(1).getRank() == rank2) || 
             (this.cards.get(0).getRank() == rank2 && this.cards.get(1).getRank() == rank1))) {
            handTrue = true;
        }
        
        return handTrue;
    }
    
    /**
     * Checks if the hand has exactly two cards and both cards are of the same rank.
     * @return  True if the hand is a pair.
     */
    public boolean isPair() {
        boolean pair = false;
        if (this.cards.size() == 2 && this.cards.get(0).getRank() == this.cards.get(1).getRank()) {
            pair = true;
        }
        
        return pair;
    }
    
    /**
     * Checks if the hand has exactly two cards and both cards are aces.
     * @return  True if the hand is a pair of aces.
     */
    public boolean isPairAces() {
        boolean pairAces = false;
        
        if (this.cards.size() == 2 && 
            this.cards.get(0).getRank() == CardRank.ACE && 
            this.cards.get(1).getRank() == CardRank.ACE) {
            pairAces = true;
        }
        
        return pairAces;
    }
    
    /**
     * Checks if the hand is a soft hand.  Soft hands are those that contain an ace and the 
     * hand total is 21 or less when the ace is counted as a value of 11.
     * @return  True if the hand is a soft hand.
     */
    public boolean isSoft() {
        boolean softTotal = false;
        
        if (this.total <= 11 && this.containsAce) {
            softTotal = true;
        }
        
        return softTotal;
    }
    
    /**
     * Remove the last card from the hand.
     * @return  The last card in the hand.
     */
    public PlayingCard removeCard() {
        PlayingCard card = null;
        
        if (this.cards.size() > 0) {
            card = this.cards.remove(this.cards.size() - 1);
            resetTotal();
        }
        
        if (this.cards.size() == 0) {
            this.containsAce = false;
        }
        
        return card;
    }
    
    /**
     * Set whether or not the hand was double downed upon.
     * @param doubleDown  True if the hand was double downed.
     */
    public void setWasDoubleDown(boolean doubleDown) {
        this.doubleDown = doubleDown;
    }
    
    /**
     * Splits the hand by removing one of the cards and returns a new hand only containing the split card.
     * 
     * @return  The second hand created from splitting the hand.
     * @precondition The method call BlackjackHand.isSplitable() returns true.
     */
    public BlackjackHand split() {
        PlayingCard returnCard = null;
        
        returnCard = this.cards.remove(1);
        this.fromSplit = true;
        
        resetTotal();
        
        return new BlackjackHand(returnCard);
    }
    
    /**
     * Checks if the hand was created from a previously split hand.
     * @return  True if the hand was not the original hand dealt to the player.
     */
    public boolean wasFromSplit() {
        return this.fromSplit;
    }
    
    /**
     * Query if the hand was double downed.
     * @return  True if the player double downed on the hand.
     */
    public boolean wasDoubleDown() {
        return this.doubleDown;
    }
    
    /**
     * Converts the hand to a string.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < this.cards.size(); i++) {
            stringBuilder.append(this.cards.get(i).toString());
        }
        
        return stringBuilder.toString();
    }
    
    /**
     * Reset the blackjack total value of the hand.
     */
    private void resetTotal() {
        this.total = 0;
        
        for (int i = 0; i < this.cards.size(); i++) {
            this.total = this.total + this.cards.get(i).getValue();
        }
    }
}
