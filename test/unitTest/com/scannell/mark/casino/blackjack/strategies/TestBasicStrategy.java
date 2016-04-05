package unitTest.com.scannell.mark.casino.blackjack.strategies;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.blackjack.strategies.BasicStrategy;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

public class TestBasicStrategy {
    @Test
    public void getAction_cantResplitAces_stand() {
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
    public void getAction_canResplitAces_split() {
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
    public void getAction_resplitMaxHandsReached_stand() {
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
    public void getAction_resplitMaxHandsNotReached_split() {
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
    public void getAction_splitAcesMustStand_stand() {
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
        BlackjackMove move2 = basicStrategy.getAction(dealerUpCard, secondHand, 2);
        
        assertTrue(move == BlackjackMove.STAND);
        assertTrue(move2 == BlackjackMove.STAND);
    }
    
    @Test
    public void getAction_resplitMaxHandsReachedNonAce_stand() {
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
    public void getAction_cantDoubleAfterSplit_hit() {
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
    public void getAction_canDoubleAfterSplit_double() {
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
    public void getAction_multiCardHandCantDouble_hit() {
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
    public void getAction_multiCardSoft17_hit() {
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
    public void getAction_multiSoft18vs8_stand() {
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
    public void getAction_multiSoft18vs9_hit() {
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
    public void getAction_multiSoft18vs10_hit() {
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
    public void getAction_multiSoft18vsAce_hit() {
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
    public void getAction_multiSoft19vs8_stand() {
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
    public void getAction_multiSoft19vsA_stand() {
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
    
    @Test
    public void getBetSize_basicStrategy_1() {
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
    public void getCount_basicStrategy_0() {
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
}
