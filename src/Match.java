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
		return (firstGuy.getRow() + secondGuy.getRow()) / 2;
	}
	
	public int findCol(Player firstGuy, Player secondGuy) {
		return (firstGuy.getCol() + secondGuy.getCol()) / 2;
	}
	
	public void giveDirections(int playRow, int playCol, Player giveTo) {
		String directions = "";
		
		double rowDif = playRow - giveTo.getRow(), colDif = playCol - giveTo.getCol();
		double slope = rowDif / colDif;
		double dist = giveTo.distanceMiles(playRow, playCol);
		
		if (colDif == 0) {
			
			if (rowDif == 0) {
				directions = "You're already at the courts!";
			}
			
			else {
				if (rowDif > 0) {
					directions = "To get there, head north for " + dist + " miles.";
				}
				else {
					directions = "To get there, head south for " + dist + " miles.";
				}
			}
		}
		
		else {
			
			if ((rowDif > 0) && (colDif > 0)) {
				directions = directionsHelper("north", "north east", "east", true, slope);
			}
			
			else if ((rowDif > 0) && (colDif < 0)) {
				directions = directionsHelper("north", "north west", "west", false, slope);
			}
			
			else if ((rowDif < 0) && (colDif > 0)) {
				directions = directionsHelper("south", "south east", "east", false, slope);
			}
			
			else if ((rowDif < 0) && (colDif < 0)) {
				directions = directionsHelper("south", "south west", "west", true, slope);
			}
			
			directions = "To get there, head " + directions + " for " + dist + " miles.";
		}	
		System.out.print("\nYou will play at (" + playRow + ", " + playCol + ").");
		System.out.print("\n" + directions);
	}
	
	public String directionsHelper(String op1, String op2, String op3, boolean positive, double slope) {
		if (positive) {
			if (slope > 2.4142) {
				return op1;
			}
			else if (slope > 0.4142) {
				return op2;
			}
		}
		else {
			if (slope < -2.4142) {
				return op1;
			}
			else if (slope < -0.4142) {
				return op2;
			}
		}
		
		return op3;
	}
	
	public String enterTime() {
		boolean valid = true;
		
		do {
			if (! valid) {
				System.out.println("Invalid input, try again.");
			}
			valid = true;
				
			System.out.print("\nOn what day do you want to play? (Ex: 4/22) ");
			playTime = userInput.nextLine().trim();
				
			if (playTime.indexOf('/') == -1) {
				valid = false;
			}
			else {
				int slashIndex = playTime.indexOf('/');
				
				if ((playTime.substring(0, slashIndex).length() > 2) || (playTime.substring(slashIndex).length() > 2)) {
					valid = false;
					continue;
				}
				if (! Character.isDigit(playTime.charAt(0)))	{
					valid = false;
				}
			}
		} while (! valid);
			
		char userPlay = 'M';
		
		do {
			if (! valid) {
				System.out.println("Invalid input, try again.");
			}
			valid = true;
			
			System.out.print("Do you want to play in the morning (M) or evening (E)? "); 
			userPlay = userInput.next().charAt(0);
			
		} while ((userPlay != 'M') && (userPlay != 'm') && (userPlay != 'E') && (userPlay != 'e'));
		
		String temp = "";
		
		do {
			if (! valid) {
				System.out.println("Invalid input, try again.");
			}
			valid = true;
			
			System.out.print("Enter the time when you want to play: (Ex: 3:00) ");
			temp = userInput.next().trim();
			
			if (temp.indexOf(':') == -1) {
				valid = false;
				continue;
			}
			for (int n = 0; n < 4; n ++) {
				if (n == 1) {
					n ++;
				}
				if (! Character.isDigit(temp.charAt(n))) {
					valid = false;
					break;
				}
			}
		} while (! valid);
		
		String AMPM = "";
		
		if ((userPlay == 'M') || (userPlay == 'm')) {
			AMPM = "AM";
		}
		else {
			AMPM = "PM";
		}
		
		return "Date: " + playTime + " Time: " + temp + " " + AMPM;
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
