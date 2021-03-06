package edu.westga.cs3152.model;

import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 * The Class MindReader
 * 
 * @author Daniel Crumpler
 * @version Fall 2020
 */
public class MindReader {

	private int botWins;
	private int userWins;
	private int roundNumber;
	private String botGuess;
	private String userGuess;
	private Hashtable<Integer, String> table;

	/**
	 * Creates a new MindReader.
	 * 
	 * @precondition none
	 * @postcondition getBotWins() == 0, getUserWins() == 0, getRoundNumber() == 0
	 */
	public MindReader() {
		this.botWins = 0;
		this.userWins = 0;
		this.roundNumber = 0;
		this.table = new Hashtable<Integer, String>();
	}

	/**
	 * Gets the bot's wins.
	 * 
	 * @return the bot's wins
	 */
	public int getBotWins() {
		return this.botWins;
	}

	/**
	 * Gets the user's wins.
	 * 
	 * @return the user's wins
	 */
	public int getUserWins() {
		return this.userWins;
	}

	/**
	 * Gets the round number.
	 * 
	 * @return the round number
	 */
	public int getRoundNumber() {
		return this.roundNumber;
	}

	/**
	 * Starts the first round of MindReader
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void start() {
		this.playRound();
	}

	/**
	 * The normal round for the game. Generates guess for the bot, reads user's
	 * guess, and outputs result in the console.
	 * 
	 * @precondition none
	 * @postcondition a point had been rewarded to the winner, and proper solution
	 *                strings is presented.
	 */
	private void playRound() {
		this.botGuess = "";
		this.userGuess = "";
		this.roundNumber += 1;
		System.out.println("Guess head or tails and I'll predict your guess.");
		System.out.println("What is your guess [h/t] ?");
		this.makeABotGuess();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("h") || input.equalsIgnoreCase("t")) {
				this.userGuess = input;
				break;
			}
		}
		this.addGuessToHashTable(this.roundNumber, this.userGuess);
		boolean correctGuess = this.correctGuess();
		String formattedGuess = this.formatGuess(correctGuess);
		System.out.println(formattedGuess);
		System.out.println("Score = " + this.botWins + " | " + this.userWins + System.lineSeparator());
		if (this.botWins < 25 && this.userWins < 25) {
			this.playRound();
		} else {
			scanner.close();
			if (this.botWins >= 25) {
				System.out.print("The bot won.");
			} else if (this.userWins >= 25) {
				System.out.print("You won.");
			}
		}
	}

	/**
	 * Adds the Guess to the HashTable.
	 * 
	 * @precondition none
	 * @postcondition table.size() = table.size() + 1
	 * 
	 * @param key   the key
	 * @param value the value
	 */
	private void addGuessToHashTable(int key, String value) {
		this.table.put(key, value);
	}

	/**
	 * Makes a guess for the bot.
	 * 
	 * @precondition none
	 * @postcondition this.botGuess = a head/tails choice.
	 */
	private void makeABotGuess() {
		if (this.table.size() < 4) {
			Random random = new Random();
			boolean randomBoolean = random.nextBoolean();
			if (randomBoolean) {
				this.botGuess = "h";
			} else {
				this.botGuess = "t";
			}
		} else {
			this.botGuess = this.table.get(this.roundNumber - 4);
		}
	}

	/**
	 * Checks to see if the bot's guess equals the user's guess.
	 * 
	 * @return if the bot's guess equals the user's guess
	 */
	private boolean correctGuess() {
		if (this.botGuess.equals(this.userGuess)) {
			return true;
		}
		return false;
	}

	/**
	 * Formats the outcome string for the program.
	 * 
	 * @precondition none
	 * @postcondition the win is added to the proper winner.
	 * 
	 * @param correctGuess the bot's guess equal to user's guess
	 * @return a formatted string
	 */
	private String formatGuess(boolean correctGuess) {
		if (correctGuess) {
			this.botWins += 1;
			return "Yes!. I too predicted " + this.botGuess + ". ";
		} else if (!correctGuess) {
			this.userWins += 1;
			return "No. I predicted " + this.botGuess + ".";
		} else {
			return "Error";
		}
	}
}
