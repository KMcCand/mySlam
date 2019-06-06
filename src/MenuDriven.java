//package mySlam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

public class MenuDriven {
	
	public static ArrayList<Player> association = new ArrayList<Player>();
	
	public static void main(String[] args) {
		System.out.println("Welcome to our match finding program!!!");
		System.out.print("Generating players, please wait...");
		
		Scanner userInput = new Scanner(System.in);
		generatePlayers();
		
		System.out.println("\n\nTo start off, enter your basic information\n-----------------------------------------------");
		
		String input = locInput(), name = nameInput();
		int gender = genderInput(), age = ageInput();
		double rating = ratingInput(), maxDist = maxDistInput();
		
		int x = Integer.parseInt(input.substring(0, input.indexOf(" ")));
		int y = Integer.parseInt(input.substring(input.indexOf(" ") + 1));	
		Player user = null;
		
		if (gender == 1) {
			user = new Player(name, true, age, 0, rating, x, y);
			addPlayer(user);
		}
		else {
			user = new Player(name, false, age, 0, rating, x , y);
			addPlayer(user);
		}
		

		System.out.println("All the registered Players:\n\n");
		printList();
		System.out.println("\n-------------------------------------------------------------------");
		
		int userChoice;
		
		do {
			do {

				System.out.println("\n\n\nWhat do you want to do:");
				System.out.println("	Play a Singles Match - (1)");
				System.out.println("	Play a Doubles Match - (2)");
				System.out.println("	Play a Mixed Doubles Match - (3)");
				System.out.println("	Add Money to your account - (4)");
				System.out.println("	Quit (0)");
				
				userChoice = userInput.nextInt();
				
			} while ((userChoice < 0) || (userChoice > 4));
			
			switch(userChoice) {
			
			case 1:
				user.makeSinglesMatch(association, maxDist);
				break;
				
			case 2:
				char havePartner;
				
				do {
					System.out.print("Do you have a partner? (Y or N) ");
					havePartner = userInput.next().charAt(0);
					
				} while ((havePartner != 'Y') && (havePartner != 'N') && (havePartner != 'y') && (havePartner != 'n')); 
			
				if ((havePartner == 'Y') || (havePartner == 'y')) {
					
					user.makeDoublesMatch(association, getPartnerFromName(), maxDist);
					
				}
				
				else {
					
					user.makeDoublesMatch(association, maxDist);
				}
				break;
				
			case 3:
				char haveMixedPartner;
				
				do {
					System.out.print("Do you have a partner? (Y or N) ");
					haveMixedPartner = userInput.next().charAt(0);
					
				} while ((haveMixedPartner != 'Y') && (haveMixedPartner != 'N') && (haveMixedPartner != 'y') && (haveMixedPartner != 'n')); 
			
				if ((haveMixedPartner == 'Y') || (haveMixedPartner == 'y')) {
					
					user.makeMixedDoublesMatch(association, getPartnerFromName(), maxDist);
					
				}
				
				else {
					user.makeMixedDoublesMatch(association, maxDist);
				}
				break;
				
			case 4:
				System.out.print("How many dollars do you want to add: ");
				user.addPay(userInput.nextInt());
				
				System.out.print("You now have $" + user.getCash() + ".");
				break;
			}	
			
		} while (userChoice != 0);
		
		System.out.print("\n\nThanks for matching with us. Come again!");
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
		
		// Format the name, capitalize first letter of first and last
		return formatName(name);
	}
	
	public static String formatName(String name) {
		name = name.toLowerCase();
		int spacePos = name.indexOf(' ');
		
		return Character.toUpperCase(name.charAt(0)) + name.substring(1, spacePos + 1) + Character.toUpperCase(name.charAt(spacePos + 1)) + name.substring(spacePos + 2);
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
	
	public static double maxDistInput() {
		Scanner userInput = new Scanner (System.in);
		
		System.out.print("\nHow many miles are you willing to drive to play? ");
		return userInput.nextInt() * 2;
	}
	
	
	public static void addPlayer(Player dude) {
		int index = 0;
		
		while (index < association.size() && (dude.getName().compareTo(association.get(index).getName()) > 0)) {
			index ++;
		}
			
		association.add(index, dude);	
	}
	
	public static void generatePlayers()
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
			addPlayer(makeRandPlayer(maleInput.nextLine(), USMap, true));
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
			addPlayer(makeRandPlayer(femaleInput.nextLine(), USMap, false));		
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
			
			String lastName = lastInput.nextLine().trim();
						
			association.get(lastCount).addLastName(lastName);
			association.get(2001 - lastCount).addLastName(lastName);
			
			lastCount++;
		}
		
		//Remove the last two Players
		association.remove(association.size() - 1);
		association.remove(association.size() - 1);
	}
	
	public static Player makeRandPlayer(String name, Picture USMap, boolean gender) {
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
				
		return new Player(name.trim(), gender, (rand.nextInt(38) + 12), 0, (rand.nextInt(13) * 0.5 + 1), row, col);
	}
	
	public static void printList()
	{
		for (int n = 0; n < association.size(); n ++){
			System.out.println(n + " " + association.get(n));
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
			
			index = findName(partnerName);
			
		} while (index == -1);
		
		return association.get(index);
	}


	public static int findName(String findThisName) {
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