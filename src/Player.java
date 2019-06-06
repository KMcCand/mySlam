//package mySlam;
import java.util.ArrayList;

public class Player {

	private String name = "";
	private boolean gender;
	private int age, row, col;
	private double rating, cash;
	
	public Player() {
		
	}
	
	public Player(String tname, boolean theGender, int tage, int tcash, double tsrating, int theRow, int theCol)
	{
		name = tname;
		gender = theGender;
		age = tage;
		cash = tcash;
		rating = tsrating;
		row = theRow;
		col = theCol;
	}
	
	public String getName()
	{
		return name;
	}
	public double getSinglesRating()
	{
		return rating;	
	}
	
	public boolean getGender() {
		return gender;
	}
	
	public void addLastName(String last)
	{
		name = name + " " + Character.toUpperCase(last.charAt(0)) + last.substring(1).toLowerCase();
		
	}
	
	public double distanceMiles(Player p2) {
		
		double pixelDist = Math.sqrt(Math.pow((double) (p2.getCol() - col), 2) + Math.pow((double) (p2.getRow() - row), 2));
		return pixelDist * 3.1538;
	}
	
	public double distanceMiles(int theRow, int theCol) {
		
		double pixelDist = Math.sqrt(Math.pow((double) (theCol - col), 2) + Math.pow((double) (theRow - row), 2));
		return pixelDist * 3.1538;
	}
	
	public void addSinglesRating(double newRate)
	{
		rating += newRate;
	}
	
	public double getCash()
	{
		return cash;
	}
	
	public void addPay(double winnings)
	{
		cash += winnings;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public String matchToString() {
		return name + ", " + rating;
	}
	
	public String toString() {
		// NOTE TO US: THE QUESTION MARK IS A LESS CODE WAY TO SAY MALE IF GENDER IS TRUE, FEMALE IF FALSE
		return name + ", " + ((gender) ? 'M' : 'F') + " |  " + age + " years old, " + cash + " dollars. Rating: " + rating;
	}
	
	public void makeSinglesMatch(ArrayList<Player> association, double maxDist) {
		
		Player playThisGuy = findRightPlayer(association, true, maxDist);
		
		if (playThisGuy.getName().equals("")) {
			System.out.print("There are no players in your area.");
		}
		else {
			SinglesMatch goodMatch = new SinglesMatch(this, playThisGuy, association);
		}
	}
	
	public void makeDoublesMatch(ArrayList<Player> association, double maxDist) {
		
		Player myPartner = findRightPlayer(association, true, maxDist);
		
		if (myPartner.getName().equals("")) {
			System.out.print("There are no partners in your area.");
		}
		else {
			makeDoublesMatch(association, myPartner, maxDist);
		}
	}
	
	public void makeDoublesMatch(ArrayList<Player> association, Player myPartner, double maxDist) {
		
		if (myPartner.getGender() == gender) {
			
			double medRating = ((double) rating + myPartner.getSinglesRating()) / 2;
			
			double minRatingDif = Integer.MAX_VALUE, secondMinRatingDif = Integer.MAX_VALUE;
			Player team2First = new Player();
			Player team2Second = new Player();
			
			for (Player onePlayer : association) {
				
				if (distanceMiles(onePlayer) < maxDist) {
					
					if (! onePlayer.equals(myPartner)) {
						
						if (! onePlayer.equals(this)) {
						
							if (onePlayer.getGender() == gender) {
						
								double ratingDif = Math.abs(onePlayer.getSinglesRating() - medRating);
								
								if (ratingDif < minRatingDif) {
									secondMinRatingDif = minRatingDif;
									team2First = team2Second;
									
									minRatingDif = ratingDif;
									team2Second = onePlayer;
								}
							}
						}
					}
				}
			}
			
			if (team2First.getName().equals("") || team2Second.getName().equals("")) {
				System.out.print("There are no teams in your area.");
			}
			else {
				DoublesMatch goodDubsMatch = new DoublesMatch(this, myPartner, team2First, team2Second, association);
			}
		}
		
		else {
			System.out.print("Your partner is not the same gender as you. You should consider playing mixed doubles.");
		}
	}
	
	public void makeMixedDoublesMatch(ArrayList<Player> association, double maxDist) {
		
		Player myPartner = findRightPlayer(association, false, maxDist);
		
		if (myPartner.getName().equals("")) {
			System.out.print("There are no partners in your area.");
		}
		else {
			makeMixedDoublesMatch(association, myPartner, maxDist);
		}
	}
	
	public void makeMixedDoublesMatch(ArrayList<Player> association, Player myPartner, double maxDist) {
		if (myPartner.getGender() != gender) {
			
			double minRatingDif = Integer.MAX_VALUE, minRatingDif2 = Integer.MAX_VALUE;
			Player team2First = new Player();
			Player team2Second = new Player();
			
			for (Player onePlayer : association) {
				
				if (distanceMiles(onePlayer) < maxDist) {
					
					if (! onePlayer.equals(myPartner)) {
						
						if (! onePlayer.equals(this)) {
						
							if (onePlayer.getGender() == gender) {
						
								double ratingDif = Math.abs(onePlayer.getSinglesRating() - rating);
								
								if (ratingDif < minRatingDif) {
									team2First = onePlayer;
									minRatingDif = ratingDif;
								}
							}
							else {
								
								double ratingDif2 = Math.abs(onePlayer.getSinglesRating() - myPartner.getSinglesRating());
								
								if (ratingDif2 < minRatingDif2) {
									team2Second = onePlayer;
									minRatingDif2 = ratingDif2;
								}
							}
						}
					}
				}
			}
			
			if (team2First.getName().equals(null) || team2Second.getName().equals(null)) {
				System.out.print("There are no teams in your area.");
			}
			else {
				MixedDoublesMatch goodMixedDubsMatch = new MixedDoublesMatch(this, myPartner, team2First, team2Second, association);
			}
		}
		
		else {
			System.out.print("Your partner has the same gender as you, so you cannot play mixed doubles. Consider playing regular doubles instead.");
		}
	}
	
	public Player findRightPlayer(ArrayList<Player> association, boolean sameGender, double maxDist) {
		
		double minRatingDif = Integer.MAX_VALUE;
		Player playThisGuy = new Player();
		
		for (Player onePlayer : association) {
			if (distanceMiles(onePlayer) < maxDist) {
				
				if (! onePlayer.equals(this)) {
					
					if (sameGender == (onePlayer.getGender() == gender)) {
						
						double ratingDif = Math.abs(onePlayer.getSinglesRating() - rating);
						
						if (ratingDif < minRatingDif) {
							
							minRatingDif = ratingDif;
							playThisGuy = onePlayer;
						}
					}
				}
			}
		}
		
		return playThisGuy;
	}
	
}
