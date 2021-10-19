/**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class InventoryItem {
 // class variables
   private static double taxRate = 0.0;

 // instance variables
   protected String name;
   protected double price;     
   
   // constructor
   /**
    * @param nameIn the name.
    * @param priceIn the price.
    */
   public InventoryItem(String nameIn, double priceIn) {
      name = nameIn;
      price = priceIn;
   }
   
   // methods
   /**
    * @return name the name.
    */
   public String getName() {
      return name;
   }
   
   /**
    * @return result the cost.
    */
   public double calculateCost() {
      double result = price * (1 + taxRate);
      return result;
   }
   
   /**
    * @param taxRateIn the taxRate.
    */
   public static void setTaxRate(double taxRateIn) {
      taxRate = taxRateIn;
   }
   
   /**
    * @return result the String.
    */
   public String toString() {
      String result = name + ": $" + calculateCost();
      return result;
   }
}
