package blackjackStrategies;

import rules.BlackjackRules;
import casino.blackjack.BlackjackHand;
import casino.playingCard.PlayingCard;
import enumerations.BlackjackMove;

public abstract class BlackjackStrategy {
    public static final int BASIC_STRATEGY = 101;
    public static final int COMPOSITION_STRATEGY = 201;
    public static final int KISS_I_STRATEGY = 202;
    
    public abstract BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand playerHand, int numPlayerHands);

    public abstract boolean getInsuranceAction();
    
    public abstract int getBetSize();
    
    public abstract void resetCount();
    
    public abstract void adjustCount(PlayingCard dealtCard);
    
    public abstract int getCount();
    
    public abstract void initialize(BlackjackRules rules, int numDecks);
}
