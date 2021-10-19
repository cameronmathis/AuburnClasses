 /**
  * @author Cameron Mathis
  * @version 16 April 2018
  */
public class Division  {

  // static methods
  /**
   * @param numerator the numerator.
   * @param denominator the denominator.
   * @return result as an int.
   */
   public static int intDivide(int numerator, int denominator) {
   
      try {
         int result = (numerator / denominator);
         return result;
      }
      
      catch (ArithmeticException e) {
         return 0;
      }
   }
   
  /**
   * @param numerator the numerator.
   * @param denominator the denominator.
   * @return result as an float.
   */
   public static float decimalDivide(int numerator, int denominator) {
   
      float result = ((float) numerator / denominator);
      
      if (denominator == 0) {
         throw new IllegalArgumentException("The denominator "
            + "cannot be zero.");
      }
      
      return result;
   }
}
