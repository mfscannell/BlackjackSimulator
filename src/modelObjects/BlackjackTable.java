package modelObjects;

import java.util.ArrayList;

import enumerations.BlackjackMove;
import exceptions.InvalidNumDecksException;
import exceptions.InvalidShoeException;
import exceptions.TableOperationException;
import rules.BasicStrategy;
import rules.BlackjackRules;
import rules.CompositionStrategy;
import rules.KISSIStrategy;

public class BlackjackTable {
	private int numHands;
	private int numPlayers;
	private Shoe shoe;
	private DiscardTray discardTray;
	private BlackjackRules rules;
	private ArrayList<BlackjackPlayer> players;
	private ArrayList<ArrayList<BlackjackHand>> playersHands;
	private BlackjackHand dealerHand;
	private BlackjackDealer dealer;
	private CompositionStrategy compositionStrategy;
	private KISSIStrategy kissIStrategy;
	private boolean insuranceOffered;
	
	public static final int MIN_PLAYERS = 1;
	public static final int MAX_PLAYERS = 7;
	
	/**
	 * Constructor
	 * @param numHands  The number of hands to simulate.
	 * @param shoe  The shoe containing the cards.
	 * @param rules  The rules for the table.
	 */
	public BlackjackTable(int numHands, Shoe shoe, BlackjackRules rules) {
		this.numHands = numHands;
		numPlayers = 0;
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
		try {
			compositionStrategy = new CompositionStrategy(rules, shoe.getNumDecks());
			kissIStrategy = new KISSIStrategy(rules, shoe.getNumDecks());
		} catch (InvalidNumDecksException e) {
			e.printStackTrace();
		}
	}//end constructor
	
	/**
	 * Add a player to the table at the specified seat.
	 * @param blackjackPlayer
	 * @param seat
	 * @throws TableOperationException Thrown if the seat is already taken.
	 */
	public void addPlayer(BlackjackPlayer blackjackPlayer, int seat) throws TableOperationException {
		if (players.get(seat) != null || 
			playersHands.get(seat) != null || 
			seat < 0 || 
			seat >= MAX_PLAYERS) {
			throw new TableOperationException("That seat is already taken");
		}
		ArrayList<BlackjackHand> hands = new ArrayList<BlackjackHand>();
		playersHands.set(seat, hands);
		blackjackPlayer.setHands(hands);
		players.set(seat, blackjackPlayer);
		numPlayers++;
	}//end method addPlayer
	
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
		if (shoe.wasCutCardMet() || kissIStrategy.walkAway(shoe.getNumDecks())) {
			System.out.println("***CUT CARD MET***");
			refillShoe();
		}
		
		if (shoe.isNewShoe()) {
			shoe.shuffleShoe();
			BlackjackCard initialCard = shoe.dealCard();
			discardTray.addCard(initialCard);
			kissIStrategy.resetCount();
		}
		
		resetInsurance();
		setBetAmounts();
		dealInitialCards();
		
		if (dealerHand.isFirstCardAce()) {
			offerInsurance();
		}
		
		if (!dealerHand.isBlackjack()) {
			playPlayersTurns();
		}
		
		exposeDealersCard();
		
		if (!dealerHand.isBlackjack()) {
			playDealersTurn();
		}
		
