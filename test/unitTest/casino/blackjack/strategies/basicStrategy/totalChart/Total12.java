package unitTest.casino.blackjack.strategies.basicStrategy.totalChart;

import static org.junit.Assert.assertTrue;

import mockData.casino.blackjack.MockBlackjackHandHard;
import mockData.casino.blackjack.MockBlackjackRules;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.BlackjackCard;
import casino.blackjack.strategies.BasicStrategy;
import casino.blackjack.strategies.BlackjackStrategy;
import casino.blackjack.enumerations.BlackjackMove;
import casino.blackjack.enumerations.Strategy;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

public class Total12 {
    private static BlackjackRules rules;
    private static BlackjackHand hand;
    private static BlackjackPlayer player;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rules = MockBlackjackRules.getDefaultRules();
        hand = MockBlackjackHandHard.getHandHard12();
        player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
    }

    @Test
    public void testTotal12vs2() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal12vs3() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal12vs4() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal12vs5() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal12vs6() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal12vs7() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal12vs8() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal12vs9() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal12vs10() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.KING, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal12vsA() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }

}
