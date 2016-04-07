package unitTest.com.scannell.mark.casino.gambler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.blackjack.enumerations.Strategy;
import com.scannell.mark.casino.blackjack.strategies.BasicStrategy;
import com.scannell.mark.casino.gambler.BlackjackPlayer;
import com.scannell.mark.casino.gambler.Gambler;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

import mockData.com.scannell.mark.casino.blackjack.MockBlackjackHandPairs;
import mockData.com.scannell.mark.casino.blackjack.MockBlackjackRules;

public class TestBlackjackPlayer {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void addStrategyLayer_compositionKISS() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        player.addStrategyLayer(Strategy.COMPOSITION_STRATEGY);
        player.addStrategyLayer(Strategy.KISS_I_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("KISSIStrategy CompositionStrategy BasicStrategy") == 0);
    }
    
    @Test
    public void addStrategyLayer_basic_resets() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        player.addStrategyLayer(Strategy.COMPOSITION_STRATEGY);
        player.addStrategyLayer(Strategy.KISS_I_STRATEGY);
        player.addStrategyLayer(Strategy.BASIC_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("BasicStrategy") == 0);
    }
    
    @Test
    public void adjustCashTotal_2() {
    	Gambler gambler = new BlackjackPlayer(50);
    	BlackjackPlayer blackjackPlayer = new BlackjackPlayer(10);
    	
    	gambler.adjustCashTotal(2);
    	blackjackPlayer.adjustCashTotal(-3);
    	
    	assertTrue(gambler.getCashTotal() == 52);
    	assertTrue(blackjackPlayer.getCashTotal() == 7);
    }
    
    @Test
    public void getAction_basicPair10vs2_stand() {
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BlackjackHand hand = MockBlackjackHandPairs.getPair10();
        BlackjackRules rules = MockBlackjackRules.getDefaultRules();
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.addStrategyLayer(Strategy.BASIC_STRATEGY);
        player.initializeStrategy(rules, 4);
        
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
    }
    
    @Test
    public void getBetAmount_Initial() {
    	Gambler gambler = new BlackjackPlayer(50);
    	BlackjackPlayer blackjackPlayer = new BlackjackPlayer(10);
    	
    	assertTrue(gambler.getCashTotal() == 50);
    	assertTrue(blackjackPlayer.getCashTotal() == 10);
    }
    
    @Test
    public void resetBaseStrategy_basicStrategy() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("BasicStrategy") == 0);
    }
}
