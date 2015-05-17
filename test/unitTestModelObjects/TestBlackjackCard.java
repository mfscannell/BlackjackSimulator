package unitTestModelObjects;
import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.PlayingCard;

import org.junit.Test;

import rules.BlackjackRules;
import enumerations.CardRank;
import enumerations.CardSuit;


public class TestBlackjackCard {
	private BlackjackRules.Builder blackjackRulesBuilder = new BlackjackRules.Builder();
	private BlackjackRules blackjackRules = blackjackRulesBuilder.build();

	@Test
	public void testTenIsTenValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.TEN, CardSuit.CLUBS);
		assertTrue(blackjackCard.isTenValue());
	}
	
	@Test
	public void testJackIsTenValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.JACK, CardSuit.HEARTS);
		assertTrue(blackjackCard.isTenValue());
	}
	
	@Test
	public void testQueenIsTenValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.QUEEN, CardSuit.HEARTS);
		assertTrue(blackjackCard.isTenValue());
	}
	
	@Test
	public void testKingIsTenValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.KING, CardSuit.DIAMONDS);
		assertTrue(blackjackCard.isTenValue());
	}
	
	@Test
	public void testIsNotTenValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		assertFalse(blackjackCard.isTenValue());
	}
}
