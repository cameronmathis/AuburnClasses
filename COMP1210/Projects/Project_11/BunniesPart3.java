import java.io.FileNotFoundException;
/**
 * @author Cameron Mathis
 * @version 18 April 2018
 */
public class BunniesPart3 {

    /**
     * @param args the file of bunnies.
     * @throws FileNotFoundException if file not found.
     */
   public static void main(String[] args) {
      
      BunnyList bL = new BunnyList();
      try {
         if (args.length == 0) {
            System.out.println("*** File name not provided by command line"
               + " argument.\nProgram ending.");
            return;
         }
         bL.readBunnyFile(args[0]);
      }
      
      catch (FileNotFoundException e) {
         System.out.println("*** File not found.\nProgram ending.");
      }
      
      System.out.println(bL.summary());
      
      System.out.println(bL.listByName());
      
      System.out.println(bL.listByCost());
      
      System.out.print(bL.excludedRecordsList());
   }
}