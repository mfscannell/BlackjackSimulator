package unitTest.casino.blackjack.strategies.basicStrategy.softChart;

import static org.junit.Assert.assertTrue;

import mockData.casino.blackjack.BlackjackHandSoft;
import mockData.casino.blackjack.DefaultRulesSingleton;

import org.junit.BeforeClass;
import org.junit.Test;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.BlackjackCard;
import casino.blackjack.strategies.BasicStrategy;
import casino.blackjack.strategies.BlackjackStrategy;
import casino.blackjack.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;
import casino.playingCard.enumerations.CardSuit;

public class SoftAce4 {
    private static BlackjackRules rules;
    private static BlackjackHand hand;
    private static BlackjackPlayer player;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rules = DefaultRulesSingleton.getDefaultRules();
        hand = BlackjackHandSoft.getHandAce4();
        player = new BlackjackPlayer(0);
        player.resetBaseStrategy(BlackjackStrategy.BASIC_STRATEGY);
    }

    @Test
    public void test4vs2() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void test4vs3() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.THREE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void test4vs4() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FOUR, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void test4vs5() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void test4vs6() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.DOUBLE);
    }
    
    @Test
    public void test4vs7() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.SEVEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void test4vs8() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.EIGHT, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void test4vs9() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(hand.isHand(CardRank.ACE, CardRank.FOUR));
    }
    
    @Test
    public void test4vs10() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }
    
    @Test
    public void test4vsA() {
        int numDecks = 4;
        
        BlackjackCard dealerUpCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
        BasicStrategy strategy = new BasicStrategy(rules, numDecks);
        
        player.initializeStrategy(rules, numDecks);
        
        assertTrue(strategy.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
        assertTrue(player.getAction(dealerUpCard, hand, 1) == BlackjackMove.HIT);
    }

}
