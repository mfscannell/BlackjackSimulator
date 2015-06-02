package modelObjects;

public class BlackjackDealer extends Gambler {
	private double chipCount;
	private BlackjackHand hand;
	
	public BlackjackDealer() {
		super(0);
		chipCount = 0;
		hand = null;
	}
	
	/**
	 * Checks if the dealers up-card is an ace.
	 * @return  True if the dealer's up-card is an ace.
	 */
	public boolean isUpCardAce() {
		return hand.isFirstCardAce();
	}
	
	public void setHand(BlackjackHand hand) {
		this.hand = hand;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Dealer\n");
		
		stringBuilder.append(hand.toString());
		
		return stringBuilder.toString();
	}
}
