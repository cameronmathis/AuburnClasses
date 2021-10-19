import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * @author Cameron Mathis
 * @version 21 February 2017
 */
public class ConicalFrustumListApp {

   /**
    * @param args Command line arguments (not used).
    * @throws FileNotFoundException if file not found.
    */
   public static void main(String[] args) throws FileNotFoundException {
   
      Scanner userInput = new Scanner(System.in);
      String listName;
      String label;
      double radius1;
      double radius2;
      double height;
      ArrayList<ConicalFrustum> inputList = new ArrayList<ConicalFrustum>();
      
      // get file         
      System.out.print("Enter file name: ");
      String input = userInput.nextLine();
      Scanner scanFile = new Scanner(new File(input));
      
      // scan file and assing values
      listName = scanFile.nextLine();
      while (scanFile.hasNext()) {   
         label = scanFile.nextLine();
         radius1 = Double.parseDouble(scanFile.nextLine());
         radius2 = Double.parseDouble(scanFile.nextLine());
         height = Double.parseDouble(scanFile.nextLine());   
         inputList.add(new ConicalFrustum(label, radius1, radius2, height));
      }
      scanFile.close();
      
      // create ConicalFrustumList object and print things     
      ConicalFrustumList cl = new ConicalFrustumList(listName, inputList);
      System.out.print("\n" + cl + "\n\n" + cl.summaryInfo());
   }
}