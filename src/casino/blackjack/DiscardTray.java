package casino.blackjack;

import java.util.ArrayList;

import casino.playingCard.PlayingCard;

public class DiscardTray {
    ArrayList<PlayingCard> discardTray;
    
    /**
     * Constructor
     */
    public DiscardTray() {
        this.discardTray = new ArrayList<PlayingCard>();
    }
    
    public void addCard(final PlayingCard card) {
        this.discardTray.add(card);
    }
    
    public PlayingCard removeCard() {
        PlayingCard card = null;
        
        if (this.discardTray.size() > 0) {
            card = this.discardTray.remove(this.discardTray.size() - 1);
        }
        
        return card;
    }
    
    public int getNumCards() {
        return this.discardTray.size();
    }
    
    public String toString() {
        String string = "";
        
        for (int i = 0; i < this.discardTray.size(); i++) {
            string = string.concat(this.discardTray.get(i).toString());
        }
        
        return string;
    }
}
