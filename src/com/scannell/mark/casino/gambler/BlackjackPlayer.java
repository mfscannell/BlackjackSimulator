package com.scannell.mark.casino.gambler;

import java.util.ArrayList;
import java.util.Observable;

import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.blackjack.BlackjackTable;
import com.scannell.mark.casino.playingCard.PlayingCard;
import com.scannell.mark.casino.blackjack.strategies.BasicStrategy;
import com.scannell.mark.casino.blackjack.strategies.BlackjackStrategy;
import com.scannell.mark.casino.blackjack.strategies.CompositionStrategy;
import com.scannell.mark.casino.blackjack.strategies.KISSIStrategy;
import com.scannell.mark.casino.blackjack.strategies.MartingaleStrategy;
import com.scannell.mark.casino.blackjack.strategies.NullStrategy;
import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.blackjack.enumerations.Strategy;

public class BlackjackPlayer extends Gambler {
    private ArrayList<BlackjackHand> hands;
    private boolean insurance;
    private BlackjackStrategy blackjackStrategy;
    private double maxBet;
    private double maxCash;
    private double minCash;

    /**
     * Constructor.
     * @param cashTotal  The total amount of cash a player has.
     */
    public BlackjackPlayer(double cashTotal) {
        super(cashTotal);
        this.hands = null;
        this.insurance = false;
        this.blackjackStrategy = new NullStrategy();
    }
    
    /**
     * Add a new layer of blackjack strategy on top of the current layer.  If the strategy layer is a base layer, then the
     * player's strategy will reset to the base layer and there will be no other layers of strategy.  The new blackjack 
     * strategy layer will be referred to when getting a strategy.  If that layer cannot provide a move, the layer will 
     * obtain a move from the next lower layer.  After the final layer of strategy has been added, the method
     * initializeStrategy will have to be called to initialize the player's strategy.
     * @param strategyDescription The description for the strategy layer.
     */
    public void addStrategyLayer(Strategy strategyDescription) {
        if (strategyDescription == Strategy.BASIC_STRATEGY) {
            this.resetBaseStrategy(strategyDescription);
        } else {
            this.enhanceStrategy(strategyDescription);
        }
    }
    
    @Override
    public void adjustCashTotal(double amount) {
        super.adjustCashTotal(amount);
        
        if (this.cashTotal < this.minCash) {
            this.minCash = this.cashTotal;
        }
        
        if (this.cashTotal > this.maxCash)  {
            this.maxCash = this.cashTotal;
        }
        
        this.blackjackStrategy.notifyCashAdjustment(amount);
    }
    
    /**
     * Get the move (Hit, stand, split, double down) the player will make.
     * @param dealerUpCard  The dealer's exposed card.
     * @param playerHand  The hand the player makes a move on.
     * @param numHands  The number of hands the player has after splits and re-splits.
     * @return  The move the player makes on the hand.
     */
    public BlackjackMove getAction(PlayingCard dealerUpCard, BlackjackHand playerHand, int numHands) {
        return this.blackjackStrategy.getAction(dealerUpCard, playerHand, numHands);
    }
    
    /**
     * Sets up the blackjack move strategy based upon the rules at the table and the number of decks
     * in the shoe at the table.
     * @param rules The rules at the table.
     * @param numDecks  The number of decks in the shoe at the table.
     */
    public void initializeStrategy(BlackjackRules rules, int numDecks) {
        this.blackjackStrategy.initialize(rules, numDecks);
    }
    
    /**
     * Remove all layers of the player's strategy and replace it with a base strategy.
     * @param basicStrategyDescription  The base strategy to reset the player's strategy to.
     */
    public void resetBaseStrategy(Strategy basicStrategyDescription) {
        if (basicStrategyDescription == Strategy.BASIC_STRATEGY) {
            this.blackjackStrategy = new BasicStrategy();
        }
    }
    
    /**
     * Reset the player's strategy card count.  After resetting the player's strategy, the method initializeStrategy
     * will have to be called to initialize the strategy with the rules and the number of decks in the shoe.
     */
    public void resetCount() {
        this.blackjackStrategy.resetCount();
    }
    
    /**
     * Set the bet amount according to the player's strategy.
     */
    public void setBetAmount() {
        super.setBetAmount(this.blackjackStrategy.getBetSize());
        
        if (this.betAmount > this.maxBet) {
            this.maxBet = this.betAmount;
        }
    }
    
    /**
     * Associates the hands with the player.
     * @param hands The hands to be associated with the player.
     */
    public void setHands(final ArrayList<BlackjackHand> hands) {
        this.hands = hands;
    }
    
    /**
     * Checks if a player takes insurance.
     * @return  True if a player takes insurance.
     */
    public boolean takesInsurance() {
        return this.blackjackStrategy.getInsuranceAction();
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("Bet this round:" + betAmount + "\n");
        
        if (this.insurance) {
            stringBuilder.append("Does Takes Insurance\n");
        } else {
            stringBuilder.append("Doesn't Takes Insurance\n");
        }
        
        for (int i = 0; i < this.hands.size(); i++) {
            stringBuilder.append(this.hands.get(i).toString());
            stringBuilder.append("\n");
        }
        
        stringBuilder.append("Cash at end of round:" + cashTotal + "\n");
        stringBuilder.append("Count at end of round:" + this.blackjackStrategy.getCount() + "\n");
        stringBuilder.append("Max bet:" + this.maxBet + "\n");
        stringBuilder.append("Min cash:" + this.minCash + "\n");
        stringBuilder.append("Max cash:" + this.maxCash + "\n");
        stringBuilder.append("\n");
        
        
        
        return stringBuilder.toString();
    }
    
    /**
     * Gets the player's strategy in string form.
     * @return
     */
    public String toStringStrategy() {
    	return this.blackjackStrategy.toString();
    }

    @Override
    /**
     * Notifies the player of the playing card that was dealt so they can adjust their card count.
     * @param arg0 The BlackjackTable
     * @param arg1 The Playing card that was dealt from the shoe and exposed to the players.
     */
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof PlayingCard) {
            PlayingCard card = (PlayingCard)arg1;
            this.blackjackStrategy.adjustCount(card);
        }
    }
    
    /**
     * Add an additional layer of blackjack strategy to the player's strategy.
     * @param strategyDecoratorDescription
     */
    private void enhanceStrategy(Strategy strategyDecoratorDescription) {
    	if (strategyDecoratorDescription == Strategy.COMPOSITION_STRATEGY) {
    		this.blackjackStrategy = new CompositionStrategy(this.blackjackStrategy);
    	} else if (strategyDecoratorDescription == Strategy.KISS_I_STRATEGY) {
    		this.blackjackStrategy = new KISSIStrategy(this.blackjackStrategy);
    	} else if (strategyDecoratorDescription == Strategy.MARTINGALE_STRATEGY) {
    	    this.blackjackStrategy = new MartingaleStrategy(this.blackjackStrategy, 10, 1000);
    	}
    }
}
