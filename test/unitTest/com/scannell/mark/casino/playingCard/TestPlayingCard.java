package unitTest.com.scannell.mark.casino.playingCard;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.playingCard.PlayingCard;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;


public class TestPlayingCard {

    @Test
    public void isAce_ace_true() {
        PlayingCard card = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        assertTrue(card.isAce());
    }
    
    @Test
    public void isAce_two_false() {
        PlayingCard card = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        assertFalse(card.isAce());
    }
    
    @Test
    public void isFaceCard_jack_true() {
        PlayingCard card = new BlackjackCard(CardRank.JACK, CardSuit.CLUBS);
        assertTrue(card.isFaceCard());
    }
    
    @Test
    public void isFaceCard_five_false() {
        PlayingCard card = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        assertFalse(card.isFaceCard());
    }

}
