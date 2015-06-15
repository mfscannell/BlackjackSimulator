package unitTestRules;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.BeforeClass;
import org.junit.Test;

import rules.BlackjackRules;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;

public class TestBlackjackRules {
	private static BlackjackRules.Builder rulesBuilder;
	private static BlackjackRules rules;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDoubleAfterSplitAllowed(true);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setCanResplitAces(true);
		rules = rulesBuilder.build();
	}

	@Test
	public void testGetDealersMoveHit() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
	}
	
	@Test
	public void testGetDealersMoveStand() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
	}
	
	@Test
	public void testGetDealersMoveHitSoft() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
	}
	
	@Test
	public void testGetDealersMoveStandSoft() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
	}
	
	@Test
	public void testAceCardValue() {
		assertTrue(rules.getCardValue(CardRank.ACE) == 1);
	}
	
	@Test
	public void testTenCardValue() {
		assertTrue(rules.getCardValue(CardRank.TEN) == 10);
	}
	
	@Test
	public void testDoubleDownWin() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		playerHand.setWasDoubleDown(true);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_WIN);
	}
	
	@Test
	public void testDoubleDownWinDealerBust() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		playerHand.setWasDoubleDown(true);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		dealerHand.addCard(dealerThirdCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_WIN);
	}
	
	@Test
	public void testDoubleDownLosePlayerBust() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		playerHand.setWasDoubleDown(true);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		dealerHand.addCard(dealerThirdCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_LOSE);
	}
	
	@Test
	public void testDoubleDownLose() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		playerHand.setWasDoubleDown(true);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		dealerHand.addCard(dealerThirdCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_LOSE);
	}
	
	@Test
	public void testLoseBust() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		dealerHand.addCard(dealerThirdCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_LOSE);
	}
	
	@Test
	public void testLose() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		dealerHand.addCard(dealerThirdCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_LOSE);
	}
	
	@Test
	public void testWinDealerBust() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		dealerHand.addCard(dealerThirdCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_WIN);
	}
	
	@Test
	public void testWin() {
		BlackjackHand playerHand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(firstCard);
		playerHand.addCard(secondCard);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		playerHand.addCard(thirdCard);
		
		BlackjackHand dealerHand = new BlackjackHand();
		BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		dealerHand.addCard(dealerFirstCard);
		dealerHand.addCard(dealerSecondCard);
		dealerHand.addCard(dealerThirdCard);
		
		double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand);
		
		assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_WIN);
	}

}
