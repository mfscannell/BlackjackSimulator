package unitTest.com.scannell.mark.casino.gambler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.enumerations.Strategy;
import com.scannell.mark.casino.gambler.BlackjackPlayer;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

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
    public void testResetBaseStrategy_basicStrategy() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("BasicStrategy") == 0);
    }
    
    @Test
    public void testAddStrategyLayer_compositionKISS() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        player.addStrategyLayer(Strategy.COMPOSITION_STRATEGY);
        player.addStrategyLayer(Strategy.KISS_I_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("KISSIStrategy CompositionStrategy BasicStrategy") == 0);
    }
    
    @Test
    public void testAddStrategyLayer_basicResets() {
        BlackjackPlayer player = new BlackjackPlayer(0);
        player.resetBaseStrategy(Strategy.BASIC_STRATEGY);
        player.addStrategyLayer(Strategy.COMPOSITION_STRATEGY);
        player.addStrategyLayer(Strategy.KISS_I_STRATEGY);
        player.addStrategyLayer(Strategy.BASIC_STRATEGY);
        
        String actual = player.toStringStrategy();
        
        assertTrue(actual.compareTo("BasicStrategy") == 0);
    }
}
