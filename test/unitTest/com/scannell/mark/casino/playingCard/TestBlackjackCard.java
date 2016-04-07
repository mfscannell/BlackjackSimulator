package unitTest.com.scannell.mark.casino.playingCard;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;


public class TestBlackjackCard {
    private BlackjackRules.Builder blackjackRulesBuilder = new BlackjackRules.Builder();
    private BlackjackRules blackjackRules = blackjackRulesBuilder.build();

    @Test
    public void isTenValue_ten_true() {
        BlackjackCard blackjackCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
        assertTrue(blackjackCard.isTenValue());
    }
    
    @Test
    public void isTenValue_jack_true() {
        BlackjackCard blackjackCard = new BlackjackCard(CardRank.JACK, CardSuit.HEARTS);
        assertTrue(blackjackCard.isTenValue());
    }
    
    @Test
    public void isTenValue_queen_true() {
        BlackjackCard blackjackCard = new BlackjackCard(CardRank.QUEEN, CardSuit.HEARTS);
        assertTrue(blackjackCard.isTenValue());
    }
    
    @Test
    public void isTenValue_king_true() {
        BlackjackCard blackjackCard = new BlackjackCard(CardRank.KING, CardSuit.DIAMONDS);
        assertTrue(blackjackCard.isTenValue());
    }
    
    @Test
    public void isTenValue_ace_false() {
        BlackjackCard blackjackCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        assertFalse(blackjackCard.isTenValue());
    }
}
