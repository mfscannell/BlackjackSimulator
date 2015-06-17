package unitTestRules;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;
import modelObjects.PlayingCard;

import org.junit.Test;

import rules.BasicStrategy;
import rules.BlackjackRules;
import rules.BlackjackRules.Builder;
import rules.KISSIStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;
import exceptions.InvalidShoeException;

public class TestKISSIStrategy {
	private BlackjackRules.Builder rulesBuilder;
	private BlackjackRules rules;
	
	private KISSIStrategy kissIStrategy;
	
	private void beforeEach(int numDecks) {
		rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDoubleAfterSplitAllowed(true);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setCanResplitAces(true);
		rules = rulesBuilder.build();
		
		try {
			kissIStrategy = new KISSIStrategy(rules, numDecks);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdjustCountRed2() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.TWO, CardSuit.HEARTS));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount);
	}
	
	@Test
	public void testAdjustCountBlack2() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.TWO, CardSuit.SPADES));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount - 1);
	}
	
	@Test
	public void testAdjustCount4() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount - 1);
	}
	
	@Test
	public void testAdjustCount5() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount - 1);
	}
	
	@Test
	public void testAdjustCount6() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount - 1);
	}
	
	@Test
	public void testAdjustCountJack() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.JACK, CardSuit.SPADES));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount + 1);
	}
	
	@Test
	public void testAdjustCountQueen() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.QUEEN, CardSuit.SPADES));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount + 1);
	}
	
	@Test
	public void testAdjustCountKing() {
		beforeEach(1);
		
		int initialCount = kissIStrategy.getCount();
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.KING, CardSuit.SPADES));
		int afterCount = kissIStrategy.getCount();
		
		assertTrue(initialCount == afterCount + 1);
	}
	
	@Test
	public void testDoesNotTakeInsurance2Deck() {
		int numDecks = 2;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		
		try {
			assertFalse(kissIStrategy.getInsuranceMove());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoesTakeInsurance2Deck() {
		int numDecks = 2;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		
		try {
			assertTrue(kissIStrategy.getInsuranceMove());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoesNotTakeInsurance4Deck() {
		int numDecks = 4;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
		
		try {
			assertFalse(kissIStrategy.getInsuranceMove());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoesTakeInsurance4Deck() {
		int numDecks = 4;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
		
		try {
			assertTrue(kissIStrategy.getInsuranceMove());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBetSizeIs1With2Decks() {
		int numDecks = 2;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		
		assertTrue(kissIStrategy.getBetSize() == 1);
	}
	
	@Test
	public void testGetBetSizeIs2With2Decks() {
		int numDecks = 2;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		
		assertTrue(kissIStrategy.getBetSize() == 2);
	}
	
	@Test
	public void testGetBetSizeIs4With2Decks() {
		int numDecks = 2;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		
		assertTrue(kissIStrategy.getBetSize() == 4);
	}
	
	@Test
	public void testGetBetSizeIs6With2Decks() {
		int numDecks = 2;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		
		assertTrue(kissIStrategy.getBetSize() == 6);
	}
	
	@Test
	public void testGetBetSizeIs1With6Decks() {
		int numDecks = 6;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
		
		assertTrue(kissIStrategy.getBetSize() == 1);
	}
	
	@Test
	public void testGetBetSizeIs2With6Decks() {
		int numDecks = 6;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
		
		assertTrue(kissIStrategy.getBetSize() == 2);
	}
	
	@Test
	public void testGetBetSizeIs4With6Decks() {
		int numDecks = 6;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
		
		assertTrue(kissIStrategy.getBetSize() == 4);
	}
	
	@Test
	public void testGetBetSizeIs6With6Decks() {
		int numDecks = 6;
		beforeEach(numDecks);
		
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
		kissIStrategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
		
		assertTrue(kissIStrategy.getBetSize() == 6);
	}

	@Test
	public void testMultiCantDouble() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setDoubleAfterSplitAllowed(true);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			KISSIStrategy kissIStrategy = new KISSIStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.TWO, CardSuit.HEARTS);
			BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.DIAMONDS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS);
			
			BlackjackMove move = kissIStrategy.getAction(dealerUpCard, hand, 2);
			
			assertTrue(move == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAction9vs2() {
		beforeEach(2);
		PlayingCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		
		PlayingCard playersFirstCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		PlayingCard playersSecondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
		
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(playersFirstCard);
		hand.addCard(playersSecondCard);
		
		try {
			KISSIStrategy strategy = new KISSIStrategy(rules, 2);
			
			strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
			
			BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
			assertTrue(move == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAction11vsA() {
		beforeEach(2);
		PlayingCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		
		PlayingCard playersFirstCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		PlayingCard playersSecondCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(playersFirstCard);
		hand.addCard(playersSecondCard);
		
		try {
			KISSIStrategy strategy = new KISSIStrategy(rules, 2);
			
			strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
			BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
			assertTrue(move == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAction16vs10() {
		beforeEach(2);
		PlayingCard dealerUpCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		
		PlayingCard playersFirstCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		PlayingCard playersSecondCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(playersFirstCard);
		hand.addCard(playersSecondCard);
		
		try {
			KISSIStrategy strategy = new KISSIStrategy(rules, 2);
			
			strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
			BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
			assertTrue(move == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetActionA7vs2() {
		beforeEach(2);
		PlayingCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		
		PlayingCard playersFirstCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		PlayingCard playersSecondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
		
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(playersFirstCard);
		hand.addCard(playersSecondCard);
		
		try {
			KISSIStrategy strategy = new KISSIStrategy(rules, 2);
			
			strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
			BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
			assertTrue(move == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetActionA8vs5() {
		beforeEach(2);
		PlayingCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		
		PlayingCard playersFirstCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		PlayingCard playersSecondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(playersFirstCard);
		hand.addCard(playersSecondCard);
		
		try {
			KISSIStrategy strategy = new KISSIStrategy(rules, 2);
			
			strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
			BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
			assertTrue(move == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetActionA8vs6() {
		beforeEach(2);
		PlayingCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		PlayingCard playersFirstCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		PlayingCard playersSecondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
		
		BlackjackHand hand = new BlackjackHand();
		hand.addCard(playersFirstCard);
		hand.addCard(playersSecondCard);
		
		try {
			KISSIStrategy strategy = new KISSIStrategy(rules, 2);
			
			strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
			strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
			BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
			assertTrue(move == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}

}
