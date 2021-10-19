/**
* @author Cameron Mathis
* @version 2 April 2018
*/
public class BunniesPart1 {

    /**
     * @param args Command line arguments (not used).
     * @throws FileNotFoundException if file not found.
     */
   public static void main(String[] args) {
      
      PetBunny pb1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      pb1.toString();
      
      HouseBunny hb1 = new HouseBunny("Spot", "Really Mixed", 5.8, 0.15);
      hb1.toString();
      
      JumpingBunny jb1 = new JumpingBunny("Speedy", "English", 6.3, 25.0);
      jb1.toString();
      
      ShowBunny sb1 = new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0);
      sb1.toString();
   }
}
