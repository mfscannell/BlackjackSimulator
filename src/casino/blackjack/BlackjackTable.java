package casino.blackjack;

import java.util.ArrayList;
import java.util.Observable;

import casino.gambler.BlackjackDealer;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.PlayingCard;
import casino.blackjack.rules.BlackjackRules;
import casino.playingCard.enumerations.BlackjackMove;
import exceptions.InvalidShoeException;
import exceptions.TableSeatNumberInvalidException;
import exceptions.TableSeatTakenException;

/**
 * A blackjack table used to play a game of blackjack.
 * @author mscannell
 *
 */
public class BlackjackTable extends Observable {
    public static final int MIN_PLAYERS = 1;
    public static final int MAX_PLAYERS = 7;
    
    private Shoe shoe;
    private DiscardTray discardTray;
    private BlackjackRules rules;
    private ArrayList<BlackjackPlayer> players;
    private ArrayList<ArrayList<BlackjackHand>> playersHands;
    private BlackjackHand dealerHand;
    private BlackjackDealer dealer;
    
    /**
     * Constructor
     * @param numHandsToSimulate  The number of hands to simulate.
     * @param numDecks  The number of decks of cards in the shoe.
     * @param deckPenetration  The percentage of cards dealt out before the shoe is refilled.
     * @param rules  The rules for the table.
     */
    public BlackjackTable(int numDecks, int deckPenetration, BlackjackRules rules) {
        try {
            this.shoe = new Shoe(numDecks, deckPenetration);
            this.rules = rules;
            this.players = new ArrayList<BlackjackPlayer>();
            this.playersHands = new ArrayList<ArrayList<BlackjackHand>>();
            this.dealerHand = new BlackjackHand();
            
            for (int i = 0; i < MAX_PLAYERS; i++) {
                this.players.add(null);
                this.playersHands.add(null);
            }
            
            this.discardTray = new DiscardTray();
            this.dealer = null;
        } catch (InvalidShoeException e) {
            e.printStackTrace();
        }
    }
    
    public void addPlayerAtSeat(BlackjackPlayer blackjackPlayer, int seat) throws TableSeatTakenException, TableSeatNumberInvalidException {
        checkIfValidSeat(seat);
        checkIfSeatOccupied(seat);
        seatPlayerAndAssociateHands(blackjackPlayer, seat);
    }
    
    /**
     * Checks if the table has a player sitting at the specified seat.
     * @param seat  The seat to check.
     * @return  True if a player is sitting at the specified seat.
     */
    public boolean hasPlayerAtSeat(int seat) {
        boolean seatOccupied = false;
        
        if (this.players.get(seat) != null) {
            seatOccupied = true;
        }
        
        return seatOccupied;
    }
    
    /**
     * Play one round of blackjack.
     */
    public void playRound() {
        if (doesShoeNeedRefill()) {
            System.out.println("***CUT CARD MET***");
            refillShoe();
            shuffleShoeAndDiscardFirstCard();
            resetPlayersCardCounts();
        }
        
        setBetAmountForAllPlayers();
        dealInitialCards();
        
        if (this.dealerHand.isFirstCardAce()) {
            setInsuranceForAllPlayers();
            adjustAllPlayersChipsForInsurance();
        }
        
        if (!this.dealerHand.isBlackjack()) {
            playPlayersTurns();
        }
        
        exposeDealerHoleCard();
        playDealerTurn();
        payoutPlayers();
        printTable();
        printPlayers();
        printCardCount();
        collectAllCards();
    }
    
    /**
     * Associates a blackjack dealer with the table.
     * @param dealer  The blackjack dealer.
     */
    public void setDealer(BlackjackDealer dealer) {
        this.dealer = dealer;
        this.dealer.setHand(this.dealerHand);
    }
    
