/**
  * @author Cameron Mathis
  * @version 9 April 2018
  */
public class ItemsList {  
   // instance variables
   private InventoryItem[] inventory; 
   private int count;
   
   // constructor
   /**
    */
   public ItemsList() {
   
      inventory = new InventoryItem[20];
      count = 0;
   }
   
   // methods
   /**
    * @param itemIn the inventory item.
    */
   public void addItem(InventoryItem itemIn) {
   
      inventory[count] = itemIn;
      count++;
   }

   /**
    * @param electronicsSurcharge the electronicsSurcharge.
    * @return total the total;
    */
   public double calculateTotal(double electronicsSurcharge) {
   
      double total = 0;
      
      for (int i = 0; i < count; i++) {
         if (inventory[i] instanceof ElectronicsItem) {
            total += inventory[i].calculateCost() + electronicsSurcharge;
         }
         else {
            total += inventory[i].calculateCost();
         }
      }
      
      return total;
   }
   
   /**
    * @return output the items list.
    */
   public String toString() {
      
      String output = "All inventory:\n\n";
      
      for (int i = 0; i < count; i++) {
         output += inventory[i] + "\n";
      }
      
      return output;
   }
}
