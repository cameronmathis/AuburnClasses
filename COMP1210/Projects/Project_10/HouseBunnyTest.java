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
    */
   @Test public void getNameTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals("Spot", tb1.getName());
   }
   
   /**
    * Test setName().
    */
   @Test public void setNameTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setName("NotSpot");
      Assert.assertEquals("NotSpot", tb1.getName());
   }
   
   /**
    * Test getBreed().
    */
   @Test public void getBreedTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals("Really Mixed", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    */
   @Test public void setBreedTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setBreed("Not Really Mixed");
      Assert.assertEquals("Not Really Mixed", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    */
   @Test public void getWeightTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(5.8, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    */
   @Test public void setWeightTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setWeight(3.6);
      Assert.assertEquals(3.6, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    */
   @Test public void getBunnyCountTest() {
      Bunny.resetBunnyCount();
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    */
   @Test public void resetBunnyCountTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    */
   @Test public void estimatedMonthlyCostTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(15.01, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    */
   @Test public void toStringTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      String expected = "Spot (HouseBunny)   Breed: Really Mixed   "
         + "Weight: 5.8\nEstimated Monthly Cost: $15.01 (includes "
         + "15.0% for wear and tear)";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest1() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest2() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spo", "Really Mixed", 5.8, 0.15);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest3() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixe", 5.8, 0.15);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest4() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.7, 0.15);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest5() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.14);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest6() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    */
   @Test public void hashCodeTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest1() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spo", "Really Mixed", 5.8, 0.15);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest2() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spott", "Really Mixed", 5.8, 0.15);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest3() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      HouseBunny tb2 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
   
   /**
    * Test getWearAndTear().
    */
   @Test public void getWearAndTearTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      Assert.assertEquals(0.15, tb1.getWearAndTear(), .01);
   }
   
   /**
    * Test setName().
    */
   @Test public void setWearAndTearTest() {
      HouseBunny tb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      tb1.setWearAndTear(0.16);
      Assert.assertEquals(0.16, tb1.getWearAndTear(), .01);
   }
}
