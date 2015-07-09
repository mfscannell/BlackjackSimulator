package modelObjects;

public class BlackjackDealer extends Gambler {
	private double chipCount;
	private BlackjackHand hand;
	
	public BlackjackDealer() {
		super(0);
		chipCount = 0;
		hand = null;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Dealer\n");
		
		stringBuilder.append(hand.toString());
		
		return stringBuilder.toString();
	}
}
