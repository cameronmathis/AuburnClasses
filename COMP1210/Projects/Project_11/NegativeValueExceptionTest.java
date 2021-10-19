import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cameron Mathis
 * @version 9 April 2018
 */
public class NegativeValueExceptionTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test NegativeValueException().
    */
   @Test public void negativeValueExceptionTest1() {
      boolean thrown = false;
      try {
         HouseBunny hb = new HouseBunny("Spot", "Mixed", -0.08, 0);
      }
      catch (NegativeValueException e) {
         thrown = true;
      }
      Assert.assertTrue("Expected NegativeValueException to be thrown.",
         thrown); /* or alternatively: */
      Assert.assertEquals("Expected NegativeValueException to be thrown.",
         true, thrown);
   }
   
   /**
    * Test NegativeValueException().
    */
   @Test public void negativeValueExceptionTest2() {
      boolean thrown = false;
      try {
         HouseBunny hb = new HouseBunny("Spot", "Mixed", 0, -0.08);
      }
      catch (NegativeValueException e) {
         thrown = true;
      }
      Assert.assertTrue("Expected NegativeValueException to be thrown.",
         thrown); /* or alternatively: */
      Assert.assertEquals("Expected NegativeValueException to be thrown.",
         true, thrown);
   }
}
