/**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class PetBunny extends Bunny {
   // constants
   /**
     * shipping cost is 1.85.
     */
   public static final double BASE_COST = 1.85;    
   
   // constructor
   /**
    * @param nameIn the name.
    * @param breedIn the breed.
    * @param weightIn the weight.
    */
   public PetBunny(String nameIn, String breedIn, double weightIn) {
      super(nameIn, breedIn, weightIn);
   }
   
   // methods
   /**
    * @return the estimated monthly cost.
    */
   public double estimatedMonthlyCost() {
      return BASE_COST * weight;
   }
   
   /**
    * @return result the String.
    */
   public String toString() {
      return super.toString();
   }
}