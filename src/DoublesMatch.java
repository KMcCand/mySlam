import java.util.Collections;
import java.util.Scanner;

public class DoublesMatch extends Match {
	
	public DoublesMatch() {
		
	}
	
	public DoublesMatch(Player team1First, Player team1Second, Player team2First, Player team2Second) {
		enterTime();
		
		playRow = findRow(findRow(team1First, team1Second), findRow(team2First, team2Second));
		playCol = findCol(findCol(team1First, team1Second), findCol(team2First, team2Second));
		
		enterWinner();
	}
	
	public void enterWinner() {
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
			
			int index = Collections.binarySearch(menuDriven.Association, skippedName);
			menuDriven.Association.get(index).addPay(-5);
			
			break;
		case 3:
			boolean valid = true;
			
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
					String winner1Name = winnerLine.substring(0, spacePos);
					String winner2Name = winnerLine.substring(spacePos + 1);
				}
				
			} while (valid = false);
			
			int Winner1Index = Collections.binarySearch(menuDriven.Association, winner1Name);
			int Winner2Index = Collections.binarySearch(menuDriven.Association, winner2Name);
			
			winner1 = menuDriven.Association.get(Winner1Index);
			winner1.addPay(5);
			
			winner2 = menuDriven.Association.get(Winner2Index);
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
