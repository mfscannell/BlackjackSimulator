package unitTestStrategies.basicStrategy.softChart;

import static org.junit.Assert.assertTrue;
import mockData.BlackjackHandSoft;
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

public class SoftAce8 {
	private static BlackjackRules rules;
	private static BlackjackHand hand;
	private static BlackjackPlayer player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rules = DefaultRulesSingleton.getDefaultRules();
		hand = BlackjackHandSoft.getHandAce8();
		player = new BlackjackPlayer(0, false);
	}

	@Test
	public void test8vs2() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs3() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs4() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs5() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs6() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs7() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs8() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs9() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs10() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vsA() {
	    int numDecks = 4;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
	}
	
	@Test
	public void test8vs6DealerHitSoft17() {
	    int numDecks = 4;
	    
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setDoubleAfterSplitAllowed(false);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}
	
	@Test
	public void test8vs6SingleDeck() {
	    int numDecks = 1;
	    
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		BasicStrategy strategy = new BasicStrategy(rules, numDecks);
		
		player.updateStrategy(rules, numDecks);
		
		assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
		assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
	}
}
