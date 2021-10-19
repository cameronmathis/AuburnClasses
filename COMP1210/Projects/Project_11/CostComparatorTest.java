import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cameron Mathis
 * @version 9 April 2018
 */
public class CostComparatorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
    
   /**
    * Test compare().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareTest1() throws NegativeValueException {
      PetBunny b1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      HouseBunny b2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      CostComparator cC = new CostComparator();
      Assert.assertEquals(-1, cC.compare(b1, b2));
   }
   
   /**
    * Test compare().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareTest2() throws NegativeValueException {
      JumpingBunny b1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      HouseBunny b2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      CostComparator cC = new CostComparator();
      Assert.assertEquals(1, cC.compare(b1, b2));
   }
   
   /**
    * Test compare().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareTest3() throws NegativeValueException {
      PetBunny b1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny b2 = new PetBunny("Floppy", "Holland Lop", 3.5);
      CostComparator cC = new CostComparator();
      Assert.assertEquals(0, cC.compare(b1, b2));
   }
}
