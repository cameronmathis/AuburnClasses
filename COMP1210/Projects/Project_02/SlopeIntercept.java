import java.util.Scanner;
  
/** 
 * Takes the x and y coordinates of two points as doubles and
 * calculates the slope (if defined) of the line defined by the two points.
 * If the slope is defined, the program calculates and prints the Y intercept
 * and then further, if the slope is not 0, it calculates and
 * prints the X intercept.
 *
 * @author Cameron Mathis
 * @version 24 January 2017
 */
public class SlopeIntercept {
  /**
   * Takes the x and y coordinates of two points as doubles and
   * calculates the slope (if defined) of the line defined by the two points.
   * If the slope is defined, the program calculates and prints the Y intercept
   * and then further, if the slope is not 0, it calculates and
   * prints the X intercept.
   *     
   * @param args Command line arguments (not used).
   */

   public static void main(String[] args) {
   
      Scanner userInput = new Scanner(System.in);
      double x1 = 0;
      double y1 = 0;
      double x2 = 0;
      double y2 = 0;
      double slope = 0;
      double yint = 0;
      double xint = 0;
   
      //prompt the user for X and Y coordinates of starting point
      System.out.println("Enter the X and Y coordinates of starting point: ");
      System.out.print("\tx1 = ");
      x1 = userInput.nextDouble();
      System.out.print("\ty1 = ");
      y1 = userInput.nextDouble();
      
      //promt the user for the X and Y coordinates of end point
      System.out.println("Enter the X and Y coordinates of ending point: ");
      System.out.print("\tx2 = ");
      x2 = userInput.nextDouble();
      System.out.print("\ty2 = ");
      y2 = userInput.nextDouble();
      
      //calculate the slope, Y intercept, and X intercept
      slope = (y2 - y1) / (x2 - x1);
      yint = y1 - (slope * x1);
      xint = -1 * (yint / slope);
       
      if (x1 == x2) { //if x1 = x2 print "Slope: "undefined""           
         System.out.print("Slope: \"undefined\"");
      }
      else { //else print value for slope, Y intercept, and X intercept
         System.out.println("Slope: " + slope);
         System.out.println("Y intercept: " + yint);
         System.out.print("X intercept: "); 
         
         if (y1 == y2) { //if x1 = x2 print "X intercept: "undefined""
            System.out.print("\"undefined\""); 
         }
         else { //else print value for X intercept
            System.out.print(xint);
         }
      }
   }
}
