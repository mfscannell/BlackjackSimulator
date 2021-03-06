package unitTest.com.scannell.mark.casino.blackjack.strategies;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.playingCard.BlackjackCard;
import com.scannell.mark.casino.playingCard.PlayingCard;
import com.scannell.mark.casino.blackjack.strategies.BasicStrategy;
import com.scannell.mark.casino.blackjack.strategies.BlackjackStrategy;
import com.scannell.mark.casino.blackjack.strategies.KISSIStrategy;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;
import com.scannell.mark.casino.playingCard.enumerations.CardSuit;

public class TestKISSIStrategy {
    private BlackjackRules.Builder rulesBuilder;
    private BlackjackRules rules;
    
    private BlackjackStrategy strategy;
    
    private void beforeEach(int numDecks) {
        rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setDealerHitsSoft17(true);
        rulesBuilder.setCanResplitAces(true);
        rules = rulesBuilder.build();
        
        BlackjackStrategy basicStrategy = new BasicStrategy();
        strategy = new KISSIStrategy(basicStrategy);
        strategy.initialize(rules, numDecks);
    }
    
    @Test
    public void adjustCount_red2_noChange() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.TWO, CardSuit.HEARTS));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount);
    }
    
    @Test
    public void adjustCount_black2_decrement1() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.TWO, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void adjustCount_4_decrement1() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void adjustCount_5_decrement1() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void adjustCount_6_decrement1() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void adjustCount_J_increment1() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.JACK, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount + 1);
    }
    
    @Test
    public void adjustCount_Q_increment1() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.QUEEN, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount + 1);
    }
    
    @Test
    public void adjustCount_K_increment1() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.KING, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount + 1);
    }
    
    @Test
    public void getInsuranceAction_2deckincrement4_dontTakeInsurance() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        
        assertFalse(strategy.getInsuranceAction());
    }
    
    @Test
    public void getInsuranceAction_2deckincrement5_takeInsurance() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        
        assertTrue(strategy.getInsuranceAction());
    }
    
    @Test
    public void getInsuranceAction_4deckincrement10_dontTakeInsuranc() {
        int numDecks = 4;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        
        assertFalse(strategy.getInsuranceAction());
    }
    
    @Test
    public void getInsuranceAction_4deckincrement11_takeInsurance() {
        int numDecks = 4;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        
        assertTrue(strategy.getInsuranceAction());
    }
    
    @Test
    public void getBetSize_2deckIncr2_1() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        
        assertTrue(strategy.getBetSize() == 1);
    }
    
    @Test
    public void getBetSize_2deckIncr3_2() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        
        assertTrue(strategy.getBetSize() == 2);
    }
    
    @Test
    public void getBetSize_2deckIncr4_4() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        
        assertTrue(strategy.getBetSize() == 4);
    }
    
    @Test
    public void getBetSize_2deckIncr5_4() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        
        assertTrue(strategy.getBetSize() == 4);
    }
    
    @Test
    public void getBetSize_6deckIncr9_1() {
        int numDecks = 6;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
        
        assertTrue(strategy.getBetSize() == 1);
    }
    
    @Test
    public void getBetSize_6deckIncr10_2() {
        int numDecks = 6;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        
        assertTrue(strategy.getBetSize() == 2);
    }
    
    @Test
    public void getBetSize_6deckIncr11_4() {
        int numDecks = 6;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        
        assertTrue(strategy.getBetSize() == 4);
    }
    
    @Test
    public void getBetSize_6deckIncr12_6() {
        int numDecks = 6;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.HEARTS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.DIAMONDS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
        
        assertTrue(strategy.getBetSize() == 6);
    }

    @Test
    public void getAction_A2vs5_double() {
        BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
        rulesBuilder.setBlackjackPayoutMultiple(1.5);
        rulesBuilder.setDealerHitsSoft17(true);
        rulesBuilder.setDoubleAfterSplitAllowed(true);
        rulesBuilder.setMaxHandsAfterSplits(4);
        rulesBuilder.setCanResplitAces(false);
        BlackjackRules rules = rulesBuilder.build();
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new KISSIStrategy(strategy);
        strategy.initialize(rules, 2);
        
        BlackjackCard firstCard = new BlackjackCard(CardRank.TWO, CardSuit.HEARTS);
        BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.DIAMONDS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS);
        
        BlackjackMove move = strategy.getAction(dealerUpCard, hand, 2);
        
        assertTrue(move == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void getAction_2deck9vs2incrCount3_double() {
        beforeEach(2);
        PlayingCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        
        PlayingCard playersFirstCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        PlayingCard playersSecondCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(playersFirstCard);
        hand.addCard(playersSecondCard);
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new KISSIStrategy(strategy);
        strategy.initialize(rules, 2);
        
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        
        BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
        assertTrue(move == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void getAction_2deck11vsAincrCount3_double() {
        beforeEach(2);
        PlayingCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
        
        PlayingCard playersFirstCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        PlayingCard playersSecondCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(playersFirstCard);
        hand.addCard(playersSecondCard);
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new KISSIStrategy(strategy);
        strategy.initialize(rules, 2);
        
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
        assertTrue(move == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void getAction_2Deck16vsTincrCount3_stand() {
        beforeEach(2);
        PlayingCard dealerUpCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
        
        PlayingCard playersFirstCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        PlayingCard playersSecondCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(playersFirstCard);
        hand.addCard(playersSecondCard);
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new KISSIStrategy(strategy);
        strategy.initialize(rules, 2);
        
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
        assertTrue(move == BlackjackMove.STAND);
    }
    
    @Test
    public void getAction_2deckA7vs2IncrCount3_double() {
        beforeEach(2);
        PlayingCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
        
        PlayingCard playersFirstCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        PlayingCard playersSecondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(playersFirstCard);
        hand.addCard(playersSecondCard);
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new KISSIStrategy(strategy);
        strategy.initialize(rules, 2);
        
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
        assertTrue(move == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void getAction_2DeckA8vs5Incr3_double() {
        beforeEach(2);
        PlayingCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
        
        PlayingCard playersFirstCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        PlayingCard playersSecondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(playersFirstCard);
        hand.addCard(playersSecondCard);
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new KISSIStrategy(strategy);
        strategy.initialize(rules, 2);
        
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
            assertTrue(move == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void getAction_2DeckA8vs6Incr3_double() {
        beforeEach(2);
        PlayingCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
        
        PlayingCard playersFirstCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        PlayingCard playersSecondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(playersFirstCard);
        hand.addCard(playersSecondCard);
        
        BlackjackStrategy strategy = new BasicStrategy();
        strategy = new KISSIStrategy(strategy);
        strategy.initialize(rules, 2);
        
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS));
        BlackjackMove move = strategy.getAction(dealerUpCard, hand, 1);
        assertTrue(move == BlackjackMove.DOUBLE);
    }

}
