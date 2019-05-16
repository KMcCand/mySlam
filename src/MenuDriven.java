//package mySlam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuDriven {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userinput = new Scanner(System.in);
		ArrayList<Player> association = new ArrayList<Player>();
		generatePlayers(association);
		printList(association);
		//make not static or make reference correct
		System.out.println("Welcome to our match finding program!!!");
		System.out.println("To start off, enter your basic information\n-----------------------------------------------");
		
		//print the map to the screen
		System.out.println("\nPlease enter the x and y coordinates of your location on the map");
		String input = PictureTester.getCoordinates();
		//3.153 miles per pixel
				
		int x = Integer.parseInt(input.substring(0, input.indexOf(" ")));
		int y = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
		
		System.out.println("\nWhat is your name (First Last)?");
		String name = userinput.nextLine();
		System.out.println("\nWhat gender would you prefer to play as?\n1. Male, 2. Female");
		int gender = userinput.nextInt();
		System.out.println("\nWhat is your age?");
		int age = userinput.nextInt();
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
		
		double rating = userinput.nextDouble();
		
		System.out.println("--------------------------------------------------\nDo you want to enter advanced settings?");
		System.out.println("(Preferences on fitness and social levels, serve speed, playing up or down, etc)");
		
		System.out.println("Enter your serve speed to the nearest mph: ");
		int serve = userinput.nextInt();
		if(gender == 1)
		{
			Player user = new Player(name, "male", age, serve, 0, rating, x, y);
			addPlayer(user, association);
		}
		else if (gender == 2)
		{
			Player user = new Player(name, "female", age, serve, 0, rating, x , y);
			addPlayer(user, association);
		}
		
		
		//print x  and y coordinates
		
		userinput.close();
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
		Random rand = new Random();
		
		//player generation loop
		//GET THE GENDER AND NAME
			//LOCATION PROPERTY
				
				String yay = "Male.txt"; //Male File
				File files = new File(yay);
				Scanner input3 = null;
				try
				{
					input3 = new Scanner(files);
				}
				catch (FileNotFoundException ex)
				{
					System.out.println("*** Cannot open " + yay + " ***");
					System.exit(1);  // quit the program
				} 

				while(input3.hasNextLine())
				{
					String name = input3.nextLine();
					name = name;
					boolean legit = false;
					
					//Coordinate checking loop
					int col = 0;
					int row = 0;
					do {
						
						col = rand.nextInt(1000);
						row = rand.nextInt(557);
						
						Picture USMap = new Picture("images/USMap.jpg");
						legit = Picture.testColor(row, col, USMap);
					}while(legit = false);
					
					//////
					double rate = rand.nextInt(8) + 1;
					int subtract = rand.nextInt(2);
					if(subtract == 1)
					{
						rate = rate - .5;
					}
					//AGE PROPERTY
					int age = rand.nextInt(38) + 12;
					
					//CASH PROPERTY
					int cash = 0;
					
					//SERVE SPEED PROPERTY
					int speed = rand.nextInt(50) + 70;
					
					Player player = new Player(name, "male", age, speed, cash, rate, col, row);
					addPlayer(player, association);
				}
				
				String boy = "Female.txt"; //Male File
				File filed = new File(boy);
				Scanner input = null;
				try
				{
					input = new Scanner(filed);
				}
				catch (FileNotFoundException ex)
				{
					System.out.println("*** Cannot open " + boy + " ***");
					System.exit(1);  // quit the program
				} 

				while(input.hasNextLine())
				{
					String name = input.nextLine();
					
					boolean legit;
					
					//Coordinate checking loop
					int col = 0;
					int row = 0;
					do {
						
						col = rand.nextInt(1000);
						row = rand.nextInt(557);
						
						Picture USMap = new Picture("images/USMap.jpg");
						legit = Picture.testColor(row, col, USMap);
						
					}while(legit = false);
					
					
					double rate = rand.nextInt(8) + 1;
					int subtract = rand.nextInt(2);
					if(subtract == 1)
					{
						rate = rate - .5;
					}
					//AGE PROPERTY
					int age = rand.nextInt(38) + 12;
					
					//CASH PROPERTY
					int cash = 0;
					
					//SERVE SPEED PROPERTY
					int speed = rand.nextInt(50) + 70;
					
					Player player = new Player(name, "female", age, speed, cash, rate, col, row);
					addPlayer(player, association);
				}
			
				String cool = "LastNames.txt"; //Male File
				File fi = new File(yay);
				Scanner input5 = null;
				try
				{
					input5 = new Scanner(fi);
				}
				catch (FileNotFoundException ex)
				{
					System.out.println("*** Cannot open " + cool + " ***");
					System.exit(1);  // quit the program
				} 

				int lastCount = 0;
				while(input5.hasNextLine())
				{
					String lastName = input5.nextLine();
					association.get(lastCount).addLastName(lastName);
					association.get(1999-lastCount).addLastName(lastName);
					lastCount++;
				}
			
			
			
			//RATE PROPERTY
				//virs new code 
		
			
	}

	
	public static void printList(ArrayList<Player> association)
	{
		for(Player p: association)
		{
			System.out.println(p.toString());
		}
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
