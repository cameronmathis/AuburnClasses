import java.util.Scanner;
import java.text.DecimalFormat;

/** 
 * Evaluates an expression for a value x.
 *
 * @author Cameron Mathis
 * @version 28 January 2017
 */
public class ExpressionEvaluator {

  /**
   * Evaluates an expression for a value x.
   *     
   * @param args Command line arguments (not used).
   */

   public static void main(String[] args) {
   
      Scanner userInput = new Scanner(System.in);
      DecimalFormat fmt = new DecimalFormat("#,##0.0####");
      double x;
      double numerator;
      double denominator;
      double resultD;
      String resultS;
      int decL = 0;
      int decR = 0;
      
      //prompt user for value of x
      System.out.print("Enter a value for x: ");
      x = userInput.nextDouble();
      
      //calculate the result and # of digits to  left & right of decimal point
      numerator = Math.sqrt(Math.abs(10.4 * Math.pow(x, 10) - x + 1));
      denominator = 3.5 * Math.pow(x, 4) + 2.5 * Math.pow(x, 2) + 1.5 * x + .5;
      resultD = numerator / denominator;
      resultS = Double.toString(resultD);
      decL = resultS.indexOf(".");
      decR = resultS.length() - resultS.indexOf(".") - 1;
         
      //display the result and # of digits to  left & right of decimal point
      System.out.println("Result: " + resultD);
      System.out.println("# digits to left of decimal point: " + decL);
      System.out.println("# digits to right of decimal point: " + decR);
      System.out.println("Formatted Result: " + fmt.format(resultD));
   }
}
