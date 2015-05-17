package unitTestRules;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.Test;

import rules.BlackjackRules;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;

public class TestBlackjackRules {
	private BlackjackRules.Builder rulesBuilder;
	private BlackjackRules rules;
	
	private void beforeEach() {
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
		beforeEach();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
	}
	
	@Test
	public void testGetDealersMoveStand() {
		beforeEach();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
	}
	
	@Test
	public void testGetDealersMoveHitSoft() {
		beforeEach();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
	}
	
	@Test
	public void testGetDealersMoveStandSoft() {
		beforeEach();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
	}
	
	@Test
	public void testAceCardValue() {
		beforeEach();
		
		assertTrue(rules.getCardValue(CardRank.ACE) == 1);
	}
	
	@Test
	public void testTenCardValue() {
		beforeEach();
		
		assertTrue(rules.getCardValue(CardRank.TEN) == 10);
	}

}
