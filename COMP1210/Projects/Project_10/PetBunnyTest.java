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
    */
   @Test public void getNameTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals("Floppy", tb1.getName());
   }
   
   /**
    * Test setName().
    */
   @Test public void setNameTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      tb1.setName("NotFloppy");
      Assert.assertEquals("NotFloppy", tb1.getName());
   }
   
   /**
    * Test getBreed().
    */
   @Test public void getBreedTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals("Holland Lop", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    */
   @Test public void setBreedTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      tb1.setBreed("Not Holland Lop");
      Assert.assertEquals("Not Holland Lop", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    */
   @Test public void getWeightTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(3.5, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    */
   @Test public void setWeightTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      tb1.setWeight(3.6);
      Assert.assertEquals(3.6, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    */
   @Test public void getBunnyCountTest() {
      Bunny.resetBunnyCount();
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    */
   @Test public void resetBunnyCountTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    */
   @Test public void estimatedMonthlyCostTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(6.48, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    */
   @Test public void toStringTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      String expected = "Floppy (PetBunny)   Breed: Holland Lop   "
         + "Weight: 3.5\nEstimated Monthly Cost: $6.48";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest1() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest2() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Flopp", "Holland Lop", 3.5);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest3() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland", 3.5);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest4() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland Lop", 3.4);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest5() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    */
   @Test public void hashCodeTest() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest1() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Flopp", "Holland Lop", 3.5);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest2() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppyy", "Holland Lop", 3.5);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest3() {
      PetBunny tb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      PetBunny tb2 = new PetBunny("Floppy", "Holland Lop", 3.5);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
}
