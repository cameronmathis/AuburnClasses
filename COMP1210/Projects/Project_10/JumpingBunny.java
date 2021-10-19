import java.text.DecimalFormat;   
 /**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class JumpingBunny extends Bunny {
// instance variables
   private double trainingCost;

   // constants
   /**
     * shipping cost is 2.50.
     */
   public static final double BASE_COST = 2.50;    
   
   // constructor
   /**
    * @param nameIn the name.
    * @param breedIn the breed.
    * @param weightIn the weight.
    * @param trainingCostIn the trainingCost.
    */
   public JumpingBunny(String nameIn, String breedIn, double weightIn, 
      double trainingCostIn) {
      super(nameIn, breedIn, weightIn);
      trainingCost = trainingCostIn;
   }
   
   // methods
   /**
    * @return trainingCost the trainingCost.
    */
   public double getTrainingCost() {
      return trainingCost;
   }
   
   /**
    * @param trainingCostIn the trainingCost.
    */
   public void setTrainingCost(double trainingCostIn) {
      trainingCost = trainingCostIn;
   }
   
   /**
    * @return the estimated monthly cost.
    */
   public double estimatedMonthlyCost() {
      return BASE_COST * weight + trainingCost;
   }
   
   /**
    * @return result the String.
    */
   public String toString() {
      DecimalFormat fmt1 = new DecimalFormat("$#,##0.00;($#,##0.00)");
      return super.toString() + " (includes " + fmt1.format(getTrainingCost())
         + " for training)";
   }
   
   /**
    * @param obj the object entered.
    * @return boolean value based on if object entered matches bunny.
    */ 
   public boolean equals(Object obj) {
      if (!(obj instanceof Bunny)) { 
         return false;
      }        
      else {
         JumpingBunny b = (JumpingBunny) obj;
         return (super.equals(obj)
            && Math.abs(trainingCost - b.getTrainingCost()) < .000001);
      }           
   }
   
   /**
    * @return zero.
    */ 
   public int hashCode() {
      return 0;
   }
}