package com.scannell.mark.casino.blackjack.strategies;

import com.scannell.mark.casino.blackjack.enumerations.BlackjackMove;
import com.scannell.mark.casino.blackjack.BlackjackHand;
import com.scannell.mark.casino.blackjack.BlackjackRules;
import com.scannell.mark.casino.playingCard.PlayingCard;

public abstract class BlackjackStrategy {
    public static final int BASIC_STRATEGY = 101;
    public static final int COMPOSITION_STRATEGY = 201;
    public static final int KISS_I_STRATEGY = 202;
    public static final int MARTINGALE_STRATEGY = 203;
    
    /**
     * Adjust the strategy's card count based upon the dealt card.
     * @param dealtCard  The card dealt from the shoe.
     */
    public abstract void adjustCount(PlayingCard dealtCard);
    
    /**
     * Get the strategy's recommended action (hit, stand, double, split).
     * @param dealerUpCard  The dealer's exposed card.
     * @param playerHand  The player's hand.
     * @param numPlayerHands  The number of hands the player has.
     * @return  The recommended action the player should play.
     */
    public abstract BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand playerHand, int numPlayerHands);

    /**
     * Get the bet size the strategy recommends.
     * @return  The recommended bet size.
     */
    public abstract double getBetSize();
    
    /**
     * Get the strategy's card count.
     * @return  The strategy's card count.
     */
    public abstract int getCount();
    
    /**
     * Get the recommended insurance action when insurance is offered by the dealer.
     * @return  True if the strategy recommends taking insurance.
     */
    public abstract boolean getInsuranceAction();
    
    /**
     * Update the strategy based upon the rules and the number of decks used at the table.
     * @param rules  The rules at the table.
     * @param numDecks  The number of decks in the shoe at the table.
     */
    public abstract void initialize(BlackjackRules rules, int numDecks);
    
    /**
     * Update the strategy based upon the winnings / losings of the player.
     * @param cashAdjustment
     */
    public abstract void notifyCashAdjustment(double cashAdjustment);
    
    /**
     * Reset the card count to the initial count.
     */
    public abstract void resetCount();
}
