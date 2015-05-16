package unitTestModelObjects;
import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.PlayingCard;

import org.junit.Test;

import enumerations.CardRank;
import enumerations.CardSuit;


public class TestPlayingCard {

	@Test
	public void testIsAce() {
		PlayingCard card = new BlackjackCard(CardRank.ACE, CardSuit.CLUBS);
		assertTrue(card.isAce());
	}
	
	@Test
	public void testIsNotAce() {
		PlayingCard card = new BlackjackCard(CardRank.TWO, CardSuit.CLUBS);
		assertFalse(card.isAce());
	}
	
	@Test
	public void testIsFaceCard() {
		PlayingCard card = new BlackjackCard(CardRank.JACK, CardSuit.CLUBS);
		assertTrue(card.isFaceCard());
	}
	
	@Test
	public void testIsNotFaceCard() {
		PlayingCard card = new BlackjackCard(CardRank.FIVE, CardSuit.CLUBS);
		assertTrue(!card.isFaceCard());
	}

}
