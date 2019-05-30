import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
	  
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        
        
        count ++;
      }
    }
    
    
    System.out.print("The count: " + count);
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel belowPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color belowColor = null;
    for (int row = 0; row < pixels.length - 1; row++)
    {
      for (int col = 0; col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        belowPixel = pixels[row + 1][col];
        
        belowColor = belowPixel.getColor();
        rightColor = rightPixel.getColor();
        if ((leftPixel.colorDistance(rightColor) > edgeDist) || (leftPixel.colorDistance(belowColor) > edgeDist)) {
          leftPixel.setColor(Color.BLACK);
        }
        
        else {
          leftPixel.setColor(Color.WHITE);
        }
      }
    }
  }
  
  public void keepOnlyBlue() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  pixelObj.setRed(0);
			  pixelObj.setGreen(0);
		  }
	  }
  }
  
  public void negate() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  pixelObj.setRed(255 - pixelObj.getRed());
			  pixelObj.setGreen(255 - pixelObj.getGreen());
			  pixelObj.setBlue(255 - pixelObj.getBlue());
		  }
	  }
  }
  
  public void grayscale() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  int average = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
			  pixelObj.setRed(average);
			  pixelObj.setGreen(average);
			  pixelObj.setBlue(average);
		  }
	  }
  }
  
  public void fixUnderwater() {
	  Pixel[][] pixels = this.getPixels2D();
	  int fish;
	  double distance = 0;
	  
	  for (Pixel[] rowArray : pixels) {
		  for (Pixel pixelObj : rowArray) {
			  //fish = 2;
			 // if ((pixelObj.getBlue() < 160) || (pixelObj.getGreen() > 185) || (pixelObj.getRed() > 28)) {
				  //fish = 0;
			 // }
			 // else if ((pixelObj.getBlue() < 170) && (pixelObj.getGreen() > 165) && (pixelObj.getRed() > 22)) {
				 // fish = fish -1;
			//  }
			  
			 // else if ((pixelObj.getRed() > 22) && (pixelObj.getRow() > 127)) {
				//  if ((pixelObj.getGreen() > 168) && (pixelObj.getBlue() < 152)){
					 // fish = fish - 1;
				//  }
			 // }
			 
			//if (pixelObj.getAlpha()	 < 255)	{
				//pixelObj.setBlue(255);
				  //pixelObj.setRed(255);
				 // pixelObj.setGreen(255);
			//}
			  //distance = pixelObj.colorDistance(new Color(18,160,172));
			  //if (distance >= 20) {
				 // fish--;
			 // }
			  //else if (distance >= 25) {
				 // fish = 0;
			 // }
			  
			 // if(fish <= 0) {
				 // pixelObj.setBlue(255);
				// pixelObj.setRed(255);
				 // pixelObj.setGreen(255);
			  //}
			  pixelObj.setGreen(0);
			  pixelObj.setBlue(0);
			if ((pixelObj.getRow() <= 200)) {
			 
			  pixelObj.setRed(pixelObj.getRed() * 7);
			  
			}
			  
			  //if ((pixelObj.getRow() <= 200)) {
				 
			  //}
			  else {
				  pixelObj.setRed(pixelObj.getRed() * 5);
				  //pixelObj.setGreen(pixelObj.getGreen() - pixelObj.getRed());
				 // pixelObj.setBlue(pixelObj.getBlue() - pixelObj.getRed());
				  //pixelObj.setRed(0);
				  
				  
			  }
			
			
			  
				  
	
			  
			  
	  }
	  } 
	  
	  
	  }
  
  public void MirrorVerticalRightToLeft() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  int width = pixels[0].length;
	  
	  
	  for (int row = 0; row < pixels.length; row ++) {
		  for (int col = 0; col < width / 2; col ++) {
			  leftPixel = pixels[row][col];
			  rightPixel = pixels[row][width - 1 - col];
			  leftPixel.setColor(rightPixel.getColor());
		  }
	  }
  }
  
  public void MirrorHori() {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  
	  int height = pixels.length;
	  
	  for (int row = 0; row < height / 2; row ++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[height - 1 - row][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  public void MirrorBottomUp() {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  
	  int height = pixels.length;
	  
	  for (int row = 0; row < height / 2; row ++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[height - 1 - row][col];
			  topPixel.setColor(bottomPixel.getColor());
		  }
	  }
  }
  
  public void MirrorDiagonal() {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  Pixel topRightPixel = null;
	  Pixel bottomLeftPixel = null;
	  
	  for (int row = 0; row < pixels.length; row ++) {
		  for (int col = 0; col < row; col ++) {
			  bottomLeftPixel = pixels[row][col];
			  topRightPixel = pixels[col][row];
			  topRightPixel.setColor(bottomLeftPixel.getColor());
		  }
	  }
  }
  
  public void MirrorArms() {
	  int mirrorRow = 185;
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (int row = 154; row < mirrorRow; row ++) {
		  for (int col = 100; col < 170; col ++) {
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[mirrorRow - row + mirrorRow][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
	  
	  mirrorRow = 192;
	  
	  for (int row = 170; row < mirrorRow; row ++) {
		  for (int col = 238; col < 295; col ++) {
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[mirrorRow - row + mirrorRow][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
	  
  }
  
  public void mirrorGull() { 
	  
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  int mirrorCol = 370;
	  
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (int row = 230; row < 330; row ++) {
		  for (int col = 235; col < 345; col ++) {
			  if ((pixels[row][col].getBlue() < 110) || (pixels[row][col].getRed() > 145)) {
				  leftPixel = pixels[row][col];
				  rightPixel = pixels[row + 5][mirrorCol - col + mirrorCol];
				  rightPixel.setColor(leftPixel.getColor());
			  }
		  }
	  }
	  
	    
  }
  
  public void copy2(Picture fromPic, int cStartRow, int cEndRow, int cStartCol, int cEndCol, int startRow, int startCol) {
	  Pixel oldPixel = null;
	  Pixel newPixel = null;
	  Pixel[][] toPixels = this.getPixels2D();
	  Pixel[][] fromPixels = fromPic.getPixels2D();
	  
	  for (int oldRow = cStartRow, newRow = startRow; oldRow < cEndRow && newRow < toPixels.length; oldRow++, newRow ++) {
		  for(int oldCol = cStartCol, newCol = startCol; oldCol < cEndCol && newCol < toPixels[0].length; oldCol++, newCol ++) {
			  
			  oldPixel = fromPixels[oldRow][oldCol];
			  newPixel = toPixels[newRow][newCol];
			  newPixel.setColor(oldPixel.getColor());
			  
		  }
	  }
  }
  
  public void createCollage2(){
	  Picture beach = new Picture("beach.jpg");
	  Picture blueMotorcycle = new Picture("blueMotorcycle.jpg");
	  
	  blueMotorcycle.grayscale();
	  beach.negate();
	  
	  
	  this.copy2(blueMotorcycle, 0, 300, 200, 300, 100, 0);
	  this.copy2(beach, 0, 300, 0, 300, 300, 0);
	  
	  this.MirrorDiagonal();
	  
	  this.copy2(beach, 400, 450, 400, 450, 0, 0);
	  this.write("collage.jpg");
  }
  
  public void edgeDetection2(int edgeDist) {
	  Pixel middlePixel = null;
	  Pixel leftPixel = null;
	  Pixel abovePixel = null;
	  
	  Pixel[][] pixels = this.getPixels2D();
	  int[][] remembering = new int [641][481];
	  
	  Color leftColor = null;
	  Color aboveColor = null;
	  
	  for (int row = 2; row < pixels.length; row++) {
		  for (int col = 2; col < pixels[0].length; col++) {
			  middlePixel = pixels[row][col];
			  leftPixel = pixels[row][col - 2];
			  abovePixel = pixels[row - 2][col];
			  leftColor = leftPixel.getColor();
			  aboveColor = abovePixel.getColor();
			  
			  if ((middlePixel.colorDistance(leftColor) > edgeDist)) {
				  remembering[row][col] = 5;
				  remembering[row][col - 1] = 5;
				  remembering[row][col - 2] = 5;
			  }
			  
			  else if (middlePixel.colorDistance(aboveColor) > edgeDist) {
				  remembering[row][col] = 5;
				  remembering[row - 1][col] = 5;
				  remembering[row - 2][col] = 5;
			  }
		  }
	  }
	  
	  for (int y = 2; y < pixels.length; y ++) {
		  for (int x = 2; x < pixels[0].length; x ++) {
			  if (remembering[y][x] == 5) {
				  pixels[y][x].setColor(Color.ORANGE);
			  }
		  }
	  }
	  // NOTE: By making the border 2 pixels, hopefully the detection will be more accurate, because the change should by greater over two pixels than one. 
  }
  
  
  public int[] getLargestRGBForColumn(int col) {
	  int rgbArray[] = new int[3];
	  
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (int row = 0; row < pixels.length; row ++) {
		  if (pixels[row][col].getRed() > rgbArray[0]) {
			  rgbArray[0] = pixels[row][col].getRed();
		  }
		  
		  if (pixels[row][col].getGreen() > rgbArray[1]) {
			  rgbArray[1] = pixels[row][col].getGreen();
		  }
		  
		  if (pixels[row][col].getBlue() > rgbArray[2]) {
			  rgbArray[2] = pixels[row][col].getBlue();
		  }
	  }
	  
	  
	  return rgbArray;
	  
  }
  
  
  public void rotateRight() {
	  Pixel[][] pixels = this.getPixels2D();
	  //Pixel[][] storage = null;
	  
	  Picture use = new Picture ("temple.jpg");
	  //int tempArray[][] = new int[pixels.length][pixels[0].length];
	  
	  
	  this.copy2Quiz(use, 0, pixels.length / 2, 0, pixels[0].length / 2, 0, pixels[0].length /2);
	  this.copy2Quiz(use, 0, pixels.length / 2, pixels[0].length / 2, pixels[0].length, pixels.length/2, pixels[0].length /2);
	  
	  
	  
	  this.write("temple.jpg");
	  
	  
	  
	  
	  
	  
	  //for (int row = 0; row < pixels.length; row ++) {
		  //for (int col = 0; col < pixels[0].length; col++) {
			  //if ((col < pixels[0].length / 2) && (row < pixels.length / 2)) {
				  //pixels[pixels[0].length - col][row] = pixels[row][col];
			  //}
		  //}
	  //}
  }
  
  
  public void copy2Quiz(Picture fromPic, int cStartRow, int cEndRow, int cStartCol, int cEndCol, int startRow, int startCol) {
	  Pixel oldPixel = null;
	  Pixel newPixel = null;
	  Pixel[][] toPixels = this.getPixels2D();
	  Pixel[][] fromPixels = fromPic.getPixels2D();
	  
	  for(int oldCol = cStartCol, newCol = startCol; oldCol < cEndCol && newCol < toPixels[0].length; oldCol++, newCol ++) {
	  for (int oldRow = cStartRow, newRow = startRow; oldRow < cEndRow && newRow < toPixels.length; oldRow++, newRow ++) {
			  
			  oldPixel = fromPixels[oldRow][oldCol];
			  newPixel = toPixels[newRow][newCol];
			  newPixel.setColor(oldPixel.getColor());
			  
		  }
	  }
  }
  
  
  public static boolean testColor(int row, int col, Picture testImage, boolean generating) {
	  Pixel[][] array = testImage.getPixels2D();
	  Pixel selected = array[row][col];
	  
	  if (selected.getRed() == 255) {
		  if (selected.getGreen() == 255) {
			  if (selected.getBlue() == 255) {
				  if (! generating) {
					  System.out.println("Those coordinates are in a foreign country.");
				  }
				  return false;
			  }
		  }
	  }
	  else if (selected.getRed() == 184) {
		  if (selected.getGreen() == 229) {
			  if (selected.getBlue() == 250) {
				  if (! generating) {
					  System.out.println("Those coordinates are in the ocean.");
				  }
				  return false;
			  }
		  }
	  }
	  
	  return true;
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
