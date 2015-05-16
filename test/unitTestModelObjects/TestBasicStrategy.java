package unitTestModelObjects;

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
	public void testChart() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		int numDecks = 6;
		
		BasicStrategy basicStrategy;
		try {
			basicStrategy = new BasicStrategy(rules, numDecks);
			System.out.println("************************************************");
			System.out.println("6 deck, no hit soft 17, no DAS");
			System.out.println("----------------------------------");
			System.out.println("Total Chart");
			basicStrategy.printTotalChart();
			System.out.println("Soft Chart");
			basicStrategy.printSoftChart();
			System.out.println("Pair Chart");
			basicStrategy.printPairChart();
			System.out.println("************************************************");
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChartDAS() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(true);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		int numDecks = 6;
		
		BasicStrategy basicStrategy;
		try {
			basicStrategy = new BasicStrategy(rules, numDecks);
			System.out.println("************************************************");
			System.out.println("6 deck, no hit soft 17, DAS");
			System.out.println("----------------------------------");
			System.out.println("Total Chart");
			basicStrategy.printTotalChart();
			System.out.println("Soft Chart");
			basicStrategy.printSoftChart();
			System.out.println("Pair Chart");
			basicStrategy.printPairChart();
			System.out.println("************************************************");
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChartSoft17() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		int numDecks = 6;
		
		BasicStrategy basicStrategy;
		try {
			basicStrategy = new BasicStrategy(rules, numDecks);
			System.out.println("************************************************");
			System.out.println("6 deck, hit soft 17, no DAS");
			System.out.println("----------------------------------");
			System.out.println("Total Chart");
			basicStrategy.printTotalChart();
			System.out.println("Soft Chart");
			basicStrategy.printSoftChart();
			System.out.println("Pair Chart");
			basicStrategy.printPairChart();
			System.out.println("************************************************");
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChart2Deck() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		int numDecks = 2;
		
		BasicStrategy basicStrategy;
		try {
			basicStrategy = new BasicStrategy(rules, numDecks);
			System.out.println("************************************************");
			System.out.println("2 deck, no hit soft 17, no DAS");
			System.out.println("----------------------------------");
			System.out.println("Total Chart");
			basicStrategy.printTotalChart();
			System.out.println("Soft Chart");
			basicStrategy.printSoftChart();
			System.out.println("Pair Chart");
			basicStrategy.printPairChart();
			System.out.println("************************************************");
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChart1Deck() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		int numDecks = 1;
		
		BasicStrategy basicStrategy;
		try {
			basicStrategy = new BasicStrategy(rules, numDecks);
			System.out.println("************************************************");
			System.out.println("1 deck, no hit soft 17, no DAS");
			System.out.println("----------------------------------");
			System.out.println("Total Chart");
			basicStrategy.printTotalChart();
			System.out.println("Soft Chart");
			basicStrategy.printSoftChart();
			System.out.println("Pair Chart");
			basicStrategy.printPairChart();
			System.out.println("************************************************");
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPairAcesCantResplit() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
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
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, rules, 2);
			
			assertTrue(move == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPairAcesCantResplit2() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(true);
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
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, rules, 4);
			
			assertTrue(move == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSplitAceMustStand() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(2);
		rulesBuilder.setResplitAces(false);
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
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, rules, 2);
			
			assertTrue(move == BlackjackMove.STAND);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPairMaxResplitReached() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(false);
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
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, rules, 4);
			
			assertTrue(move == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCantDAS() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(false);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(true);
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
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, rules, 2);
			
			assertTrue(move == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMultiCantDouble() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayout(1.5);
		rulesBuilder.setDealerHitsSoft17(false);
		rulesBuilder.setDoubleAfterSplit(true);
		rulesBuilder.setMaxHands(4);
		rulesBuilder.setResplitAces(true);
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
			
			BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, rules, 2);
			
			assertTrue(move == BlackjackMove.HIT);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}

}