		comparePlayersToDealer();
		printTable();
		printPlayers();
		printCardCount();
		collectAllCards();
	}//end method playRound
	
	/**
	 * Sets all players to not take insurance when the dealer offers it.
	 */
	private void resetInsurance() {
		insuranceOffered = false;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != null) {
				players.get(i).setsTakesInsurance(false);
			}
		}
	}//end method resetInsurance
	
	/**
	 * Sets each player's bet amount at the start of the round as a reference to the minimum bet.
	 */
	private void setBetAmounts() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != null) {
				if (players.get(i).doesCountsCards()) {
					players.get(i).setBetAmount(kissIStrategy.getBetSize(shoe.getNumDecks()));
				} else {
					players.get(i).setBetAmount(1);
				}
			}
		}
	}//end method setBetAmounts
	
	/**
	 * Deal two cards to each player and the dealer.
	 */
	private void dealInitialCards() {
		for (int i = 0; i < BlackjackRules.NUM_CARDS_PER_INITIAL_DEAL; i++) {
			for (int j = 0; j < players.size(); j++) {
				//deal card to player
				if (players.get(j) == null) {
					continue;
				}
				
				BlackjackCard dealtCard = shoe.dealCard();
				adjustCount(dealtCard);
				
				if (playersHands.get(j).size() == 0) {
					BlackjackHand hand = new BlackjackHand();
					playersHands.get(j).add(hand);
				}
					
				playersHands.get(j).get(0).addCard(dealtCard);
			}
			
			//deal card to dealer
			BlackjackCard dealersCard = shoe.dealCard();
			
			if (i == 0) {
				adjustCount(dealersCard);
			}
			
			dealerHand.addCard(dealersCard);
		}
	}//end method dealInitialCards
	
	/**
	 * Offer insurance to the players.
	 * @precond This can only be done if the dealer's up-card is an ace.
	 */
	private void offerInsurance() {
		insuranceOffered = true;
		
		for (int i = 0; i < players.size(); i++) {
			boolean insurance = false;
			if (players.get(i) == null) {
				continue;
			}
			
			if (players.get(i).doesCountsCards()) {
				try {
					insurance = kissIStrategy.getInsuranceMove(shoe.getNumDecks());
				} catch (InvalidShoeException e) {
					e.printStackTrace();
				}
			} else {
				insurance = compositionStrategy.getInsuranceMove();
			}
			
			players.get(i).setsTakesInsurance(insurance);
		}
	}//end method offerInsurance
	
	/**
	 * Play each of the players turns.
	 */
	private void playPlayersTurns() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != null) {
				playPlayerTurn(i);
			}
		}
	}//end method playPlayersTurns
	
	/**
	 * Play the hand(s) of the player at the specified seat.
	 * @param seat  The seat of the player to play the hands.
	 */
	private void playPlayerTurn(final int seat) {
		int j = 0;
		final BlackjackCard dealerUpCard = dealerHand.getFirstCard();
		
		while (j < playersHands.get(seat).size()) {
			BlackjackMove move;
			BlackjackCard dealtCard;
			final BlackjackHand playerHand = playersHands.get(seat).get(j);
			final int numHands = playersHands.get(seat).size();
			
			if (players.get(seat).doesCountsCards()) {
				move = kissIStrategy.getAction(dealerUpCard, playerHand, rules, numHands);
			} else {
				move = compositionStrategy.getAction(dealerUpCard, playerHand, rules, numHands);
			}
			
			if (playerHand.getNumCards() < 2) {
				dealtCard = shoe.dealCard();
				adjustCount(dealtCard);
				playersHands.get(seat).get(j).addCard(dealtCard);
			} else {
				switch (move) {
					case STAND:		//System.out.println("" + seat + " stands");
									j++;
									break;
					case HIT:		//System.out.println("" + seat + " hits");
									dealtCard = shoe.dealCard();
									adjustCount(dealtCard);
									playersHands.get(seat).get(j).addCard(dealtCard);
									
									if (playersHands.get(seat).get(j).isBust()) {
										j++;
									}
									
									break;
					case SPLIT:		//System.out.println("" + seat + " splits hand");
									BlackjackCard splitCard = playersHands.get(seat).get(j).split();
									BlackjackHand splitHand = new BlackjackHand(splitCard, true);
									playersHands.get(seat).get(j).setFromSplit(true);
									splitHand.setFromSplit(true);
									playersHands.get(seat).add(j + 1, splitHand);
									break;
					case DOUBLE:	//System.out.println("" + seat + " double downs");
									dealtCard = shoe.dealCard();
									adjustCount(dealtCard);
									playersHands.get(seat).get(j).addCard(dealtCard);
									playersHands.get(seat).get(j).setWasDoubleDown(true);
									j++;
									break;
					default:		break;
				}
			}
		}
	}//end method playPlayerTurn
	
	
	
	/**
	 * Exposes the dealer's hole card so the players can see.
	 */
	private void exposeDealersCard() {
		adjustCount(dealerHand.getSecondCard());
	}
	
	/**
	 * Play the dealers turn.
	 */
	private void playDealersTurn() {
		boolean dealerStands = false;
		
		if (dealerPlayable()) {
			while (!dealerStands) {
				BlackjackMove dealersMove = rules.getDealersMove(dealerHand);
				
				if (dealersMove == BlackjackMove.HIT) {
					BlackjackCard dealtCard = shoe.dealCard();
					
					adjustCount(dealtCard);
					dealerHand.addCard(dealtCard);
				} else {
					dealerStands = true;
				}
			}
		}
	}//end method playDealersTurn
	
	/**
	 * Checks if the dealers hand is playable.  The dealer's hand is playable if any of the
	 * players' cards are not blackjack and are not busted.
	 * @return  True if any players' hands are not busts nor blackjack.
	 */
	private boolean dealerPlayable() {
		boolean playable = false;
		
		for (int i = 0; i < players.size(); i++) {
			for (int j = 0; j < playersHands.get(i).size(); j++) {
				if (!playersHands.get(i).get(j).isBust()) {
					playable = true;
				}
				
				if (!playersHands.get(i).get(j).isBlackjack()) {
					playable = true;
				}
				
				if (playable) {
					break;
				}
			}
			
			if (playable) {
				break;
			}
		}
		
		return playable;
	}//end method dealerPlayable
	
	/**
	 * Adjusts the player's chip stacks according to what they have vs the dealer.
	 */
	private void comparePlayersToDealer() {
		checkDealerBlackjack();
		checkIfInsuranceOffered();
		payoutPlayers();
	}//end method comparePlayersToDealer
	
	/**
	 * Adjust the players chip counts if the dealer has blackjack.  Any player who does not
	 * have blackjack will lose money.  Any player with blackjack will push.
	 */
	private void checkDealerBlackjack() {
		if (dealerHand.isBlackjack()) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i) != null) {
					final double betAmount = players.get(i).getBetAmount();
					final double handLosings = betAmount * -1.0;
				
					//adjust the players cash total if the dealer has blackjack and the player doesn't
					if (playersHands.get(i).get(0).getTotal() != 21) {
						players.get(i).adjustCashTotal(handLosings);
					}
				}
			}
		}
	}//end method checkDealerBlackjack
	
	/**
	 * Adjust the players chip counts if insurance was offered.
	 */
	private void checkIfInsuranceOffered() {
		if (insuranceOffered) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i) == null) {
					continue;
				}
				
				final double betAmount = players.get(i).getBetAmount();
				final double insuranceWinnings = BlackjackRules.INSURANCE_PAYOUT * BlackjackRules.INSURANCE_BET_SIZE * betAmount;
				final double insuranceLosings = BlackjackRules.INSURANCE_BET_SIZE * betAmount * -1.0;
					
				//adjust the players cash total based upon the insurance bet.
				if (dealerHand.isBlackjack() && players.get(i).takesInsurance()) {
					players.get(i).adjustCashTotal(insuranceWinnings);
				} else if (!dealerHand.isBlackjack() && players.get(i).takesInsurance()) {
					players.get(i).adjustCashTotal(insuranceLosings);
				}
			}
		}
	}//end method checkIfInsuranceOffered
	
	/**
	 * Adjust the players chips accordingly with the exception for when the dealer has blackjack 
	 * or when insurance was offered.
	 */
	private void payoutPlayers() {
		if (!dealerHand.isBlackjack()) {
			for (int i = 0; i < players.size(); i++) {
				if (playersHands.get(i) == null && players.get(i) == null) {
					continue;
				}
				
				final double playerBet = players.get(i).getBetAmount();
				final double winnings = playerBet;
				final double losings = playerBet * -1.0;
				final double doubleWinnings = 2.0 * playerBet;
				final double doubleLosings = -2.0 * playerBet;
				final double blackjackWinnings = rules.getBlackjackPayout() * playerBet;
				
				if (playersHands.get(i).get(0).isBlackjack()) {
					System.out.println("" + i + " Player Blackjack");
					players.get(i).adjustCashTotal(blackjackWinnings);
				} else {
					for (int j = 0; j < playersHands.get(i).size(); j++) {
						BlackjackHand hand = playersHands.get(i).get(j);
						
						if (hand.wasDoubleDown() && 
							!hand.isBust() && 
							hand.getTotal() > dealerHand.getTotal()) {
							players.get(i).adjustCashTotal(doubleWinnings);
							//System.out.println("Double win 1");
						} else if (hand.wasDoubleDown() && 
								   !hand.isBust() && 
								   dealerHand.isBust()) {
							players.get(i).adjustCashTotal(doubleWinnings);
							//System.out.println("Double win 2");
						} else if (hand.wasDoubleDown() && 
								   hand.isBust()) {
							players.get(i).adjustCashTotal(doubleLosings);
							//System.out.println("Double lose 1");
						} else if (hand.wasDoubleDown() && 
								   hand.getTotal() < dealerHand.getTotal() && 
								   !dealerHand.isBust()) {
							players.get(i).adjustCashTotal(doubleLosings);
							//System.out.println("Double lose 2");
						} else if (hand.isBust()) {
							players.get(i).adjustCashTotal(losings);
							//System.out.println("lose bust");
						} else if (hand.getTotal() < dealerHand.getTotal() && !dealerHand.isBust()) {
							players.get(i).adjustCashTotal(losings);
							//System.out.println("lose 2");
						} else if (!hand.isBust() && dealerHand.isBust()) {
							players.get(i).adjustCashTotal(winnings);
							//System.out.println("win 1");
						} else if (hand.getTotal() > dealerHand.getTotal() && !hand.isBust()) {
							players.get(i).adjustCashTotal(winnings);
							//System.out.println("win 2");
						}
					}
				}
			}
		}
	}//end method payoutPlayers
	
	/**
	 * Collects all the remaining cards from the players.
	 */
	private void collectAllCards() {
		//collect the players' cards
		for (int i = 0; i < playersHands.size(); i++) {
			collectPlayerCards(i);
			
			//collect the dealer's cards
			while (dealerHand.getNumCards() > 0) {
				BlackjackCard card = dealerHand.removeCard();
				discardTray.addCard(card);
			}
		}
	}//end method collectAllCards
	
	/**
	 * Collect all the cards from the player at the specified seat.
	 * @param seat  The seat of the player to collect the cards from.
	 */
	private void collectPlayerCards(final int seat) {
		while (playersHands.get(seat) != null && playersHands.get(seat).size() > 0) {
			BlackjackHand hand = playersHands.get(seat).remove(playersHands.get(seat).size() - 1);
			
			while (hand.getNumCards() > 0) {
				BlackjackCard card = hand.removeCard();
				discardTray.addCard(card);
			}
		}
	}//end method collectPlayerCards
	
	/**
	 * Refills the shoe with all the cards from the discard tray.
	 */
	private void refillShoe() {
		while (discardTray.getNumCards() > 0) {
			BlackjackCard card = discardTray.removeCard();
			shoe.addCard(card);
		}
	}
	
	/**
	 * Adjust all of the card counting methods according to the dealt card.
	 * @param dealtCard  The card dealt from the shoe.
	 */
	private void adjustCount(final BlackjackCard dealtCard) {
		kissIStrategy.adjustCount(dealtCard);
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
			if (players.get(i) != null) {
				System.out.println("Seat #" + i);
				System.out.println(players.get(i).toString());
			}
		}
		System.out.println(dealer.toString());
	}//end method printPlayers
	
	private void printCardCount() {
		System.out.println("Card count:" + kissIStrategy.getCount());
	}
}
