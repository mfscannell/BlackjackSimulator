package unitTest.casino.blackjack.strategies.basicStrategy.totalChart;

import static org.junit.Assert.assertTrue;

import mockData.casino.blackjack.MockBlackjackHandHard;
import mockData.casino.blackjack.MockBlackjackRules;

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

public class Total11 {
    private static BlackjackRules rules;
    private static BlackjackHand hand;
    private static BlackjackPlayer player;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rules = MockBlackjackRules.getDefaultRules();
        hand = MockBlackjackHandHard.getHandHard11();
        player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
    }

    @Test
    public void testTotal11vs2() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs3() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs4() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs5() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs6() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs7() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs8() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs9() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vs10() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.KING, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vsA() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void testTotal11vsADealerHitSoft17() {
        int numDecks = 4;
        
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(true);
        rulesBuilder.setDoubleAfterSplitAllowed(false);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(false);
        BlackjackRules rules = rulesBuilder.build();
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vsADoubleDeck() {
        int numDecks = 2;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testTotal11vsASingleDeck() {
        int numDecks = 1;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy();
        strategy.initialize(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
}
