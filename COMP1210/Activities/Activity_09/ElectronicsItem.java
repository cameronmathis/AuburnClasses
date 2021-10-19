/**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class ElectronicsItem extends InventoryItem {
 // instance variables
   protected double weight; 
   
   // constants
   /**
     * shipping cost is 1.5.
     */
   public static final double SHIPPING_COST = 1.5;    
   
   // constructor
   /**
    * @param nameIn the name.
    * @param priceIn the price.
    * @param weightIn the weight.
    */
   public ElectronicsItem(String nameIn, double priceIn, double weightIn) {
      super(nameIn, priceIn);
      weight = weightIn;
   }
   
   /**
    * @return result the cost.
    */
   public double calculateCost() {
      return super.calculateCost() + (SHIPPING_COST * weight);
   }
}