import java.util.ArrayList;
import java.text.DecimalFormat;
/**
 * @author Cameron Mathis
 * @version 21 February 2017
 */
public class ConicalFrustumList {
 // instance variables
   private String listName;
   private ArrayList<ConicalFrustum> list;    
   
   // constructor
   /**
    * @param listNameIn the name of list.
    * @param listIn the list of ConicalFrustum objects.
    */
   public ConicalFrustumList(String listNameIn, ArrayList<ConicalFrustum> 
      listIn) {
      
      listName = listNameIn;
      list = listIn;
   }
   
   // methods
   /**
    * Get the name of list.
    *    
    * @return listName.
    */
   public String getName() {
   
      return listName;
   }
    
   /**
    * Get the number of ConicalFrustum objects..
    *    
    * @return number of ConicalFrustum objects.
    */
   public int numberOfConicalFrustums() {
   
      int num = list.size();
      return num;
   }
    
   /**
    * Get the total surface areas for all ConicalFrustum objects in the list.
    *    
    * @return the total surface areas for all ConicalFrustum objects in the
    * list.
    */
   public double totalSurfaceArea() {
   
      if (numberOfConicalFrustums() == 0) {
         return 0;
      }
      else {
         int index = 0;
         double totSA = 0;
         while (index < numberOfConicalFrustums()) {
            totSA += list.get(index).totalSurfaceArea();
            index++;
         }
         return totSA;
      }
   }

   /**
    * Get the total volumes for all ConicalFrustum objects in the list.
    *    
    * @return the total volumes for all ConicalFrustum objects in the list.
    */
   public double totalVolume() {
   
      if (numberOfConicalFrustums() == 0) {
         return 0;
      }
      else {
         int index = 0;
         double totVol = 0;
         while (index < numberOfConicalFrustums()) {
            totVol += list.get(index).volume();
            index++;
         }
         return totVol; 
      }     
   }
   
   /**
    * Get the average for the total surface area for all ConicalFrustum objects
    * in the list.
    *    
    * @return the average for the total surface area for all ConicalFrustum
    * objects in the list.
    */
   public double averageSurfaceArea() {
   
      if (numberOfConicalFrustums() == 0) {
         return 0;
      }
      else {
         double avgSA = totalSurfaceArea() / numberOfConicalFrustums();
         return avgSA;
      }
   }
   
   /**
    * Get the average volume for all ConicalFrustum objects in the list.
    *    
    * @return the average volume for all ConicalFrustum objects in the list.
    */
   public double averageVolume() {
   
      if (numberOfConicalFrustums() == 0) {
         return 0;
      }
      else {
         double avgVol = totalVolume() / numberOfConicalFrustums();
         return avgVol;
      }
   }

   /**
    * Coverts to string.
    *
    * @return string.
    */
   public String toString() {
   
      int index = 0;
      String info = listName + "\n";
      while (index < numberOfConicalFrustums()) {
         info += "\n" + list.get(index).toString() + "\n";
         index++;
      }
      return info;
   }

   /**
    * Get the summary info.
    *
    * @return summaryInfo.
    */
   public String summaryInfo() {
   
      DecimalFormat fmt = new DecimalFormat("#,##0.0##");
      String info = "----- Summary for " + listName + " -----"
         + "\nNumber of ConicalFrustum Objects: " + numberOfConicalFrustums()
         + "\nTotal Surface Area: " + fmt.format(totalSurfaceArea())
         + "\nTotal Volume: " + fmt.format(totalVolume())
         + "\nAverage Surface Area: " + fmt.format(averageSurfaceArea())
         + "\nAverage Volume: " + fmt.format(averageVolume());
      return info;
   }
}