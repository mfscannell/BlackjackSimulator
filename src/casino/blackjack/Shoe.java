package casino.blackjack;

import java.util.ArrayList;

import casino.blackjack.exceptions.InvalidShoeException;
import casino.playingCard.BlackjackCard;
import casino.playingCard.PlayingCard;

/**
 * A shoe of playing cards used to deal out playing cards at a blackjack table.
 * @author mscannell
 *
 */
public class Shoe {
    public static final int NUM_CARDS_PER_DECK = 52;
    public static final int SHUFFLES_PER_DECK = 2;
    public static final int MIN_NUM_DECKS = 1;
    public static final int MAX_NUM_DECKS = 8;
    public static final int MIN_DECK_PENETRATION = 1;
    public static final int MAX_DECK_PENETRATION = 99;
    private ArrayList<PlayingCard> shoe;
    private int numDecks;
    private int deckPenetration;
    
    /**
     * Constructor.  Creates a shoe containing a number of deck of 52 playing cards.
     * @param numDecks  The number of standard 52-card decks used in a shoe.
     * @param deckPenetration  The percentage of cards in the shoe dealt out before the
     * cut card is met.
     * @throws InvalidShoeException 
     */
    public Shoe(int numDecks, int deckPenetration) throws InvalidShoeException {
        checkNumDecksIsValid(numDecks);
        checkDeckPenetrationIsValid(deckPenetration);
        setShoeParameters(numDecks, deckPenetration);
        initializeShoe();
    }
    
    /**
     * Add a card to the shoe.
     * @param card  The card to be added to the shoe.
     */
    public void addCard(final PlayingCard card) {
        this.shoe.add(card);
    }
    
    /**
     * Remove a card from the shoe.
     * @return  A card from the shoe to be dealt out.
     */
    public PlayingCard dealCard() {
        PlayingCard dealtCard = this.shoe.remove(this.shoe.size() - 1);
        
        return dealtCard;
    }
    
    /**
     * Get the number of cards remaining in the shoe.
     * @return  The number of cards remaining in the shoe.
     */
    public int getNumCardsRemaining() {
        return this.shoe.size();
    }
    
    /**
     * Gets the number of decks used to create the shoe.
     * @return  The number of decks used to create the shoe.
     */
    public int getNumDecks() {
        return this.numDecks;
    }
    
    /**
     * Checks if no cards have been dealt from the shoe after refilling the shoe.
     * @return  True if the shoe is filled up.
     */
    public boolean isNewShoe() {
        boolean newShoe = false;
        
        if (this.shoe.size() == this.numDecks * NUM_CARDS_PER_DECK) {
            newShoe = true;
        }
        
        return newShoe;
    }
    
    public void print() {
        for (int i = 0; i < this.shoe.size(); i++) {
            System.out.println("" + i + ":" + this.shoe.get(i).toString());
        }
    }
    
    /**
     * Shuffles the cards in the shoe.
     */
    public void shuffleShoe() {
        for (int i = 0; i < SHUFFLES_PER_DECK * this.numDecks; i++) {
            for (int j = 0; j < this.shoe.size(); j++) {
                int swapIndex = (int) (Math.random() * this.shoe.size());
                PlayingCard temp = this.shoe.get(j);
                this.shoe.set(j, this.shoe.get(swapIndex));
                this.shoe.set(swapIndex, temp);
            }
        }
    }
    
    /**
     * Checks if the cut card was encountered in the shoe.
     * @return  True if the cut card was encountered.
     */
    public boolean wasCutCardMet() {
        boolean cutCardMet = false;
        double currentPenetration = 100.0 * (NUM_CARDS_PER_DECK * numDecks - this.shoe.size()) / (NUM_CARDS_PER_DECK * this.numDecks);
        
        if (currentPenetration >= this.deckPenetration) {
            cutCardMet = true;
        }
        
        return cutCardMet;
    }
    
    /**
     * Check if the specified deck penetration is neither too small nor too large.  The deck penetration signifies where in the
     * shoe the cut card will be located.
     * @param deckPenetration  The specified percentage of the shoe being dealt out prior to the cut card being encountered.
     * @throws InvalidShoeException
     */
    private void checkDeckPenetrationIsValid(int deckPenetration) throws InvalidShoeException {
        if (deckPenetration < MIN_DECK_PENETRATION || deckPenetration > MAX_DECK_PENETRATION) {
            throw new InvalidShoeException("Invalid deck penetration (" + deckPenetration + ")");
        }
    }
    
    /**
     * Checks if the specified number of decks of cards used to create the shoe is neither too small nor too large.
     * @param numDecks  The specified number of 52-card decks of playing cards used to create the shoe.
     * @throws InvalidShoeException
     */
    private void checkNumDecksIsValid(int numDecks) throws InvalidShoeException {
        if (numDecks < MIN_NUM_DECKS || numDecks > MAX_NUM_DECKS) {
            throw new InvalidShoeException("Invalid deck size (" + numDecks + ")");
        }
    }
    
    /**
     * Fill the shoe with all the playing cards in sorted order.
     */
    private void initializeShoe() {
        for (int i = 0; i < this.numDecks; i++) {
            for (int j = 0; j < PlayingCard.NUMBER_OF_SUITS; j++) {
                for (int k = 0; k < PlayingCard.NUMBER_OF_RANKS; k++) {
                    this.shoe.add(new BlackjackCard(PlayingCard.RANKS[k], PlayingCard.SUITS[j]));
                }
            }
        }
    }
    
    /**
     * Specify the parameters to construct the shoe.
     * @param numDecks  The number of 52-card decks of playing cards used to create the shoe.
     * @param deckPenetration  The specified percentage of the shoe being dealt out prior to the cut card being encountered.
     */
    private void setShoeParameters(int numDecks, int deckPenetration) {
        this.shoe = new ArrayList<PlayingCard>();
        this.numDecks = numDecks;
        this.deckPenetration = deckPenetration;
    }
}
