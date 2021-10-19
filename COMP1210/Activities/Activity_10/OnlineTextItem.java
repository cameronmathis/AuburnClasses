/**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public abstract class OnlineTextItem extends InventoryItem {
   // constructor
   /**
    * @param nameIn the name.
    * @param priceIn the price.
    */
   public OnlineTextItem(String nameIn, double priceIn) {
      super(nameIn, priceIn);
   }
   
   /**
    * @return price the cost.
    */
   public double calculateCost() {
      return price;
   }
}