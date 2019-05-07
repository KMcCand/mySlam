package mySlam;

import java.util.Scanner;

public class PictureTester {
	
	//NOTES TO KYLE AND VIR:
	    // how to deal with alaska and Hawaii - based on their coordinates on this map, they are already very far away so maybe it will work naturally
		// make border white
		// multiply distance in map by 3.1538 to get distance in real life (one pixel is 3.1538 miles)

	public static String getCoordinates() {
		Scanner userInput = new Scanner (System.in);
		
		Picture USMap = new Picture("images/USMap.jpg");
		USMap.explore();
		
		String coordinates = "";
		
		// Get user location coordinates
		String line;
		int spacePos, row = 0, col = 0;
		boolean valid = true;
		
		do {
			if (valid == false) {
				System.out.print("Try again.\n\n");
			}
			valid = true;
			
			System.out.print("Use the zoom function in the upper left to find the coordinates of your location (row colum): ");
			line = userInput.nextLine();
			spacePos = line.indexOf(' ');
			
			if ((line.indexOf(',') > 0) || (spacePos == - 1)) {
				valid = false;
				System.out.println("Invalid input.");
			}
			else {
				
				row = Integer.parseInt(line.substring(0, spacePos));
				col = Integer.parseInt(line.substring(spacePos + 1));

				if ((row < 0) || (row > 724) || (col < 0) || (col > 999)) {
					valid = false;
					System.out.println("Invalid input.");
				}
				else {
					valid = Picture.testColor(row, col, USMap);
				}
			}
			
			
		} while (valid == false);
				
		coordinates += row + " " + col; 
		
		
		return coordinates;
	}

}
