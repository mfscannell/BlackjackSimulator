package blackjackStrategies;

import rules.BlackjackRules;
import casino.blackjack.BlackjackHand;
import casino.playingCard.PlayingCard;
import enumerations.BlackjackMove;

public class NullStrategy extends BlackjackStrategy {
	@Override
    public void adjustCount(PlayingCard dealtCard) {
    }

    @Override
    public BlackjackMove getAction(PlayingCard dealerUpCard, BlackjackHand playerHand, int numPlayerHands) {
        return null;
    }
    
    @Override
    public int getBetSize() {
        return 0;
    }
    
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean getInsuranceAction() {
        return false;
    }
    
    @Override
    public void initialize(BlackjackRules rules, int numDecks) {
    }
    
    @Override
    public void resetCount() {
    }
}
