package unitTestModelObjects;

import static org.junit.Assert.*;
import modelObjects.BlackjackCard;
import modelObjects.Shoe;

import org.junit.Test;

import exceptions.InvalidShoeException;

public class TestShoe {

	@Test
	public void testWasCutCardMetBound() {
		Shoe shoe;
		try {
			shoe = new Shoe(6, 66);
			for (int i = 0; i < 206; i++) {
				shoe.dealCard();
			}
			
			assertTrue(shoe.wasCutCardMet());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWasCutCardMet() {
		Shoe shoe;
		try {
			shoe = new Shoe(6, 66);
			for (int i = 0; i < 250; i++) {
				shoe.dealCard();
			}
			
			assertTrue(shoe.wasCutCardMet());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testWasNotCutCardMetBound() {
		Shoe shoe;
		try {
			shoe = new Shoe(6, 66);
			
			for (int i = 0; i < 205; i++) {
				shoe.dealCard();
			}
			
			assertFalse(shoe.wasCutCardMet());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWasNotCutCardMet() {
		Shoe shoe;
		try {
			shoe = new Shoe(6, 66);
			
			for (int i = 0; i < 20; i++) {
				shoe.dealCard();
			}
			
			assertFalse(shoe.wasCutCardMet());
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testShuffleOneDeck() {
		Shoe shoe;
		try {
			shoe = new Shoe(1, 66);
			
			System.out.println("************************************************");
			System.out.println("TestShoe.testShuffleOneDeck");
			System.out.println("---------------------------");
			shoe.shuffleShoe();
			shoe.print();
			System.out.println("************************************************");
		} catch (InvalidShoeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testShuffleTwoDeck() {
		Shoe shoe;
		try {
			shoe = new Shoe(2, 66);
			System.out.println("************************************************");
			System.out.println("TestShoe.testShuffleTwoDeck");
			System.out.println("---------------------------");
			shoe.shuffleShoe();
			shoe.print();
			System.out.println("************************************************");
		} catch (InvalidShoeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemainingCards() {
		Shoe shoe;
		try {
			shoe = new Shoe(1, 66);
			System.out.println("************************************************");
			System.out.println("TestShoe.testRemainingCards");
			System.out.println("---------------------------");
			System.out.println("Starting cards");
			shoe.print();
			shoe.shuffleShoe();
			System.out.println("Starting cards");
			shoe.print();
			
			for (int i = 0; i < 10; i++) {
				BlackjackCard card = shoe.dealCard();
				System.out.println("Dealt card:" + card);
			}
			
			System.out.println("Remaining cards");
			shoe.print();
			System.out.println("************************************************");
		} catch (InvalidShoeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = InvalidShoeException.class)
	public void testNumDecksTooLarge() throws InvalidShoeException {
		int numDecks = 9;
		int deckPenetration = 50;
		
		Shoe shoe = new Shoe(numDecks, deckPenetration);
	}

}
