import java.util.Scanner;
import java.text.DecimalFormat;


/** 
 * Accepts coded ticket information to another time and place (and back)
 * as input that includes the date, time, category, price, and seat,
 * followed by the description of the travel.
 *
 * @author Cameron Mathis
 * @version 28 January 2017
 */
public class TimeTravel {

   static final double STUDENT_DISCOUNT = .50;
   static final double EMPLOYEE_DISCOUNT = .75;
  /**
   * Accepts coded ticket information to another time and place (and back)
   * as input that includes the date, time, category, price, and seat,
   * followed by the description of the travel.
   *     
   * @param args Command line arguments (not used).
   */
   public static void main(String[] args) {
   
      Scanner userInput = new Scanner(System.in);
      DecimalFormat fmt1 = new DecimalFormat("$#,##0.00;($#,##0.00)");
      DecimalFormat fmt2 = new DecimalFormat("0000");
      String ticketCode;
      char category;
      String priceS;
      double priceD;
      double cost;
      int prizeNumber = (int) (Math.random() * 9999) + 1;
      
     //prompt user for their ticket code
      System.out.print("Enter ticket code: ");
      ticketCode = userInput.nextLine();   
      
      if (ticketCode.length() < 26) { // invalid code
         System.out.println("");
         System.out.println("*** Invalid ticket code ***");
         System.out.println("Ticket code must have at least 26 characters.");
      }
      else { // valid code
         //get ticket info
         ticketCode = ticketCode.trim();
         category = ticketCode.charAt(12);
         priceS = ticketCode.substring(13, 22);
         priceD = Double.parseDouble(priceS);
         priceD /= 100;
         if (category == 's') {
            cost = priceD * STUDENT_DISCOUNT;
         }
         else if (category == 'e') {
            cost = priceD * EMPLOYEE_DISCOUNT;
         }
         else {
            cost = priceD;
         }
                  
         //display info
         System.out.println("");
         System.out.println("Time: " + ticketCode.substring(0, 2)
            + ":" + ticketCode.substring(2, 4)
            + "   Date: " + ticketCode.substring(4, 6)
            + "/" + ticketCode.substring(6, 8)
            + "/" + ticketCode.substring(8, 12)
            + "   Seat: " + ticketCode.substring(22, 25));
         System.out.println("Itinerary: "
            + ticketCode.substring(25));
         System.out.println("Price: " + fmt1.format(priceD) 
            + "   Cost: " + fmt1.format(cost) 
            + "   Category: " + ticketCode.charAt(12));         
         System.out.println("Prize Number: " + fmt2.format(prizeNumber));
      }
   }
}

