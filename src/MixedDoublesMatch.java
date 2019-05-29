//package mySlam;

import java.util.ArrayList;

public class MixedDoublesMatch extends DoublesMatch {
	
	boolean validMixedTeam = false;
	
	public MixedDoublesMatch(Player team1First, Player team1Second, Player team2First, Player team2Second, ArrayList<Player> association) {
		super(team1First, team1Second, team2First, team2Second, association);
		
		if (checkMixedTeam(team1First, team1Second)) {
			if (checkMixedTeam(team2First, team2Second)) {
				validMixedTeam = true;
			}
		}
	}
	
	public boolean checkMixedTeam(Player firstOne, Player secondOne) {
		if (firstOne.getGender().equals("female")) {
			if (secondOne.getGender().contentEquals("male")) {
				return true;
			}
		}
		else if (firstOne.getGender().equals("male")) {
			if (secondOne.getGender().equals("female")) {
				return true;
			}
		}
		return false;
	}
}
