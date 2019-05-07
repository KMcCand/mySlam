//package mySlam;

public class MixedDoublesMatch extends DoublesMatch {
	
	boolean validMixedTeam = false;
	
	public MixedDoublesMatch(Player team1First, Player team1Second, Player team2First, Player team2Second) {
		super(team1First, team1Second, team2First, team2Second);
		
		if (checkMixedTeam(team1First, team1Second)) {
			if (checkMixedTeam(team2First, team2Second)) {
				validMixedTeam = true;
			}
		}
	}
	
	public boolean checkMixedTeam(Player firstOne, Player secondOne) {
		if (firstOne instanceof Female) {
			if (secondOne instanceof Male) {
				return true;
			}
		}
		else if (firstOne instanceof Male) {
			if (secondOne instanceof Female) {
				return true;
			}
		}
		return false;
	}
}
