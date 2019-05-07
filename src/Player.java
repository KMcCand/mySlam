//package mySlam;

public abstract class Player {

	public String name;
	public int age;
	public String gender;
	public int serve;
	public int cash;
	public double rating;
	//public double drating;
	public int x;
	public int y;
	
	public Player()
	{
		
	}
	
	public Player(String named, int tage, int tserve, int tcash, double tsrating, int xcord, int ycord)
	{
		name = named;
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
	public abstract Player findMixed(Player person);
}
