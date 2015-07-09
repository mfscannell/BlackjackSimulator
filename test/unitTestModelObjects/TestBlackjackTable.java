package unitTestModelObjects;

import static org.junit.Assert.*;
import mockData.BlackjackHandHard;
import mockData.DefaultRulesSingleton;
import modelObjects.BlackjackPlayer;
import modelObjects.BlackjackTable;
import modelObjects.Shoe;

import org.junit.BeforeClass;
import org.junit.Test;

import rules.BlackjackRules;
import exceptions.InvalidShoeException;
import exceptions.TableSeatNumberInvalidException;
import exceptions.TableSeatTakenException;

public class TestBlackjackTable {
	private static Shoe shoe;
	private static BlackjackRules.Builder rulesBuilder;
	private static BlackjackRules blackjackRules;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			shoe = new Shoe(2, 55);
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
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
		BlackjackTable blackjackTable = new BlackjackTable(shoe, blackjackRules);
		
		try {
			blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0, true), 1);
			
			assertTrue(blackjackTable.hasPlayerAtSeat(1));
		} catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void hasPlayerAtSeatFalse() {
		BlackjackTable blackjackTable = new BlackjackTable(shoe, blackjackRules);
		
		try {
			blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0, true), 2);
			
			assertFalse(blackjackTable.hasPlayerAtSeat(1));
		} catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void canAddPlayer() {
		BlackjackTable blackjackTable = new BlackjackTable(shoe, blackjackRules);
		
		try {
			blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0, true), 1);
		} catch (TableSeatTakenException | TableSeatNumberInvalidException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=TableSeatTakenException.class)
	public void cantAddPlayerSeatTaken() throws TableSeatTakenException, TableSeatNumberInvalidException {
		BlackjackTable blackjackTable = new BlackjackTable(shoe, blackjackRules);
		
		blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0, true), 1);
		blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0, true), 1);
	}
	
	@Test(expected=TableSeatNumberInvalidException.class)
	public void cantAddPlayerInvalidSeat() throws TableSeatTakenException, TableSeatNumberInvalidException {
		BlackjackTable blackjackTable = new BlackjackTable(shoe, blackjackRules);
		
		blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0, true), -1);
	}
	
	@Test(expected=TableSeatNumberInvalidException.class)
	public void cantAddPlayerInvalidSeatHigh() throws TableSeatTakenException, TableSeatNumberInvalidException {
		BlackjackTable blackjackTable = new BlackjackTable(shoe, blackjackRules);
		
		blackjackTable.addPlayerAtSeat(new BlackjackPlayer(0, true), 7);
	}
}
