package unitTestModelObjects;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;
import modelObjects.PlayingCard;

import org.junit.Test;

import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidMoveException;

public class TestBlackjackHand {
	
	@Test
	public void testGetNumCards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.getNumCards() == 2);
	}
	
	@Test
	public void testValueNoAceNoBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.getBlackjackTotal() == 9);
	}
	
	@Test
	public void testValueNoAceBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertTrue(hand.getBlackjackTotal() == 23);
	}
	
	@Test
	public void testValueSoftAceNoBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.getBlackjackTotal() == 15);
	}
	
	@Test
	public void testValueTwoAces() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.getBlackjackTotal() == 12);
	}
	
	@Test
	public void testValueHardAceNoBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertTrue(hand.getBlackjackTotal() == 16);
	}
	
	@Test
	public void testValueHardAceBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.CLUBS);
		BlackjackCard fourthCard = new BlackjackCard(CardRank.NINE, CardSuit.CLUBS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		hand.addCard(fourthCard);
		
		assertTrue(hand.getBlackjackTotal() == 25);
	}
	
	@Test
	public void testBustNoAceNotBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isBust());
	}
	
	@Test
	public void testBustNoAceBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertTrue(hand.isBust());
	}
	
	@Test
	public void testBustSoftAceNoBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isBust());
	}
	
	@Test
	public void testBustHardAceNoBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertFalse(hand.isBust());
	}
	
	@Test
	public void testBustHardAceBust() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard fourthCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		hand.addCard(fourthCard);
		
		assertTrue(hand.isBust());
	}
	
	@Test
	public void testIsPair() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.isPair());
	}
	
	@Test
	public void testIsNotPairTwoCards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isPair());
	}
	
	@Test
	public void testIsNotPairOneCard() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		
		assertFalse(hand.isPair());
	}
	
	@Test
	public void testIsNotPairManyCards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.HEARTS);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertFalse(hand.isPair());
	}
	
	@Test
	public void testIsPairAces() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.isPairAces());
	}
	
	@Test
	public void testIsNotPairAces() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isPairAces());
	}
	
	@Test
	public void testIsNotPairAcesManyCards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertFalse(hand.isPairAces());
	}
	
	@Test
	public void testSplitIsReturnCard() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		PlayingCard returnCard = null;
		
		returnCard = hand.split();
		
		assertTrue(returnCard.equals(secondCard) && hand.getNumCards() == 1);
	}
	
	@Test
	public void testIsSoftHand2Cards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.isSoft());
	}
	
	@Test
	public void testIsSoftHand3Cards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertTrue(hand.isSoft());
	}
	
	@Test
	public void testIsNotSoftHand2Cards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isSoft());
	}
	
	@Test
	public void testIsNotSoftHand3Cards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.FOUR, CardSuit.DIAMONDS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertFalse(hand.isSoft());
	}
	
	@Test
	public void testUpCardIsAce() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.isFirstCardAce());
	}
	
	@Test
	public void testUpCardIsNotAce() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isFirstCardAce());
	}
	
	@Test
	public void testUpCardIsNotAce2() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isFirstCardAce());
	}
	
	@Test
	public void testHasRank() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.hasCardOfRank(CardRank.NINE));
	}
	
	@Test
	public void testDoesntHasRank() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.hasCardOfRank(CardRank.SEVEN));
	}
	
	@Test
	public void testIsBlackjack() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.isBlackjack());
	}
	
	@Test
	public void testIsNotBlackjack() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.KING, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isBlackjack());
	}
	
	@Test
	public void testIsNotBlackjackSplit() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		PlayingCard card = hand.split();
		
		BlackjackHand secondHand = new BlackjackHand(card);
		
		BlackjackCard thirdCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
		BlackjackCard fourthCard = new BlackjackCard(CardRank.TEN, CardSuit.HEARTS);
		
		hand.addCard(thirdCard);
		secondHand.addCard(fourthCard);
		
		assertFalse(hand.isBlackjack());
		assertFalse(secondHand.isBlackjack());
	}
	
	@Test
	public void testIsNotBlackjack11() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isBlackjack());
	}
	
	@Test
	public void testHasExactlyTwoCards() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.hasExactlyTwoCards());
	}
	
	@Test
	public void testHasExactlyTwoCardsFails() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
		BlackjackCard thirdCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		hand.addCard(thirdCard);
		
		assertFalse(hand.hasExactlyTwoCards());
	}
	
	@Test
	public void testIsHand() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertTrue(hand.isHand(CardRank.EIGHT, CardRank.THREE));
		assertTrue(hand.isHand(CardRank.THREE, CardRank.EIGHT));
	}
	
	@Test
	public void testIsHandFalse() {
		BlackjackHand hand = new BlackjackHand();
		BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
		BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
		
		hand.addCard(firstCard);
		hand.addCard(secondCard);
		
		assertFalse(hand.isHand(CardRank.EIGHT, CardRank.FIVE));
	}

}
