import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Cameron Mathis
 * @version 2 April 2018
 */
public class ShowBunnyTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test getName().
    */
   @Test public void getNameTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals("Bigun", tb1.getName());
   }
   
   /**
    * Test setName().
    */
   @Test public void setNameTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setName("NotBigun");
      Assert.assertEquals("NotBigun", tb1.getName());
   }
   
   /**
    * Test getBreed().
    */
   @Test public void getBreedTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals("Flemish Giant", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    */
   @Test public void setBreedTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setBreed("Not Flemish Giant");
      Assert.assertEquals("Not Flemish Giant", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    */
   @Test public void getWeightTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(14.6, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    */
   @Test public void setWeightTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setWeight(14.5);
      Assert.assertEquals(14.5, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    */
   @Test public void getBunnyCountTest() {
      Bunny.resetBunnyCount();
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    */
   @Test public void resetBunnyCountTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    */
   @Test public void estimatedMonthlyCostTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(62.15, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    */
   @Test public void toStringTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      String expected = "Bigun (ShowBunny)   Breed: Flemish Giant   "
         + "Weight: 14.6\nEstimated Monthly Cost: $62.15 (includes "
         + "$22.00 for grooming)";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest1() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest2() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigu", "Flemish Giant", 14.6, 22.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest3() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Gian", 14.6, 22.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest4() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.7, 22.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest5() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.1);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest6() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    */
   @Test public void hashCodeTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest1() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigu", "Flemish Giant", 14.6, 22.0);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest2() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigunn", "Flemish Giant", 14.6, 22.0);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest3() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
   
   /**
    * Test getGroomingCost().
    */
   @Test public void getTrainingCostTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(22.0, tb1.getGroomingCost(), .01);
   }
   
   /**
    * Test setGroomingCost().
    */
   @Test public void setTrainingCostTest() {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setGroomingCost(22.1);
      Assert.assertEquals(22.1, tb1.getGroomingCost(), .01);
   }
}
