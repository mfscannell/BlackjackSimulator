package unitTest.casino.blackjack.strategies;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.playingCard.BlackjackCard;
import casino.blackjack.strategies.BasicStrategy;
import casino.blackjack.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

public class TestBasicStrategy {
    @Test
    public void testRulesCantResplitAces() {
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(false);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(false);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.DIAMONDS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        BlackjackHand hand = new BlackjackHand();
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
        BlackjackMove move2 = basicStrategy.getAction(dealerUpCard, secondHand, 2);
        
        assertTrue(move == BlackjackMove.STAND);
        assertTrue(move2 == BlackjackMove.STAND);
    }
    
    @Test
    public void testRulesCanResplitAces() {
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(false);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.DIAMONDS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        BlackjackHand hand = new BlackjackHand();
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
        BlackjackMove move2 = basicStrategy.getAction(dealerUpCard, secondHand, 2);
        
        assertTrue(move == BlackjackMove.SPLIT);
        assertTrue(move2 == BlackjackMove.SPLIT);
    }
    
    @Test
    public void testMaxHandsReached() {
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(false);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(false);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.DIAMONDS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        BlackjackHand hand = new BlackjackHand();
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 4);
        
        assertTrue(move == BlackjackMove.STAND);
    }
    
    @Test
    public void testMaxHandsNotReached() {
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(false);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.DIAMONDS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        BlackjackHand hand = new BlackjackHand();
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 3);
        
        assertTrue(move == BlackjackMove.SPLIT);
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
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
        
        BlackjackHand hand = new BlackjackHand();
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
        
        assertTrue(move == BlackjackMove.STAND);
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
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.SIX, CardSuit.HEARTS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 4);
        
        assertTrue(move == BlackjackMove.STAND);
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
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
        
        assertTrue(move == BlackjackMove.HIT);
    }
    
    @Test
    public void testCanDAS() {
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BlackjackCard fourthCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackHand secondHand = hand.split();
        
        hand.addCard(thirdCard);
        secondHand.addCard(fourthCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
        
        assertTrue(move == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void testGetBetSize() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
    	
        assertTrue(basicStrategy.getBetSize() == 1);
    }
    
    @Test
    public void testGetCount() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
    	
        assertTrue(basicStrategy.getCount() == 0);
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
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 2);
        
        assertTrue(move == BlackjackMove.HIT);
    }
    
    @Test
    public void testMultiSoft17() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 1);
        
        assertTrue(move == BlackjackMove.HIT);
    }
    
    @Test
    public void testMultiSoft18vs8() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 1);
        
        assertTrue(move == BlackjackMove.STAND);
    }
    
    @Test
    public void testMultiSoft18vs9() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 1);
        
        assertTrue(move == BlackjackMove.HIT);
    }
    
    @Test
    public void testMultiSoft18vs10() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 1);
        
        assertTrue(move == BlackjackMove.HIT);
    }
    
    @Test
    public void testMultiSoft18vsAce() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 1);
        
        assertTrue(move == BlackjackMove.HIT);
    }
    
    @Test
    public void testMultiSoft19vsEight() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 1);
        
        assertTrue(move == BlackjackMove.STAND);
    }
    
    @Test
    public void testMultiSoft19vsAce() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BasicStrategy basicStrategy = new BasicStrategy();
        basicStrategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        
        BlackjackMove move = basicStrategy.getAction(dealerUpCard, hand, 1);
        
        assertTrue(move == BlackjackMove.STAND);
    }

}
