package unitTestStrategies.basicStrategy.pairChart;

import static org.junit.Assert.assertTrue;
import mockData.BlackjackHandPairs;
import mockData.DefaultRulesSingleton;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.BlackjackCard;
import rules.BlackjackRules;
import blackjackStrategies.BasicStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;

/**
 * Tests the Basic Strategy Chart when the player has a pair of 8s and the player has not split his hand 
 * (he only has one hand).
 * @author mscannell
 *
 */
public class Pair8 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;
	private static BlackjackPlayer player;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandPairs.getPair8();
		player = new BlackjackPlayer(0, false);
	}

	@Test
	public void testPair8vs2() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs3() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs4() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs5() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs6() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs7() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs8() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs9() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vs10() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
	
	@Test
	public void testPair8vsA() {
	    int numDecks = 2;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
	}
}
