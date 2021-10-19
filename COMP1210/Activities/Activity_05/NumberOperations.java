/**
 * @author Cameron Mathis
 * @version 5 February 2017
 */
public class NumberOperations {
   private int number;
   
//constructor
/**
 * @param numberIn the number.
 */
   public NumberOperations(int numberIn) {
   
      number = numberIn;
   }
   
//methods
/**
 * Gets the value.
 *
 * @return number.
 */
   public int getValue() {
   
      return number;
   }
   
/**
 * Checks odds under.
 *
 * @return odds.
 */
   public String oddsUnder() {
   
      String output = "";
      int i = 0;
      while (i < number) {
         if (i % 2 != 0) {
            output += i + "\t";
         }
         i++;
      }
      return output;
   }

/**
 * Checks powers.
 *
 * @return powers.
 */
   public String powersTwoUnder() {
   
      String output = "";
      int powers = 1;
      while (powers < number) {
         output += powers + "\t";
         powers = powers * 2;
      }
      return output;
   }
   
/**
 * Checks if is greater.
 *
 * @param compareNumber the compare number.
 * @return compareNumber.
 */
   public int isGreater(int compareNumber) {
   
      if (number > compareNumber) {
         return 1;
      }
      else if (number < compareNumber) {
         return -1;
      }
      else {
         return 0;
      }
   }

/**
 * Coverts to string.
 *
 * @return string.
 */
   public String toString() {
   
      return number + "";
   }
}