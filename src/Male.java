//package mySlam;

public class Male extends Player {

	public Male()
	{
		
	}
	
	public Male(String name, int tage, int tserve, int cash, double trating, int x, int y)
	{
		super(name, tage, tserve, cash, trating, x , y);
	}
	
	public Player findMixed(Player searching)
	{
		Player player = new Female("", 0, 0, 0, 0, 0, 0);
		return player;
	}
}
