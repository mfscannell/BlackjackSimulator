package unitTest.casino.blackjack.strategies.basicStrategy.pairChart;

import static org.junit.Assert.assertTrue;

import mockData.casino.blackjack.MockBlackjackHandPairs;
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

/**
 * Tests the Basic Strategy Chart when the player has a pair of 4s and the player has not split his hand 
 * (he only has one hand).
 * @author mscannell
 *
 */
public class Pair4 {
    private static BlackjackRules rules;
    private static BlackjackHand hand;
    private static BlackjackPlayer player;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        rules = MockBlackjackRules.getDefaultRules();
        hand = MockBlackjackHandPairs.getPair4();
        player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
    }

    @Test
    public void testPair4vs2() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs3() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs4() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs5() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs6() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs7() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs8() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs9() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs10() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vsA() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testPair4vs5DAS() {
        int numDecks = 4;
        
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(false);
        BlackjackRules rules = rulesBuilder.build();
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair4vs6DAS() {
        int numDecks = 4;
        
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(false);
        BlackjackRules rules = rulesBuilder.build();
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair4vs5SingleDeck() {
        int numDecks = 1;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPair4vs6SingleDeck() {
        int numDecks = 1;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
}
