package unitTestStrategies.basicStrategy.totalChart;

import static org.junit.Assert.assertTrue;
import mockData.BlackjackHandHard;
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

public class Total13 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandHard.getHandHard13();
	}

	@Test
	public void testTotal13vs2() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testTotal13vs3() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testTotal13vs4() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testTotal13vs5() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testTotal13vs6() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void testTotal13vs7() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void testTotal13vs8() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void testTotal13vs9() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void testTotal13vs10() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.KING, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}
	
	@Test
	public void testTotal13vsA() {
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
	}

}
