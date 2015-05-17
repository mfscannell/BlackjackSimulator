package unitTestRules;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

import org.junit.Test;

import rules.BasicStrategy;
import rules.BlackjackRules;
import rules.KISSIStrategy;
import enumerations.BlackjackMove;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidNumDecksException;

public class TestKISSIStrategy {

	@Test
	public void testMultiCantDouble() {
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setBlackjackPayoutMultiple(1.5);
		rulesBuilder.setDealerHitsSoft17(true);
		rulesBuilder.setDoubleAfterSplitAllowed(true);
		rulesBuilder.setMaxHandsAfterSplits(4);
		rulesBuilder.setCanResplitAces(false);
		BlackjackRules rules = rulesBuilder.build();
		
		try {
			KISSIStrategy kissIStrategy = new KISSIStrategy(rules, 2);
			
			BlackjackCard firstCard = new BlackjackCard(CardRank.TWO, CardSuit.HEARTS);
			BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.DIAMONDS);
			
			BlackjackHand hand = new BlackjackHand();
			hand.addCard(firstCard);
			hand.addCard(secondCard);
			hand.setFromSplit(false);
			
			BlackjackCard dealerUpCard = new BlackjackCard(CardRank.FIVE, CardSuit.DIAMONDS);
			
			BlackjackMove move = kissIStrategy.getAction(dealerUpCard, hand, rules, 2);
			
			assertTrue(move == BlackjackMove.DOUBLE);
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}

}
