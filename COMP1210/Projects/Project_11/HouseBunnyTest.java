import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Cameron Mathis
 * @version 2 April 2018
 */
public class HouseBunnyTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test getName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getNameTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals("Spot", tb1.getName());
   }
   
   /**
    * Test setName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setNameTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setName("NotSpot");
      Assert.assertEquals("NotSpot", tb1.getName());
   }
   
   /**
    * Test getBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBreedTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals("Really Mixed", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setBreedTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setBreed("Not Really Mixed");
      Assert.assertEquals("Not Really Mixed", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getWeightTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(5.8, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setWeightTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setWeight(3.6);
      Assert.assertEquals(3.6, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBunnyCountTest() throws NegativeValueException {
      Bunny.resetBunnyCount();
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void resetBunnyCountTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void estimatedMonthlyCostTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(15.01, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void toStringTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      String expected = "Spot (HouseBunny)   Breed: Really Mixed   "
         + "Weight: 5.8\nEstimated Monthly Cost: $15.01 (includes "
         + "15.0% for wear and tear)";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest1() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest2() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spo", "Really Mixed", 5.8, 0.15);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest3() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixe", 5.8, 0.15);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest4() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.7, 0.15);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest5() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.14);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest6() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void hashCodeTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest1() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spo", "Really Mixed", 5.8, 0.15);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest2() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spott", "Really Mixed", 5.8, 0.15);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest3() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
   
   /**
    * Test getWearAndTear().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getWearAndTearTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(0.15, tb1.getWearAndTear(), .01);
   }
   
   /**
    * Test setName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setWearAndTearTest() throws NegativeValueException {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setWearAndTear(0.16);
      Assert.assertEquals(0.16, tb1.getWearAndTear(), .01);
   }
}
