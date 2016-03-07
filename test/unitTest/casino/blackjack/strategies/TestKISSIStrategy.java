package unitTest.casino.blackjack.strategies;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.playingCard.BlackjackCard;
import casino.playingCard.PlayingCard;
import casino.blackjack.strategies.BasicStrategy;
import casino.blackjack.strategies.BlackjackStrategy;
import casino.blackjack.strategies.KISSIStrategy;
import casino.blackjack.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

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
    public void testAdjustCountRed2() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.TWO, CardSuit.HEARTS));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount);
    }
    
    @Test
    public void testAdjustCountBlack2() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.TWO, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void testAdjustCount4() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void testAdjustCount5() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void testAdjustCount6() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount - 1);
    }
    
    @Test
    public void testAdjustCountJack() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.JACK, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount + 1);
    }
    
    @Test
    public void testAdjustCountQueen() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.QUEEN, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount + 1);
    }
    
    @Test
    public void testAdjustCountKing() {
        beforeEach(1);
        
        int initialCount = strategy.getCount();
        strategy.adjustCount(new BlackjackCard(CardRank.KING, CardSuit.SPADES));
        int afterCount = strategy.getCount();
        
        assertTrue(initialCount == afterCount + 1);
    }
    
    @Test
    public void testDoesNotTakeInsurance2Deck() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        
        assertFalse(strategy.getInsuranceAction());
    }
    
    @Test
    public void testDoesTakeInsurance2Deck() {
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
    public void testDoesNotTakeInsurance4Deck() {
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
    public void testDoesTakeInsurance4Deck() {
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
    public void testGetBetSizeIs1With2Decks() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        
        assertTrue(strategy.getBetSize() == 1);
    }
    
    @Test
    public void testGetBetSizeIs2With2Decks() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        
        assertTrue(strategy.getBetSize() == 2);
    }
    
    @Test
    public void testGetBetSizeIs4With2Decks() {
        int numDecks = 2;
        beforeEach(numDecks);
        
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FIVE, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.SIX, CardSuit.SPADES));
        strategy.adjustCount(new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS));
        
        assertTrue(strategy.getBetSize() == 4);
    }
    
    @Test
    public void testGetBetSizeIs6With2Decks() {
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
    public void testGetBetSizeIs1With6Decks() {
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
    public void testGetBetSizeIs2With6Decks() {
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
    public void testGetBetSizeIs4With6Decks() {
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
    public void testGetBetSizeIs6With6Decks() {
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
    public void testMultiCantDouble() {
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
    public void testGetAction9vs2() {
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
    public void testGetAction11vsA() {
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
    public void testGetAction16vs10() {
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
    public void testGetActionA7vs2() {
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
    public void testGetActionA8vs5() {
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
    public void testGetActionA8vs6() {
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
