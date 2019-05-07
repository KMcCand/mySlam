package mySlam;

import java.awt.Menu;
import java.util.Collections;
import java.util.Scanner;

public class SinglesMatch extends Match{

	Scanner userInput = new Scanner (System.in);
		
	public SinglesMatch(Player firstGuy, Player secondGuy) {
		enterTime();
		
		playRow = findRow(firstGuy, secondGuy);
		playCol = findCol(firstGuy, secondGuy);
		
		enterWinner();
	}
		
	public void enterWinner() {
		System.out.print("Who won the match? (If you decided not to play, enter 0, if they didn't show up, enter 1.)");
		String firstInput = userInput.next();
			
		if (firstInput.indexOf('1') > 0) {
				
			System.out.print("What was their name: ");
			String skippedName = userInput.next(); 
			
			int index = Collections.binarySearch(menuDriven.Association, skippedName);
			menuDriven.Association.get(index).addPay(-5);
		}
			
		else if (firstInput.indexOf('0') == - 1) {
				
			String winnerName = userInput.next();
			int index = Collections.binarySearch(menuDriven.Association, winnerName);
			menuDriven.Association.get(index).addPay(5);
			winner1 = menuDriven.Association.get(index);
		}
	}
		
	public Player getWinner() {
		return winner1;
	}
}
