//package mySlam;

public class Player {

	// do we want these to be private????
	public String name, gender;
	public int serve, cash, age, x, y;
	public double rating /* ,drating */;
	
	public Player() {
		
	}
	
	public Player(String tname, int tage, int tserve, int tcash, double tsrating, int xcord, int ycord)
	{
		name = tname;
		age = tage;
		serve = tserve;
		cash = tcash;
		rating = tsrating;
		x = xcord;
		y = ycord;
	}
	
	public String getName()
	{
		return name;
	}
	public double getSinglesRating()
	{
		return rating;	
	}
	
	
	
	public int getServe()
	{
		return serve;
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
		return y;
	}
	
	public int getCol()
	{
		return x;
	}
	
	public String toString()
	{
		String string = "";
		string = string + "name: " + name + "age: " + age;
		return string;
	}
	public Player findMixed(Player person) {
		if (gender.equals("female")) {
			// complete this later 
		}
		return new Player();
	}
}
