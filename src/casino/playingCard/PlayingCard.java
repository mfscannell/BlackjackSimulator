package casino.playingCard;

import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

/**
 * Represents a standard playing card found in a deck of 52 cards.
 * @author mscannell
 *
 */
public abstract class PlayingCard {
    protected CardSuit suit;
    protected CardRank rank;
    
    public static final CardSuit[] SUITS = {
        CardSuit.CLUBS, 
        CardSuit.DIAMONDS, 
        CardSuit.HEARTS, 
        CardSuit.SPADES
    };
    public static final CardRank[] RANKS = {
        CardRank.ACE, 
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
        CardRank.KING
    };
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
    
    /**
     * Get the rank (Ace, King, Queen, etc.) of the playing card.
     * @return  The rank of the playing card.
     */
    public CardRank getRank() {
        return this.rank;
    }
    
    /**
     * Get the suit (clubs, diamonds, hearts, spades) of the playing card.
     * @return  The suit of the playing card.
     */
    public CardSuit getSuit() {
        return this.suit;
    }
    
    /**
     * Get the value of the playing card as determined by the game being played.
     * @return  The value of the playing card.
     */
    public abstract int getValue();
    
    /**
     * Returns true if the card is an ace.
     * @return True if the card is an ace.
     */
    public boolean isAce() {
        boolean ace = false;
        
        if (this.rank == CardRank.ACE) {
            ace = true;
        }
        
        return ace;
    }
    
    /**
     * Returns true if the card is a face card.
     * @return True if the card is a face card.
     */
    public boolean isFaceCard() {
        boolean faceCard = false;
        
        if (this.rank == CardRank.JACK || this.rank == CardRank.QUEEN || this.rank == CardRank.KING) {
            faceCard = true;
        }
        
        return faceCard;
    }
    
    /**
     * Checks if the card is a Heart or a Diamond.
     * @return  True if the card is a red card.
     */
    public boolean isRedCard() {
        boolean redCard = false;
        
        if (this.suit == CardSuit.HEARTS || this.suit == CardSuit.DIAMONDS) {
            redCard = true;
        }
        
        return redCard;
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.rank);
        stringBuilder.append(" ");
        stringBuilder.append(this.suit);
        stringBuilder.append("]");
        
        return stringBuilder.toString();
    }
}
