import java.text.DecimalFormat;
/**
  * @author Cameron Mathis
  * @version 4 April 2018
  */
public abstract class Bunny implements Comparable<Bunny> {
 // class variables
   protected static int bunnyCount;

 // instance variables
   protected String name;
   protected String breed;
   protected double weight;     
   
   // constructor
   /**
    * @param nameIn the name.
    * @param breedIn the breed.
    * @param weightIn the weight.
    * @throws NegativeValueException if value is negative.
    */
   public Bunny(String nameIn, String breedIn, double weightIn) 
      throws NegativeValueException {
      name = nameIn;
      breed = breedIn;
      weight = weightIn;
      bunnyCount++;
      if (weight < 0) {
         bunnyCount--;
         throw new NegativeValueException();
      }
   }
   
   // methods
   /**
    * @return name the name.
    */
   public String getName() {
      return name;
   }
   
   /**
    * @param nameIn the name.
    */
   public void setName(String nameIn) {
      name = nameIn;
   }
   
   /**
    * @return breed the breed.
    */
   public String getBreed() {
      return breed;
   }
   
   /**
    * @param breedIn the breed.
    */
   public void setBreed(String breedIn) {
      breed = breedIn;
   }
   
   /**
    * @return weight the weight.
    */
   public double getWeight() {
      return weight;
   }
   
   /**
    * @param weightIn the weight.
    */
   public void setWeight(double weightIn) {
      weight = weightIn;
   }
   
   /**
    * @return bunnyCount the bunnyCount.
    */
   public static int getBunnyCount() {
      return bunnyCount;
   }
   
   /**
    * resets bunnyCount.
    */
   public static void resetBunnyCount() {
      bunnyCount = 0;
   }
   
   /**
    * @return the estimated monthly cost.
    */
   public abstract double estimatedMonthlyCost();
   
   /**
    * @return result the String.
    */
   public String toString() {
      DecimalFormat fmt1 = new DecimalFormat("$#,##0.00;($#,##0.00)");
      String result = name  + " (" + this.getClass().getName() + ")   Breed: "
         + getBreed() + "   Weight: " + getWeight() + "\nEstimated Monthly"
         + " Cost: " + fmt1.format(estimatedMonthlyCost());
      return result;
   }
   
   /**
    * @param obj the object entered.
    * @return boolean value based on if object entered matches bunny.
    */ 
   public boolean equals(Object obj) {
   
      if (!(obj instanceof Bunny)) { 
         return false;
      }        
      else {
         Bunny b = (Bunny) obj;
         return (name.equalsIgnoreCase(b.getName())
                     && breed.equalsIgnoreCase(b.getBreed())
                     && Math.abs(weight - b.getWeight()) < .000001);
      }            
   }
   
   /**
    * @return zero.
    */ 
   public int hashCode() {
      return 0;
   }
   
   /**
    * @param obj the Bunny object entered.
    * @return result an int.
    */
   public int compareTo(Bunny obj) {
      int result = this.getName().compareTo(obj.getName());
      return result;
   }
}
