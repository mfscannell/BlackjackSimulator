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
 * Tests the Basic Strategy Chart when the player has a pair of 9s and the player has not split his hand 
 * (he only has one hand).
 * @author mscannell
 *
 */
public class Pair9 {
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
	public void testPair9vs2() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs3() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs4() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs5() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs6() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs7() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs8() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs9() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.SPLIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vs10() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPair9vsA() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			BasicStrategy strategy = new BasicStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}

}
