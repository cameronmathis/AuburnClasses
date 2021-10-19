import java.util.Scanner;

/** 
 * Allows the user to enter the amount of cash in whole dollars
 * and then displays the number bills by denomination be dispensed
 * if the limit of $300 is not exceeded.  
 *
 * @author Cameron Mathis
 * @version 24 January 2017
 */
public class ATM {
  /**
   * Allows the user to enter the amount of cash in whole dollars
   * and then displays the number bills by denomination be dispensed
   * if the limit of $300 is not exceeded. 
   *     
   * @param args Command line arguments (not used).
   */

   public static void main(String[] args) {
   
      Scanner userInput = new Scanner(System.in);
      int amount = 0;
      int twenties = 0;
      int tens = 0;
      int fives = 0;
      int ones = 0;
      
      //prompt the user for an amount
      System.out.print("Enter the amount: ");
      amount = userInput.nextInt();
      
      //calculate bills by denomination
      twenties = amount / 20;
      tens = (amount % 20) / 10;
      fives = (amount % 10) / 5;
      ones = amount % 5;
      
      if (amount > 300) { //inform user that limit is exceeded
         System.out.print("Limit of $300 exceeded!");
      }
      else { //display bills by denomination
         System.out.println("Bills by denomination:");
         System.out.println("\t$20: " + twenties);
         System.out.println("\t$10: " + tens);
         System.out.println("\t$5: " + fives);
         System.out.println("\t$1: " + ones);
         System.out.println("$" + amount + " = (" + twenties + " * $20) + ("
            + tens + " * $10) + (" + fives + " * $5) + (" + ones + " * $1)");
      }       
   }
}

