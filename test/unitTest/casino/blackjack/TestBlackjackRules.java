package unitTest.casino.blackjack;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.playingCard.BlackjackCard;
import casino.blackjack.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

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
    public void getDealersMove_low_hit() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
    }
    
    @Test
    public void getDealersMove_hard17_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
    }
    
    @Test
    public void getDealersMove_soft17_hit() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(rules.getDealersMove(hand) == BlackjackMove.HIT);
    }
    
    @Test
    public void getDealersMove_soft17_stand() {
    	BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setDealerHitsSoft17(false);
        rulesBuilder.setCanResplitAces(true);
        BlackjackRules rules = rulesBuilder.build();
        
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
    }
    
    @Test
    public void getDealersMove_soft18_stand() {
        BlackjackHand hand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
        
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        assertTrue(rules.getDealersMove(hand) == BlackjackMove.STAND);
    }
    
    @Test
    public void getCardValue_ace() {
        assertTrue(rules.getCardValue(CardRank.ACE) == 1);
    }
    
    @Test
    public void getCardValue_ten() {
        assertTrue(rules.getCardValue(CardRank.TEN) == 10);
    }
    
    @Test
    public void getPayoutAdjustment_2xplayerDoesntBust_doubleDownWin() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_WIN);
    }
    
    @Test
    public void getPayoutAdjustment_2xplayerDoesntBust_doubleDownLose() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_LOSE);
    }
    
    @Test
    public void getPayoutAdjustMent_2xplayerBust_doubleDownLose() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_LOSE);
    }
    
    @Test
    public void getPayoutAdjustment_2xdealerBust_doubleDownWin() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_WIN);
    }
    
    @Test
    public void getPayoutAdjustment_2xbothBust_doubleDownLose() {
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
        BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        dealerHand.addCard(dealerThirdCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_DOUBLE_DOWN_LOSE);
    }
    
    @Test
    public void getPayoutAdjustment_playerLose_handLose() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_LOSE);
    }
    
    @Test
    public void getPayoutAdjustment_bothDontBust_handLose() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_LOSE);
    }
    
    @Test
    public void getPayoutAdjustment_bothDontBust_handWin() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_WIN);
    }
    
    @Test
    public void getPayoutAdjustment_playerBustDealerDoesntBust_handLose() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_LOSE);
    }
    
    @Test
    public void getPayoutAdjustment_playerDoesntBustdealerBust_handWin() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_WIN);
    }
    
    
    
    @Test
    public void getPayoutAdjustment_bothBust_handLose() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        playerHand.addCard(thirdCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        dealerHand.addCard(dealerThirdCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_LOSE);
    }
    
    @Test
    public void getPayoutAdjustment_win_handWin() {
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
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_WIN);
    }
    
    @Test
    public void getPayoutAdjustment_push_handPush() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_PUSH);
    }
    
    @Test
    public void getPayoutAdjustment_push21_handPush() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        playerHand.addCard(thirdCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        dealerHand.addCard(dealerThirdCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_PUSH);
    }
    
    @Test
    public void getPayoutAdjustment_push21MultiHand_handPush() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard thirdCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        playerHand.addCard(thirdCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        BlackjackCard dealerThirdCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        dealerHand.addCard(dealerThirdCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 2);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_PUSH);
    }
    
    @Test
    public void getPayoutAdjustment_blackjack_handBlackjack() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == rules.getBlackjackPayoutMultiple());
    }
    
    @Test
    public void getPayoutAdjustment_21multiHand_handBlackjack() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 2);
        
        assertTrue(payoutRate == rules.PAYOUT_HAND_WIN);
    }
    
    @Test
    public void getPayoutAdjustment_bothBlackjack_handPush() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_PUSH);
    }
    
    @Test
    public void getPayoutAdjustment_dealerBlackjack_handLose() {
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
        playerHand.addCard(firstCard);
        playerHand.addCard(secondCard);
        
        BlackjackHand dealerHand = new BlackjackHand();
        BlackjackCard dealerFirstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
        BlackjackCard dealerSecondCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        dealerHand.addCard(dealerFirstCard);
        dealerHand.addCard(dealerSecondCard);
        
        double payoutRate = rules.getPayoutAdjustment(playerHand, dealerHand, 1);
        
        assertTrue(payoutRate == BlackjackRules.PAYOUT_HAND_LOSE);
    }

}