    /**
     * Adjust the chip count for all players after an insurance scenario.  Each player's chip count will be adjusted only
     * if he took insurance.
     */
    private void adjustAllPlayersChipsForInsurance() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                adjustPlayerChipsForInsurance(i);
            }
        }
    }
    
    /**
     * Adjust the chip count for the player at the seat after an insurance scenario.  The player's chip count will be
     * adjusted only if he took insurance.
     * @param seat  The seat of the player to adjust the chip count for.
     */
    private void adjustPlayerChipsForInsurance(int seat) {
        final double betAmount = this.players.get(seat).getBetAmount();
        final double insuranceWinnings = BlackjackRules.PAYOUT_INSURANCE * BlackjackRules.INSURANCE_BET_SIZE * betAmount;
        final double insuranceLosings = BlackjackRules.INSURANCE_BET_SIZE * betAmount * -1.0;
        
        if (this.dealerHand.isBlackjack() && this.players.get(seat).takesInsurance()) {
            this.players.get(seat).adjustCashTotal(insuranceWinnings);
        } else if (!this.dealerHand.isBlackjack() && this.players.get(seat).takesInsurance()) {
            this.players.get(seat).adjustCashTotal(insuranceLosings);
        }
    }
    
    /**
     * Check if a seat at the table is occupied.
     * @param seat  The seat number to check if the seat is occupied.
     * @throws TableSeatTakenException
     */
    private void checkIfSeatOccupied(int seat) throws TableSeatTakenException {
        if (isSeatOccupied(seat)) {
            throw new TableSeatTakenException("That seat is already taken");
        }
    }
    
    /**
     * Check if the specified seat number is a valid seat number.
     * @param seat  The seat number to check if it is a valid seat.
     * @throws TableSeatNumberInvalidException
     */
    private void checkIfValidSeat(int seat) throws TableSeatNumberInvalidException {
        if (!isValidSeat(seat)) {
            throw new TableSeatNumberInvalidException("Seat number must be between 0 and " + MAX_PLAYERS);
        }
    }
    
    /**
     * Collect all the cards from all the players and the dealer and place those cards in the discard tray.
     */
    private void collectAllCards() {
        for (int i = 0; i < this.playersHands.size(); i++) {
            collectPlayerCardsAtSeat(i);
        }
        
        collectDealerCards();
    }
    
    /**
     * Collect all the cards from the dealer and place them in the discard tray.
     */
    private void collectDealerCards() {
        while (this.dealerHand.getNumCards() > 0) {
            PlayingCard card = this.dealerHand.removeCard();
            this.discardTray.addCard(card);
        }
    }
    
    /**
     * Collect all the cards from the player at the specified seat and place them in the discard tray.
     * @param seat  The seat of the player to collect the cards from.
     */
    private void collectPlayerCardsAtSeat(final int seat) {
        while (this.playersHands.get(seat) != null && this.playersHands.get(seat).size() > 0) {
            BlackjackHand hand = this.playersHands.get(seat).remove(this.playersHands.get(seat).size() - 1);
            
            while (hand.getNumCards() > 0) {
                PlayingCard card = hand.removeCard();
                this.discardTray.addCard(card);
            }
        }
    }
    
    /**
     * Deal a card from the shoe to the specified hand.
     * @param hand  The hand to deal a card from the shoe.
     */
    private void dealCardFromShoeToHand(BlackjackHand hand) {
        PlayingCard dealtCard = this.shoe.dealCard();
        notifyCardValueToPlayers(dealtCard);
        hand.addCard(dealtCard);
    }
    
    /**
     * Deal a card from the shoe to the dealer.  Notify each player of the card if the card is the dealer's first card.
     * @param cardPosition  The card number of the dealer.
     */
    private void dealCardToDealer(int cardPosition) {
        PlayingCard dealersCard = this.shoe.dealCard();
        
        if (cardPosition == 0) {
            notifyCardValueToPlayers(dealersCard);
        }
        
        this.dealerHand.addCard(dealersCard);
    }
    
    /**
     * Deal a card from the shoe to the player at the specified seat for the player's initial hand.
     * @param seat  The seat of the player to deal the card to.
     */
    private void dealCardToPlayerInitialHand(int seat) {
        BlackjackHand hand = retrievePlayerFirstHand(seat);
        dealCardFromShoeToHand(hand);
    }
    
    /**
     * Deal the initial two cards to each player and the dealer.
     */
    private void dealInitialCards() {
        for (int i = 0; i < BlackjackRules.NUM_CARDS_PER_INITIAL_DEAL; i++) {
            for (int j = 0; j < this.players.size(); j++) {
                if (hasPlayerAtSeat(j)) {
                    dealCardToPlayerInitialHand(j);
                }
            }
            
            dealCardToDealer(i);
        }
    }
    
    /**
     * Checks if the shoe needs to be refilled.  The shoe needs to be refilled if the cut card in the shoe was encountered.
     * @return  True if the shoe needs to be refilled.
     */
    private boolean doesShoeNeedRefill() {
        boolean shoeNeedsRefill = false;
        
        if (this.shoe.wasCutCardMet()) {
            shoeNeedsRefill = true;
        }
        
        return shoeNeedsRefill;
    }
    
    /**
     * Show the dealer's hole card to the players and notify them of it.
     */
    private void exposeDealerHoleCard() {
        notifyCardValueToPlayers(this.dealerHand.getSecondCard());
    }
    
    /**
     * Checks if the dealers hand is playable.  The dealer's hand is playable if any of the
     * players' hands are neither blackjack nor busted.
     * @return  True if any players' hands are not busts nor blackjack.
     */
    private boolean hasPlayerHandRemaining() {
        boolean playable = false;
        
        for (int i = 0; i < this.players.size() && !playable; i++) {
            for (int j = 0; j < this.playersHands.get(i).size() && !playable; j++) {
                if (!this.playersHands.get(i).get(j).isBust()) {
                    playable = true;
                }
                
                if (!this.playersHands.get(i).get(j).isBlackjack()) {
                    playable = true;
                }
            }
        }
        
        return playable;
    }
    
    /**
     * Checks if the seat is occupied.
     * @param seat  The seat number to check if the seat is occupied.
     * @return  True if the seat is occupied.
     */
    private boolean isSeatOccupied(int seat) {
        boolean seatOccupied = this.players.get(seat) != null;
        
        return seatOccupied;
    }
    
    /**
     * Checks if the seat is a valid seat number.
     * @param seat  The seat number to check its validity.
     * @return  True if the seat is a valid seat number.
     */
    private boolean isValidSeat(int seat) {
        boolean validSeat = true;
        
        if (seat < 0 || seat >= MAX_PLAYERS) {
            validSeat = false;
        }
        
        return validSeat;
    }
    
    /**
     * Notify each player of the card that is exposed.
     * @param card
     */
    private void notifyCardValueToPlayers(PlayingCard card) {
        setChanged();
        notifyObservers(card);
        clearChanged();
    }
    
    /**
     * Adjust the players chips accordingly with the exception for when the dealer has blackjack 
     * or when insurance was offered.
     */
    private void payoutPlayers() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                payoutPlayer(i);
            }
        }
    }
    
    /**
     * Payout the player at the specified seat at the end of the round based upon his hand and the dealer's hand.
     * @param seat  The seat of the player to adjust his pay for.
     */
    private void payoutPlayer(int seat) {
        final double playerBet = this.players.get(seat).getBetAmount();
        
        for (int i = 0; i < this.playersHands.get(seat).size(); i++) {
            final BlackjackHand hand = this.playersHands.get(seat).get(i);
            final int numPlayerHands = this.playersHands.get(seat).size();
            final double playerPayout = this.rules.getPayoutAdjustment(hand, this.dealerHand, numPlayerHands) * playerBet;
            this.players.get(seat).adjustCashTotal(playerPayout);
        }
    }
    
    /**
     * Play the dealer's turn.  The dealer will hit until he either busts or the rules state he must stand.
     */
    private void playDealerTurn() {
        if (hasPlayerHandRemaining()) {
            while (this.rules.getDealersMove(this.dealerHand) != BlackjackMove.STAND) {
                dealCardFromShoeToHand(this.dealerHand);
            }
        }
    }
    
    /**
     * Play each of the players turns.
     */
    private void playPlayersTurns() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                playPlayerTurn(i);
            }
        }
    }
    
    /**
     * Play the player's turn at the specified seat.
     * @param seat  The seat of the player to play.
     */
    private void playPlayerTurn(final int seat) {
        int j = 0;
        final PlayingCard dealerUpCard = this.dealerHand.getFirstCard();
        
        while (j < this.playersHands.get(seat).size()) {
            BlackjackMove move;
            final BlackjackHand playerHand = this.playersHands.get(seat).get(j);
            final int numHands = this.playersHands.get(seat).size();
            
            if (playerHand.getNumCards() < 2) {
                dealCardFromShoeToHand(playerHand);
            } else {
                move = this.players.get(seat).getAction(dealerUpCard, playerHand, numHands);
                
                switch (move) {
                    case STAND:
                        j++;
                        break;
                    case HIT:
                        dealCardFromShoeToHand(playerHand);
                                    
                        if (playerHand.isBust()) {
                            j++;
                        }
                        
                        break;
                    case SPLIT:
                        BlackjackHand splitHand = playerHand.split();
                        this.playersHands.get(seat).add(j + 1, splitHand);
                        break;
                    case DOUBLE:
                        dealCardFromShoeToHand(playerHand);
                        playerHand.setWasDoubleDown(true);
                        j++;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    private void printCardCount() {
        //System.out.println("Card count:" + kissIStrategy.getCount());
    }
    
    /**
     * Prints the players to the console
     */
    private void printPlayers() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                System.out.println("Seat #" + i);
                System.out.println(players.get(i).toString());
            }
        }
        System.out.println(this.dealer.toString());
    }
    
    private void printTable() {
        if (this.dealerHand.isBlackjack()) {
            System.out.println("Dealer Blackjack");
        }
    }
    
    /**
     * Refill the shoe with all the cards in the discard tray.
     */
    private void refillShoe() {
        while (this.discardTray.getNumCards() > 0) {
            PlayingCard card = this.discardTray.removeCard();
            this.shoe.addCard(card);
        }
    }
    
    /**
     * Reset each players card counting strategy card count.
     */
    private void resetPlayersCardCounts() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                this.players.get(i).resetCount();
            }
        }
    }
    
    /**
     * Retrieve the first hand of the player sitting at the specified seat.  The player may have multiple hands if he has
     * split his hands.
     * @param seat  The seat of the player to retrieve the hand from.
     * @return  The first hand of the player at the seat.
     */
    private BlackjackHand retrievePlayerFirstHand(int seat) {
        BlackjackHand hand;
        
        if (this.playersHands.get(seat).size() == 0) {
            hand = new BlackjackHand();
            this.playersHands.get(seat).add(hand);
        } else {
            hand = this.playersHands.get(seat).get(0);
        }
        
        return hand;
    }
    
    /**
     * Seat a player at the table and create his blackjack hand.
     * @param blackjackPlayer  The player to be added to the table.
     * @param seat  The seat the player will sit at.
     */
    private void seatPlayerAndAssociateHands(BlackjackPlayer blackjackPlayer, int seat) {
        ArrayList<BlackjackHand> hands = new ArrayList<BlackjackHand>();
        this.playersHands.set(seat, hands);
        this.players.set(seat, blackjackPlayer);
        blackjackPlayer.setHands(hands);
        blackjackPlayer.initializeStrategy(this.rules, this.shoe.getNumDecks());
        addObserver(blackjackPlayer);
    }
    
    /**
     * Set how much each player will bet at the start of the round.
     */
    private void setBetAmountForAllPlayers() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                this.players.get(i).setBetAmount();
            }
        }
    }
    
    /**
     * Set whether or not each player takes insurance.
     */
    private void setInsuranceForAllPlayers() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                this.players.get(i).setTakesInsurance();
            }
        }
    }
    
    /**
     * Shuffle the cards in the shoe and then discard the first card and place that card in the discard tray.
     */
    private void shuffleShoeAndDiscardFirstCard() {
        this.shoe.shuffleShoe();
        PlayingCard initialCard = this.shoe.dealCard();
        this.discardTray.addCard(initialCard);
    }
}
