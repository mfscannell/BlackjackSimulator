package casino.gambler;

import java.util.ArrayList;
import java.util.Observable;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackTable;
import casino.playingCard.PlayingCard;
import rules.BlackjackRules;
import blackjackStrategies.BasicStrategy;
import blackjackStrategies.BlackjackStrategy;
import blackjackStrategies.CompositionStrategy;
import blackjackStrategies.KISSIStrategy;
import blackjackStrategies.NullStrategy;
import enumerations.BlackjackMove;

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
        hands = null;
        insurance = false;
        blackjackStrategy = new NullStrategy();
    }
    
    /**
     * Checks if a player takes insurance.
     * @return  True if a player takes insurance.
     */
    public boolean takesInsurance() {
        return insurance;
    }
    
    /**
     * Associates the hands with the player.
     * @param hands The hands to be associated with the player.
     */
    public void setHands(final ArrayList<BlackjackHand> hands) {
        this.hands = hands;
    }
    
    /**
     * Sets whether or not a player takes insurance when the dealer offers insurance.
     * @param insurance  True if the player takes insurance.
     */
    public void setTakesInsurance(boolean insurance) {
        this.insurance = insurance;
    }
    
    public void setTakesInsurance() {
        this.insurance = blackjackStrategy.getInsuranceAction();
    }
    
    public BlackjackMove getAction(PlayingCard dealerUpCard, BlackjackHand playerHand, int numHands) {
        return blackjackStrategy.getAction(dealerUpCard, playerHand, numHands);
    }
    
    public void resetCount() {
        blackjackStrategy.resetCount();
    }
    
    public void setBetAmount() {
        super.setBetAmount(blackjackStrategy.getBetSize());
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < hands.size(); i++) {
            stringBuilder.append(hands.get(i).toString());
            stringBuilder.append("\n");
        }
        
        stringBuilder.append("Cash:" + cashTotal + "\n");
        stringBuilder.append("Current bet:" + betAmount + "\n");
        stringBuilder.append("\n");
        
        if (insurance) {
            stringBuilder.append("Does Takes Insurance\n");
        } else {
            stringBuilder.append("Doesn't Takes Insurance\n");
        }
        
        return stringBuilder.toString();
    }
    
    public void addStrategyLayer(int strategyDescription) {
        if (strategyDescription == BlackjackStrategy.BASIC_STRATEGY) {
            resetBaseStrategy(strategyDescription);
        } else {
            enhanceStrategy(strategyDescription);
        }
    }
    
    public void resetBaseStrategy(int basicStrategyDescription) {
        if (basicStrategyDescription == BlackjackStrategy.BASIC_STRATEGY) {
            blackjackStrategy = new BasicStrategy();
        }
    }
    
    public void enhanceStrategy(int strategyDecoratorDescription) {
        switch (strategyDecoratorDescription) {
            case BlackjackStrategy.COMPOSITION_STRATEGY:
                blackjackStrategy = new CompositionStrategy(blackjackStrategy);
                break;
            case BlackjackStrategy.KISS_I_STRATEGY:
                blackjackStrategy = new KISSIStrategy(blackjackStrategy);
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
            blackjackStrategy.adjustCount(card);
        }
    }
}
