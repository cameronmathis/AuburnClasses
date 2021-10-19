import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Cameron Mathis
 * @version 2 April 2018
 */
public class PetBunnyTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test getName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getNameTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals("Floppy", tb1.getName());
   }
   
   /**
    * Test setName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setNameTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      tb1.setName("NotFloppy");
      Assert.assertEquals("NotFloppy", tb1.getName());
   }
   
   /**
    * Test getBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBreedTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals("Holland Lop", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setBreedTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      tb1.setBreed("Not Holland Lop");
      Assert.assertEquals("Not Holland Lop", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getWeightTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(3.5, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setWeightTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      tb1.setWeight(3.6);
      Assert.assertEquals(3.6, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBunnyCountTest() throws NegativeValueException {
      Bunny.resetBunnyCount();
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void resetBunnyCountTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void estimatedMonthlyCostTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(6.48, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void toStringTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      String expected = "Floppy (PetBunny)   Breed: Holland Lop   "
         + "Weight: 3.5\nEstimated Monthly Cost: $6.48";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest1() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest2() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Flopp", "Holland Lop", 3.5);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest3() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland", 3.5);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest4() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland Lop", 3.4);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest5() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void hashCodeTest() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest1() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Flopp", "Holland Lop", 3.5);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest2() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppyy", "Holland Lop", 3.5);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest3() throws NegativeValueException {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
}
