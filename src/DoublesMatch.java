import java.util.Scanner;
import java.util.ArrayList;

public class DoublesMatch extends Match {
	
	public DoublesMatch() {
		
	}
	
	public DoublesMatch(Player team1First, Player team1Second, Player team2First, Player team2Second, ArrayList<Player> theAssociation) {
		
		association = theAssociation;
		user = team1First;
		userPartner = team1Second;
		them = team2First;
		themPartner = team2Second;
		
		System.out.print(team1First.matchToString() + " and " + team1Second.matchToString() + " versus " + team2First.matchToString() + " and " + team2Second.matchToString()); 
		
		playRow = (findRow(team1First, team1Second) + findRow(team2First, team2Second)) / 2;
		playCol = (findCol(team1First, team1Second) + findCol(team2First, team2Second)) / 2;
		giveDirections(playRow, playCol, team1First);
		
		enterWinner();
	}
	
	public void enterWinner() {
		Scanner userInput = new Scanner (System.in);
		int userChoice = 0;
		
		do {
		
			System.out.println("\n\n\n\nChoose:");
			System.out.println("  1 - They won");
			System.out.println("  2 - You won");
			System.out.println("  3 - They didn't show up");
			System.out.println("  4 - You guys decided not to play");
			
			userChoice = userInput.nextInt();
			
		} while ((userChoice < 1) || (userChoice > 4));
		
		switch(userChoice) {
		
		case 1:
			addThePay(them, 2.5);
			addThePay(themPartner, 2.5);
			
			System.out.print("They have each been given $2.50.");
			changeRating(them, themPartner, user, userPartner);
			
			break;
			
		case 2:
			addThePay(user, 2.5);
			addThePay(userPartner, 2.5);
			
			System.out.print("You have each been given $2.50.");
			changeRating(user, userPartner, them, themPartner);
			
			break;
			
		case 3:
			addThePay(them, -2.5);
			addThePay(themPartner, -2.5);
			
			System.out.print("They were each fined $2.50.");
			break;
		}
	}
}
