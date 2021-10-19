import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import org.junit.Assert;

/**
 * SelectorTest.java
 * Illustrates JUnit tests for the Selector class.
 *
 * @author Cameron Mathis (clm0081@auburn.edu)
 * @version 2018-08-22
 *
 */
public class SelectorTest {
   
   /** Test with min being 2.  */
   @Test
   public void testMin1() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int expected = 2;
      int actual = Selector.min(a);
      assertEquals(expected, actual);
   }
   
    /** Test with error thrown.  */
   @Test
   public void testMin2() {
      int[] a = new int[0];
      try {
         Selector.min(a);
         Assert.fail("Did not throw IllegalArgumentException.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true); //pass
      }
      catch (Exception e) {
         Assert.fail("Thre incorrect excception.");
      }
   }
         
   /** Test with max being 8.  */
   @Test
   public void testMax1() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int expected = 8;
      int actual = Selector.max(a);
      assertEquals(expected, actual);
   }
   
   /** Test with error thrown.  */
   @Test
   public void testMax2() {
      int[] a = new int[0];
      try {
         Selector.max(a);
         Assert.fail("Did not throw IllegalArgumentException.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true); //pass
      }
      catch (Exception e) {
         Assert.fail("Thre incorrect excception.");
      }
   }

   /** Test with range being [7,3,3,4].  */
   @Test
   public void testRange1() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int[] expected = {7,3,3,4};
      int[] actual = Selector.range(a,3,7);
      Assert.assertArrayEquals(expected, actual);
   }
   
   /** Test with error thrown.  */
   @Test
   public void testRange2() {
      int[] a = new int[0];
      try {
         Selector.range(a,3,7);
         Assert.fail("Did not throw IllegalArgumentException.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true); //pass
      }
      catch (Exception e) {
         Assert.fail("Thre incorrect excception.");
      }
   
   }
   
   /** Test with key being 5 & ceiling being 7.  */
   @Test
   public void testCeiling1() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int expected = 7;
      int actual = Selector.ceiling(a,5);
      assertEquals(expected, actual);
   }
   
   /** Test with error thrown.  */
   @Test
   public void testCeiling2() {
      int[] a = new int[0];
      try {
         Selector.ceiling(a,5);
         Assert.fail("Did not throw IllegalArgumentException.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true); //pass
      }
      catch (Exception e) {
         Assert.fail("Thre incorrect excception.");
      }
   }
   
   /** Test with key being 5 & ceiling being 4.  */
   @Test
   public void testFloor1() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int expected = 4;
      int actual = Selector.floor(a,5);
      assertEquals(expected, actual);
   }
   
   /** Test with error thrown.  */
   @Test
   public void testFloor2() {
      int[] a = new int[0];
      try {
         Selector.floor(a,5);
         Assert.fail("Did not throw IllegalArgumentException.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true); //pass
      }
      catch (Exception e) {
         Assert.fail("Thre incorrect excception.");
      }
   }
   
   /** Test with k being 3 & kmin being 4.  */
   @Test
   public void testKmin1() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int expected = 4;
      int actual = Selector.kmin(a,3);
      assertEquals(expected, actual);
   }
   
   /** Test with error thrown.  */
   @Test
   public void testKmin2() {
      int[] a = new int[0];
      try {
         Selector.kmin(a,3);
         Assert.fail("Did not throw IllegalArgumentException.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true); //pass
      }
      catch (Exception e) {
         Assert.fail("Thre incorrect excception.");
      }
   
   }

   
   /** Test with k being 3 & kmax being 4.  */
   @Test
   public void testKmax1() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int expected = 4;
      int actual = Selector.kmax(a,3);
      assertEquals(expected, actual);
   }
   
   /** Test with error thrown.  */
   @Test
   public void testKmax2() {
      int[] a = new int[0];
      try {
         Selector.kmax(a,3);
         Assert.fail("Did not throw IllegalArgumentException.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true); //pass
      }
      catch (Exception e) {
         Assert.fail("Thre incorrect excception.");
      }
   }
}