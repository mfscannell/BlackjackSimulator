package unitTest.com.scannell.mark.casino.blackjack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.playingCard.PlayingCard;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

public class TestBlackjackHand {
    
    @Test
    public void testGetNumCards_twocards() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
        BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.getNumCards() == 2);
    }
    
    @Test
    public void testGetBlackjackTotal_noAceNoBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
        BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.getBlackjackTotal() == 9);
    }
    
    @Test
    public void testGetBlackjackTotal_noAceBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertTrue(hand.getBlackjackTotal() == 23);
    }
    
    @Test
    public void testGetBlackjackTotal_softAceNoBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.getBlackjackTotal() == 15);
    }
    
    @Test
    public void testGetBlackjackTotal_twoAces() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.getBlackjackTotal() == 12);
    }
    
    @Test
    public void testGetBlackjackTotal_hardAceNoBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertTrue(hand.getBlackjackTotal() == 16);
    }
    
    @Test
    public void testGetBlackjackTotal_hardAceBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        hand.addCard(fourthCard);
        
        assertTrue(hand.getBlackjackTotal() == 25);
    }
    
    @Test
    public void testIsBust_noAce_notBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isBust());
    }
    
    @Test
    public void testIsBust_noAce_bust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertTrue(hand.isBust());
    }
    
    @Test
    public void testIsBust_softAce_noBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertFalse(hand.isBust());
    }
    
    @Test
    public void testIsBust_hardAce_noBust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertFalse(hand.isBust());
    }
    
    @Test
    public void testIsBust_hardAce_bust() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        hand.addCard(fourthCard);
        
        assertTrue(hand.isBust());
    }
    
    @Test
    public void testIsPair_pairSixes_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.isPair());
    }
    
    @Test
    public void testIsPair_twoCards_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isPair());
    }
    
    @Test
    public void testIsPair_oneCard_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        
        assertFalse(hand.isPair());
    }
    
    @Test
    public void testIsPair_manyCards_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.HEARTS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertFalse(hand.isPair());
    }
    
    @Test
    public void testIsPair_aces_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.isPairAces());
    }
    
    @Test
    public void testIsPair_aceThree_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isPairAces());
    }
    
    @Test
    public void testIsPair_manyAces_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertFalse(hand.isPairAces());
    }
    
    @Test
    public void testSplit_pairNines() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        assertTrue(secondHand.getNumCards() == 1 && hand.getNumCards() == 1);
        assertTrue(hand.getBlackjackTotal() == 9);
        assertTrue(secondHand.getBlackjackTotal() == 9);
    }
    
    @Test
    public void testIsSoft_a9_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.isSoft());
    }
    
    @Test
    public void testIsSoft_a37_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertTrue(hand.isSoft());
    }
    
    @Test
    public void testIsSoft_79_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isSoft());
    }
    
    @Test
    public void testIsSoft_a74_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertFalse(hand.isSoft());
    }
    
    @Test
    public void testIsFirstCardAce_ace_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.isFirstCardAce());
    }
    
    @Test
    public void testIsFirstCardAce_k9_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isFirstCardAce());
    }
    
    @Test
    public void testIsFirstCardAce_kA_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isFirstCardAce());
    }
    
    @Test
    public void testHasCardOfRank_9_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.hasCardOfRank(CardRank.NINE));
    }
    
    @Test
    public void testHasCardOfRank_7_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.hasCardOfRank(CardRank.SEVEN));
    }
    
    @Test
    public void testIsBlackjack_kA_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.isBlackjack());
    }
    
    @Test
    public void testIsBlackjack_k9_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isBlackjack());
    }
    
    @Test
    public void testIsBlackjack_aTSplit_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        BlackjackCard thirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.TEN, CardSuit.HEARTS);
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        assertFalse(hand.isBlackjack());
        assertFalse(secondHand.isBlackjack());
    }
    
    @Test
    public void testIsBlackjack_83_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isBlackjack());
    }
    
    @Test
    public void testHasExactlyTwoCards_83_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.hasExactlyTwoCards());
    }
    
    @Test
    public void testHasExactlyTwoCards_839_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertFalse(hand.hasExactlyTwoCards());
    }
    
    @Test
    public void testIsHand_83_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.isHand(CardRank.EIGHT, CardRank.THREE));
        assertTrue(hand.isHand(CardRank.THREE, CardRank.EIGHT));
    }
    
    @Test
    public void testIsHand_83_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertFalse(hand.isHand(CardRank.EIGHT, CardRank.FIVE));
        assertFalse(hand.isHand(CardRank.FIVE, CardRank.EIGHT));
        assertFalse(hand.isHand(CardRank.NINE, CardRank.THREE));
        assertFalse(hand.isHand(CardRank.THREE, CardRank.NINE));
        assertFalse(hand.isHand(CardRank.NINE, CardRank.FIVE));
    }
    
    @Test
    public void testIsHand_839MultiCard_false() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        assertFalse(hand.isHand(CardRank.EIGHT, CardRank.THREE));
        assertFalse(hand.isHand(CardRank.EIGHT, CardRank.NINE));
        assertFalse(hand.isHand(CardRank.THREE, CardRank.EIGHT));
        assertFalse(hand.isHand(CardRank.THREE, CardRank.NINE));
        assertFalse(hand.isHand(CardRank.NINE, CardRank.THREE));
        assertFalse(hand.isHand(CardRank.NINE, CardRank.EIGHT));
    }
    
    @Test
    public void testGetFirstCard_8_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.getFirstCard().equals(firstCard));
    }
    
    @Test
    public void testGetSecondCard_3_true() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(hand.getSecondCard().equals(secondCard));
    }

}
