//package mySlam;

import java.util.Random;
import java.util.ArrayList;

import java.util.Scanner;
public class MenuDriven {
	
	private static Scanner userinput;
	public static ArrayList<Player> Association = new ArrayList<Player>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner userinput = new Scanner(System.in);
		int option = 1;
		System.out.println("Welcome to the tennis self rating and location tracking system");
		System.out.println("This basic information cannot be changed");
		//print the picture to the sceen
		System.out.println("To start off please enter the x and y coordinates of your location based off of the map on your screen");
		String input = PictureTester.getCoordinates();
		//3.153 miles per pixel
		
		System.out.println(input);
		
		int x = Integer.parseInt(input.substring(0, input.indexOf(" ")));
		int y = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
		
		System.out.println("Wat is your name (First Last)?");
		String name = userinput.nextLine();
		System.out.println("Enter Gender 1. Male, 2. Female");
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
			Player user = new Male(name, age, serve, 0, rating, x, y);
			//Association.add(user);
		}
		else if (gender == 2)
		{
			Player user = new Female(name, age, serve, 0, rating, x , y);
			//Association.add(user);
		}
		
		
		//print x  and y coordinates
		
		
	}
	
	public void addAplayer(Player dude)
	{
		String name = dude.getName();
		int index = 0;
		for(Player p : Association)
		{
			int value = name.compareTo(p.getName());
			
			if(value == 0)
			{
				return;
			}
			
			else if(value < 0)
			{
				Association.add(index, dude);
				return;
			}
			index++;
		}
		Association.add(dude);
		
	}
	
	public void generatePlayers(ArrayList Association)
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
					
					Player player = new Male(name, age, speed, cash, rate, col, row);
					Association.add(player);
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
					
					Player player = new Female(name, age, speed, cash, rate, col, row);
					Association.add(player);
				}
			
			
			
			
			//RATE PROPERTY
		
			
		
	}

	
	public void printList()
	{
		for(Player p: Association)
		{
			System.out.println(p.toString());
		}
	}
	
public double distanceMiles(int row1, int col1, int row2, int col2) {
		
		double pixelDist = Math.sqrt(Math.pow((double) (col2 - col1), 2) + Math.pow((double) (row2 - row1), 2));
		double milesDist = pixelDist * 3.1538;
		
		return milesDist;
	}


}