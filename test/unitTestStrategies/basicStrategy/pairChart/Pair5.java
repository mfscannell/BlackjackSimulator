package unitTestStrategies.basicStrategy.pairChart;

import static org.junit.Assert.*;
import static org.junit.AfterClass.*;
import static org.junit.BeforeClass.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.BeforeClass;
import org.junit.Test;

import rules.BasicStrategy;
import rules.BlackjackRules;
import rules.BasicStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;

/**
 * Tests the Basic Strategy Chart when the player has a pair of 5s and the player has not split his hand 
 * (he only has one hand).
 * @author mscannell
 *
 */
public class Pair5 {
	private static BlackjackRules rules;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplitAllowed(false);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(false);
		rules = rulesBuilder.build();
	}

	@Test
	public void testPair5vs2() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs3() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs4() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs5() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs6() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs7() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs8() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs9() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
	public void testPair5vs10() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		
		int numDecks = 4;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			System.out.println("testPair5vs10:" + strategy.getAction(dealerUpCard, hand, numDecks));
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair5vsA() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
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
