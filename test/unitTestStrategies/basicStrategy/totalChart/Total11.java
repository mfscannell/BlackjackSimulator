package unitTestStrategies.basicStrategy.totalChart;

import static org.junit.Assert.*;
import mockData.BlackjackHandHard;
import mockData.DefaultRulesSingleton;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.BeforeClass;
import org.junit.Test;

import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;
import rules.BasicStrategy;
import rules.BlackjackRules;

public class Total11 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandHard.getHandHard11();
	}

	@Test
	public void testTotal11vs2() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs3() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs4() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs5() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs6() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs7() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs8() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs9() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vs10() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.KING, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTotal11vsA() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}

}
