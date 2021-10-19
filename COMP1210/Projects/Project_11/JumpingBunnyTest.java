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
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getNameTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals("Speedy", tb1.getName());
   }
   
   /**
    * Test setName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setNameTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setName("NotSpeedy");
      Assert.assertEquals("NotSpeedy", tb1.getName());
   }
   
   /**
    * Test getBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBreedTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals("English", tb1.getBreed());
   }
   
   /**
    * Test setBreed().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setBreedTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setBreed("Not English");
      Assert.assertEquals("Not English", tb1.getBreed());
   }
   
   /**
    * Test getWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getWeightTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(6.3, tb1.getWeight(), .01);
   }
   
   /**
    * Test setWeight().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setWeightTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setWeight(6.4);
      Assert.assertEquals(6.4, tb1.getWeight(), .01);
   }
   
   /**
    * Test getBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBunnyCountTest() throws NegativeValueException {
      Bunny.resetBunnyCount();
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(1, Bunny.getBunnyCount());
   }
   
   /**
    * Test resetBunnyCount().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void resetBunnyCountTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Bunny.resetBunnyCount();
      Assert.assertEquals(0, Bunny.getBunnyCount());
   }
   
   /**
    * Test estimatedMonthlyCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void estimatedMonthlyCostTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(40.75, tb1.estimatedMonthlyCost(), .01);
   }
   
   /**
    * Test toString().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void toStringTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      String expected = "Speedy (JumpingBunny)   Breed: English   "
         + "Weight: 6.3\nEstimated Monthly Cost: $40.75 (includes "
         + "$25.00 for training)";
      Assert.assertEquals(expected, tb1.toString());
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest1() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Object obj = new Object();
      Assert.assertFalse(tb1.equals(obj));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest2() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speed", "English", 6.3, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest3() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "Englis", 6.3, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest4() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.2, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest5() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.1);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertFalse(tb1.equals(tb2));
   }
   
   /**
    * Test equals().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void equalsTest6() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertTrue(tb1.equals(tb2));
   }
   
   /**
    * Test hachCode().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void hashCodeTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(0, tb1.hashCode());
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest1() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speed", "English", 6.3, 25.0);
      Assert.assertTrue(0 < tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest2() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedyy", "English", 6.3, 25.0);
      Assert.assertTrue(0 > tb1.compareTo(tb2));
   }
   
   /**
    * Test compareTo().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void compareToTest3() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      JumpingBunny tb2 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(0, tb1.compareTo(tb2));
   }
   
   /**
    * Test getTrainingCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getTrainingCostTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      Assert.assertEquals(25.0, tb1.getTrainingCost(), .01);
   }
   
   /**
    * Test setTrainingCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void setTrainingCostTest() throws NegativeValueException {
      JumpingBunny tb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      tb1.setTrainingCost(25.1);
      Assert.assertEquals(25.1, tb1.getTrainingCost(), .01);
   }
}
