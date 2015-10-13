package casino.blackjack;

import java.util.ArrayList;

import casino.playingCard.PlayingCard;

public class DiscardTray {
    ArrayList<PlayingCard> discardTray;
    
    /**
     * Constructor
     */
    public DiscardTray() {
        discardTray = new ArrayList<PlayingCard>();
    }
    
    public void addCard(final PlayingCard card) {
        discardTray.add(card);
    }
    
    public PlayingCard removeCard() {
        PlayingCard card = null;
        
        if (discardTray.size() > 0) {
            card = discardTray.remove(discardTray.size() - 1);
        }
        
        return card;
    }
    
    public int getNumCards() {
        return discardTray.size();
    }
    
    public String toString() {
        String string = "";
        
        for (int i = 0; i < discardTray.size(); i++) {
            string = string.concat(discardTray.get(i).toString());
        }
        
        return string;
    }

}
