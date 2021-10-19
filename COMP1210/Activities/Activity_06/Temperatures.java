import java.util.ArrayList;
/**
 * @author Cameron Mathis
 * @version 26 February 2018
 */
public class Temperatures {
 // instance variable
   private ArrayList<Integer> temperatures;
   
   // constructor
   /**
    * @param temperaturesIn the temperatures.
    */
   public Temperatures(ArrayList<Integer> temperaturesIn) {
   
      temperatures = temperaturesIn;
   }
   // methods
   /**
    * @return low the low temp.
    */
   public int getLowTemp() {
   
      if (temperatures.isEmpty()) {
         return 0;
      }
      int low = temperatures.get(0);
      for (int i = 0; i < temperatures.size(); i++) {
         if (temperatures.get(i) < low) {
            low = temperatures.get(i);
         }
      }
      return low;
   }

   /**
    * @return high the high temp.
    */
   public int getHighTemp() {
   
      if (temperatures.isEmpty()) {
         return 0;
      }
      int high = temperatures.get(0);
      for (Integer temp : temperatures) {
         if (temp > high) {
            high = temp;
         }
      }
      return high;
   }

   /**
    * @param lowIn the lower minimum.
    * @return the lower minimum.
    */
   public int lowerMinimum(int lowIn) {
   
      return lowIn < getLowTemp() ? lowIn : getLowTemp();
   }

   /**
    * @param highIn the higher maximum.
    * @return the higher maximum.
    */
   public int higherMaximum(int highIn) {
   
      return highIn > getHighTemp() ? highIn : getHighTemp();
   }

   /**
    * @return temps.
    */
   public String toString() {
   
      return "\tTemperatures: " + temperatures
           + "\n\tLow: " + getLowTemp()
           + "\n\tHigh: " + getHighTemp();
   }
}
