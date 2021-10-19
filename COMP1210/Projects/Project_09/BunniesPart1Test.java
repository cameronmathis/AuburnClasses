import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Cameron Mathis
 * @version 2 April 2018
 */
public class BunniesPart1Test {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test getBunnyCount().
    */
   @Test public void getBunnyCountTest() {
      BunniesPart1 vp1 = new BunniesPart1();
      Bunny.resetBunnyCount();
      BunniesPart1.main(null);
      Assert.assertEquals(4, Bunny.getBunnyCount());
   }
}
