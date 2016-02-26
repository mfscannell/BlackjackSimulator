package unitTestStrategies.basicStrategy.pairChart;

import static org.junit.Assert.assertTrue;
import mockData.BlackjackHandPairs;
import mockData.DefaultRulesSingleton;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.BlackjackCard;
import casino.blackjack.strategies.BasicStrategy;
import casino.blackjack.strategies.BlackjackStrategy;
import casino.blackjack.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

/**
 * Tests the Basic Strategy Chart when the player has a pair of 7s and the player has not split his hand 
 * (he only has one hand).
 * @author mscannell
 *
 */
public class Pair7 {
    private static BlackjackRules rules;
    private static BlackjackHand hand;
    private static BlackjackPlayer player;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        rules = DefaultRulesSingleton.getDefaultRules();
        hand = BlackjackHandPairs.getPair7();
        player = new BlackjackPlayer(0);
        player.resetBaseStrategy(BlackjackStrategy.BASIC_STRATEGY);
    }

    @Test
    public void testPair7vs2() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair7vs3() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair7vs4() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair7vs5() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair7vs6() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair7vs7() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair7vs8() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair7vs9() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair7vs10() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair7vsA() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair7vs10SingleDeck() {
        int numDecks = 1;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
}
