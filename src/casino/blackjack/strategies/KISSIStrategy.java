package casino.blackjack.strategies;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackRules;
import casino.playingCard.PlayingCard;
import casino.blackjack.enumerations.BlackjackMove;
import casino.playingCard.enumerations.CardRank;

public class KISSIStrategy extends BlackjackStrategyDecorator {
    BlackjackStrategy blackjackStrategy;
    /**Initial Card Counts at the start of a new shoe based upon the number of decks used. */
    public static final int[] INITIAL_COUNTS = {20, 18, 17, 15, 14, 12, 10, 8, 6};
    public static final int[] WALK_AWAY = {14, 14, 14, 12, 10, 8, 6, 4, 2};
    private int initialCount;
    private int count;
    private BlackjackRules rules;
    private int numDecks;
    public static int KISS_COUNT = 20;
    
    /**
     * Constructor.  The BasicStrategy.initialize method must be called after using this constructor to 
     * initialize the class.
     * @param blackjackStrategy  The blackjack strategy that will be referred to if this strategy cannot result in a 
     * recommended action.
     */
    public KISSIStrategy(BlackjackStrategy blackjackStrategy) {
        this.blackjackStrategy = blackjackStrategy;
    }
    
    @Override
    public void adjustCount(PlayingCard card) {
        int cardValue = card.getValue();
        
        if ((cardValue == 2 && !card.isRedCard()) || 
            cardValue == 4 || 
            cardValue == 5 || 
            cardValue == 6) {
            this.count++;
        } else if (card.isFaceCard()) {
            this.count--;
        }
    }
    
    @Override
    public BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand playerHand, int numPlayerHands) {
        BlackjackMove move = BlackjackMove.STAND;
        
        if (this.count >= KISS_COUNT) {
            if (playerHand.getBlackjackTotal() == 9 && !playerHand.isSoft() && dealerUpCard.getValue() == 2) {
                move = BlackjackMove.DOUBLE;
            } else if (playerHand.getBlackjackTotal() == 11 && dealerUpCard.isAce()) {
                move = BlackjackMove.DOUBLE;
            } else if (playerHand.getBlackjackTotal() == 16 && dealerUpCard.getValue() == 10) {
                move = BlackjackMove.STAND;
            } else if (playerHand.isHand(CardRank.ACE, CardRank.SEVEN) && dealerUpCard.getValue() == 2) {
                move = BlackjackMove.DOUBLE;
            } else if (playerHand.isHand(CardRank.ACE, CardRank.EIGHT) && dealerUpCard.getValue() == 5) {
                move = BlackjackMove.DOUBLE;
            } else if (playerHand.isHand(CardRank.ACE, CardRank.EIGHT) && dealerUpCard.getValue() == 6) {
                move = BlackjackMove.DOUBLE;
            } else {
                move = this.blackjackStrategy.getAction(dealerUpCard, playerHand, numPlayerHands);
            }
        } else {
            move = this.blackjackStrategy.getAction(dealerUpCard, playerHand, numPlayerHands);
        }
        
        if (move == BlackjackMove.DOUBLE && playerHand.wasFromSplit() && !this.rules.isDoubleAfterSplitAllowed()) {
            move = BlackjackMove.HIT;
        }
        
        return move;
    }
    
    @Override
    public int getBetSize() {
        int betSize = 1;
        
        if (this.numDecks <= 2) {
            if (this.count <= 19) {
                betSize = 1;
            } else if (this.count == 20) {
                betSize = 2;
            } else if (this.count == 21) {
                betSize = 4;
            } else if (this.count >= 22) {
                betSize = 4;
            }
        } else if (4 <= this.numDecks && this.numDecks <= 8) {
            if (this.count <= 19) {
                betSize = 1;
            } else if (this.count == 20) {
                betSize = 2;
            } else if (this.count == 21) {
                betSize = 4;
            } else if (this.count >= 22) {
                betSize = 6;
            }
        }
        
        return betSize;
    }
    
    @Override
    public int getCount() {
        return this.count;
    }
    
    @Override
    public boolean getInsuranceAction() {
        boolean insurance = false;
        
        if (this.numDecks == 1 && this.count >= 21) {
            insurance = true;
        } else if (this.numDecks == 2 && this.count >= 22) {
            insurance = true;
        } else if (4 <= this.numDecks && this.numDecks <= 8 && this.count >= 25) {
            insurance = true;
        } else {
            insurance = this.blackjackStrategy.getInsuranceAction();
        }
        
        return insurance;
    }
    
    @Override
    public void initialize(BlackjackRules rules, int numDecks) {
        this.rules = rules;
        this.numDecks = numDecks;
        this.initialCount = INITIAL_COUNTS[numDecks];
        this.count = initialCount;
        
        this.blackjackStrategy.initialize(rules, numDecks);
    }
    
    @Override
    public void resetCount() {
        this.count = this.initialCount;
    }
    
    /**
     * Check to see if the card counter should walk away from an unfavorable table.
     * @return  True if the player should walk away.
     */
    public boolean shouldWalkAway() {
        boolean walk = false;
        
        if (this.count <= WALK_AWAY[this.numDecks]) {
            walk = true;
        }
        
        return walk;
    }
    
    public String toString() {
    	return "KISSIStrategy " + this.blackjackStrategy.toString();
    }
}
