package unitTestModelObjects;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.Test;

import rules.BlackjackRules;
import rules.CompositionStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;

public class TestCompositionStrategy {

	@Test
	public void test102vs4() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			CompositionStrategy strategy = new CompositionStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, rules, numDecks) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}//end method test102vs4
	
	@Test
	public void test84vs4() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			CompositionStrategy strategy = new CompositionStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, rules, numDecks) == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}//end method test84vs4
	
	@Test
	public void test16vs10with4() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.JACK, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			CompositionStrategy strategy = new CompositionStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, rules, numDecks) == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}//end method test16vs10with4
	
	@Test
	public void test16vs10with5() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.QUEEN, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			CompositionStrategy strategy = new CompositionStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, rules, numDecks) == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}//end method test16vs10with4
	
	@Test
	public void test16vsnon10with5() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		int numDecks = 2;
		
		try {
			CompositionStrategy strategy = new CompositionStrategy(rules, numDecks);
			
			assertTrue(strategy.getAction(dealerUpCard, hand, rules, numDecks) == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}//end method test16vsnon10with5

}
