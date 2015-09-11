package mockData;

import casino.blackjack.BlackjackHand;
import casino.playingCard.BlackjackCard;
import enumerations.CardRank;
import enumerations.CardSuit;

public class BlackjackHandPairs {
	private static BlackjackHand handPair2;
	private static BlackjackHand handPair3;
	private static BlackjackHand handPair4;
	private static BlackjackHand handPair5;
	private static BlackjackHand handPair6;
	private static BlackjackHand handPair7;
	private static BlackjackHand handPair8;
	private static BlackjackHand handPair9;
	private static BlackjackHand handPair10;
	private static BlackjackHand handPairA;
	
	public static BlackjackHand getPair2() {
		if (handPair2 == null) {
			handPair2 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.TWO, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.TWO, CardSuit.HEARTS);
			handPair2.addCard(firstCard);
			handPair2.addCard(secondCard);
		}
		
		return handPair2;
	}
	
	public static BlackjackHand getPair3() {
		if (handPair3 == null) {
			handPair3 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.THREE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.THREE, CardSuit.HEARTS);
			handPair3.addCard(firstCard);
			handPair3.addCard(secondCard);
		}
		
		return handPair3;
	}
	
	public static BlackjackHand getPair4() {
		if (handPair4 == null) {
			handPair4 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.FOUR, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.FOUR, CardSuit.HEARTS);
			handPair4.addCard(firstCard);
			handPair4.addCard(secondCard);
		}
		
		return handPair4;
	}
	
	public static BlackjackHand getPair5() {
		if (handPair5 == null) {
			handPair5 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.FIVE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.FIVE, CardSuit.HEARTS);
			handPair5.addCard(firstCard);
			handPair5.addCard(secondCard);
		}
		
		return handPair5;
	}
	
	public static BlackjackHand getPair6() {
		if (handPair6 == null) {
			handPair6 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.SIX, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.SIX, CardSuit.HEARTS);
			handPair6.addCard(firstCard);
			handPair6.addCard(secondCard);
		}
		
		return handPair6;
	}
	
	public static BlackjackHand getPair7() {
		if (handPair7 == null) {
			handPair7 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.SEVEN, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.SEVEN, CardSuit.HEARTS);
			handPair7.addCard(firstCard);
			handPair7.addCard(secondCard);
		}
		
		return handPair7;
	}
	
	public static BlackjackHand getPair8() {
		if (handPair8 == null) {
			handPair8 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.EIGHT, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.EIGHT, CardSuit.HEARTS);
			handPair8.addCard(firstCard);
			handPair8.addCard(secondCard);
		}
		
		return handPair8;
	}
	
	public static BlackjackHand getPair9() {
		if (handPair9 == null) {
			handPair9 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.NINE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.NINE, CardSuit.HEARTS);
			handPair9.addCard(firstCard);
			handPair9.addCard(secondCard);
		}
		
		return handPair9;
	}
	
	public static BlackjackHand getPair10() {
		if (handPair10 == null) {
			handPair10 = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.TEN, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.TEN, CardSuit.HEARTS);
			handPair10.addCard(firstCard);
			handPair10.addCard(secondCard);
		}
		
		return handPair10;
	}
	
	public static BlackjackHand getPairA() {
		if (handPairA == null) {
			handPairA = new BlackjackHand();
			BlackjackCard firstCard = new BlackjackCard(CardRank.ACE, CardSuit.SPADES);
			BlackjackCard secondCard = new BlackjackCard(CardRank.ACE, CardSuit.HEARTS);
			handPairA.addCard(firstCard);
			handPairA.addCard(secondCard);
		}
		
		return handPairA;
	}

}
