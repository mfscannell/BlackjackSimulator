package unitTestModelObjects;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackTable;
import casino.gambler.BlackjackPlayer;
import rules.BlackjackRules;
import exceptions.TableSeatNumberInvalidException;
import exceptions.TableSeatTakenException;

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
    public void hasPlayerAtSeatTrue() {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        try {
            blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
            
            assertTrue(blackjackTable.hasPlayerAtSeat(1));
        } catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void hasPlayerAtSeatFalse() {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        try {
            blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 2);
            
            assertFalse(blackjackTable.hasPlayerAtSeat(1));
        } catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void canAddPlayer() {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        try {
            blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
        } catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
            e.printStackTrace();
        }
    }
    
    @Test(expected=TableSeatTakenException.class)
    public void cantAddPlayerSeatTaken() throws TableSeatTakenException, TableSeatNumberInvalidException {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 1);
    }
    
    @Test(expected=TableSeatNumberInvalidException.class)
    public void cantAddPlayerInvalidSeat() throws TableSeatTakenException, TableSeatNumberInvalidException {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), -1);
    }
    
    @Test(expected=TableSeatNumberInvalidException.class)
    public void cantAddPlayerInvalidSeatHigh() throws TableSeatTakenException, TableSeatNumberInvalidException {
        BlackjackTable blackjackTable = new BlackjackTable(2, 55, blackjackRules);
        
        blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0), 7);
    }
}
