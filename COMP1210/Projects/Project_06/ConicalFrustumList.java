import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;
import java.io.FileNotFoundException; 
/**
 * @author Cameron Mathis
 * @version 28 February 2017
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
   
   /**
    * Get the array list.
    *
    * @return list.
    */
   public ArrayList<ConicalFrustum> getList() {
   
      return list;
   }
   
   /**
    * Get the array list.
    *
    * @throws FileNotFoundException if file not found.
    * @param fileNameIn the file name.
    * @return cl.
    */
   public ConicalFrustumList readFile(String fileNameIn) 
      throws FileNotFoundException {
   
      Scanner scanFile = new Scanner(new File(fileNameIn));
      ArrayList<ConicalFrustum> newList = new ArrayList<ConicalFrustum>();
      String listName1 = "";
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
         newList.add(new ConicalFrustum(label, radius1, radius2, height));
      }
      scanFile.close();
      
      ConicalFrustumList cl = new ConicalFrustumList(listName1, newList);
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
   
      list.add(new ConicalFrustum(label, radius1, radius2, height));
   }
   
   /**
    * Finds a conical frustum.
    *
    * @param labelIn the label.
    * @return cf if found and null if not.
    */
   public ConicalFrustum findConicalFrustum(String labelIn) {
    
      ConicalFrustum result = null;
      int index = -1;
      for (ConicalFrustum cf1 : list) {
         if (cf1.getLabel().equalsIgnoreCase(labelIn)) {
            index = list.indexOf(cf1);
            break;
         }
      }
      if (index >= 0) {
         result = list.get(index);
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
   
      ConicalFrustum cf = null;
      cf = findConicalFrustum(labelIn);
      list.remove(cf);
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
      
      int index = list.indexOf(findConicalFrustum(label));
      ConicalFrustum cf = deleteConicalFrustum(label);
      if (cf != null) {
         list.add(index, new ConicalFrustum(label, radius1, radius2, height));
         return true;
      }
      else {
         return false;
      }
   }
}