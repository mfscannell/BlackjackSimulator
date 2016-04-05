package unitTest.com.scannell.mark.casino.blackjack.strategies;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.blackjack.strategies.BasicStrategy;
import com.scannell.mark.casino.blackjack.strategies.BlackjackStrategy;
import com.scannell.mark.casino.blackjack.strategies.CompositionStrategy;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;
import mockData.com.scannell.mark.casino.blackjack.MockBlackjackRules;

public class TestCompositionStrategy {
    private static BlackjackRules rules;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rules = MockBlackjackRules.getDefaultRules();
    }

    @Test
    public void getAction_102vs4_hit() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.HIT);
    }
    
    @Test
    public void getAction_210vs4_hit() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.HIT);
    }
    
    @Test
    public void getAction_102vs6_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.STAND);
    }
    
    @Test
    public void getAction_210vs6_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.STAND);
    }
    
    @Test
    public void getAction_84vs4_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.STAND);
    }
    
    @Test
    public void getAction_hard16with4vs10_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.JACK, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.STAND);
    }
    
    @Test
    public void getAction_hard16with5vs10_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.QUEEN, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.STAND);
    }
    
    @Test
    public void getAction_soft16with4vs10_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.JACK, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.HIT);
    }
    
    @Test
    public void getAction_soft16with5vs10_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.JACK, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.HIT);
    }
    
    @Test
    public void getAction_16vs10without4or5_hit() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.QUEEN, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.HIT);
    }
    
    @Test
    public void getAction_16vsnon10with5_hit() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        
        int numDecks = 2;
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
            
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.HIT);
    }
    
    @Test
    public void getBetSize_1() {
    	int numDecks = 6;
    	
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
            
        assertTrue(strategy.getBetSize() == 1);
    }
    
    @Test
    public void getCount_0() {
    	int numDecks = 6;
    	
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
            
        assertTrue(strategy.getCount() == 0);
    }
    
    @Test
    public void getInsuranceAction_false() {
    	int numDecks = 6;
    	
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new CompositionStrategy(strategy);
        strategy.initialize(rules, numDecks);
            
        assertFalse(strategy.getInsuranceAction());
    }
}
