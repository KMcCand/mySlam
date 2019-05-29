//package mySlam;
import java.util.ArrayList;
public class Player {

	// do we want these to be private????
	public String name, gender;
	public int cash, age, row, col;
	public double rating;
	
	public final double maxDist = 30.0;
	
	public Player() {
		
	}
	
	public Player(String tname, String theGender, int tage, int tcash, double tsrating, int theRow, int theCol)
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
	
	public String getGender() {
		return gender;
	}
	
	public void addLastName(String last)
	{
		name = name + " " + last;
	}
	public double distanceMiles(Player p2) {
		
		double pixelDist = Math.sqrt(Math.pow((double) (p2.getCol() - col), 2) + Math.pow((double) (p2.getRow() - row), 2));
		double milesDist = pixelDist * 3.1538;
		
		return milesDist;
	}
	
	public void setSinglesRating(double newRate)
	{
		rating = newRate;
	}
	
	public int getCash()
	{
		return cash;
	}
	
	public void addPay(int winnings)
	{
		cash = cash + winnings;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public String toString()
	{
		String string = "";
		string = string + "Name: " + name + " Age: " + age + " Cash: " + cash + " Rating: " + rating;
		return string;
	}
	
	public void makeSinglesMatch(ArrayList<Player> association) {
		
		// do we want to avoid males playing females? if so, move this to male / female and make it gender specific. 
		// I think its ok for males to play females, in which case we leave this here. 
		
		double minRatingDif = Integer.MAX_VALUE;
		Player playThisGuy = new Player();
		
		for (Player onePlayer : association) {
			if (distanceMiles(onePlayer) < maxDist) {
				
				double ratingDif = Math.abs(onePlayer.getSinglesRating() - rating);
				
				if (ratingDif < minRatingDif) {
					minRatingDif = ratingDif;
					playThisGuy = onePlayer;
				}
			}
		}
		
		SinglesMatch goodMatch = new SinglesMatch(playThisGuy, this, association);
	}
	
	public void makeDoublesMatch(ArrayList<Player> association) {
		
	}
	
	public void makeDoublesMatch(ArrayList<Player> association, Player myPartner) {
		
	}
	
}
