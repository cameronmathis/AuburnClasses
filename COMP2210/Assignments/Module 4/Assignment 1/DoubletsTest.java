import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * DoubletsTest.java
 * Illustrates JUnit tests for the Doublets class.
 *
 * @author Cameron Mathis (clm0081@auburn.edu)
 * @version 2018-10-23
 *
 */
public class DoubletsTest {

   /** Test getHammingDistance(String str1, String str2) **/
   @Test public void getHammingDistanceTest1() {
      Doublets obj = new Doublets(null);
      String a = "";
      String b = "";
      int expected = 0;
      int actual = obj.getHammingDistance(a, b);
      assertEquals(expected, actual);
   }
   
   /** Test getHammingDistance(String str1, String str2) **/
   @Test public void getHammingDistanceTest2() {
      Doublets obj = new Doublets(null);
      String a = "a";
      String b = "";
      int expected = -1;
      int actual = obj.getHammingDistance(a, b);
      assertEquals(expected, actual);
   }
   
   /** Test getHammingDistance(String str1, String str2) **/
   @Test public void getHammingDistanceTest3() {
      Doublets obj = new Doublets(null);
      String a = "";
      String b = "b";
      int expected = -1;
      int actual = obj.getHammingDistance(a, b);
      assertEquals(expected, actual);
   }
   
   /** Test getHammingDistance(String str1, String str2) **/
   @Test public void getHammingDistanceTest4() {
      Doublets obj = new Doublets(null);
      String a = "a";
      String b = "b";
      int expected = 1;
      int actual = obj.getHammingDistance(a, b);
      assertEquals(expected, actual);
   }
   
   /** Test getHammingDistance(String str1, String str2) **/
   @Test public void getHammingDistanceTest5() {
      Doublets obj = new Doublets(null);
      String a = "a";
      String b = "a";
      int expected = 0;
      int actual = obj.getHammingDistance(a, b);
      assertEquals(expected, actual);
   }
   
   
}
