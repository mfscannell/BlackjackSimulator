package casino.gambler;

import java.util.ArrayList;

import casino.blackjack.BlackjackHand;
import casino.blackjack.BlackjackTable;
import casino.playingCard.PlayingCard;
import rules.BlackjackRules;
import util.Observable;
import blackjackStrategies.BasicStrategy;
import blackjackStrategies.BlackjackStrategy;
import blackjackStrategies.CompositionStrategy;
import blackjackStrategies.KISSIStrategy;
import enumerations.BlackjackMove;

public class BlackjackPlayer extends Gambler {
	private boolean countsCards;
	private ArrayList<BlackjackHand> hands;
	private boolean insurance;
	private BlackjackStrategy blackjackStrategy;
	
	private static final String COUNTS_CARDS = "Counts cards";
	private static final String DOESNT_COUNTS_CARDS = "Doesn't counts cards";

	/**
	 * Constructor.
	 * @param cashTotal  The total amount of cash a player has.
	 * @param countsCards  True if the player counts cards.
	 */
	public BlackjackPlayer(double cashTotal, boolean countsCards) {
		super(cashTotal);
		this.countsCards = countsCards;
		hands = null;
		insurance = false;
	}
	
	/**
	 * Checks if a player takes insurance.
	 * @return  True if a player takes insurance.
	 */
	public boolean takesInsurance() {
		return insurance;
	}
	
	public void setCountsCards(boolean countsCards) {
		this.countsCards = countsCards;
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
	
	/**
	 * Checks whether or not the player is counting cards.
	 * @return  True if the player counts cards.
	 */
	public boolean doesCountsCards() {
		return countsCards;
	}
	
	public void update(final Observable observable, final Object args) {
		if (observable instanceof BlackjackTable) {
			BlackjackTable blackjackTable = (BlackjackTable)observable;
			BlackjackRules rules = blackjackTable.getRules();
			int numDecks = blackjackTable.getNumDecksInShoe();
			
			if (countsCards) {
				BlackjackStrategy basicStrategy = new BasicStrategy(rules, numDecks);
				BlackjackStrategy compositionStrategy = new CompositionStrategy(basicStrategy);
				blackjackStrategy = new KISSIStrategy(compositionStrategy, rules, numDecks);
			} else {
			    BlackjackStrategy basicStrategy = new BasicStrategy(rules, numDecks);
				blackjackStrategy = new CompositionStrategy(basicStrategy);
			}
		}
	}
	
	public void resetCount() {
		blackjackStrategy.resetCount();
	}
	
	public void adjustCount(PlayingCard dealtCard) {
		blackjackStrategy.adjustCount(dealtCard);
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
		
		if (countsCards) {
			stringBuilder.append(COUNTS_CARDS);
		} else {
			stringBuilder.append(DOESNT_COUNTS_CARDS);
		}
		stringBuilder.append("\n");
		
		if (insurance) {
			stringBuilder.append("Does Takes Insurance\n");
		} else {
			stringBuilder.append("Doesn't Takes Insurance\n");
		}
		
		return stringBuilder.toString();
	}
}
