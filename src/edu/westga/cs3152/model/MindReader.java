package edu.westga.cs3152.model;

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
	private String botGuess;
	private String userGuess;
	
	public MindReader() {
		this.botWins = 0;
		this.userWins = 0;
	}

	public int getBotWins() {
		return this.botWins;
	}

	public int getUserWins() {
		return this.userWins;
	}
	
	public void start() {
		this.playRound();
	}
	
	private void playRound() {
		this.botGuess =  "";
		this.userGuess = "";
		System.out.println("Guess head or tails and I'll predict your guess.");
		System.out.println("What is your guess [h/t] ?");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("h") || input.equalsIgnoreCase("t")) {
				this.userGuess = input;
				break;
			}
		}
		this.makeABotGuess();
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
	
	private void makeABotGuess() {
		this.botGuess = "h";
	}
	
	private boolean correctGuess() {
		if (this.botGuess.equals(this.userGuess)) {
			return true;
		}
		return false;
	}
	
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
