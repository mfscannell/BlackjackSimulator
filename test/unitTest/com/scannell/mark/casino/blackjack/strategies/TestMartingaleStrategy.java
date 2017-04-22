package unitTest.com.scannell.mark.casino.blackjack.strategies;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.blackjack.strategies.BasicStrategy;
import com.scannell.mark.casino.blackjack.strategies.BlackjackStrategy;
import com.scannell.mark.casino.blackjack.strategies.MartingaleStrategy;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

public class TestMartingaleStrategy {
    @Test
    public void  getBetSizeInitial1(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 1.0, 0.01);
    }
    
    @Test
    public void  getBetSizeOneLoss2(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        blackjackStrategy.notifyCashAdjustment(-1);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 2.0, 0.01);
    }
    
    @Test
    public void  getBetSizeTwoLosses4(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        blackjackStrategy.notifyCashAdjustment(-1);
        blackjackStrategy.notifyCashAdjustment(-2);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 4.0, 0.01);
    }
    
    @Test
    public void  getBetSizeThreeLosses(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        blackjackStrategy.notifyCashAdjustment(-1);
        blackjackStrategy.notifyCashAdjustment(-2);
        blackjackStrategy.notifyCashAdjustment(-4);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 8.0, 0.01);
    }
    
    @Test
    public void  getBetSizeMaxBet(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        blackjackStrategy.notifyCashAdjustment(-1);
        blackjackStrategy.notifyCashAdjustment(-2);
        blackjackStrategy.notifyCashAdjustment(-4);
        blackjackStrategy.notifyCashAdjustment(-8);
        blackjackStrategy.notifyCashAdjustment(-16);
        blackjackStrategy.notifyCashAdjustment(-32);
        blackjackStrategy.notifyCashAdjustment(-64);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 100, 0.01);
    }
    
    @Test
    public void  getBetSizeMaxBetWin(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        blackjackStrategy.notifyCashAdjustment(-1);
        blackjackStrategy.notifyCashAdjustment(-2);
        blackjackStrategy.notifyCashAdjustment(-4);
        blackjackStrategy.notifyCashAdjustment(-8);
        blackjackStrategy.notifyCashAdjustment(-16);
        blackjackStrategy.notifyCashAdjustment(-32);
        blackjackStrategy.notifyCashAdjustment(-64);
        
        blackjackStrategy.getBetSize();
        
        blackjackStrategy.notifyCashAdjustment(100);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 1, 0.01);
    }
    
    @Test
    public void  getBetSizeThreeLossesReset(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        blackjackStrategy.notifyCashAdjustment(-1);
        blackjackStrategy.notifyCashAdjustment(-2);
        blackjackStrategy.notifyCashAdjustment(-4);
        blackjackStrategy.notifyCashAdjustment(8);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 1.0, 0.01);
    }
    
    @Test
    public void  getBetSizeThreeLossesBlackjack(){
        BlackjackStrategy basicStrategy = new BasicStrategy();
        
        BlackjackStrategy blackjackStrategy = new MartingaleStrategy(basicStrategy, 1, 100);
        
        blackjackStrategy.notifyCashAdjustment(-1);
        blackjackStrategy.notifyCashAdjustment(-2);
        blackjackStrategy.notifyCashAdjustment(-4);
        blackjackStrategy.notifyCashAdjustment(12);
        
        double betSize = blackjackStrategy.getBetSize();
        
        assertEquals(betSize, 1.0, 0.01);
    }
}
