package unitTestStrategies.basicStrategy.softChart;

import static org.junit.Assert.*;
import mockData.BlackjackHandSoft;
import mockData.DefaultRulesSingleton;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.BeforeClass;
import org.junit.Test;

import blackjackStrategies.BasicStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;
import rules.BlackjackRules;

public class SoftAce3 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandSoft.getHandAce3();
	}

	@Test
	public void test3vs2() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vs3() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vs4() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vs5() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}
	
	@Test
	public void test3vs6() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}
	
	@Test
	public void test3vs7() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vs8() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vs9() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vs10() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vsA() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test3vs4DoubleDeck() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}
	
	@Test
	public void test3vs4SingleDeck() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 1;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}

}
