package modelObjects;

import java.util.ArrayList;

public class DiscardTray {
	ArrayList<BlackjackCard> discardTray;
	
	/**
	 * Constructor
	 */
	public DiscardTray() {
		discardTray = new ArrayList<BlackjackCard>();
	}
	
	/**
	 * Adds the card to the discard tray.
	 * @param card  The card being added to the discard tray.
	 */
	public void addCard(final BlackjackCard card) {
		discardTray.add(card);
	}
	
	public BlackjackCard removeCard() {
		BlackjackCard card = null;
		
		if (discardTray.size() > 0) {
			card = discardTray.remove(discardTray.size() - 1);
		}
		
		return card;
	}
	
	/**
	 * Get the number of cards in the discard tray.
	 * @return  The number of cards in the discard tray.
	 */
	public int getNumCards() {
		return discardTray.size();
	}
	
	public String toString() {
		String string = "";
		
		for (int i = 0; i < discardTray.size(); i++) {
			string = string.concat(discardTray.get(i).toString());
		}
		
		return string;
	}

}
