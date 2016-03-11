package unitTest.casino.blackjack.strategies;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.playingCard.BlackjackCard;
import casino.blackjack.strategies.BasicStrategy;
import casino.blackjack.strategies.BlackjackStrategy;
import casino.blackjack.strategies.CompositionStrategy;
import casino.blackjack.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;
import mockData.casino.blackjack.MockBlackjackRules;

public class TestCompositionStrategy {
    private static BlackjackRules rules;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rules = MockBlackjackRules.getDefaultRules();
    }

    @Test
    public void test102vs4() {
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
    public void test210vs4() {
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
    public void test102vs6() {
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
    public void test210vs6() {
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
    public void test84vs4() {
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
    public void test16vs10with4() {
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
    public void test16vs10with5() {
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
    public void test16vs10without45() {
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
    public void test16vsnon10with5() {
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
}
