package unitTestStrategies.basicStrategy.softChart;

import static org.junit.Assert.assertTrue;
import mockData.BlackjackHandSoft;
import mockData.DefaultRulesSingleton;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.playingCard.BlackjackCard;
import rules.BlackjackRules;
import blackjackStrategies.BasicStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;

public class SoftAce2 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandSoft.getHandAce2();
	}

	@Test
	public void test2vs2() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vs3() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vs4() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vs5() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}
	
	@Test
	public void test2vs6() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}
	
	@Test
	public void test2vs7() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vs8() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vs9() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vs10() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vsA() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void test2vs4SingleDeck() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 1;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}

}
