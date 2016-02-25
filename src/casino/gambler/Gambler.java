package casino.gambler;

import java.util.Observer;

/**
 * This class represents a gambler.
 * @author mscannell
 *
 */
public abstract class Gambler implements Observer {
    protected double cashTotal;
    protected double betAmount;
    
    /**
     * Constructor
     * @param cashTotal  The gambler's initial cash total.
     */
    public Gambler(double cashTotal) {
        this.cashTotal = cashTotal;
        this.betAmount = 0;
    }
    
    /**
     * Adjusts the gambler's cash amount by the specified amount.
     * @param amount  The amount to adjust the gambler's cash total.
     */
    public void adjustCashTotal(double amount) {
        this.cashTotal = this.cashTotal + amount;
    }
    
    /**
     * Gets the amount of how much a gambler bets.
     * @return  The amount a gambler bets.
     */
    public double getBetAmount() {
        return this.betAmount;
    }
    
    /**
     * Get the gambler's cash total.
     * @return  The gambler's cash total.
     */
    public double getCashTotal() {
        return this.cashTotal;
    }
    
    /**
     * Sets how much the gambler will bet on his next wager.
     * @param betAmount  How much a gambler will bet.
     */
    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }
}
