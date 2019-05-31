//package mySlam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuDriven {
	
	public static ArrayList<Player> association = new ArrayList<Player>();
	
	public static void main(String[] args) {
		System.out.print("Generating players, please wait...");
		
		Scanner userInput = new Scanner(System.in);
		generatePlayers(association);
		
		System.out.println("Welcome to our match finding program!!!");
		System.out.println("To start off, enter your basic information\n-----------------------------------------------");
		
		String input = locInput(), name = nameInput();
		int gender = genderInput(), age = ageInput();
		double rating = ratingInput();
		
		int x = Integer.parseInt(input.substring(0, input.indexOf(" ")));
		int y = Integer.parseInt(input.substring(input.indexOf(" ") + 1));	
		Player user = null;
		
		if (gender == 1) {
			user = new Player(name, "male", age, 0, rating, x, y);
			addPlayer(user, association);
		}
		else {
			user = new Player(name, "female", age, 0, rating, x , y);
			addPlayer(user, association);
		}
		
		
		System.out.println("Your stats: " + user.toString() + "\n");
		
		System.out.println("All the registered Players:\n\n");
		printList(association);
		
		int userChoice;
		do {
			do {
				
				System.out.println("\n\nWhat do you want to do:");
				System.out.println("	Play a Singles Match - (1)");
				System.out.println("	Play a Doubles Match - (2)");
				System.out.println("	Play a Mixed Doubles Match - (3)");
				System.out.println("	Add Money to your account - (4)");
				System.out.println("	Quit (0)");
				
				userChoice = userInput.nextInt();
				
			} while ((userChoice < 0) || (userChoice > 4));
			
			switch(userChoice) {
			
			case 1:
				user.makeSinglesMatch(association);
				break;
				
			case 2:
				char havePartner;
				
				do {
					System.out.print("Do you have a partner? (Y or N) ");
					havePartner = userInput.next().charAt(0);
					
				} while ((havePartner != 'Y') && (havePartner != 'N') && (havePartner != 'y') && (havePartner != 'n')); 
			
				if ((havePartner == 'Y') || (havePartner == 'y')) {
					
					user.makeDoublesMatch(association, getPartnerFromName());
					
				}
				
				else {
					
					user.makeDoublesMatch(association);
				}
				break;
				
			case 3:
				char haveMixedPartner;
				
				do {
					System.out.print("Do you have a partner? (Y or N) ");
					haveMixedPartner = userInput.next().charAt(0);
					
				} while ((haveMixedPartner != 'Y') && (haveMixedPartner != 'N') && (haveMixedPartner != 'y') && (haveMixedPartner != 'n')); 
			
				if ((haveMixedPartner == 'Y') || (haveMixedPartner == 'y')) {
					
					user.makeMixedDoublesMatch(association, getPartnerFromName());
					
				}
				
				else {
					
					user.makeMixedDoublesMatch(association);
					
					// useless comment
				}
				break;
				
			case 4:
				System.out.print("How much money do you want to add: ");
				
				int addMoney = userInput.nextInt();
				user.addPay(addMoney);
				break;
			}	
			
		} while (userChoice != 0);
		
		System.out.print("\n\nThanks for matchign with us. Come again!");
		userInput.close();
	}
	
	public static String locInput() {
		
		System.out.println("\nUse the zoom function in the upper left to enter your location (row col):");
		return PictureTester.getCoordinates();
		//3.153 miles per pixel
		
	}
	
	public static String nameInput() {
		Scanner userInput = new Scanner(System.in);
		boolean valid = true;
		String name = "";
		
		do {
			if (! valid) {
				System.out.println("Invalid input, try again.");
			}
			
			System.out.println("\nWhat do you like to be called (First Last)?");
			name = userInput.nextLine();
			
			valid = true;
			name = name.trim();
			if (name.indexOf(' ') == -1) {
				valid = false;
			}
		
		} while (! valid);
		
		name.toLowerCase();
		Character.toUpperCase(name.charAt(0));
		Character.toUpperCase(name.charAt(name.indexOf(' ') + 1));
		
		return name;
	}
	
	public static int genderInput() {
		Scanner userInput = new Scanner(System.in);
		int gender = 1;
		
		do {
			if ((gender < 1) || (gender > 2)) {
				System.out.println("Invalid input, try again.");
			}
			System.out.println("\nWhat gender would you prefer to play as?\n1. Male, 2. Female");
			gender = userInput.nextInt();
			
		} while ((gender < 1) || (gender > 2));
		
		return gender;
	}
	
	public static int ageInput() {
		Scanner userInput = new Scanner(System.in);
		int age = 30, lastEnteredAge = 30;
		
		do {
			lastEnteredAge = age;
			if (age < 1) {
				System.out.print("Invalid input, try again.");
			}
			else if (age < 5) {
				System.out.println("Are you sure you are that young? Enter your age again.");
			}
			else if (age > 100) {
				System.out.println("Maybe you shouldn't be playing tennis. Enter your age again.");
			}
			System.out.println("\nWhat is your age?");
			age = userInput.nextInt();
			
			if (age == lastEnteredAge) {
				userInput.close();
				return age;
			}
			
		} while ((age < 5) || (age > 100));	
		
		return age;
	}
	
	public static double ratingInput() {
		Scanner userInput = new Scanner(System.in);
		double rating;
		
		do {
		
			System.out.println("\nWhat experience do you have with tennis:");
			System.out.println("   Beginning   1.0 Just starting to play tennis\n" + 
					"       |       1.5 Limited experience, still working on getting the ball into play\n" + 
					"       |       2.0 Needs on-court experience, has obvious stroke weaknesses but familiar with basic positions for singles and doubles\n\n" + 
					
					"       |       2.5 Learning to judge where the ball is going, though court coverage is weak. Can do short rallies of slow pace\n" + 
					"       |       3.0 Fairly consistent when hitting medium-paced shots, but not that comfortable with directional control, depth or power.\n" + 
					"       |       3.5 Has improved stroke dependability with directional control on moderate shots, but lacks depth and variety. Has aggressive net play and improved court coverage\n\n" + 
					
					"       |       4.0 Dependable strokes, including directional control and depth on both forehand and backhand, plus the ability to use lobs, overheads, approach shots and volleys\n" + 
					"       |       4.5 Starting to use power and spins and beginning to handle pace. Has sound footwork, can control shot depth and vary game plan according to opponents. Can hit first serves with power and accuracy\n" + 
					"       |       5.0 Good shot anticipation and has an outstanding shot or exceptional consistency. Often hits winners or forces errors off of short balls. Can put away volleys and execute lobs, drop shots, half volleys and overheads\n\n" +
					
					"       |       5.5 Has developed power and/or consistency as a major weapon. Can vary strategies in a competitive situation and hit dependable shots in a stress situation\n" + 
					"       |       6.0 Has obtained a sectional or national ranking\n" + 
					"       |       6.5 Has extensive satellite tournament experience\n" + 
					"      Pro      7.0 Makes most of their income from tennis");
			
			rating = userInput.nextDouble();
		} while ((rating < 1.0) || (rating > 7.0));
		
		return rating;
	}
	
	
	public static void addPlayer(Player dude, ArrayList<Player> association)
	{
		String name = dude.getName();
		int index = 0;
		
		while ((index < association.size()) && (name.compareTo(association.get(index).getName()) > 0)) {
			index ++;
		}
		association.add(index, dude);
		
	}
	
	public static void generatePlayers(ArrayList<Player> association)
	{
		Picture USMap = new Picture("images/USMap.jpg");		
		
		
		// READ IN BOY NAMES
		String pathname = "Male.txt"; 
		File file = new File(pathname);
		
		Scanner maleInput = null;
		try {
			maleInput = new Scanner(file);
		}
		catch (FileNotFoundException ex) {
			
			System.out.println("*** Cannot open " + pathname + " ***");
			System.exit(1);  // quit the program
		} 

		while(maleInput.hasNextLine()) {
			
			String name = maleInput.nextLine();
			addPlayer(makeRandPlayer(name, USMap), association);
		}
		
		
		// READ IN GIRL NAMES
		pathname = "Female.txt"; 
		file = new File(pathname);
		
		Scanner femaleInput = null;
		try {
			femaleInput = new Scanner(file);
		}
		catch (FileNotFoundException ex) {
			
			System.out.println("*** Cannot open " + pathname + " ***");
			System.exit(1);  // quit the program
		} 

		while(femaleInput.hasNextLine()) {
			
			String name = femaleInput.nextLine();	 
			addPlayer(makeRandPlayer(name, USMap), association);		
		}
			
		
		// ADD ON LAST NAMES
		pathname = "LastNames.txt";
		file = new File(pathname);
		Scanner lastInput = null;
		
		try {
			lastInput = new Scanner(file);
		}
		catch (FileNotFoundException ex) {
			
			System.out.println("*** Cannot open " + pathname + " ***");
			System.exit(1);  // quit the program
		} 

		int lastCount = 0;
		while(lastInput.hasNextLine()) {
			
			String lastName = lastInput.nextLine().toLowerCase();
			Character.toUpperCase(lastName.charAt(0));
			
			association.get(lastCount).addLastName(lastName);
			association.get(1999-lastCount).addLastName(lastName);
			
			lastCount++;
			
		}
	}

	
	public static Player makeRandPlayer(String name, Picture USMap) {
		Random rand = new Random();
		
		//Coordinate checking loop
		boolean legit = false;
		int col = 0;
		int row = 0;
				
		do {
					
			col = rand.nextInt(1000);
			row = rand.nextInt(557);
			legit = Picture.testColor(row, col, USMap, true);
					
		} while(legit == false);
				
		double rating = rand.nextInt(13) + 1;
		int age = rand.nextInt(38) + 12;
		
		return new Player(name, "female", age, 0, rating, col, row);
	}
	
	public static void printList(ArrayList<Player> association)
	{
		for(Player p: association)
		{
			System.out.println(p.toString());
		}
	}
	
	
	public static Player getPartnerFromName() {
		Scanner userInput = new Scanner (System.in);
		int index = 0;
		
		do {
			
			if (index == -1) {
				System.out.println("That name is not in the association. Try again.");
			}
			
			System.out.print("What is the name of your partner: ");
			String partnerName = userInput.nextLine();
			
			index = findName(partnerName, association);
			
		} while (index == -1);
		
		return association.get(index);
	}


	public static int findName(String findThisName, ArrayList<Player> association) {
		int start = 0, end = association.size(), mid;
	
		while (start <= end) {
			mid = (start + end) / 2;
			String midName = association.get(mid).getName();
			
			if (midName.equals(findThisName)) {
				return mid;
			}
			else if (findThisName.compareTo(midName) > 0) {
				start = mid;
			}
			else {
				end = mid;
			}
		}
	
		return -1;
	}
}