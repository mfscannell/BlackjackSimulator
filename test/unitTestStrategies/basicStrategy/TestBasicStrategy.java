package unitTestStrategies.basicStrategy;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.Test;

import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;
import rules.BasicStrategy;
import rules.BlackjackRules;

public class TestBasicStrategy {
	@Test
	public void testPairAcesCantResplit() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplitAllowed(false);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			BasicStrategy basicStrategy = new BasicStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			hand.setFromSplit(true);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
			
			assertTrue(move == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPairAcesCantResplit2() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplitAllowed(false);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(true);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			BasicStrategy basicStrategy = new BasicStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			hand.setFromSplit(true);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 4);
			
			assertTrue(move == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSplitAceMustStand() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplitAllowed(false);
		rulesBuilder.setMaxHandsAfterSplits(2);
		rulesBuilder.setCanResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			BasicStrategy basicStrategy = new BasicStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			hand.setFromSplit(true);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
			
			assertTrue(move == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPairMaxResplitReached() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplitAllowed(false);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			BasicStrategy basicStrategy = new BasicStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			hand.setFromSplit(true);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 4);
			
			assertTrue(move == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCantDAS() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplitAllowed(false);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(true);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			BasicStrategy basicStrategy = new BasicStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			hand.setFromSplit(true);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
			
			assertTrue(move == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMultiCantDouble() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplitAllowed(true);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(true);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			BasicStrategy basicStrategy = new BasicStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
			BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			hand.addCard(thirdCard);
			hand.setFromSplit(false);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
			
			assertTrue(move == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}

}
