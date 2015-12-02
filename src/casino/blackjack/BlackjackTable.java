package casino.blackjack;

import java.util.ArrayList;
import java.util.Observable;

import casino.gambler.BlackjackDealer;
import casino.gambler.BlackjackPlayer;
import casino.playingCard.PlayingCard;
import rules.BlackjackRules;
import enumerations.BlackjackMove;
import exceptions.InvalidShoeException;
import exceptions.TableSeatNumberInvalidException;
import exceptions.TableSeatTakenException;

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
        boolean seatOccupied = this.players.get(seat) != null;
        
        return seatOccupied;
    }
    
    private void seatPlayerAndAssociateHands(BlackjackPlayer blackjackPlayer, int seat) {
        ArrayList<BlackjackHand> hands = new ArrayList<BlackjackHand>();
        this.playersHands.set(seat, hands);
        this.players.set(seat, blackjackPlayer);
        blackjackPlayer.setHands(hands);
        blackjackPlayer.initializeStrategy(this.rules, this.shoe.getNumDecks());
        addObserver(blackjackPlayer);
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
    
    private boolean doesShoeNeedRefill() {
        boolean shoeNeedsRefill = false;
        
        if (this.shoe.wasCutCardMet()) {
            shoeNeedsRefill = true;
        }
        
        return shoeNeedsRefill;
    }
    
    private void refillShoe() {
        while (this.discardTray.getNumCards() > 0) {
            PlayingCard card = this.discardTray.removeCard();
            this.shoe.addCard(card);
        }
    }
    
    private void shuffleShoeAndDiscardFirstCard() {
        this.shoe.shuffleShoe();
        PlayingCard initialCard = this.shoe.dealCard();
        this.discardTray.addCard(initialCard);
    }
    
    private void resetPlayersCardCounts() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                this.players.get(i).resetCount();
            }
        }
    }
    
    public boolean hasPlayerAtSeat(int seat) {
        boolean seatOccupied = false;
        
        if (this.players.get(seat) != null) {
            seatOccupied = true;
        }
        
        return seatOccupied;
    }
    
    private void setBetAmountForAllPlayers() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                this.players.get(i).setBetAmount();
            }
        }
    }
    
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
    
    private void dealCardToPlayerInitialHand(int seat) {
        BlackjackHand hand = retrievePlayerFirstHand(seat);
        dealCardFromShoeToHand(hand);
    }
    
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
    
    private void dealCardFromShoeToHand(BlackjackHand hand) {
        PlayingCard dealtCard = this.shoe.dealCard();
        notifyCardValueToPlayers(dealtCard);
        hand.addCard(dealtCard);
    }
    
    private void notifyCardValueToPlayers(PlayingCard card) {
        setChanged();
        notifyObservers(card);
        clearChanged();
    }
    
    private void dealCardToDealer(int cardPosition) {
        PlayingCard dealersCard = this.shoe.dealCard();
        
        if (cardPosition == 0) {
            notifyCardValueToPlayers(dealersCard);
        }
        
        this.dealerHand.addCard(dealersCard);
    }
    
    private void setInsuranceForAllPlayers() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                this.players.get(i).setTakesInsurance();
            }
        }
    }
    
    private void adjustAllPlayersChipsForInsurance() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                adjustPlayerChipsForInsurance(i);
            }
        }
    }
    
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
    
    private void playPlayersTurns() {
        for (int i = 0; i < this.players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                playPlayerTurn(i);
            }
        }
    }
    
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
    
    private void exposeDealerHoleCard() {
        notifyCardValueToPlayers(this.dealerHand.getSecondCard());
    }
    
    private void playDealerTurn() {
        if (hasPlayerHandRemaining()) {
            while (this.rules.getDealersMove(this.dealerHand) != BlackjackMove.STAND) {
                dealCardFromShoeToHand(this.dealerHand);
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
    
    private void payoutPlayer(int seat) {
        final double playerBet = this.players.get(seat).getBetAmount();
        
        for (int i = 0; i < this.playersHands.get(seat).size(); i++) {
            final BlackjackHand hand = this.playersHands.get(seat).get(i);
            final int numPlayerHands = this.playersHands.get(seat).size();
            final double playerPayout = this.rules.getPayoutAdjustment(hand, this.dealerHand, numPlayerHands) * playerBet;
            this.players.get(seat).adjustCashTotal(playerPayout);
        }
    }
    
    private void collectAllCards() {
        for (int i = 0; i < this.playersHands.size(); i++) {
            collectPlayerCardsAtSeat(i);
        }
        
        collectDealerCards();
    }
    
    private void collectPlayerCardsAtSeat(final int seat) {
        while (this.playersHands.get(seat) != null && this.playersHands.get(seat).size() > 0) {
            BlackjackHand hand = this.playersHands.get(seat).remove(this.playersHands.get(seat).size() - 1);
            
            while (hand.getNumCards() > 0) {
                PlayingCard card = hand.removeCard();
                this.discardTray.addCard(card);
            }
        }
    }
    
    private void collectDealerCards() {
        while (this.dealerHand.getNumCards() > 0) {
            PlayingCard card = this.dealerHand.removeCard();
            this.discardTray.addCard(card);
        }
    }
    
    private void printTable() {
        if (this.dealerHand.isBlackjack()) {
            System.out.println("Dealer Blackjack");
        }
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
    
    private void printCardCount() {
        //System.out.println("Card count:" + kissIStrategy.getCount());
    }
}
