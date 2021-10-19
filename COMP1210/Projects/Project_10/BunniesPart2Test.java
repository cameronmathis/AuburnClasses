import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
/**
 * @author Cameron Mathis
 * @version 9 April 2018
 */
public class BunniesPart2Test {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   /**
    * Test bunniesPartTwo().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void bunniesPartTwoTest()   
      throws FileNotFoundException {
   
      BunniesPart2 bPart2Obj = new BunniesPart2(); // test constructor 
   
      Bunny.resetBunnyCount();
   
      String[] args = {"bunnies1.txt"};
      BunniesPart2.main(args);
      Assert.assertEquals(4, Bunny.getBunnyCount());
   }
}
