package blackjackStrategies;

import rules.BlackjackRules;
import casino.blackjack.BlackjackHand;
import casino.playingCard.PlayingCard;
import enumerations.BlackjackMove;

public class NullStrategy extends BlackjackStrategy {

    @Override
    public BlackjackMove getAction(PlayingCard dealerUpCard, BlackjackHand playerHand, int numPlayerHands) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getInsuranceAction() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getBetSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void resetCount() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void adjustCount(PlayingCard dealtCard) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void initialize(BlackjackRules rules, int numDecks) {
        // TODO Auto-generated method stub
        
    }

}
