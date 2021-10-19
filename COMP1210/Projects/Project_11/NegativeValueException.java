/**
 * @author Cameron Mathis
 * @version 18 April 2018
 */
public class NegativeValueException extends Exception {
   
   // constructor
   /***/
   public NegativeValueException() {
      super("Numeric values must be nonnegative");
   }
}