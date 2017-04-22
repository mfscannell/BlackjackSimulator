package com.scannell.mark.casino.blackjack.strategies;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.playingCard.PlayingCard;

public class MartingaleStrategy extends BlackjackStrategyDecorator {
    private BlackjackStrategy blackjackStrategy;
    private double maxBetSize;
    private double betSize;
    private double initialBetSize;
    private double betFactor;
    
    public MartingaleStrategy(BlackjackStrategy blackjackStrategy, int initialBetSize, int maxBetSize) {
        this.blackjackStrategy = blackjackStrategy;
        this.initialBetSize = initialBetSize;
        this.maxBetSize = maxBetSize;
        this.betFactor = 1;
        this.betSize = initialBetSize;
    }

    @Override
    public void adjustCount(PlayingCard dealtCard) {
        this.blackjackStrategy.adjustCount(dealtCard);
    }

    @Override
    public BlackjackMove getAction(PlayingCard dealerUpCard, BlackjackHand playerHand, int numPlayerHands) {
        return this.blackjackStrategy.getAction(dealerUpCard, playerHand, numPlayerHands);
    }

    @Override
    public double getBetSize() {
        if (this.betSize < this.initialBetSize) {
            this.betSize = this.initialBetSize;
        }
        
        if (this.betSize > this.maxBetSize) {
            this.betSize = this.maxBetSize;
        }
        
        return this.betSize;
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
        this.betSize = this.betSize - cashAdjustment;
    }

    @Override
    public void resetCount() {
        // do nothing
    }

    public String toString() {
        return "MartingaleStrategy " + this.blackjackStrategy.toString();
    }
}
