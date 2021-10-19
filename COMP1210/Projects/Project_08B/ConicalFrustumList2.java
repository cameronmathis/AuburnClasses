import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;
import java.io.FileNotFoundException; 
/**
 * @author Cameron Mathis
 * @version 28 March 2018
 */
public class ConicalFrustumList2 {
 // instance variables
   private String listName;
   private ConicalFrustum[] list; 
   private int numberOfConicalFrustums;   
   
   // constructor
   /**
    * @param listNameIn the name of list.
    * @param listIn the array of ConicalFrustum objects.
    * @param numberOfConicalFrustumsIn the number of ConicalFrustum objects
    * in the list.
    */
   public ConicalFrustumList2(String listNameIn, ConicalFrustum[] listIn,
      int numberOfConicalFrustumsIn) {
      
      listName = listNameIn;
      list = listIn;
      numberOfConicalFrustums = numberOfConicalFrustumsIn;
   }
   
   // methods
   /**
    * Get the name of array.
    *    
    * @return listName.
    */
   public String getName() {
   
      return listName;
   }
    
   /**
    * Get the number of ConicalFrustum objects.
    *    
    * @return number of ConicalFrustum objects.
    */
   public int numberOfConicalFrustums() {
   
      return numberOfConicalFrustums;
   }
    
   /**
    * Get the total surface areas for all ConicalFrustum objects in the array.
    *    
    * @return the total surface areas for all ConicalFrustum objects in the
    * array.
    */
   public double totalSurfaceArea() {
   
      if (numberOfConicalFrustums() == 0) {
         return 0;
      }
      else {
         int index = 0;
         double totSA = 0;
         while (index < numberOfConicalFrustums()) {
            totSA += list[index].totalSurfaceArea();
            index++;
         }
         return totSA;
      }
   }

   /**
    * Get the total volumes for all ConicalFrustum objects in the array.
    *    
    * @return the total volumes for all ConicalFrustum objects in the array.
    */
   public double totalVolume() {
   
      if (numberOfConicalFrustums() == 0) {
         return 0;
      }
      else {
         int index = 0;
         double totVol = 0;
         while (index < numberOfConicalFrustums()) {
            totVol += list[index].volume();
            index++;
         }
         return totVol; 
      }     
   }
   
   /**
    * Get the average for the total surface area for all ConicalFrustum objects
    * in the array.
    *    
    * @return the average for the total surface area for all ConicalFrustum
    * objects in the array.
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
    * Get the average volume for all ConicalFrustum objects in the array.
    *    
    * @return the average volume for all ConicalFrustum objects in the array.
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
         info += "\n" + list[index] + "\n";
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
   
   /**
    * Get the array.
    *
    * @return list.
    */
   public ConicalFrustum[] getList() {
   
      return list;
   }
   
   /**
    * Get the array list.
    *
    * @throws FileNotFoundException if file not found.
    * @param fileNameIn the file name.
    * @return cl.
    */
   public ConicalFrustumList2 readFile(String fileNameIn) 
      throws FileNotFoundException {
   
      Scanner scanFile = new Scanner(new File(fileNameIn));
      ConicalFrustum[] newList = new ConicalFrustum[100];
      String listName1 = "";
      int numCF = 0;
      String label = "";
      double radius1 = 0;
      double radius2 = 0;
      double height = 0;
   
      listName1 = scanFile.nextLine();
      
      while (scanFile.hasNext()) {   
         label = scanFile.nextLine();
         radius1 = Double.parseDouble(scanFile.nextLine());
         radius2 = Double.parseDouble(scanFile.nextLine());
         height = Double.parseDouble(scanFile.nextLine());
         ConicalFrustum cF = new ConicalFrustum(label, radius1, radius2,
            height);
         newList[numCF] = cF;
         numCF++;
      }
      scanFile.close();
      
      ConicalFrustumList2 cl = new ConicalFrustumList2(listName1, newList,
         numCF);
      return cl;
   }

   /**
    * Add a conical frustum.
    *
    * @param label the label.
    * @param radius1 the first radius.
    * @param radius2 the second radius.
    * @param height the height
    */
   public void addConicalFrustum(String label, double radius1, double radius2,
      double height) {
   
      ConicalFrustum cF = new ConicalFrustum(label, radius1, radius2, height);
      list[numberOfConicalFrustums] = cF;
      numberOfConicalFrustums++;
   }
   
