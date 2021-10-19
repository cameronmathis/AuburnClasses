/**
 * Provides a factory method for creating word search games. 
 *
 * @author Cameron Mathis (clm0081@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 11/8/2018
 */
public class WordSearchGameFactory {

   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    */
   public static WordSearchGame createGame() {
      // You must return an instance of your solution class here instead of null.
      Game g = new Game();
      return g;
   }
}