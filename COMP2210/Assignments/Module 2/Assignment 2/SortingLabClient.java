import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
//import java.util.Comparable;
/**
 * SortingLabClient.java
 *
 * Provides a simple example client of SortingLab.java.
 *
 * NOTE: The generic type of SortingLab must be bound
 *       to a Comparable type. The sorting methods in
 *       SortingLab use the natural ordering of the
 *       elements in the parameter array.
 *
 * @author    Dean Hendrix (dh@auburn.edu)
 * @version   2018-09-16
 *
 */
public final class SortingLabClient {

   /** Drives execution. */
   public static void main(String[] args) {
   
      // instantiate the SortingLab class
      // using your Banner ID number as the key value
      int key = 903828685;
      SortingLab<String> sls = new SortingLab<String>(key);
   
      // run each sort a few times before trying to
      // collect timing data
      String[] as = {"D", "B", "E", "C", "A"};
      for (int i = 0; i < 10; i++) {
         sls.sort1(as);
         sls.sort2(as);
         sls.sort3(as);
         sls.sort4(as);
         sls.sort5(as);
      }
   
      /** An array of Data. */
      Data[] ad = {new Data("A",2), new Data("A", 4), new Data("A", 5), 
         new Data("B", 2), new Data("A", 3), new Data("A", 1)};
   
      // use to determine stability
      SortingLab<Data> sld = new SortingLab<Data>(key);
      sld.sort4(ad);
      for (Data element : ad) {
         System.out.println(element);
      }
      
      // generate timing data for one sort method using
      // the "doubling strategy" from lecture and lab
      SortingLab<Integer> sli = new SortingLab<Integer>(key);
      int M = 2000000; // max capacity for array
      int N = 10000;   // initial size of array
      double start;
      double elapsedTime;
      for (; N < M; N *= 2) {
         Integer[] ai = getIntegerArray(N, Integer.MAX_VALUE);
         start = System.nanoTime();
         sli.sort1(ai);
         elapsedTime = (System.nanoTime() - start) / 1_000_000_000d;
         System.out.print(N + "\t");
         System.out.printf("%4.3f\n", elapsedTime);
      }
   }

   /** return an array of random integer values. */
   private static Integer[] getIntegerArray(int N, int max) {
      Integer[] a = new Integer[N];
      java.util.Random rng = new java.util.Random();
      for (int i = 0; i < N; i++) {
         a[i] = rng.nextInt(max);
      }
      return a;
   }
   
   // /** return an array of increasing integer values. */
   // private static Integer[] getIntegerArray(int N, int max) {
      // Integer[] a = new Integer[N];
      // for (int i = 0; i < N; i++) {
         // a[i] = i;
      // }
      // return a;
   // }
   
   /**
    * Defines an example composite "data" value with two fields.
    */
   static class Data implements Comparable<Data>{
      String  stringVal;
      Integer integerVal;
      
      public Data(String sval, Integer ival) {
         stringVal = sval;
         integerVal = ival;
      }
      
      @Override
      /**
       * Returns a string representation of this Data.
       * @return a formatted string with s and i values
       */
      public String toString() {
         return "(" + stringVal + ", " + integerVal + ")";
      }
      
      @Override
      public int compareTo(Data other) {
         return Integer.compare(this.integerVal, other.integerVal);
      }
   }
}
