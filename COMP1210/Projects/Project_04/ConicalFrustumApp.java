import java.util.Scanner;
/**
 * @author Cameron Mathis
 * @version 07 February 2017
 */
public class ConicalFrustumApp {
  
  /**
   *@param args Command line arguments (not used).
   */
   public static void main(String[] args) {
   
      Scanner userInput = new Scanner(System.in);
      String label;
      double radius1;
      double radius2;
      double height;
     
      System.out.println("Enter label, radius1, radius2, and height"
         + " for a conical frustum.");
      System.out.print("\tlabel: ");
      label = userInput.nextLine();
      System.out.print("\tradius1: ");
      radius1 = userInput.nextDouble();
      if (radius1 < 0) {
         System.out.print("Error: radius1 must be non-negative.");
         return;
      }
      System.out.print("\tradius2: ");
      radius2 = userInput.nextDouble();
      if (radius2 < 0) {
         System.out.print("Error: radius2 must be non-negative.");
         return;
      }
      System.out.print("\theight: ");
      height = userInput.nextDouble();
      if (height < 0) {
         System.out.print("Error: height must be non-negative.");
         return;
      }
      ConicalFrustum c = new ConicalFrustum(label, radius1, radius2, height);
      System.out.print(c.toString());
   }
}