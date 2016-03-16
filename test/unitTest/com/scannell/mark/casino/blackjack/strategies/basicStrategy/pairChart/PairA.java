package unitTest.com.scannell.mark.casino.blackjack.strategies.basicStrategy.pairChart;

import static org.junit.Assert.assertTrue;

import mockData.com.scannell.mark.casino.blackjack.MockBlackjackHandPairs;
import mockData.com.scannell.mark.casino.blackjack.MockBlackjackRules;

import org.junit.BeforeClass;
import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.gambler.BlackjackPlayer;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.blackjack.strategies.BasicStrategy;
import com.scannell.mark.casino.blackjack.strategies.BlackjackStrategy;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.blackjack.enumerations.Strategy;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

/**
 * Tests the Basic Strategy Chart when the player has a pair of Aces and the player has not split his hand 
 * (he only has one hand).
 * @author mscannell
 *
 */
public class PairA {
    private static BlackjackRules rules;
    private static BlackjackHand hand;
    private static BlackjackPlayer player;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        rules = MockBlackjackRules.getDefaultRules();
        hand = MockBlackjackHandPairs.getPairA();
        player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
    }

    @Test
    public void testPairAvs2() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs3() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs4() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs5() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs6() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs7() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs8() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs9() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvs10() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testPairAvsA() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
    }

}
