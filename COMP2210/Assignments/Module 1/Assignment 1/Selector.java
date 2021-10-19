import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Cameron Mathis (clm0081@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2018-08-22
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) throws IllegalArgumentException {
      if ((a == null) || (a.length <= 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int target = a[0];
         for (int i = 0; i < a.length; i++) {
            if (a[i] < target) {
               target = a[i];
            }
         }
         return target;
      }
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) throws IllegalArgumentException {
      if ((a == null) || (a.length <= 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int target = a[0];
         for (int i = 0; i < a.length; i++) {
            if (a[i] > target) {
               target = a[i];
            }
         }
         return target;
      }
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) throws IllegalArgumentException {
      if ((a == null) || (a.length <= 0) || (k < 1)) {
         throw new IllegalArgumentException();
      }
      else {
         int[] b = Arrays.copyOf(a, a.length);
         Arrays.sort(b);
         for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == b[i + 1]) {
               int[] c = b;
               b = new int[b.length - 1];
               int x = 0;
               for (int y = 0; y < c.length; y++) {
                  if (y != i) {
                     b[x] = c[y];
                     x++;
                  }
               }
            }
         }
         if (k > b.length) {
            throw new IllegalArgumentException();
         }
         else {
            int tempMin = Selector.min(b);
            int key = tempMin + 1;
            for (int i = k - 1; i > 0; i--) {
               tempMin = Selector.ceiling(b,key);
               key = tempMin + 1;
            }
            return tempMin;
         }
      }
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) throws IllegalArgumentException {
      if ((a == null) || (a.length <= 0) || (k < 1)) {
         throw new IllegalArgumentException();
      }
      else {
         int[] b = Arrays.copyOf(a, a.length);
         Arrays.sort(b);
         for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == b[i + 1]) {
               int[] c = b;
               b = new int[b.length - 1];
               int x = 0;
               for (int y = 0; y < c.length; y++) {
                  if (y != i) {
                     b[x] = c[y];
                     x++;
                  }
               }
            }
         }
         if (k > b.length) {
            throw new IllegalArgumentException();
         }
         else {
            int tempMax = Selector.max(b);
            int key = tempMax - 1;
            for (int i = k - 1; i > 0; i--) {
               tempMax = Selector.floor(b,key);
               key = tempMax - 1;
            }
            return tempMax;
         }
      }
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) throws IllegalArgumentException {
      if ((a == null) || (a.length <= 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int[] b = {};
         int z = 1;
         for (int i = 0; i < a.length; i++) {
            if ((a[i] >= low) && (a[i] <= high)) {
               b = Arrays.copyOf(b, z);
               b[z - 1] = a[i];
               z++;
            }
         }
         return b;
      }
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) throws IllegalArgumentException {
      if ((a == null) || (a.length <= 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int target = key;
         boolean b = true;
         boolean c = false;
         for (int i = 0; i < a.length; i++) {
            if ((a[i] >= key) && b) {
               target = a[i];
               b = false;
               c = true;
            }
            else if ((a[i] >= key) && (a[i] < target)) {
               target = a[i];
               c = true;
            }
         }
         if (c) {
            return target;
         }
         else {
            throw new IllegalArgumentException();
         }
      }
   }

   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) throws IllegalArgumentException {
      if ((a == null) || (a.length <= 0)) {
         throw new IllegalArgumentException();
      }
      else {
         int target = key;
         boolean b = true;
         boolean c = false;
         for (int i = 0; i < a.length; i++) {
            if ((a[i] <= key) && b) {
               target = a[i];
               b = false;
               c = true;
            }
            else if ((a[i] <= key) && (a[i] > target)) {
               target = a[i];
               c = true;
            }
         }
         if (c) {
            return target;
         }
         else {
            throw new IllegalArgumentException();
         }
      }
   }
}
