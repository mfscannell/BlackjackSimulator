package com.scannell.mark.casino.blackjack.strategies;

import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.playingCard.PlayingCard;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;

public class CompositionStrategy extends BlackjackStrategyDecorator {
    private BlackjackStrategy blackjackStrategy;

    /**
     * Constructor.  The BasicStrategy.initialize method must be called after using this constructor to 
     * initialize the class.
     * @param blackjackStrategy  The blackjack strategy that will be referred to if this strategy cannot result in a 
     * recommended action.
     */
    public CompositionStrategy(BlackjackStrategy blackjackStrategy) {
        this.blackjackStrategy = blackjackStrategy;
    }
    
    @Override
    public void adjustCount(PlayingCard dealtCard) {
        this.blackjackStrategy.adjustCount(dealtCard);
    }
    
    @Override
    public BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand playerHand, int numPlayerHands) {
        BlackjackMove move = BlackjackMove.HIT;
        
        if (!playerHand.isSoft() && 
            playerHand.getBlackjackTotal() == 16 && 
            (playerHand.hasCardOfRank(CardRank.FOUR) || playerHand.hasCardOfRank(CardRank.FIVE)) &&
            dealerUpCard.getValue() == 10) {
            move = BlackjackMove.STAND;
        } else if (playerHand.isHand(CardRank.TEN, CardRank.TWO) && dealerUpCard.isValue(4)) {
            move = BlackjackMove.HIT;
        } else {
            move = this.blackjackStrategy.getAction(dealerUpCard, playerHand, numPlayerHands);
        }
        
        return move;
    }
    
    @Override
    public double getBetSize() {
        return 1;
    }
    
    @Override
    public int getCount() {
        return this.blackjackStrategy.getCount();
    }
    
    @Override
    public boolean getInsuranceAction() {
        return this.blackjackStrategy.getInsuranceAction();
    }
    
    @Override
    public void initialize(BlackjackRules rules, int numDecks) {
        this.blackjackStrategy.initialize(rules, numDecks);
    }
    
    @Override
    public void notifyCashAdjustment(double cashAdjustment) {
    }
    
    @Override
    public void resetCount() {
        //do nothing
    }
    
    public String toString() {
    	return "CompositionStrategy " + this.blackjackStrategy.toString();
    }
}
