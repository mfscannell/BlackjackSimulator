package modelObjects;

import java.util.ArrayList;

import blackjackStrategies.BasicStrategy;
import blackjackStrategies.CompositionStrategy;
import blackjackStrategies.KISSIStrategy;
import enumerations.BlackjackMove;
import exceptions.InvalidNumDecksException;
import exceptions.TableSeatNumberInvalidException;
import exceptions.TableSeatTakenException;
import rules.BlackjackRules;

public class BlackjackTable {
	public static final int MIN_PLAYERS = 1;
	public static final int MAX_PLAYERS = 7;
	
	private Shoe shoe;
	private DiscardTray discardTray;
	private BlackjackRules rules;
	private ArrayList<BlackjackPlayer> players;
	private ArrayList<ArrayList<BlackjackHand>> playersHands;
	private BlackjackHand dealerHand;
	private BlackjackDealer dealer;
//	private CompositionStrategy compositionStrategy;
//	private KISSIStrategy kissIStrategy;
	private boolean insuranceOffered;
	
	/**
	 * Constructor
	 * @param numHandsToSimulate  The number of hands to simulate.
	 * @param shoe  The shoe containing the cards.
	 * @param rules  The rules for the table.
	 */
	public BlackjackTable(Shoe shoe, BlackjackRules rules) {
		this.shoe = shoe;
		this.rules = rules;
		players = new ArrayList<BlackjackPlayer>();
		playersHands = new ArrayList<ArrayList<BlackjackHand>>();
		
		for (int i = 0; i < MAX_PLAYERS; i++) {
			players.add(null);
			playersHands.add(null);
		}
		
		discardTray = new DiscardTray();
		dealer = null;
//		try {
//			compositionStrategy = new CompositionStrategy(rules, shoe.getNumDecks());
//			kissIStrategy = new KISSIStrategy(rules, shoe.getNumDecks());
//		} catch (InvalidNumDecksException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Add a player to the table at the specified seat.
	 * @param blackjackPlayer
	 * @param seat
	 * @throws TableSeatTakenException Thrown if the seat is already taken.
	 * @throws TableSeatNumberInvalidException Thrown if the seat is an invalid seat number.
	 */
	public void addPlayerAtSeat(BlackjackPlayer blackjackPlayer, int seat) throws TableSeatTakenException, TableSeatNumberInvalidException {
		checkIfValidSeat(seat);
		checkIfSeatOccupied(seat);
		seatPlayerAndAssociateHands(blackjackPlayer, seat);
	}
	
	private void checkIfValidSeat(int seat) throws TableSeatNumberInvalidException {
		if (!isValidSeat(seat)) {
			throw new TableSeatNumberInvalidException("Seat number must be between 0 and " + MAX_PLAYERS);
		}
	}
	
	private boolean isValidSeat(int seat) {
		boolean validSeat = true;
		
		if (seat < 0 || seat >= MAX_PLAYERS) {
			validSeat = false;
		}
		
		return validSeat;
	}
	
	private void checkIfSeatOccupied(int seat) throws TableSeatTakenException {
		if (isSeatOccupied(seat)) {
			throw new TableSeatTakenException("That seat is already taken");
		}
	}
	
	private boolean isSeatOccupied(int seat) {
		boolean seatOccupied = players.get(seat) != null || playersHands.get(seat) != null;
		
		return seatOccupied;
	}
	
	private void seatPlayerAndAssociateHands(BlackjackPlayer blackjackPlayer, int seat) {
		ArrayList<BlackjackHand> hands = new ArrayList<BlackjackHand>();
		playersHands.set(seat, hands);
		blackjackPlayer.setHands(hands);
		players.set(seat, blackjackPlayer);
		blackjackPlayer.notify(rules, shoe.getNumDecks());
	}
	
	/**
	 * Associates a blackjack dealer with the table.
	 * @param dealer  The blackjack dealer.
	 */
	public void setDealer(BlackjackDealer dealer) {
		dealerHand = new BlackjackHand();
		dealer.setHand(dealerHand);
		this.dealer = dealer;
	}
	
	/**
	 * Play one round of blackjack.
	 */
	public void playRound() {
		if (doesShoeNeedRefill()) {
			System.out.println("***CUT CARD MET***");
			refillShoe();
			shuffleShoe();
		}
		
		setBetAmountForAllPlayers();
		dealInitialCards();
		checkInsuranceScenario();
		
		if (!dealerHand.isBlackjack()) {
			playPlayersTurns();
		}
		
		exposeDealerHoleCard();
		playDealerTurn();
		adjustAllPlayersChipsForInsurance();
		payoutPlayers();
		printTable();
		printPlayers();
		printCardCount();
		collectAllCards();
	}
	
	private boolean doesShoeNeedRefill() {
		boolean shoeNeedsRefill = false;
		
		if (shoe.wasCutCardMet()) {
			shoeNeedsRefill = true;
		}
		
		return shoeNeedsRefill;
	}
	
	private void refillShoe() {
		while (discardTray.getNumCards() > 0) {
			PlayingCard card = discardTray.removeCard();
			shoe.addCard(card);
		}
	}
	
	private void shuffleShoe() {
		shoe.shuffleShoe();
		PlayingCard initialCard = shoe.dealCard();
		discardTray.addCard(initialCard);
		
		for (int i = 0; i < players.size(); i++) {
			if (hasPlayerAtSeat(i)) {
				players.get(i).resetCount();
			}
		}
	}
	
	private boolean hasPlayerAtSeat(int seat) {
		boolean seatOccupied = false;
		
		if (players.get(seat) != null) {
			seatOccupied = true;
		}
		
		return seatOccupied;
	}
	
	private void setBetAmountForAllPlayers() {
		for (int i = 0; i < players.size(); i++) {
			if (hasPlayerAtSeat(i)) {
				players.get(i).setBetAmount();
			}
		}
	}
	
	private void dealInitialCards() {
		for (int i = 0; i < BlackjackRules.NUM_CARDS_PER_INITIAL_DEAL; i++) {
			for (int j = 0; j < players.size(); j++) {
				if (hasPlayerAtSeat(j)) {
					dealCardToPlayer(j);
				}
			}
			
			dealCardToDealer(i);
		}
	}
	
	private void dealCardToPlayer(int seat) {
		PlayingCard dealtCard = shoe.dealCard();
		adjustCount(dealtCard);
		
		if (playersHands.get(seat).size() == 0) {
			BlackjackHand hand = new BlackjackHand();
			playersHands.get(seat).add(hand);
		}
			
		playersHands.get(seat).get(0).addCard(dealtCard);
	}
	
	private void dealCardToDealer(int cardPosition) {
		PlayingCard dealersCard = shoe.dealCard();
		
		if (cardPosition == 0) {
			adjustCount(dealersCard);
		}
		
		dealerHand.addCard(dealersCard);
	}
	
	private void checkInsuranceScenario() {
		insuranceOffered = false;
		
		if (dealerHand.isFirstCardAce()) {
			insuranceOffered = true;
		}
		
		for (int i = 0; i < players.size(); i++) {
			if (hasPlayerAtSeat(i)) {
				setInsuranceTakenForPlayer(i);
			}
		}
	}
	
	private void setInsuranceTakenForPlayer(int seat) {
		if (insuranceOffered) {
			players.get(seat).setTakesInsurance();
		} else {
			players.get(seat).setTakesInsurance(false);
		}
	}
	
	/**
	 * Play each of the players turns.
	 */
	private void playPlayersTurns() {
		for (int i = 0; i < players.size(); i++) {
			if (hasPlayerAtSeat(i)) {
				playPlayerTurn(i);
			}
		}
	}
	
	private void playPlayerTurn(final int seat) {
		int j = 0;
		final PlayingCard dealerUpCard = dealerHand.getFirstCard();
		
		while (j < playersHands.get(seat).size()) {
			BlackjackMove move;
			final BlackjackHand playerHand = playersHands.get(seat).get(j);
			final int numHands = playersHands.get(seat).size();
			
			if (playerHand.getNumCards() < 2) {
				dealCardFromShoeToPlayer(playerHand);
			} else {
				move = players.get(seat).getAction(dealerUpCard, playerHand, numHands);
				
				switch (move) {
					case STAND:		j++;
									break;
					case HIT:		dealCardFromShoeToPlayer(playerHand);
									
									if (playerHand.isBust()) {
										j++;
									}
									
									break;
					case SPLIT:		PlayingCard splitCard = playerHand.split();
									BlackjackHand splitHand = new BlackjackHand(splitCard);
									playersHands.get(seat).add(j + 1, splitHand);
									break;
					case DOUBLE:	dealCardFromShoeToPlayer(playerHand);
									playerHand.setWasDoubleDown(true);
									j++;
									break;
					default:		break;
				}
			}
		}
	}
	
	private void dealCardFromShoeToPlayer(BlackjackHand hand) {
		PlayingCard dealtCard = shoe.dealCard();
		adjustCount(dealtCard);
		hand.addCard(dealtCard);
	}
	
	private void exposeDealerHoleCard() {
		adjustCount(dealerHand.getSecondCard());
	}
	
	private void playDealerTurn() {
		if (hasPlayerHandRemaining()) {
			while (rules.getDealersMove(dealerHand) != BlackjackMove.STAND) {
				dealCardFromShoeToPlayer(dealerHand);
			}
		}
	}
	
	/**
	 * Checks if the dealers hand is playable.  The dealer's hand is playable if any of the
	 * players' hands are neither blackjack nor busted.
	 * @return  True if any players' hands are not busts nor blackjack.
	 */
	private boolean hasPlayerHandRemaining() {
		boolean playable = false;
		
		for (int i = 0; i < players.size() && !playable; i++) {
			for (int j = 0; j < playersHands.get(i).size() && !playable; j++) {
				if (!playersHands.get(i).get(j).isBust()) {
					playable = true;
				}
				
				if (!playersHands.get(i).get(j).isBlackjack()) {
					playable = true;
				}
			}
		}
		
		return playable;
	}
	
	/**
	 * Adjust the players chip counts if insurance was offered.
	 */
	private void adjustAllPlayersChipsForInsurance() {
		if (insuranceOffered) {
			for (int i = 0; i < players.size(); i++) {
				if (hasPlayerAtSeat(i)) {
					adjustPlayerChipsForInsurance(i);
				}
			}
		}
	}
	
	private void adjustPlayerChipsForInsurance(int seat) {
		final double betAmount = players.get(seat).getBetAmount();
		final double insuranceWinnings = BlackjackRules.PAYOUT_INSURANCE * BlackjackRules.INSURANCE_BET_SIZE * betAmount;
		final double insuranceLosings = BlackjackRules.INSURANCE_BET_SIZE * betAmount * -1.0;
		
		//adjust the players cash total based upon the insurance bet.
		if (dealerHand.isBlackjack() && players.get(seat).takesInsurance()) {
			players.get(seat).adjustCashTotal(insuranceWinnings);
		} else if (!dealerHand.isBlackjack() && players.get(seat).takesInsurance()) {
			players.get(seat).adjustCashTotal(insuranceLosings);
		}
	}
	
	/**
	 * Adjust the players chips accordingly with the exception for when the dealer has blackjack 
	 * or when insurance was offered.
	 */
	private void payoutPlayers() {
		for (int i = 0; i < players.size(); i++) {
			if (hasPlayerAtSeat(i)) {
				payoutPlayer(i);
			}
		}
	}
	
	private void payoutPlayer(int seat) {
		final double playerBet = players.get(seat).getBetAmount();
		
		for (int i = 0; i < playersHands.get(seat).size(); i++) {
			final BlackjackHand hand = playersHands.get(seat).get(i);
			final int numPlayerHands = playersHands.get(seat).size();
			final double playerPayout = rules.getPayoutAdjustment(hand, dealerHand, numPlayerHands) * playerBet;
			players.get(seat).adjustCashTotal(playerPayout);
		}
	}
	
	private void collectAllCards() {
		for (int i = 0; i < playersHands.size(); i++) {
			collectPlayerCardsAtSeat(i);
		}
		
		collectDealerCards();
	}
	
	private void collectPlayerCardsAtSeat(final int seat) {
		while (playersHands.get(seat) != null && playersHands.get(seat).size() > 0) {
			BlackjackHand hand = playersHands.get(seat).remove(playersHands.get(seat).size() - 1);
			
			while (hand.getNumCards() > 0) {
				PlayingCard card = hand.removeCard();
				discardTray.addCard(card);
			}
		}
	}
	
	private void collectDealerCards() {
		while (dealerHand.getNumCards() > 0) {
			PlayingCard card = dealerHand.removeCard();
			discardTray.addCard(card);
		}
	}
	
	/**
	 * Adjust all of the card counting methods according to the dealt card.
	 * @param dealtCard  The card dealt from the shoe.
	 */
	private void adjustCount(final PlayingCard dealtCard) {
		for (int i = 0; i < players.size(); i++) {
			if (hasPlayerAtSeat(i)) {
				players.get(i).adjustCount(dealtCard);
			}
		}
	}
	
	private void printTable() {
		if (insuranceOffered) {
			System.out.println("Insurance Offered");
		} else {
			System.out.println("Insurance Was Not Offered");
		}
		
		if (dealerHand.isBlackjack()) {
			System.out.println("Dealer Blackjack");
		}
	}
	
	/**
	 * Prints the players to the console
	 */
	private void printPlayers() {
		for (int i = 0; i < players.size(); i++) {
			if (hasPlayerAtSeat(i)) {
				System.out.println("Seat #" + i);
				System.out.println(players.get(i).toString());
			}
		}
		System.out.println(dealer.toString());
	}
	
	private void printCardCount() {
		//System.out.println("Card count:" + kissIStrategy.getCount());
	}
}
