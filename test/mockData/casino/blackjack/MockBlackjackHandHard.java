package mockData.casino.blackjack;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

public class MockBlackjackHandHard {
    private static BlackjackHand handHard8;
    private static BlackjackHand handHard9;
    private static BlackjackHand handHard10;
    private static BlackjackHand handHard11;
    private static BlackjackHand handHard12;
    private static BlackjackHand handHard13;
    private static BlackjackHand handHard14;
    private static BlackjackHand handHard15;
    private static BlackjackHand handHard16;
    private static BlackjackHand handHard17;
    private static BlackjackHand handHard18;
    private static BlackjackHand handHard19;
    
    public static BlackjackHand getHandHard8() {
        if (handHard8 == null) {
            handHard8 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.HEARTS);
            handHard8.addCard(firstCard);
            handHard8.addCard(secondCard);
        }
        
        return handHard8;
    }
    
    public static BlackjackHand getHandHard9() {
        if (handHard9 == null) {
            handHard9 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
            handHard9.addCard(firstCard);
            handHard9.addCard(secondCard);
        }
        
        return handHard9;
    }
    
    public static BlackjackHand getHandHard10() {
        if (handHard10 == null) {
            handHard10 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS);
            handHard10.addCard(firstCard);
            handHard10.addCard(secondCard);
        }
        
        return handHard10;
    }
    
    public static BlackjackHand getHandHard11() {
        if (handHard11 == null) {
            handHard11 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
            handHard11.addCard(firstCard);
            handHard11.addCard(secondCard);
        }
        
        return handHard11;
    }
    
    public static BlackjackHand getHandHard12() {
        if (handHard12 == null) {
            handHard12 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS);
            handHard12.addCard(firstCard);
            handHard12.addCard(secondCard);
        }
        
        return handHard12;
    }
    
    public static BlackjackHand getHandHard13() {
        if (handHard13 == null) {
            handHard13 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
            handHard13.addCard(firstCard);
            handHard13.addCard(secondCard);
        }
        
        return handHard13;
    }
    
    public static BlackjackHand getHandHard14() {
        if (handHard14 == null) {
            handHard14 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.HEARTS);
            handHard14.addCard(firstCard);
            handHard14.addCard(secondCard);
        }
        
        return handHard14;
    }
    
    public static BlackjackHand getHandHard15() {
        if (handHard15 == null) {
            handHard15 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.HEARTS);
            handHard15.addCard(firstCard);
            handHard15.addCard(secondCard);
        }
        
        return handHard15;
    }
    
    public static BlackjackHand getHandHard16() {
        if (handHard16 == null) {
            handHard16 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.JACK, CardSuit.HEARTS);
            handHard16.addCard(firstCard);
            handHard16.addCard(secondCard);
        }
        
        return handHard16;
    }
    
    public static BlackjackHand getHandHard17() {
        if (handHard17 == null) {
            handHard17 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.JACK, CardSuit.HEARTS);
            handHard17.addCard(firstCard);
            handHard17.addCard(secondCard);
        }
        
        return handHard17;
    }
    
    public static BlackjackHand getHandHard18() {
        if (handHard18 == null) {
            handHard18 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.JACK, CardSuit.HEARTS);
            handHard18.addCard(firstCard);
            handHard18.addCard(secondCard);
        }
        
        return handHard18;
    }
    
    public static BlackjackHand getHandHard19() {
        if (handHard19 == null) {
            handHard19 = new BlackjackHand();
            BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
            BlackjackCard secondCard = new BlackjackCard(CardRank.JACK, CardSuit.HEARTS);
            handHard19.addCard(firstCard);
            handHard19.addCard(secondCard);
        }
        
        return handHard19;
    }

}
