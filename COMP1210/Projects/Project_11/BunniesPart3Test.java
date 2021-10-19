import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
/**
 * @author Cameron Mathis
 * @version 9 April 2018
 */
public class BunniesPart3Test {

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test bunniesPartTwo().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void bunniesPart3Test1()   
      throws FileNotFoundException {
   
      BunniesPart3 bPart2Obj = new BunniesPart3(); // test constructor 
   
      Bunny.resetBunnyCount();
   
      String[] args = {"bunnies2.txt"};
      BunniesPart3.main(args);
      Assert.assertEquals(4, Bunny.getBunnyCount());
   }  
   
   /**
    * Test bunniesPartTwo().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void bunniesPart3Test2()   
      throws FileNotFoundException {
   
      BunniesPart3 bPart2Obj = new BunniesPart3(); // test constructor 
   
      Bunny.resetBunnyCount();
   
      String[] args = {"not.txt"};
      BunniesPart3.main(args);
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test bunniesPartTwo().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void bunniesPart3Test3()   
      throws FileNotFoundException {
             
      BunniesPart3 bPart2Obj = new BunniesPart3(); // test constructor 
   
      Bunny.resetBunnyCount();
   
      String[] args = {};
      BunniesPart3.main(args);
      Assert.assertTrue(args.length == 0);
      Assert.assertEquals(0, Bunny.getBunnyCount());
   } 
}
