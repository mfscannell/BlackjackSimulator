package unitTest.casino.gambler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.enumerations.Strategy;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.BlackjackCard;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

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
    public void testResetBaseStrategy() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("BasicStrategy") == 0);
    }
    
    @Test
    public void testAddStrategyLayerCompositionKISS() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        player.addStrategyLayer(Strategy.COMPOSITION_STRATEGY);
        player.addStrategyLayer(Strategy.KISS_I_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("KISSIStrategy CompositionStrategy BasicStrategy") == 0);
    }
    
    @Test
    public void testAddStrategyLayerBasicResets() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        player.addStrategyLayer(Strategy.COMPOSITION_STRATEGY);
        player.addStrategyLayer(Strategy.KISS_I_STRATEGY);
        player.addStrategyLayer(Strategy.BASIC_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("BasicStrategy") == 0);
    }
}
