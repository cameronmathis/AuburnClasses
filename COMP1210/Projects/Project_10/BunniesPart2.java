import java.io.FileNotFoundException;
/**
 * @author Cameron Mathis
 * @version 9 April 2018
 */
public class BunniesPart2 {

    /**
     * @param args the file of bunnies.
     * @throws FileNotFoundException if file not found.
     */
   public static void main(String[] args)  
      throws FileNotFoundException {
      
      BunnyList bL = new BunnyList();
      bL.readBunnyFile(args[0]);
      
      System.out.println(bL.summary());
      
      System.out.println(bL.listByName());
      
      System.out.println(bL.listByCost());
      
      System.out.print(bL.excludedRecordsList());
   }
}