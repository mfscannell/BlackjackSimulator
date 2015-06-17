package unitTestStrategies.basicStrategy.softChart;

import static org.junit.Assert.*;
import mockData.BlackjackHandSoft;
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

public class SoftAce5 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandSoft.getHandAce5();
	}

	@Test
	public void test5vs2() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5vs3() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5vs4() {
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
	public void test5vs5() {
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
	public void test5vs6() {
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
	public void test5vs7() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5vs8() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5vs9() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5vs10() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test5vsA() {
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
