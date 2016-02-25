package unitTestStrategies;

import static org.junit.Assert.assertTrue;
import mockData.DefaultRulesSingleton;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.playingCard.BlackjackCard;
import casino.blackjack.rules.BlackjackRules;
import casino.blackjack.blackjackStrategies.BasicStrategy;
import casino.blackjack.blackjackStrategies.BlackjackStrategy;
import casino.blackjack.blackjackStrategies.CompositionStrategy;
import casino.playingCard.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

public class TestCompositionStrategy {
    private static BlackjackRules rules;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rules = DefaultRulesSingleton.getDefaultRules();
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
        
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
        
        BlackjackStrategy strategy = new BasicStrategy(rules, numDecks);
        strategy = new CompositionStrategy(strategy);
            
        assertTrue(strategy.getAction(dealerUpCard, hand, numDecks) == BlackjackMove.HIT);
    }
}
