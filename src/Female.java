//package mySlam;

public class Female extends Player{

	public Female()
	{
		
	}
	
	public Female(String name, int tage, int tserve, int tcash, double tsrating, int x, int y)
	{
		super(name, tage, tserve, tcash, tsrating, x, y);
	}
	
	public Player findMixed(Player searching)
	{
		Player player = new Male("", 0, 0, 0, 0, 0, 0);
		return player;
	}
	
}
