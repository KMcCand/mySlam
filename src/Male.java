//package mySlam;

public class Male extends Player {

	public Male()
	{
		
	}
	
	public Male(String name, int tage, int tserve, int tcash, double tsrating, int row, int col)
	{
		super(name, tage, tserve, tcash, tsrating, row, col);
	}
	
	public Player makeMixedDoublesMatch (Player searching)
	{
		Player player = new Female("", 0, 0, 0, 0, 0, 0);
		return player;
	}
}
