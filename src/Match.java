//package mySlam;
import java.util.ArrayList;
import java.awt.Menu;
import java.util.Collections;
import java.util.Scanner;

public abstract class Match {
	Scanner userInput = new Scanner (System.in);
	
	int playRow, playCol;
	String playTime;
	Player winner1, winner2;
	
	public Match() {
		playRow = 0;
		playCol = 0;
		playTime = "";
		winner1 = new Player();
		winner2 = new Player();
	}
	
	public abstract void enterWinner(ArrayList<Player> association);
	
	public int findRow(Player firstGuy, Player secondGuy) {
		// NOTE: NEED TO USE GOOGLE MAPS TO FIND AN ACTUAL TENNIS CLUB WHEN GET THE CHANCE
		return (firstGuy.getRow() + secondGuy.getRow()) / 2;
	}
	
	public int findCol(Player firstGuy, Player secondGuy) {
		// NOTE: NEED TO USE GOOGLE MAPS TO FIND AN ACTUAL TENNIS CLUB WHEN GET THE CHANCE
		return (firstGuy.getCol() + secondGuy.getCol()) / 2;
	}
	
	public String enterTime() {
		boolean valid = true;
		do {
			if (valid == false) {
				System.out.println("Invalid input, try again.");
			}
			valid = true;
				
			System.out.print("\nWhat time do you want to play (ex: 12/3 3:00 PM): ");
			playTime = userInput.next();
				
			if (playTime.indexOf('/') == -1) {
				valid = false;
			}
			else if (playTime.indexOf(':') == -1) {
				valid = false;
			}
			else if ((playTime.indexOf("AM") == -1) && (playTime.indexOf("PM") == -1)) {
				valid = false;
			}
			else if (playTime.indexOf(' ') == -1) {
				valid = false;
			}
				
		} while (valid == false);
		
		return playTime;
	}
	
	public String getPlayTime() {
		return playTime;
	}
	
	public int getPlayRow() {
		return playRow;
	}
	
	public int getPlayCol() {
		return playCol;
	}
	
}
