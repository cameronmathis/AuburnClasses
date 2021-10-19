import java.text.DecimalFormat;   
 /**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class ShowBunny extends Bunny {
// instance variables
   private double groomingCost;

   // constants
   /**
     * shipping cost is 2.75.
     */
   public static final double BASE_COST = 2.75;    
   
   // constructor
   /**
    * @param nameIn the name.
    * @param breedIn the breed.
    * @param weightIn the weight.
    * @param groomingCostIn the groomingCost.
    * @throws NegativeValueException if value is negative..
    */
   public ShowBunny(String nameIn, String breedIn, double weightIn, 
      double groomingCostIn) throws NegativeValueException {
      super(nameIn, breedIn, weightIn);
      groomingCost = groomingCostIn;
      if (groomingCost < 0) {
         bunnyCount--;
         throw new NegativeValueException();
      }
   }
   
   // methods
   /**
    * @return groomingCost the groomingCost.
    */
   public double getGroomingCost() {
      return groomingCost;
   }
   
   /**
    * @param groomingCostIn the groomingCost.
    */
   public void setGroomingCost(double groomingCostIn) {
      groomingCost = groomingCostIn;
   }
   
   /**
    * @return the estimated monthly cost.
    */
   public double estimatedMonthlyCost() {
      return BASE_COST * weight + groomingCost;
   }
   
   /**
    * @return result the String.
    */
   public String toString() {
      DecimalFormat fmt1 = new DecimalFormat("$#,##0.00;($#,##0.00)");
      return super.toString() + " (includes " + fmt1.format(getGroomingCost())
         + " for grooming)";
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
         ShowBunny b = (ShowBunny) obj;
         return (super.equals(obj)
            && Math.abs(groomingCost - b.getGroomingCost()) < .000001);
      }          
   }
   
   /**
    * @return zero.
    */ 
   public int hashCode() {
      return 0;
   }
}