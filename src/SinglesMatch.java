//package mySlam;

import java.util.ArrayList;
import java.awt.Menu;
import java.util.Collections;
import java.util.Scanner;

public class SinglesMatch extends Match{

	Scanner userInput = new Scanner (System.in);
		
	public SinglesMatch(Player firstGuy, Player secondGuy, ArrayList<Player> association) {
		
		System.out.print(firstGuy.matchToString() + " versus " + secondGuy.matchToString());
		
		enterTime();
		giveDirections(findRow(firstGuy, secondGuy), findCol(firstGuy, secondGuy), firstGuy);
		
		enterWinner(association);
	}
		
	public void enterWinner(ArrayList<Player> association) {
		
		System.out.print("\n\n\nWho won the match? (If you decided not to play, enter 0, if they didn't show up, enter 1.) ");
		String firstInput = userInput.next();
			
		if (firstInput.indexOf('1') > 0) {
				
			System.out.print("What was their name: ");
			String skippedName = userInput.next(); 
			
			int index = MenuDriven.findName(skippedName, association);
			association.get(index).addPay(-5);
		}
			
		else if (firstInput.indexOf('0') == - 1) {
				
			String winnerName = userInput.next();
			int index = MenuDriven.findName(winnerName, association);
			association.get(index).addPay(5);
			winner1 = association.get(index);
		}
	}
		
	public Player getWinner() {
		return winner1;
	}
}
