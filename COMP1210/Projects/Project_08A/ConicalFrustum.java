import java.text.DecimalFormat;
/**
 * @author Cameron Mathis
 * @version 21 March 2018
 */
public class ConicalFrustum {
// class variables
   private static int count = 0;

 // instance variables
   private String label = "";
   private double radius1 = 0;
   private double radius2 = 0;
   private double height = 0;      
   
   // constructor
   /**
    * @param labelIn the label.
    * @param radius1In the radius1.
    * @param radius2In the radius2.
    * @param heightIn the height.
    */
   public ConicalFrustum(String labelIn, double radius1In, 
      double radius2In, double heightIn) {
      
      setLabel(labelIn);
      setRadius1(radius1In);
      setRadius2(radius2In);
      setHeight(heightIn);
      count++;
   }
   
   // class methods  
   /**
    * @return count the number of conical frustums.
    */ 
   public static int getCount() {
   
      return count;
   } 
   
   /**
    * Reset the number of conical frustums created.
    */ 
   public static void resetCount() {
   
      count = 0;
   }    
      
   // instance methods          
   /**
    * @return label the label.
    */
   public String getLabel() {
   
      return label;
   }
   
   /**
    * @param labelIn the label.
    * @return labelIn the label.
    */
   public boolean setLabel(String labelIn) {
   
      if (labelIn != null) {
         label = labelIn.trim();
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * @return radius1 the radius1.
    */
   public double getRadius1() {
   
      return radius1;
   }
   
   /**
    * @param radius1In the radius1.
    * @return radius1In the radius1.
    */
   public boolean setRadius1(double radius1In) {
   
      if (radius1In < 0) {
         return false;
      }
      else {
         radius1 = radius1In;
         return true;
      }
   }
   
   /**
    * @return radius2 the radius2.
    */
   public double getRadius2() {
   
      return radius2;
   }
   
   /**
    * @param radius2In the radius2.
    * @return radius2In the radius2.
    */
   public boolean setRadius2(double radius2In) {
   
      if (radius2In < 0) {
         return false;
      }
      else {
         radius2 = radius2In;
         return true;
      }
   }
   
   /**
    * @return height the height.
    */
   public double getHeight() {
   
      return height;
   }
   
   /**
    * @param heightIn the heightIn.
    * @return heightIn the heightIn.
    */
   public boolean setHeight(double heightIn) {
   
      if (heightIn < 0) {
         return false;
      }
      else {
         height = heightIn;
         return true;
      }
   }

   /**
    * @return volume the volume.
    */
   public double volume() {
   
      double vol = ((Math.PI * height) / 3) * (Math.pow(radius1, 2) 
         + Math.pow(radius2, 2) + (radius1 * radius2));
      return vol;
   }
   
   /**
    * @return slantHeight the slantHeight.
    */
   public double slantHeight() {
   
      double sHeight = Math.sqrt(Math.pow((radius1 - radius2), 2)
         + Math.pow(height, 2));
      return sHeight;
   }
   
   /**
    * @return lateralSurfaceArea the lateralSurfaceArea.
    */
   public double lateralSurfaceArea() {
   
      double latSA = Math.PI * (radius1 + radius2) * slantHeight();
      return latSA;
   }
   
   /**
    * @return totalSurfaceArea the totalSurfaceArea.
    */
   public double totalSurfaceArea() {
   
      double totSA = Math.PI * (Math.pow(radius1, 2) + Math.pow(radius2, 2)
         + (radius1 + radius2) * slantHeight());
      return totSA;
   } 
   
   /**
    * @return info the info.
    */
   public String toString() {
   
      DecimalFormat fmt = new DecimalFormat("#,##0.0##");
      String info = "ConicalFrustum \"" + label + "\" with radius1 = "
         + fmt.format(radius1) + ", radius2 = " + fmt.format(radius2)
         + ", and height = " + fmt.format(height) + " has:\n\tvolume = "
         + fmt.format(volume()) + " cubic units\n\tslant height = "
         + fmt.format(slantHeight()) + " units\n\tlateral surface area = "
         + fmt.format(lateralSurfaceArea()) + " units\n\ttotal surface area = "
         + fmt.format(totalSurfaceArea()) + " square units";  
      return info;
   }
   
   /**
    * @param obj the object entered.
    * @return boolean value based on if object entered matches conical frustum.
    */ 
   public boolean equals(Object obj) {
   
      if (!(obj instanceof ConicalFrustum)) { 
         return false;
      }        
      else {
         ConicalFrustum d = (ConicalFrustum) obj;
         return (label.equalsIgnoreCase(d.getLabel())
                     && Math.abs(radius1 - d.getRadius1()) < .000001
                     && Math.abs(radius2 - d.getRadius2()) < .000001
                     && Math.abs(height - d.getHeight()) < .000001);
      }            
   }
   
   /**
    * @return zero.
    */ 
   public int hashCode() {
   
      return 0;
   }
}