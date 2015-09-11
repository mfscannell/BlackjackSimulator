package blackjackStrategies;

import casino.blackjack.BlackjackHand;
import casino.playingCard.PlayingCard;
import enumerations.BlackjackMove;

public abstract class BlackjackStrategy {
	public abstract BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand hand, int numHands);

	public abstract boolean getInsuranceAction();
	
	public abstract int getBetSize();
	
	public abstract void resetCount();
	
	public abstract void adjustCount(PlayingCard dealtCard);
	
	public abstract int getCount();
}
