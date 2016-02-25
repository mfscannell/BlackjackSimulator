package unitTestModelObjects;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import casino.playingCard.BlackjackCard;
import casino.playingCard.PlayingCard;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;


public class TestPlayingCard {

    @Test
    public void testIsAce() {
        PlayingCard card = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        assertTrue(card.isAce());
    }
    
    @Test
    public void testIsNotAce() {
        PlayingCard card = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        assertFalse(card.isAce());
    }
    
    @Test
    public void testIsFaceCard() {
        PlayingCard card = new BlackjackCard(CardRank.JACK, CardSuit.CLUBS);
        assertTrue(card.isFaceCard());
    }
    
    @Test
    public void testIsNotFaceCard() {
        PlayingCard card = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        assertTrue(!card.isFaceCard());
    }

}
