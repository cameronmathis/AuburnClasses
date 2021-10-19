import java.text.DecimalFormat;   
 /**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class HouseBunny extends PetBunny {
// instance variables
   private double wearAndTear;

   // constants
   /**
     * shipping cost is 2.25.
     */
   public static final double BASE_COST = 2.25;    
   
   // constructor
   /**
    * @param nameIn the name.
    * @param breedIn the breed.
    * @param weightIn the weight.
    * @param wearAndTearIn the wearAndTear.
    * @throws NegativeValueException if value is negative.
    */
   public HouseBunny(String nameIn, String breedIn, double weightIn, 
      double wearAndTearIn) throws NegativeValueException {
      super(nameIn, breedIn, weightIn);
      wearAndTear = wearAndTearIn;
      if (wearAndTear < 0) {
         bunnyCount--;
         throw new NegativeValueException();
      }
   }
   
   // methods
   /**
    * @return wearAndTear the wearAndTear.
    */
   public double getWearAndTear() {
      return wearAndTear;
   }
   
   /**
    * @param wearAndTearIn the wearAndTear.
    */
   public void setWearAndTear(double wearAndTearIn) {
      wearAndTear = wearAndTearIn;
   }
   
   /**
    * @return the estimated monthly cost.
    */
   public double estimatedMonthlyCost() {
      return BASE_COST * weight * (1 + wearAndTear);
   }
   
   /**
    * @return result the String.
    */
   public String toString() {
      DecimalFormat fmt1 = new DecimalFormat("#0.0%;(#0.0%)");
      return super.toString() + " (includes " + fmt1.format(getWearAndTear())
         + " for wear and tear)";
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
         HouseBunny b = (HouseBunny) obj;
         return (super.equals(obj)
            && Math.abs(wearAndTear - b.getWearAndTear()) < .000001);
      }            
   }
   
   /**
    * @return zero.
    */ 
   public int hashCode() {
      return 0;
   }
}