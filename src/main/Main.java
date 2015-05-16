package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import rules.BlackjackRules;
import enumerations.CardRank;
import enumerations.CardSuit;
import exceptions.InvalidConfigFileException;
import exceptions.InvalidShoeException;
import exceptions.TableOperationException;
import modelObjects.BlackjackCard;
import modelObjects.BlackjackDealer;
import modelObjects.BlackjackPlayer;
import modelObjects.BlackjackTable;
import modelObjects.Shoe;

public class Main {
	private static final String shoeConfig = "#SHOE_PARAMETERS";
	private static final String shoeConfigNumDecks = "#numDecks";
	private static final String shoeConfigDeckPenetration = "#deckPenetration";
	
	private static final String rulesConfig = "#RULES";
	private static final String rulesConfigMaxHands = "#maxHands";
	private static final String rulesConfigBlackjackPayout = "#blackjackPayout";
	private static final String rulesConfigDoubleAfterSplit = "#doubleAfterSplit";
	private static final String rulesConfigDealerHitsSoft17 = "#dealerHitsSoft17";
	private static final String rulesConfigResplitAces = "#resplitAces";
	
	private static final String tableConfig = "#TABLE_PARAMETERS";
	private static final String tableConfigNumHands = "#numHands";
	
	private static final String playerConfig = "#PLAYER_PARAMETERS";
	private static final String playerConfigNumPlayers = "#numPlayers";
	private static final String playerConfigCardCounter = "#playerCardCounter";
	private static final String playerConfigSeat = "#tableSeat";
	
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
	private static int numHands;
	
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
		try {
			openFile();
			setUpShoe();
			setUpRules();
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
				//e.printStackTrace();
			}
		} while (invalidFile);
	}//end method acquireParameters
	
	/**
	 * Reads the opened configuration file to read the shoe parameters.
	 * @throws Exception
	 */
	private static void setUpShoe() throws InvalidConfigFileException {
		String heading;
		
		//read the heading
		heading = configFileReader.next();
		if (!heading.equals(shoeConfig)) {
			throw new InvalidConfigFileException("Expected " + shoeConfig);
		}
		
		//read the number of decks
		heading = configFileReader.next();
		if (!heading.equals(shoeConfigNumDecks)) {
			throw new InvalidConfigFileException("Expected " + shoeConfigNumDecks);
		}
		numDecks = configFileReader.nextInt();
		
		//read the deck penetration
		heading = configFileReader.next();
		if (!heading.equals(shoeConfigDeckPenetration)) {
			throw new InvalidConfigFileException("Expected " + shoeConfigDeckPenetration);
		}
		deckPenetration = configFileReader.nextInt();
		
		try {
			shoe = new Shoe(numDecks, deckPenetration);
		} catch (InvalidShoeException e) {
			e.printStackTrace();
		}
	}//end method setUpShoe
	
	/**
	 * Reads the opened configuration file to read the shoe parameters.
	 * @throws InvalidConfigFileException
	 */
	private static void setUpRules() throws InvalidConfigFileException {
		String heading;
		
		//read the heading
		heading = configFileReader.next();
		if (!heading.equals(rulesConfig)) {
			throw new InvalidConfigFileException("Expected " + rulesConfig);
		}
		
		//read the number of max hands after player splits/resplits
		heading = configFileReader.next();
		if (!heading.equals(rulesConfigMaxHands)) {
			throw new InvalidConfigFileException("Expected " + rulesConfigMaxHands);
		}
		maxHands = configFileReader.nextInt();
		if (maxHands < 1 || maxHands > 4) {
			throw new InvalidConfigFileException("Maximum number of hands must be between 1 and 4");
		}
		
		//read the blackjack payout
		heading = configFileReader.next();
		if (!heading.equals(rulesConfigBlackjackPayout)) {
			throw new InvalidConfigFileException("Expected " + rulesConfigBlackjackPayout);
		}
		blackjackPayout = configFileReader.nextDouble();
		
		//read the rules for double after split
		heading = configFileReader.next();
		if (!heading.equals(rulesConfigDoubleAfterSplit)) {
			throw new InvalidConfigFileException("Expected " + rulesConfigDoubleAfterSplit);
		}
		heading = configFileReader.next();
		doubleAfterSplit = Boolean.parseBoolean(heading);
		
		//read the rules for dealer hits soft 17
		heading = configFileReader.next();
		if (!heading.equals(rulesConfigDealerHitsSoft17)) {
			throw new InvalidConfigFileException("Expected " + rulesConfigDealerHitsSoft17);
		}
		heading = configFileReader.next();
		dealerHitsSoft17 = Boolean.parseBoolean(heading);
		
		//read the rules for resplitting aces
		heading = configFileReader.next();
		if (!heading.equals(rulesConfigResplitAces)) {
			throw new InvalidConfigFileException("Expected " + rulesConfigResplitAces);
		}
		heading = configFileReader.next();
		resplitAces = Boolean.parseBoolean(heading);
		
		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
		rulesBuilder.setMaxHands(maxHands);
		rulesBuilder.setBlackjackPayout(blackjackPayout);
		rulesBuilder.setDoubleAfterSplit(doubleAfterSplit);
		rulesBuilder.setDealerHitsSoft17(dealerHitsSoft17);
		rulesBuilder.setResplitAces(resplitAces);
		rules = rulesBuilder.build();
	}//end method setUpRules
	
	/**
	 * Reads the opened configuration file to read the table parameters.
	 * @throws InvalidConfigFileException
	 */
	private static void setUpTable() throws InvalidConfigFileException {
		String heading;
		
		//read the heading
		heading = configFileReader.next();
		if (!heading.equals(tableConfig)) {
			throw new InvalidConfigFileException("Expected " + tableConfig);
		}
		
		//read the number of hands to simulate
		heading = configFileReader.next();
		if (!heading.equals(tableConfigNumHands)) {
			throw new InvalidConfigFileException("Expected " + tableConfigNumHands);
		}
		numHands = configFileReader.nextInt();
		
		blackjackTable = new BlackjackTable(numHands, shoe, rules);
	}//end method setUpTable
	
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
		
		//read the heading
		heading = configFileReader.next();
		if (!heading.equals(playerConfig)) {
			throw new InvalidConfigFileException("Expected " + playerConfig);
		}
		
		//read the number of players
		heading = configFileReader.next();
		if (!heading.equals(playerConfigNumPlayers)) {
			throw new InvalidConfigFileException("Expected " + playerConfigNumPlayers);
		}
		numPlayers = configFileReader.nextInt();
		if (numPlayers < BlackjackTable.MIN_PLAYERS || numPlayers > BlackjackTable.MAX_PLAYERS) {
			throw new InvalidConfigFileException("Invalid number of players");
		}
		
		//create the players
		for (int i = 0; i < numPlayers; i++) {
			heading = configFileReader.next();
			if (!heading.equals(playerConfigSeat)) {
				throw new InvalidConfigFileException("Expected " + playerConfigSeat);
			}
			heading = configFileReader.next();
			int seat = Integer.parseInt(heading);
			
			heading = configFileReader.next();
			if (!heading.equals(playerConfigCardCounter)) {
				throw new InvalidConfigFileException("Expected " + playerConfigCardCounter);
			}
			heading = configFileReader.next();
			boolean cardCounter = Boolean.parseBoolean(heading);
			
			BlackjackPlayer player = new BlackjackPlayer(0, cardCounter);
			
			try {
				blackjackTable.addPlayer(player, seat);
			} catch (TableOperationException e) {
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
		for (int i = 0; i < numHands; i++) {
			System.out.println("**********************************");
			System.out.println("Round:" + i);
			blackjackTable.playRound();
		}
	}
}
