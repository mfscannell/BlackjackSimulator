package mockData;

import enumerations.CardRank;
import enumerations.CardSuit;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackHand;

public class BlackjackHandSoft {
	private static BlackjackHand handAce2;
	private static BlackjackHand handAce3;
	private static BlackjackHand handAce4;
	private static BlackjackHand handAce5;
	private static BlackjackHand handAce6;
	private static BlackjackHand handAce7;
	private static BlackjackHand handAce8;
	private static BlackjackHand handAce9;
	private static BlackjackHand handAce10;
	
	public static BlackjackHand getHandAce2() {
		if (handAce2 == null) {
			handAce2 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.HEARTS);
			handAce2.addCard(firstCard);
			handAce2.addCard(secondCard);
		}
		
		return handAce2;
	}
	
	public static BlackjackHand getHandAce3() {
		if (handAce3 == null) {
			handAce3 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
			handAce3.addCard(firstCard);
			handAce3.addCard(secondCard);
		}
		
		return handAce3;
	}
	
	public static BlackjackHand getHandAce4() {
		if (handAce4 == null) {
			handAce4 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS);
			handAce4.addCard(firstCard);
			handAce4.addCard(secondCard);
		}
		
		return handAce5;
	}
	
	public static BlackjackHand getHandAce5() {
		if (handAce5 == null) {
			handAce5 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
			handAce5.addCard(firstCard);
			handAce5.addCard(secondCard);
		}
		
		return handAce5;
	}
	
	public static BlackjackHand getHandAce6() {
		if (handAce6 == null) {
			handAce6 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.HEARTS);
			handAce6.addCard(firstCard);
			handAce6.addCard(secondCard);
		}
		
		return handAce6;
	}
	
	public static BlackjackHand getHandAce7() {
		if (handAce7 == null) {
			handAce7 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
			handAce7.addCard(firstCard);
			handAce7.addCard(secondCard);
		}
		
		return handAce7;
	}
	
	public static BlackjackHand getHandAce8() {
		if (handAce8 == null) {
			handAce8 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.HEARTS);
			handAce8.addCard(firstCard);
			handAce8.addCard(secondCard);
		}
		
		return handAce8;
	}
	
	public static BlackjackHand getHandAce9() {
		if (handAce9 == null) {
			handAce9 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
			handAce9.addCard(firstCard);
			handAce9.addCard(secondCard);
		}
		
		return handAce9;
	}
	
	public static BlackjackHand getHandAceTen() {
		if (handAce10 == null) {
			handAce10 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.HEARTS);
			handAce10.addCard(firstCard);
			handAce10.addCard(secondCard);
		}
		
		return handAce10;
	}

}
