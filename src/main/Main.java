package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import casino.blackjack.BlackjackTable;
import casino.blackjack.Shoe;
import casino.gambler.BlackjackDealer;
import casino.gambler.BlackjackPlayer;
import rules.BlackjackRules;
import configFile.ConfigFileHeading;
import exceptions.InvalidConfigFileException;
import exceptions.InvalidShoeException;
import exceptions.TableSeatNumberInvalidException;
import exceptions.TableSeatTakenException;

public class Main {
	
	//Shoe parameters
	private static int numDecks;
	private static int deckPenetration;
	
	//Rules parameters
	private static int maxHands;
	private static double blackjackPayout;
	private static boolean doubleAfterSplit;
	private static boolean dealerHitsSoft17;
	private static boolean resplitAces;
	
	//Table parameters
	private static int numHandsToSimulate;
	
	//Player parameters
	private static int numPlayers;
	
	private static Scanner keyboard;
	private static String configFileName;
	private static File configFile;
	private static Scanner configFileReader;
	
	private static BlackjackDealer dealer;
	private static Shoe shoe;
	private static BlackjackRules rules;
	private static BlackjackTable blackjackTable;
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("WELCOME TO BLACKJACK SIMULATOR");
		try {
			openFile();
			setUpRules();
			setUpShoe();
			setUpTable();
			setUpDealer();
			setUpPlayers();
			closeFile();
			runSimulation();
		} catch (InvalidConfigFileException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Asks the user for a configuration file name to open.
	 */
	private static void openFile() {
		keyboard = new Scanner(System.in);
		boolean invalidFile = false;
		
		do {
			try {
				System.out.print("Enter the name of the configuration file:");
				configFileName = keyboard.nextLine();
				configFile = new File(configFileName);
				configFileReader = new Scanner(configFile);
				invalidFile = false;
			} catch (FileNotFoundException e) {
				System.out.println("***ERROR, THAT FILE DOES NOT EXIST***");
				invalidFile = true;
			}
		} while (invalidFile);
	}//end method acquireParameters
	
	/**
	 * Reads the opened configuration file to read the shoe parameters.
	 * @throws InvalidConfigFileException
	 */
	private static void setUpRules() throws InvalidConfigFileException {
		String heading;
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.RULES_CONFIG_HEADING)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.RULES_CONFIG_HEADING);
		}
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.MAX_HANDS_AFTER_RESPLITS)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.MAX_HANDS_AFTER_RESPLITS);
		}
		maxHands = configFileReader.nextInt();
		if (maxHands < 1 || maxHands > 4) {
			throw new InvalidConfigFileException("Maximum number of hands must be between 1 and 4");
		}
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.BLACKJACK_PAYOUT)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.BLACKJACK_PAYOUT);
		}
		blackjackPayout = configFileReader.nextDouble();
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.DOUBLE_AFTER_SPLIT)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.DOUBLE_AFTER_SPLIT);
		}
		heading = configFileReader.next();
		doubleAfterSplit = Boolean.parseBoolean(heading);
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.DEALER_HITS_SOFT_17)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.DEALER_HITS_SOFT_17);
		}
		heading = configFileReader.next();
		dealerHitsSoft17 = Boolean.parseBoolean(heading);
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.RESPLIT_ACES)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.RESPLIT_ACES);
		}
		heading = configFileReader.next();
		resplitAces = Boolean.parseBoolean(heading);
		
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHandsAfterSplits(maxHands);
		rulesBuilder.setBlackjackPayoutMultiple(blackjackPayout);
		rulesBuilder.setDoubleAfterSplitAllowed(doubleAfterSplit);
		rulesBuilder.setDealerHitsSoft17(dealerHitsSoft17);
		rulesBuilder.setCanResplitAces(resplitAces);
		rules = rulesBuilder.build();
	}//end method setUpRules
	
	/**
	 * Reads the opened configuration file to read the shoe parameters.
	 * @throws Exception
	 */
	private static void setUpShoe() throws InvalidConfigFileException {
		String heading;
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.SHOE_CONFIG_HEADING)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.SHOE_CONFIG_HEADING);
		}
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.NUM_DECKS)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.NUM_DECKS);
		}
		numDecks = configFileReader.nextInt();
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.DECK_PENETRATION_IN_PERCENT)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.DECK_PENETRATION_IN_PERCENT);
		}
		deckPenetration = configFileReader.nextInt();
		
		try {
			shoe = new Shoe(numDecks, deckPenetration);
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the opened configuration file to read the table parameters.
	 * @throws InvalidConfigFileException
	 */
	private static void setUpTable() throws InvalidConfigFileException {
		String heading;
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.TABLE_PARAMETERS_HEADING)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.TABLE_PARAMETERS_HEADING);
		}
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.NUM_HANDS_TO_SIMULATE)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.NUM_HANDS_TO_SIMULATE);
		}
		numHandsToSimulate = configFileReader.nextInt();
		
        blackjackTable = new BlackjackTable(numDecks, deckPenetration, rules);
	}
	
	/**
	 * Creates the blackjack dealer.
	 */
	private static void setUpDealer() {
		dealer = new BlackjackDealer();
		blackjackTable.setDealer(dealer);
	}
	
	/**
	 * Reads the opened configuration file to read the player parameters
	 * @throws InvalidConfigFileException
	 */
	private static void setUpPlayers() throws InvalidConfigFileException {
		String heading;
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.PLAYER_PARAMETERS_HEADING)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.PLAYER_PARAMETERS_HEADING);
		}
		
		heading = configFileReader.next();
		if (!heading.equals(ConfigFileHeading.NUM_PLAYERS)) {
			throw new InvalidConfigFileException("Expected " + ConfigFileHeading.NUM_PLAYERS);
		}
		numPlayers = configFileReader.nextInt();
		if (numPlayers < BlackjackTable.MIN_PLAYERS || numPlayers > BlackjackTable.MAX_PLAYERS) {
			throw new InvalidConfigFileException("Invalid number of players");
		}
		
		//create the players
		for (int i = 0; i < numPlayers; i++) {
			heading = configFileReader.next();
			if (!heading.equals(ConfigFileHeading.SEAT_AT_TABLE)) {
				throw new InvalidConfigFileException("Expected " + ConfigFileHeading.SEAT_AT_TABLE);
			}
			heading = configFileReader.next();
			int seat = Integer.parseInt(heading);
			
			heading = configFileReader.next();
			if (!heading.equals(ConfigFileHeading.PLAYER_CARD_COUNTER)) {
				throw new InvalidConfigFileException("Expected " + ConfigFileHeading.PLAYER_CARD_COUNTER);
			}
			heading = configFileReader.next();
			boolean cardCounter = Boolean.parseBoolean(heading);
			
			BlackjackPlayer player = new BlackjackPlayer(0, cardCounter);
			
			try {
				blackjackTable.addPlayerAtSeat(player, seat);
			} catch (TableSeatTakenException e) {
				e.printStackTrace();
			} catch (TableSeatNumberInvalidException e) {
				e.printStackTrace();
			}
		}
	}//end method setUpPlayers
	
	/**
	 * Closes the configuration file reader.
	 */
	private static void closeFile() {
		configFileReader.close();
	}
	
	private static void runSimulation() {
		for (int i = 0; i < numHandsToSimulate; i++) {
			System.out.println("**********************************");
			System.out.println("Round:" + i);
			blackjackTable.playRound();
		}
	}
}
