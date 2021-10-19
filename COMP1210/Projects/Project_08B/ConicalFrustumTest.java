import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cameron Mathis
 * @version 28 March 2018
 */
public class ConicalFrustumTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test getCount().
    */
   @Test public void getCountTest() {
      ConicalFrustum.resetCount();
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(1, ConicalFrustum.getCount(), .000001);
   }
   
   /**
    * Test resetCount().
    */
   @Test public void resetCountTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      ConicalFrustum.resetCount();
      Assert.assertEquals(0, ConicalFrustum.getCount(), .000001);
   }
   
   /**
    * Test getLabel().
    */
   @Test public void getLabelTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals("Small", cf1.getLabel());
   }
   
   /**
    * Test setLabel().
    */
   @Test public void setLabelTest1() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertFalse(cf1.setLabel(null));
   }
   
   /**
    * Test setLabel().
    */
   @Test public void setLabelTest2() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertTrue(cf1.setLabel("Small1"));
   }
   
   /**
    * Test setLabel().
    */
   @Test public void setLabelTest3() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setLabel(null);
      Assert.assertEquals("Small", cf1.getLabel());
   }
   
   /**
    * Test setLabel().
    */
   @Test public void setLabelTest4() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setLabel("Small1");
      Assert.assertEquals("Small1", cf1.getLabel());
   }
   
   /**
    * Test getRadius1().
    */
   @Test public void getRadius1Test() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(0.5, cf1.getRadius1(), .000001);
   }
   
   /**
    * Test setRadius1().
    */
   @Test public void setRadius1Test1() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertFalse(cf1.setRadius1(-1));
   }
   
   /**
    * Test setRadius1().
    */
   @Test public void setRadius1Test2() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertTrue(cf1.setRadius1(0.51));
   }
   
   /**
    * Test setRadius1().
    */
   @Test public void setRadius1Test3() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setRadius1(-1);
      Assert.assertEquals(0.5, cf1.getRadius1(), .000001);
   }
   
   /**
    * Test setRadius1().
    */
   @Test public void setRadius1Test4() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setRadius1(0.51);
      Assert.assertEquals(0.51, cf1.getRadius1(), .000001);
   }
   
   /**
    * Test getRadius2().
    */
   @Test public void getRadius2Test() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(0.75, cf1.getRadius2(), .000001);
   }
   
   /**
    * Test setRadius2().
    */
   @Test public void setRadius2Test1() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertFalse(cf1.setRadius2(-1));
   }
   
   /**
    * Test setRadius2().
    */
   @Test public void setRadius2Test2() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertTrue(cf1.setRadius2(0.76));
   }
   
   /**
    * Test setRadius2().
    */
   @Test public void setRadius2Test3() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setRadius2(-1);
      Assert.assertEquals(0.75, cf1.getRadius2(), .000001);
   }
   
   /**
    * Test setRadius2().
    */
   @Test public void setRadius2Test4() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setRadius2(0.76);
      Assert.assertEquals(0.76, cf1.getRadius2(), .000001);
   }
   
   /**
    * Test getHeight().
    */
   @Test public void getHeightTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(0.25, cf1.getHeight(), .000001);
   }
   
   /**
    * Test setHeight().
    */
   @Test public void setHeightTest1() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertFalse(cf1.setHeight(-1));
   }
   
   /**
    * Test setHeight().
    */
   @Test public void setHeightTest2() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertTrue(cf1.setHeight(0.26));
   }
   
   /**
    * Test setHeight().
    */
   @Test public void setHeightTest3() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setHeight(-1);
      Assert.assertEquals(0.25, cf1.getHeight(), .000001);
   }
   
   /**
    * Test setHeight().
    */
   @Test public void setHeightTest4() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      cf1.setHeight(0.26);
      Assert.assertEquals(0.26, cf1.getHeight(), .000001);
   }
   
   /**
    * Test volume().
    */
   @Test public void volumeTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(0.311, cf1.volume(), .001);
   }
   
   /**
    * Test slantHeight().
    */
   @Test public void slantHeightTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(0.354, cf1.slantHeight(), .001);
   }
   
   /**
    * Test lateralSurfaceArea().
    */
   @Test public void lateralSurfaceAreaTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(1.388, cf1.lateralSurfaceArea(), .001);
   }
   
   /**
    * Test totalSurfaceArea().
    */
   @Test public void totalSurfaceAreaTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(3.941, cf1.totalSurfaceArea(), .001);
   }
   
   /**
    * Test toString().
    */
   @Test public void toStringTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      String expected = "ConicalFrustum \"" + "Small" + "\" with radius1 = "
         + "0.5" + ", radius2 = " + "0.75"
         + ", and height = " + "0.25" + " has:\n\tvolume = "
         + "0.311" + " cubic units\n\tslant height = "
         + "0.354" + " units\n\tlateral surface area = "
         + "1.388" + " units\n\ttotal surface area = "
         + "3.941" + " square units";
      Assert.assertEquals(expected, cf1.toString());
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest1() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Object obj = new Object();
      Assert.assertFalse(cf1.equals(obj));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest2() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      ConicalFrustum cf2 = new ConicalFrustum("Medium", 0.5, 0.75, 0.25);
      Assert.assertFalse(cf1.equals(cf2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest3() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      ConicalFrustum cf2 = new ConicalFrustum("Small", 5.1, 0.75, 0.25);
      Assert.assertFalse(cf1.equals(cf2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest4() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      ConicalFrustum cf2 = new ConicalFrustum("Small", 0.5, 10.2, 0.25);
      Assert.assertFalse(cf1.equals(cf2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest5() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      ConicalFrustum cf2 = new ConicalFrustum("Small", 0.5, 0.75, 15.9);
      Assert.assertFalse(cf1.equals(cf2));
   }
   
   /**
    * Test equals().
    */
   @Test public void equalsTest6() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      ConicalFrustum cf2 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertTrue(cf1.equals(cf2));
   }
   
   /**
    * Test hachCode().
    */
   @Test public void hashCodeTest() {
      ConicalFrustum cf1 = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(0, cf1.hashCode());
   }
}
