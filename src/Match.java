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
	
	Player user, userPartner, them, themPartner;
	ArrayList<Player> association;
	
	public Match() {
		playRow = 0;
		playCol = 0;
		playTime = "";
		winner1 = new Player();
		winner2 = new Player();
	}
	
	public abstract void enterWinner();
	
	public int findRow(Player firstGuy, Player secondGuy) {
		return (firstGuy.getRow() + secondGuy.getRow()) / 2;
	}
	
	public int findCol(Player firstGuy, Player secondGuy) {
		return (firstGuy.getCol() + secondGuy.getCol()) / 2;
	}
	
	public void giveDirections(int playRow, int playCol, Player giveTo) {
		String directions = "", playTime = enterTime();
		
		double rowDif = giveTo.getRow() - playRow, colDif = playCol - giveTo.getCol();
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
		
		else if (rowDif == 0) {
			
			if (colDif > 0) {
				directions = "To get there, head east for " + dist + " miles.";
			}
			else {
				directions = "To get there, head west for " + dist + " miles.";
			}
		}
		
		else {
			double slope = rowDif / colDif;
			
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
		System.out.print("\nYou will play at (" + playRow + ", " + playCol + "), on " + playTime);
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
				
			if (playTime.length() < 3) {
				valid = false;
				continue;
			}
			if (playTime.indexOf('/') == -1) {
				valid = false;
			}
			else {
				int slashIndex = playTime.indexOf('/');
				
				if ((playTime.substring(0, slashIndex).length() > 2) || (playTime.substring(slashIndex + 1).length() > 2)) {
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
			
			if (temp.length() < 4) {
				valid = false;
				continue;
			}
			if (temp.indexOf(':') == -1) {
				valid = false;
				continue;
			}
			for (int n = 0; n < 4; n ++) {
				
				if (n != temp.indexOf(':')) {
					
					if (! Character.isDigit(temp.charAt(n))) {
						
						valid = false;
						continue;
					}
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
		
		return playTime + " at " + temp + " " + AMPM + ".";
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
	
	public void addThePay(Player guy, double payAmount) {
		
		association.get(MenuDriven.findName(guy.getName(), association)).addPay(payAmount);
	}
	
	public void changeRating(Player winner, Player loser) {
		
		if (winner.getSinglesRating() <= loser.getSinglesRating()) {
			
			winner.addSinglesRating(0.1);
			loser.addSinglesRating(-0.1);
			
			System.out.println("The new ratings:");
			System.out.println("  " + winner.matchToString());
			System.out.println("  " + loser.matchToString());
		}
		else {
			System.out.println("You ratings did not change.");
		}
		
	}
	
	public void changeRating(Player winner1, Player winner2, Player loser1, Player loser2) {
		
		if (avRating(winner1, winner2) <= avRating(loser1, loser2)) {
			
			winner1.addSinglesRating(0.1);
			winner2.addSinglesRating(0.1);
			
			loser1.addSinglesRating(-0.1);
			loser2.addSinglesRating(-0.1);
			
			System.out.println("The new ratings:");
			System.out.println("  " + winner1.matchToString());
			System.out.println("  " + winner2.matchToString());
			System.out.println("  " + loser1.matchToString());
			System.out.println("  " + loser2.matchToString());
		}
		else {
			System.out.println("Your ratings didn't change.");
		}
	}
	
	public double avRating(Player first, Player second) {
		return ((first.getSinglesRating() + second.getSinglesRating()) / 2);
	}
	
}
