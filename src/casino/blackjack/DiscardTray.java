package casino.blackjack;

import java.util.ArrayList;

import casino.playingCard.PlayingCard;

/**
 * This class represents the discard tray at a blackjack table.  The discard tray holds all cards after they have been used
 * prior to them being reinserted into the shoe for reuse.
 * @author mscannell
 *
 */
public class DiscardTray {
    ArrayList<PlayingCard> discardTray;
    
    /**
     * Constructor
     */
    public DiscardTray() {
        this.discardTray = new ArrayList<PlayingCard>();
    }
    
    /**
     * Add a card to the discard tray.
     * @param card  The card to be added to the discard tray.
     */
    public void addCard(final PlayingCard card) {
        this.discardTray.add(card);
    }
    
    /**
     * Get the number of cards found in the discard tray.
     * @return  The number of cards in the discard tray.
     */
    public int getNumCards() {
        return this.discardTray.size();
    }
    
    /**
     * Remove the first card found in the discard tray.
     * @return  The card in the discard tray that is removed.
     */
    public PlayingCard removeCard() {
        PlayingCard card = null;
        
        if (this.discardTray.size() > 0) {
            card = this.discardTray.remove(this.discardTray.size() - 1);
        }
        
        return card;
    }
    
    
    
    public String toString() {
        String string = "";
        
        for (int i = 0; i < this.discardTray.size(); i++) {
            string = string.concat(this.discardTray.get(i).toString());
        }
        
        return string;
    }
}
