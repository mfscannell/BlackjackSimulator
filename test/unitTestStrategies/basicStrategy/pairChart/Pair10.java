package unitTestStrategies.basicStrategy.pairChart;

import static org.junit.Assert.assertTrue;
import mockData.BlackjackHandPairs;
import mockData.DefaultRulesSingleton;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.BeforeClass;
import org.junit.Test;

import rules.BlackjackRules;
import blackjackStrategies.BasicStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;

/**
 * Tests the Basic Strategy Chart when the player has a pair of 10s and the player has not split his hand 
 * (he only has one hand).
 * @author mscannell
 *
 */
public class Pair10 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandPairs.getPair10();
	}

	@Test
	public void testPair10vs2() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs3() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs4() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs5() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs6() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs7() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs8() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs9() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vs10() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testPair10vsA() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}

}
