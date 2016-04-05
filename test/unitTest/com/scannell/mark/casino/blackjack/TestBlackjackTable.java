package unitTest.com.scannell.mark.casino.blackjack;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.blackjack.BlackjackTable;
import com.scannell.mark.casino.gambler.BlackjackPlayer;
import com.scannell.mark.casino.blackjack.exceptions.TableSeatNumberInvalidException;
import com.scannell.mark.casino.blackjack.exceptions.TableSeatTakenException;

public class TestBlackjackTable {
    private static BlackjackRules.Builder rulesBuilder;
    private static BlackjackRules blackjackRules;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(false);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(false);
        blackjackRules = rulesBuilder.build();
    }
    
    @Test
    public void hasPlayerAtSeat_true() {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        try {
            blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
            
            assertTrue(blackjackTable.hasPlayerAtSeat(1));
        } catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void hasPlayerAtSeat_false() {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        try {
            blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 2);
            
            assertFalse(blackjackTable.hasPlayerAtSeat(1));
        } catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void addPlayerAtSeat_seatEmpty() {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        try {
            blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
        } catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
            e.printStackTrace();
        }
    }
    
    @Test(expected=TableSeatTakenException.class)
    public void addPlayerAtSeat_seatTaken() throws TableSeatTakenException, TableSeatNumberInvalidException {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
    }
    
    @Test(expected=TableSeatNumberInvalidException.class)
    public void addPlayerAtSeat_seatNumberLow_invalid() throws TableSeatTakenException, TableSeatNumberInvalidException {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), -1);
    }
    
    @Test(expected=TableSeatNumberInvalidException.class)
    public void addPlayerAtSeat_seatNumberHigh_invalid() throws TableSeatTakenException, TableSeatNumberInvalidException {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 7);
    }
}
