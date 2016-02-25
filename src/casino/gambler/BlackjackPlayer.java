package casino.gambler;

import java.util.ArrayList;
import java.util.Observable;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackTable;
import casino.playingCard.PlayingCard;
import casino.blackjack.rules.BlackjackRules;
import casino.blackjack.blackjackStrategies.BasicStrategy;
import casino.blackjack.blackjackStrategies.BlackjackStrategy;
import casino.blackjack.blackjackStrategies.CompositionStrategy;
import casino.blackjack.blackjackStrategies.KISSIStrategy;
import casino.blackjack.blackjackStrategies.NullStrategy;
import casino.playingCard.enumerations.BlackjackMove;

public class BlackjackPlayer extends Gambler {
    private ArrayList<BlackjackHand> hands;
    private boolean insurance;
    private BlackjackStrategy blackjackStrategy;

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
     * Checks if a player takes insurance.
     * @return  True if a player takes insurance.
     */
    public boolean takesInsurance() {
        return this.insurance;
    }
    
    /**
     * Associates the hands with the player.
     * @param hands The hands to be associated with the player.
     */
    public void setHands(final ArrayList<BlackjackHand> hands) {
        this.hands = hands;
    }
    
    public void setTakesInsurance() {
        this.insurance = this.blackjackStrategy.getInsuranceAction();
    }
    
    public BlackjackMove getAction(PlayingCard dealerUpCard, BlackjackHand playerHand, int numHands) {
        return this.blackjackStrategy.getAction(dealerUpCard, playerHand, numHands);
    }
    
    public void resetCount() {
        this.blackjackStrategy.resetCount();
    }
    
    public void setBetAmount() {
        super.setBetAmount(this.blackjackStrategy.getBetSize());
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < this.hands.size(); i++) {
            stringBuilder.append(this.hands.get(i).toString());
            stringBuilder.append("\n");
        }
        
        stringBuilder.append("Cash:" + cashTotal + "\n");
        stringBuilder.append("Current bet:" + betAmount + "\n");
        stringBuilder.append("\n");
        
        if (this.insurance) {
            stringBuilder.append("Does Takes Insurance\n");
        } else {
            stringBuilder.append("Doesn't Takes Insurance\n");
        }
        
        return stringBuilder.toString();
    }
    
    /**
     * Add a new layer of blackjack strategy on top of the current layer.  The new blackjack strategy layer
     * will be referred to when getting a strategy.  If that layer cannot provide a move, the layer will obtain a move
     * from the next lower layer.
     * @param strategyDescription The description for the strategy layer.
     */
    public void addStrategyLayer(int strategyDescription) {
        if (strategyDescription == BlackjackStrategy.BASIC_STRATEGY) {
            resetBaseStrategy(strategyDescription);
        } else {
            enhanceStrategy(strategyDescription);
        }
    }
    
    public void resetBaseStrategy(int basicStrategyDescription) {
        if (basicStrategyDescription == BlackjackStrategy.BASIC_STRATEGY) {
            this.blackjackStrategy = new BasicStrategy();
        }
    }
    
    public void enhanceStrategy(int strategyDecoratorDescription) {
        switch (strategyDecoratorDescription) {
            case BlackjackStrategy.COMPOSITION_STRATEGY:
                this.blackjackStrategy = new CompositionStrategy(this.blackjackStrategy);
                break;
            case BlackjackStrategy.KISS_I_STRATEGY:
                this.blackjackStrategy = new KISSIStrategy(this.blackjackStrategy);
                break;
            default:
                break;
        }
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
}