   /**
    * Finds a conical frustum.
    *
    * @param labelIn the label.
    * @return cf if found and null if not.
    */
   public ConicalFrustum findConicalFrustum(String labelIn) {
    
      ConicalFrustum result = null;
      for (int i = 0; i < numberOfConicalFrustums; i++) {
         if ((list[i].getLabel()).equalsIgnoreCase(labelIn)) {
            result = list[i];
            break;
         }
      }
      
      return result;
   }
   
   /**
    * Deletes a conical frustum.
    *
    * @param labelIn the label.
    * @return cf if deleted and null if not.
    */
   public ConicalFrustum deleteConicalFrustum(String labelIn) {
   
      ConicalFrustum cf = findConicalFrustum(labelIn);
      for (int i = 0; i < numberOfConicalFrustums; i++) {
         if ((list[i].getLabel()).equalsIgnoreCase(labelIn)) {
            for (int j = i; j < numberOfConicalFrustums - 1; j++) {
               list[j] = list[j + 1];
            }
            list[numberOfConicalFrustums - 1] = null;
            numberOfConicalFrustums--;
            break;
         }
      }
      
      return cf;
   }
   
   /**
    * Edits a conical frustum.
    *
    * @param label the label.
    * @param radius1 the first radius.
    * @param radius2 the second radius.
    * @param height the height
    * @return true if edited and false if not.
    */
   public boolean editConicalFrustum(String label, double radius1, 
      double radius2, double height) {
      
      boolean result = false;
      for (int i = 0; i < numberOfConicalFrustums; i++) {
         if ((list[i].getLabel()).equalsIgnoreCase(label)) {
            ConicalFrustum cF = new ConicalFrustum(label, radius1, radius2,
               height);
            list[i] = cF;
            result = true;
            break;
         }
         else {
            result = false;
         }
      }
      
      return result;
   }
   
   /**
    * Find tha conical frustum with the least height.
    *
    * @return cf if found and null if not.
    */
   public ConicalFrustum findConicalFrustumWithLeastHeight() {
    
      ConicalFrustum result = null;
      if (numberOfConicalFrustums > 0) {
         double height = list[1].getHeight();
         for (int i = 0; i < numberOfConicalFrustums; i++) {
            if ((list[i].getHeight()) < height) {
               height = list[i].getHeight();
               result = list[i];
            }
         }
      }
      
      return result;
   }
   
   /**
    * Find tha conical frustum with the greatest height.
    *
    * @return cf if found and null if not.
    */
   public ConicalFrustum findConicalFrustumWithGreatestHeight() {
    
      ConicalFrustum result = null;
      if (numberOfConicalFrustums > 0) {
         double height = list[1].getHeight();
         for (int i = 0; i < numberOfConicalFrustums; i++) {
            if ((list[i].getHeight()) > height) {
               height = list[i].getHeight();
               result = list[i];
            }
         }
      }
      
      return result;
   }
   
   /**
    * Find tha conical frustum with the least volume.
    *
    * @return cf if found and null if not.
    */
   public ConicalFrustum findConicalFrustumWithLeastVolume() {
    
      ConicalFrustum result = null;
      if (numberOfConicalFrustums > 0) {
         double volume = list[1].volume();
         for (int i = 0; i < numberOfConicalFrustums; i++) {
            if ((list[i].volume()) < volume) {
               volume = list[i].volume();
               result = list[i];
            }
         }
      }
      
      return result;
   }
   
   /**
    * Find tha conical frustum with the greatest volume.
    *
    * @return cf if found and null if not.
    */
   public ConicalFrustum findConicalFrustumWithGreatestVolume() {
    
      ConicalFrustum result = null;
      if (numberOfConicalFrustums > 0) {
         double volume = list[1].volume();
         for (int i = 0; i < numberOfConicalFrustums; i++) {
            if ((list[i].volume()) > volume) {
               volume = list[i].volume();
               result = list[i];
            }
         }
      }
      
      return result;
   }
}