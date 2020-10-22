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
		this.playRound();
	}

	public int getBotWins() {
		return this.botWins;
	}

	public int getUserWins() {
		return this.userWins;
	}
	
	private void start() {
		while (botWins < 25 || userWins < 25) {
			
		}
	}
	
	private void playRound() {
		System.out.println("Guess head or tails and I'll predict your guess.");
		System.out.print("What is your guess [h/t] ?");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String input = scanner.next();
			if (input.equals("h") || input.equals("t")) {
				this.userGuess = input;
				scanner.close();
			}
		}
		this.makeABotGuess();
		boolean correctGuess = this.correctGuess();
		String formattedGuess = this.formatGuess(correctGuess);
		System.out.println(formattedGuess);
		System.out.println("Score = " + this.botWins + " | " + this.userWins);
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
			return "Yes!. I too predicted " + this.botGuess + ". ";
		} else if (!correctGuess) {
			return "No. I predicted " + this.botGuess + ".";
		} else {
			return "Error";
		}
	}
}
