import java.util.Scanner;
import java.util.ArrayList;

public class DoublesMatch extends Match {
	
	public DoublesMatch() {
		
	}
	
	public DoublesMatch(Player team1First, Player team1Second, Player team2First, Player team2Second, ArrayList<Player> association) {
		
		System.out.print(team1First.toString() + " and " + team1Second.toString() + " versus " + team2First.toString() + " and " + team2Second.toString()); 
		enterTime();
		
		playRow = (findRow(team1First, team1Second) + findRow(team2First, team2Second)) / 2;
		playCol = (findCol(team1First, team1Second) + findCol(team2First, team2Second)) / 2;
		
		enterWinner(association);
	}
	
	public void enterWinner(ArrayList<Player> association) {
		Scanner userInput = new Scanner (System.in);
		int userChoice = 0;
		
		do {
		
			System.out.println("Choose:");
			System.out.println("  1   Somebody didn't show up");
			System.out.println("  2   You decided not to play");
			System.out.println("  3   Enter a winner");
			
			userChoice = userInput.nextInt();
			
		} while ((userChoice < 1) || (userChoice > 3));
		
		switch(userChoice) {
		case 1:
			System.out.print("Enter their name: ");
			String skippedName = userInput.next(); 
			
			int index = MenuDriven.findName(skippedName, association);
			// WRITE OUR OWN BINARY SEARCH METHOD SCREW THIS 
			association.get(index).addPay(-5);
			
			break;
		case 3:
			boolean valid = true;
			String winner1Name = "", winner2Name = "";
			
			do {
				
				if (valid = false) {
					System.out.println("Invalid input. Try again.");
				}
			
				System.out.print("Enter the team who won, separated by a space: ");
				String winnerLine = userInput.next();
				int spacePos = winnerLine.indexOf(' ');
				
				if (spacePos == -1) {
					valid = false;
				}
				else {
					winner1Name = winnerLine.substring(0, spacePos);
					winner2Name = winnerLine.substring(spacePos + 1);
				}
				
			} while (valid = false);
			
			int Winner1Index = MenuDriven.findName(winner1Name, association);
			int Winner2Index = MenuDriven.findName(winner2Name, association);
			
			winner1 = association.get(Winner1Index);
			winner1.addPay(5);
			
			winner2 = association.get(Winner2Index);
			winner2.addPay(5);
			
			break;
		}
	}
	
	public Player getWinner1() {
		return winner1;
	}
	
	public Player getWinner2() {
		return winner2;
	}
}
