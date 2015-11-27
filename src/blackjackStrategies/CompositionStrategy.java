package blackjackStrategies;

import rules.BlackjackRules;
import casino.blackjack.BlackjackHand;
import casino.playingCard.PlayingCard;
import enumerations.BlackjackMove;
import enumerations.CardRank;

public class CompositionStrategy extends BlackjackStrategyDecorator {
    BlackjackStrategy blackjackStrategy;

    /**
     * Constructor
     * @param rules  The specific rules at the table.
     * @param numDecks  The number of decks used in the shoe.
     * @throws InvalidNumDecksException  The number of decks specified must be between
     * 1 and 8.
     */
    public CompositionStrategy(BlackjackStrategy blackjackStrategy) {
        this.blackjackStrategy = blackjackStrategy;
    }
    
    public BlackjackMove getAction(final PlayingCard dealerUpCard, final BlackjackHand playerHand, int numPlayerHands) {
        BlackjackMove move = BlackjackMove.HIT;
        
        if (!playerHand.isSoft() && 
            playerHand.getBlackjackTotal() == 16 && 
            (playerHand.hasCardOfRank(CardRank.FOUR) || playerHand.hasCardOfRank(CardRank.FIVE)) &&
            dealerUpCard.getValue() == 10) {
            move = BlackjackMove.STAND;
        } else if (playerHand.isHand(CardRank.TEN, CardRank.TWO) && dealerUpCard.getValue() == 4) {
            move = BlackjackMove.HIT;
        } else {
            move = blackjackStrategy.getAction(dealerUpCard, playerHand, numPlayerHands);
        }
        
        return move;
    }
    
    public int getBetSize() {
        return 1;
    }
    
    public void resetCount() {
        //do nothing
    }
    
    public int getCount() {
        return blackjackStrategy.getCount();
    }
    
    public boolean getInsuranceAction() {
        return blackjackStrategy.getInsuranceAction();
    }
    
    public void adjustCount(PlayingCard dealtCard) {
        blackjackStrategy.adjustCount(dealtCard);
    }
    
    public void initialize(BlackjackRules rules, int numDecks) {
        this.blackjackStrategy.initialize(rules, numDecks);
    }
}
