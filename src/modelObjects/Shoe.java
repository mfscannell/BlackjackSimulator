package modelObjects;

import java.util.ArrayList;

import exceptions.InvalidShoeException;

public class Shoe {
	public static final int NUM_CARDS_PER_DECK = 52;
	public static final int SHUFFLES_PER_DECK = 2;
	public static final int MIN_NUM_DECKS = 1;
	public static final int MAX_NUM_DECKS = 8;
	public static final int MIN_DECK_PENETRATION = 1;
	public static final int MAX_DECK_PENETRATION = 99;
	private ArrayList<BlackjackCard> shoe;
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
		if (numDecks < MIN_NUM_DECKS || numDecks > MAX_NUM_DECKS) {
			throw new InvalidShoeException("Invalid deck size (" + numDecks + ")");
		}
		
		if (deckPenetration < MIN_DECK_PENETRATION || deckPenetration > MAX_DECK_PENETRATION) {
			throw new InvalidShoeException("Invalid deck penetration (" + deckPenetration + ")");
		}
		
		shoe = new ArrayList<BlackjackCard>();
		this.numDecks = numDecks;
		this.deckPenetration = deckPenetration;
		initializeShoe();
	}//end constructor
	
	/**
	 * Creates an initial shoe where all the cards are in sorted order.
	 */
	private void initializeShoe() {
		for (int i = 0; i < numDecks; i++) {
			for (int j = 0; j < PlayingCard.NUMBER_OF_SUITS; j++) {
				for (int k = 0; k < PlayingCard.NUMBER_OF_RANKS; k++) {
					shoe.add(new BlackjackCard(PlayingCard.RANKS[k], PlayingCard.SUITS[j]));
				}
			}
		}
	}//end method initializeShoe
	
	/**
	 * Gets the number of cards remaining in the shoe.
	 * @return  The number of cards remaining in the shoe.
	 */
	public int getNumCards() {
		return shoe.size();
	}
	
	/**
	 * Gets the number of decks used to create the shoe.
	 * @return  The number of decks used to create the shoe.
	 */
	public int getNumDecks() {
		return numDecks;
	}
	
	/**
	 * Checks if no cards have been dealt from the shoe after refilling the shoe.
	 * @return  True if the shoe is filled up.
	 */
	public boolean isNewShoe() {
		boolean newShoe = false;
		
		if (shoe.size() == numDecks * NUM_CARDS_PER_DECK) {
			newShoe = true;
		}
		
		return newShoe;
	}//end method isNewShoe
	
	/**
	 * Shuffles a shoe.
	 */
	public void shuffleShoe() {
		for (int i = 0; i < SHUFFLES_PER_DECK * numDecks; i++) {
			for (int j = 0; j < shoe.size(); j++) {
				int swapIndex = (int) (Math.random() * shoe.size());
				BlackjackCard temp = shoe.get(j);
				shoe.set(j, shoe.get(swapIndex));
				shoe.set(swapIndex, temp);
			}
		}
	}
	
	/**
	 * Removes a card from the shoe and deals it out.
	 * @return  A card dealt from the shoe.
	 */
	public BlackjackCard dealCard() {
		BlackjackCard dealtCard = shoe.remove(shoe.size() - 1);
		
		return dealtCard;
	}
	
	/**
	 * Add a card to the shoe.
	 * @param card  The card to be added to the shoe.
	 */
	public void addCard(final BlackjackCard card) {
		shoe.add(card);
	}
	
	/**
	 * Checks if the cut card in the shoe was encountered.
	 * @return  True if the cut card was encountered.
	 */
	public boolean wasCutCardMet() {
		boolean cutCardMet = false;
		double currentPenetration = 100.0 * (NUM_CARDS_PER_DECK * numDecks - shoe.size()) / (NUM_CARDS_PER_DECK * numDecks);
		
		if (currentPenetration >= deckPenetration) {
			cutCardMet = true;
		}
		
		return cutCardMet;
	}
	
	/**
	 * Print all the cards currently in the shoe.
	 */
	public void print() {
		for (int i = 0; i < shoe.size(); i++) {
			System.out.println("" + i + ":" + shoe.get(i).toString());
		}
	}

}
