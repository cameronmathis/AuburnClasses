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
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getNameTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals("Bigun", tb1.getName());
   }
   
   /**
    * Test setName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setNameTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setName("NotBigun");
      Assert.assertEquals("NotBigun", tb1.getName());
   }
   
   /**
    * Test getBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBreedTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals("Flemish Giant", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setBreedTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setBreed("Not Flemish Giant");
      Assert.assertEquals("Not Flemish Giant", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getWeightTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(14.6, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setWeightTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setWeight(14.5);
      Assert.assertEquals(14.5, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBunnyCountTest() throws NegativeValueException {
      Bunny.resetBunnyCount();
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void resetBunnyCountTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void estimatedMonthlyCostTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(62.15, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void toStringTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      String expected = "Bigun (ShowBunny)   Breed: Flemish Giant   "
         + "Weight: 14.6\nEstimated Monthly Cost: $62.15 (includes "
         + "$22.00 for grooming)";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest1() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest2() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigu", "Flemish Giant", 14.6, 22.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest3() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Gian", 14.6, 22.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest4() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.7, 22.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest5() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.1);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest6() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void hashCodeTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest1() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigu", "Flemish Giant", 14.6, 22.0);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest2() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigunn", "Flemish Giant", 14.6, 22.0);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest3() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      ShowBunny tb2 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
   
   /**
    * Test getGroomingCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getTrainingCostTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      Assert.assertEquals(22.0, tb1.getGroomingCost(), .01);
   }
   
   /**
    * Test setGroomingCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setTrainingCostTest() throws NegativeValueException {
      ShowBunny tb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      tb1.setGroomingCost(22.1);
      Assert.assertEquals(22.1, tb1.getGroomingCost(), .01);
   }
}
