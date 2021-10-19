import java.text.DecimalFormat;
/**
 * @author Cameron Mathis
 * @version 07 February 2017
 */
public class ConicalFrustum {
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
   }
   
   // methods            
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
    * @return slantHeight the slantHeight.
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
}