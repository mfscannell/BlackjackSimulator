package casino.playingCard;

import rules.BlackjackRules;
import enumerations.CardRank;
import enumerations.CardSuit;

/**
 * This class represents a playing card used in a game of blackjack.
 * @author mscannell
 *
 */
public class BlackjackCard extends PlayingCard {
    private int value;

    /**
     * Constructor
     * @param rank The rank of the card.
     * @param suit The suit of the card.
     */
    public BlackjackCard(CardRank rank, CardSuit suit) {
        super(rank, suit);
        this.value = BlackjackRules.getCardValue(rank);
    }
    
    /**
     * Get the value of the card according to the game of blackjack.
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Returns true if the card is a ten or a face card.
     * @return True if the card is a ten or a face card.
     */
    public boolean isTenValue() {
        boolean tenValue = false;
        
        if (this.rank == CardRank.TEN || this.isFaceCard()) {
            tenValue = true;
        }
        
        return tenValue;
    }
}
