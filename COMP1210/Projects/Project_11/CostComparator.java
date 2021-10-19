import java.util.Comparator;
/**
 * @author Cameron Mathis
 * @version 9 April 2018
 */
public class CostComparator implements Comparator<Bunny> { 
 
  /**
   * @param b1 the first bunny.
   * @param b2 the second bunny.
   * @return an int based on monthly cost.
   */
   public int compare(Bunny b1, Bunny b2) {
   
      if (b1.estimatedMonthlyCost() < b2.estimatedMonthlyCost()) {
         return -1;
      }
      else if (b1.estimatedMonthlyCost() > b2.estimatedMonthlyCost()) {
         return 1;
      }
      else {
         return 0;
      }
   }  
}