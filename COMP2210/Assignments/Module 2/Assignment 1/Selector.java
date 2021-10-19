import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Cameron Mathis (clm0081@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 09-06-18
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
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.size() <= 0) {
         throw new NoSuchElementException();
      }
      else {
         T target = coll.iterator().next();
         for (T element : coll) {
            if (comp.compare(element, target) < 0) {
               target = element;
            }
         }
         return target;
      }
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.size() <= 0) {
         throw new NoSuchElementException();
      }
      else {
         T target = coll.iterator().next();
         for (T element : coll) {
            if (comp.compare(element, target) > 0) {
               target = element;
            }
         }
         return target;
      }
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if ((coll.size() <= 0) || (k <= 0)) {
         throw new NoSuchElementException();
      }
      else {
         List<T> c1 = new ArrayList<T>(coll); //copy coll to c1
         java.util.Collections.<T>sort(c1, comp); //sort c1
         Iterator<T> itr = c1.iterator();
         T target = itr.next();
         //eleminate duplicates
         int z = 1;
         for (T element : c1) {
            //prevent from scanning to far
            if (z == coll.size()) {
               break;
            }
            z++;
            //check for duplicates
            target = itr.next();
            if (comp.compare(element, target) == 0) {
               Collection<T> c2 = new ArrayList<T>(c1);
               c1 = new ArrayList<T>(c2.size() - 1);
               c2.remove(element);
               for (T el : c2) {
                  c1.add(el);
               }
            }
         }
         if (k <= c1.size()) {
            target = c1.get(k - 1);               
            return target;
         }
         else {
            throw new NoSuchElementException();
         }
      }
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if ((coll.size() <= 0) || (k <= 0)) {
         throw new NoSuchElementException();
      }
      else {
         List<T> c1 = new ArrayList<T>(coll); //copy coll to c1
         java.util.Collections.<T>sort(c1, comp); //sort c1
         Iterator<T> itr = c1.iterator();
         T target = itr.next();
         //eleminate duplicates
         int z = 1;
         for (T element : c1) {
            //prevent from scanning to far
            if (z == coll.size()) {
               break;
            }
            z++;
            //check for duplicates
            target = itr.next();
            if (comp.compare(element, target) == 0) {
               Collection<T> c2 = new ArrayList<T>(c1);
               c1 = new ArrayList<T>(c2.size() - 1);
               c2.remove(element);
               for (T el : c2) {
                  c1.add(el);
               }
            }
         }
         if (k <= c1.size()) {
            target = c1.get(c1.size() - k);               
            return target;
         }
         else {
            throw new NoSuchElementException();
         }
      }
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.size() <= 0) {
         throw new NoSuchElementException();
      }
      else {
         boolean b = false;
         Collection<T> c1 = new ArrayList<T>();
         for (T element : coll) {
            if ((comp.compare(element, low) >= 0) && (comp.compare(element, high) <= 0)) {
               c1.add(element);
               b = true;
            }
         }
         if (b) {
            return c1;
         }
         else {
            throw new NoSuchElementException();
         }
      }
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.size() <= 0) {
         throw new NoSuchElementException();
      }
      else {
         boolean b = true;
         boolean c = false;
         T target = coll.iterator().next();
         for (T element : coll) {
            if ((comp.compare(element, key) >= 0) && b) {
               target = element;
               b = false;
               c = true;
            }
            else if ((comp.compare(element, key) >= 0) && (comp.compare(element, target) < 0)) {
               target = element;
               c = true;
            }
         }
         if (c) {
            return target;
         }
         else {
            throw new NoSuchElementException();
         }
      }
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) throws IllegalArgumentException, NoSuchElementException {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.size() <= 0) {
         throw new NoSuchElementException();
      }
      else {
         boolean b = true;
         boolean c = false;
         T target = coll.iterator().next();
         for (T element : coll) {
            if ((comp.compare(element, key) <= 0) && b) {
               target = element;
               b = false;
               c = true;
            }
            else if ((comp.compare(element, key) <= 0) && (comp.compare(element, target) > 0)) {
               target = element;
               c = true;
            }
         }
         if (c) {
            return target;
         }
         else {
            throw new NoSuchElementException();
         }
      }
   }
}
