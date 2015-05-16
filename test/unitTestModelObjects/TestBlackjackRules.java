package unitTestModelObjects;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.Test;

import rules.BlackjackRules;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;

public class TestBlackjackRules {

	@Test
	public void testGetDealersMoveHit() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDoubleAfterSplit(true);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setResplitAces(true);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
	}
	
	@Test
	public void testGetDealersMoveStand() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDoubleAfterSplit(true);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setResplitAces(true);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
	}
	
	@Test
	public void testGetDealersMoveHitSoft() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDoubleAfterSplit(true);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setResplitAces(true);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
	}
	
	@Test
	public void testGetDealersMoveStandSoft() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDoubleAfterSplit(true);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setResplitAces(true);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
	}

}
