/**
 * @author Cameron Mathis
 * @version 5 March 2018
 */
public class Scores {
// instance variables
   private int[] numbers;

   // constructor  
   /**
    * @param numbersIn the numbers.
    */
   public Scores(int[] numbersIn) {
   
      numbers = numbersIn;       
   }
   
   // methods            
   /**
    * @return an array of int(even-values scores).
    */
   public int[] findEvens() {
   
      int numberEvens = 0;
      
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 0) {
            numberEvens++;
         }
      }
      
      int[] evens = new int[numberEvens];
      
      int count = 0;
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 0) {
            evens[count] = numbers[i];
            count++;
         }
      }
      
      return evens;
   }
   
    /**
    * @return an array of int(odd-valued scores).
    */
   public int[] findOdds() {
   
     
      int numberOdds = 0;
      
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 1) {
            numberOdds++;
         }
      }
      
      int[] odds = new int[numberOdds];
      
      int count = 0;
      for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] % 2 == 1) {
            odds[count] = numbers[i];
            count++;
         }
      }
      
      return odds;
   }
   
    /**
    * @return average value of scores.
    */
   public double calculateAverage() {
   
      int sum = 0;
      
      for (int i = 0; i < numbers.length; i++) {
         sum += numbers[i];
      }
      return ((double) sum) / (numbers.length);
   }
   
    /**
    * @return a string containing all scores.
    */
   public String toString() {
   
      String result = "";
      
      for (int i = 0; i < numbers.length; i++) {
         result += numbers[i] + "\t";
      }
      
      return result;
   }
   
    /**
    * @return a string containing all scores in reverse.
    */
   public String toStringInReverse() {
   
      String result = "";
      
      for (int i = numbers.length - 1; i >= 0; i--) {
         result += numbers[i] + "\t";
      }
      
      return result;
   }
}
  
