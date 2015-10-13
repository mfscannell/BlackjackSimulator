package casino.gambler;

import java.util.Observer;

public abstract class Gambler implements Observer {
    protected double cashTotal;
    protected double betAmount;
    
    /**
     * Constructor
     * @param cashTotal  The gambler's initial cash total.
     */
    public Gambler(double cashTotal) {
        this.cashTotal = cashTotal;
        betAmount = 0;
    }
    
    /**
     * Gets the amount of how much a gambler bets.
     * @return  The amount a gambler bets.
     */
    public double getBetAmount() {
        return betAmount;
    }
    
    /**
     * Sets how much the gambler will bet.
     * @param betAmount  How much a gambler will bet.
     */
    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }
    
    /**
     * Adjusts the gambler's cash amount by the specified amount.
     * @param amount  The amount to adjust the gambler's cash total.
     */
    public void adjustCashTotal(double amount) {
        cashTotal = cashTotal + amount;
    }
    
    public double getCashTotal() {
        return cashTotal;
    }
}
