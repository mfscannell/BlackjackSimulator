package unitTestStrategies.basicStrategy.totalChart;

import static org.junit.Assert.assertTrue;
import mockData.BlackjackHandHard;
import mockData.DefaultRulesSingleton;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.BlackjackCard;
import rules.BlackjackRules;
import blackjackStrategies.BasicStrategy;
import blackjackStrategies.BlackjackStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;

public class Total16 {
    private static BlackjackRules rules;
    private static BlackjackHand hand;
    private static BlackjackPlayer player;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rules = DefaultRulesSingleton.getDefaultRules();
        hand = BlackjackHandHard.getHandHard16();
        player = new BlackjackPlayer(0);
        player.resetBaseStrategy(BlackjackStrategy.BASIC_STRATEGY);
    }

    @Test
    public void testTotal16vs2() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal16vs3() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal16vs4() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal16vs5() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal16vs6() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void testTotal16vs7() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal16vs8() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal16vs9() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal16vs10() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.KING, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal16vsA() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
}
