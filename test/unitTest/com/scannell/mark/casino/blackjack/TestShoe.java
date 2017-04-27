package unitTest.com.scannell.mark.casino.blackjack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.scannell.mark.casino.blackjack.Shoe;
import com.scannell.mark.casino.blackjack.exceptions.InvalidShoeException;
import com.scannell.mark.casino.playingCard.PlayingCard;

public class TestShoe {
    @Test
    public void Shoe_numDecksValid_noException() {
        Shoe shoe;
        try {
            shoe = new Shoe(3, 50);
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(expected=InvalidShoeException.class)
    public void Shoe_numDecksTooLow_InvalidShoeException() throws InvalidShoeException {
        Shoe shoe = new Shoe(0, 50);
    }
    
    @Test(expected=InvalidShoeException.class)
    public void Shoe_numDecksTooHigh_InvalidNumDecks() throws InvalidShoeException {
        Shoe shoe = new Shoe(9, 50);
    }
    
    @Test
    public void Shoe_validPenetration_noException() {
        Shoe shoe;
        try {
            shoe = new Shoe(6, 2);
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test(expected=InvalidShoeException.class)
    public void Shoe_penetrationTooLow_InvalidShoeException() throws InvalidShoeException {
        Shoe shoe = new Shoe(6, 0);
    }
    
    @Test(expected=InvalidShoeException.class)
    public void Shoe_penetrationTooHigh_InvalidShoeException() throws InvalidShoeException {
        Shoe shoe = new Shoe(6, 100);
    }
    
    @Test
    public void getNumCardsRemaining_dealTenCards_42() {
        Shoe shoe;
        try {
            shoe = new Shoe(1, 66);
            for (int i = 0; i < 10; i++) {
                shoe.dealCard();
            }
            
            assertTrue(shoe.getNumCardsRemaining() == 42);
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void wasCutCardMet_dealBound_false() {
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
    public void wasCutCardMet_dealBound_true() {
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
    public void wasCutCardMet_dealAlot_true() {
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
    public void wasCutCardMet_dealFew_false() {
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
    public void isNewShoe_dealNoCards_true() {
        Shoe shoe;
        try {
            shoe = new Shoe(6, 66);
            
            assertTrue(shoe.isNewShoe());
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void isNewShoe_dealCard_false() {
        Shoe shoe;
        try {
            shoe = new Shoe(6, 66);
            
            shoe.dealCard();
            
            assertFalse(shoe.isNewShoe());
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shuffleShoe_oneDeck() {
        Shoe shoe;
        try {
            shoe = new Shoe(1, 66);
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shuffleShoe_twoDecks() {
        Shoe shoe;
        try {
            shoe = new Shoe(2, 66);
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shuffleShoe_sixDecks() {
        Shoe shoe;
        try {
            shoe = new Shoe(6, 66);
            System.out.println("************************************************");
            System.out.println("TestShoe.testShuffleSixDeck");
            System.out.println("---------------------------");
            shoe.shuffleShoe();
            System.out.println("---------------------------");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }
            shoe.print();
            System.out.println("************************************************");
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testRemainingCards() {
        Shoe shoe;
        try {
            shoe = new Shoe(1, 66);
//            System.out.println("************************************************");
//            System.out.println("TestShoe.testRemainingCards");
//            System.out.println("---------------------------");
//            System.out.println("Starting cards");
            //shoe.print();
            //shoe.shuffleShoe();
//            System.out.println("Starting cards");
//            shoe.print();
            
//            for (int i = 0; i < 10; i++) {
//                PlayingCard card = shoe.dealCard();
//                System.out.println("Dealt card:" + card);
//            }
            
//            System.out.println("Remaining cards");
//            shoe.print();
//            System.out.println("************************************************");
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
}
