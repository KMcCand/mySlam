//package mySlam;

public class Female extends Player{

	public Female()
	{
		
	}
	
	public Female(String name, int tage, int tserve, int tcash, double tsrating, int row, int col)
	{
		super(name, tage, tserve, tcash, tsrating, row, col);
	}
	
	public Player makeMixedDoublesMatch (Player searching)
	{
		Player player = new Male("", 0, 0, 0, 0, 0, 0);
		return player;
	}
	
}
