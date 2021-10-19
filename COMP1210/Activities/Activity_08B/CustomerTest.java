import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cameron Mathis
 * @version 26 March 2018
 */
public class CustomerTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   /**
    * Test setloaction().
    */
   @Test public void setLocationTest1() {
      Customer cus = new Customer("Lane, Jane");
      cus.setLocation("Boston, MA");
      Assert.assertEquals("Boston, MA", cus.getLocation());
   }
   
   /**
    * Test setloaction().
    */
   @Test public void setLocationTest2() {
      Customer cus = new Customer("Lane, Jane");
      cus.setLocation("Atlanta", "GA");
      Assert.assertEquals("Atlanta, GA", cus.getLocation());
   }
   
   /**
    * Test changeBalance().
    */
   @Test public void changeBalanceTest() {
      Customer cus = new Customer("Lane, Jane");
      cus.changeBalance(100);
      Assert.assertEquals(100, cus.getBalance(), 0.000001);
   }
   
   /**
    * Test toString().
    */
   @Test public void toStringTest() {
      Customer cus = new Customer("Lane, Jane");
      cus.setLocation("Auburn, AL");
      cus.changeBalance(999);
      Assert.assertEquals("Lane, Jane\nAuburn, AL\n$999.0", cus.toString());
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest1() {
      Customer cus1 = new Customer("Lane, Jane");
      cus1.changeBalance(500);
      
      Customer cus2 = new Customer("Lane, Lois");
      cus2.changeBalance(500);
      
      Assert.assertTrue(cus1.compareTo(cus2) == 0);
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest2() {
      Customer cus1 = new Customer("Lane, Jane");
      cus1.changeBalance(100);
      
      Customer cus2 = new Customer("Lane, Lois");
      cus2.changeBalance(500);
      
      Assert.assertTrue(cus1.compareTo(cus2) < 0);
   }
   
   /**
    * Test compareTo().
    */
   @Test public void compareToTest3() {
      Customer cus1 = new Customer("Lane, Jane");
      cus1.changeBalance(1000);
      
      Customer cus2 = new Customer("Lane, Lois");
      cus2.changeBalance(500);
      
      Assert.assertTrue(cus1.compareTo(cus2) > 0);
   }
}
