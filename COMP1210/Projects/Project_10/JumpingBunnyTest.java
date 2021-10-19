import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Cameron Mathis
 * @version 2 April 2018
 */
public class JumpingBunnyTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test getName().
    */
   @Test public void getNameTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals("Speedy", tb1.getName());
   }
   
   /**
    * Test setName().
    */
   @Test public void setNameTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setName("NotSpeedy");
      Assert.assertEquals("NotSpeedy", tb1.getName());
   }
   
   /**
    * Test getBreed().
    */
   @Test public void getBreedTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals("English", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    */
   @Test public void setBreedTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setBreed("Not English");
      Assert.assertEquals("Not English", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    */
   @Test public void getWeightTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(6.3, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    */
   @Test public void setWeightTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setWeight(6.4);
      Assert.assertEquals(6.4, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    */
   @Test public void getBunnyCountTest() {
      Bunny.resetBunnyCount();
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    */
   @Test public void resetBunnyCountTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    */
   @Test public void estimatedMonthlyCostTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(40.75, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    */
   @Test public void toStringTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      String expected = "Speedy (JumpingBunny)   Breed: English   "
         + "Weight: 6.3\nEstimated Monthly Cost: $40.75 (includes "
         + "$25.00 for training)";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest1() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest2() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speed", "English", 6.3, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest3() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "Englis", 6.3, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest4() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.2, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest5() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.1);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest6() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    */
   @Test public void hashCodeTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest1() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speed", "English", 6.3, 25.0);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest2() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedyy", "English", 6.3, 25.0);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest3() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
   
   /**
    * Test getTrainingCost().
    */
   @Test public void getTrainingCostTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(25.0, tb1.getTrainingCost(), .01);
   }
   
   /**
    * Test setTrainingCost().
    */
   @Test public void setTrainingCostTest() {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setTrainingCost(25.1);
      Assert.assertEquals(25.1, tb1.getTrainingCost(), .01);
   }
}
