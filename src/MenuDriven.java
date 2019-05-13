//package mySlam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuDriven {
	
	public static ArrayList<Player> association = new ArrayList<Player>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userinput = new Scanner(System.in);
		
		System.out.println("Welcome to our match finding program!!!");
		System.out.println("To start off, enter your basic information\n");
		
		//print the map to the screen
		System.out.println("Please enter the x and y coordinates of your location based on the map");
		String input = PictureTester.getCoordinates();
		//3.153 miles per pixel
				
		int x = Integer.parseInt(input.substring(0, input.indexOf(" ")));
		int y = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
		
		System.out.println("What is your name (First Last)?");
		String name = userinput.nextLine();
		System.out.println("What gender would you prefer to play as?\n1. Male, 2. Female");
		int gender = userinput.nextInt();
		System.out.println("What is your age?");
		int age = userinput.nextInt();
		System.out.println("What is you rating on the 8 point USTA Scale (half points possible)");
		System.out.println("Eg. 4.0, 5.5, 7.0, 2.5");
		double rating = userinput.nextDouble();
		System.out.println("What is your average serve speed to the nearest mph?");
		int serve = userinput.nextInt();
		if(gender == 1)
		{
			Player user = new Player(name, "male", age, serve, 0, rating, x, y);
			addPlayer(user);
		}
		else if (gender == 2)
		{
			Player user = new Player(name, "female", age, serve, 0, rating, x , y);
			addPlayer(user);
		}
		
		
		//print x  and y coordinates
		
		userinput.close();
	}
	
	public static void addPlayer(Player dude)
	{
		String name = dude.getName();
		int index = 0;
		for(Player p : association)
		{
			int value = name.compareTo(p.getName());
			
			if(value == 0)
			{
				return;
			}
			
			else if(value < 0)
			{
				association.add(index, dude);
				return;
			}
			index++;
		}
		association.add(dude);
		
	}
	
	public void generatePlayers(ArrayList association)
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
					name = name + " Harris";
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
					addPlayer(player);
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
					name = name + " Pendergast";
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
					addPlayer(player);
				}
			
			
			
			
			//RATE PROPERTY
		
			
		
	}

	
	public void printList()
	{
		for(Player p: association)
		{
			System.out.println(p.toString());
		}
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
