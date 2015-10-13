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
    private boolean insuranceOffered;
    
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
            players = new ArrayList<BlackjackPlayer>();
            playersHands = new ArrayList<ArrayList<BlackjackHand>>();
            dealerHand = new BlackjackHand();
            
            for (int i = 0; i < MAX_PLAYERS; i++) {
                players.add(null);
                playersHands.add(null);
            }
            
            discardTray = new DiscardTray();
            dealer = null;
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
        boolean seatOccupied = players.get(seat) != null;
        
        return seatOccupied;
    }
    
    private void seatPlayerAndAssociateHands(BlackjackPlayer blackjackPlayer, int seat) {
        ArrayList<BlackjackHand> hands = new ArrayList<BlackjackHand>();
        playersHands.set(seat, hands);
        players.set(seat, blackjackPlayer);
        blackjackPlayer.setHands(hands);
        blackjackPlayer.updateStrategy(rules, shoe.getNumDecks());
        addObserver(blackjackPlayer);
    }
    
    /**
     * Associates a blackjack dealer with the table.
     * @param dealer  The blackjack dealer.
     */
    public void setDealer(BlackjackDealer dealer) {
        this.dealer = dealer;
        this.dealer.setHand(dealerHand);
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
    
    private void shuffleShoeAndDiscardFirstCard() {
        shoe.shuffleShoe();
        PlayingCard initialCard = shoe.dealCard();
        discardTray.addCard(initialCard);
    }
    
    private void resetPlayersCardCounts() {
        for (int i = 0; i < players.size(); i++) {
            if (hasPlayerAtSeat(i)) {
                players.get(i).resetCount();
            }
        }
    }
    
    public boolean hasPlayerAtSeat(int seat) {
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
        
        if (playersHands.get(seat).size() == 0) {
            hand = new BlackjackHand();
            playersHands.get(seat).add(hand);
        } else {
            hand = playersHands.get(seat).get(0);
        }
        
        return hand;
    }
    
    private void dealCardFromShoeToHand(BlackjackHand hand) {
        PlayingCard dealtCard = shoe.dealCard();
        notifyCardValueToPlayers(dealtCard);
        hand.addCard(dealtCard);
    }
    
    private void notifyCardValueToPlayers(PlayingCard card) {
        setChanged();
        notifyObservers(card);
        clearChanged();
    }
    
    private void dealCardToDealer(int cardPosition) {
        PlayingCard dealersCard = shoe.dealCard();
        
        if (cardPosition == 0) {
            notifyCardValueToPlayers(dealersCard);
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
                dealCardFromShoeToHand(playerHand);
            } else {
                move = players.get(seat).getAction(dealerUpCard, playerHand, numHands);
                
                switch (move) {
                    case STAND:        j++;
                                    break;
                    case HIT:        dealCardFromShoeToHand(playerHand);
                                    
                                    if (playerHand.isBust()) {
                                        j++;
                                    }
                                    
                                    break;
                    case SPLIT:        BlackjackHand splitHand = playerHand.split();
                                    playersHands.get(seat).add(j + 1, splitHand);
                                    break;
                    case DOUBLE:    dealCardFromShoeToHand(playerHand);
                                    playerHand.setWasDoubleDown(true);
                                    j++;
                                    break;
                    default:        break;
                }
            }
        }
    }
    
    private void exposeDealerHoleCard() {
        notifyCardValueToPlayers(dealerHand.getSecondCard());
    }
    
    private void playDealerTurn() {
        if (hasPlayerHandRemaining()) {
            while (rules.getDealersMove(dealerHand) != BlackjackMove.STAND) {
                dealCardFromShoeToHand(dealerHand);
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
