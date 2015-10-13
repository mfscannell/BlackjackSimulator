package casino.playingCard;

import enumerations.CardRank;
import enumerations.CardSuit;

/**
 * Represents a standard playing card found in a deck of 52 cards.
 * @author mscannell
 *
 */
public abstract class PlayingCard {
    protected CardSuit suit;
    protected CardRank rank;
    
    public static final CardSuit[] SUITS = {CardSuit.CLUBS, 
                                            CardSuit.DIAMONDS, 
                                            CardSuit.HEARTS, 
                                            CardSuit.SPADES};
    public static final CardRank[] RANKS = {CardRank.ACE, 
                                            CardRank.TWO,
                                            CardRank.THREE,
                                            CardRank.FOUR,
                                            CardRank.FIVE,
                                            CardRank.SIX,
                                            CardRank.SEVEN,
                                            CardRank.EIGHT,
                                            CardRank.NINE,
                                            CardRank.TEN,
                                            CardRank.JACK,
                                            CardRank.QUEEN,
                                            CardRank.KING};
    public static final int NUMBER_OF_SUITS = 4;
    public static final int NUMBER_OF_RANKS = 13;
    
    /**
     * Constructor
     * @param rank The rank of the card (RANK_ACE, RANK_TWO, etc.)
     * @param suit The suit of the card (SUIT_CLUBS, SUIT_DIAMONDS, etc.)
     */
    public PlayingCard(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public boolean isFaceCard() {
        boolean faceCard = false;
        
        if (rank == CardRank.JACK || rank == CardRank.QUEEN || rank == CardRank.KING) {
            faceCard = true;
        }
        
        return faceCard;
    }
    
    public boolean isAce() {
        boolean ace = false;
        
        if (rank == CardRank.ACE) {
            ace = true;
        }
        
        return ace;
    }
    
    /**
     * Checks if the card is a Heart or a Diamond.
     * @return  True if the card is a red card.
     */
    public boolean isRedCard() {
        boolean redCard = false;
        
        if (suit == CardSuit.HEARTS || suit == CardSuit.DIAMONDS) {
            redCard = true;
        }
        
        return redCard;
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(rank);
        stringBuilder.append(" ");
        stringBuilder.append(suit);
        stringBuilder.append("]");
        
        return stringBuilder.toString();
    }
    
    public abstract int getValue();
    
    public CardRank getRank() {
        return rank;
    }
    
    public CardSuit getSuit() {
        return suit;
    }

}
