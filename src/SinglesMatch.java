//package mySlam;

import java.util.ArrayList;
import java.awt.Menu;
import java.util.Collections;
import java.util.Scanner;

public class SinglesMatch extends Match{

	Scanner userInput = new Scanner (System.in);
	
		
	public SinglesMatch(Player firstGuy, Player secondGuy, ArrayList<Player> theAssociation) {
		user = firstGuy;
		them = secondGuy;
		association = theAssociation;
		
		String line = firstGuy.matchToString() + " vs. " + secondGuy.matchToString();
		printHeader(line);
		
		giveDirections(findRow(firstGuy, secondGuy), findCol(firstGuy, secondGuy), firstGuy);
		
		enterWinner();
	}
		
	public void enterWinner() {
		
		int userChoice = 4;
		
		do {
			System.out.println("\n\n\n     What was the outcome?");
			System.out.println("       1 - They won");
			System.out.println("       2 - You won");
			System.out.println("       3 - They didn't show up");
			System.out.println("       4 - You guys decided not to play");
			
			userChoice = userInput.nextInt();
			
		} while ((userChoice < 1) || (userChoice > 4));
			
		switch (userChoice) {
		
		case 1:
			addThePay(them, 5);
			System.out.print("     They have been given $5.00.");
			changeRating(them, user);
			
			break;
			
		case 2:
			addThePay(user, 5);
			System.out.print("     You have been given $5.00.");
			changeRating(user, them);
			
			break;
			
		case 3:
			addThePay(them, -5);
			System.out.print("     They have been fined $5.00.");
			break;
		}
	}
}
