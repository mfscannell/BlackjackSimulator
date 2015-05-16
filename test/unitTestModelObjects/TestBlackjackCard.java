package unitTestModelObjects;
import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.PlayingCard;

import org.junit.Test;

import enumerations.CardRank;
import enumerations.CardSuit;


public class TestBlackjackCard {

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
	
	@Test
	public void testAce() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		assertTrue(blackjackCard.getValue() == 1);
	}
	
	@Test
	public void testTwoValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.TWO, CardSuit.HEARTS);
		assertTrue(blackjackCard.getValue() == 2);
	}
	
	@Test
	public void testJackValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.JACK, CardSuit.SPADES);
		assertTrue(blackjackCard.getValue() == 10);
	}
	
	@Test
	public void testQueenValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.QUEEN, CardSuit.HEARTS);
		assertTrue(blackjackCard.getValue() == 10);
	}
	
	@Test
	public void testKingValue() {
		BlackjackCard blackjackCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		assertTrue(blackjackCard.getValue() == 10);
	}

}
