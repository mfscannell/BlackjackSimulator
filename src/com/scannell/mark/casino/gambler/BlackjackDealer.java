package com.scannell.mark.casino.gambler;

import java.util.Observable;

import com.scannell.mark.casino.blackjack.BlackjackHand;

public class BlackjackDealer extends Gambler {
    private double chipCount;
    private BlackjackHand hand;
    
    public BlackjackDealer() {
        super(0);
        this.chipCount = 0;
        this.hand = null;
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dealer\n");
        stringBuilder.append(this.hand.toString());
        
        return stringBuilder.toString();
    }
    
    public void setHand(BlackjackHand hand) {
        this.hand = hand;
    }

    @Override
    public void update(Observable observable, Object args) {
    }
}
