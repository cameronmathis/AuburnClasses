
/**
 * ExampleGameClient.java
 * A sample client for the assignment handout.
 *
 * @author      Dean Hendrix (dh@auburn.edu)
 * @version     2018-03-22
 *
 */
public class ExampleGameClient {

   /** Drives execution. */
   public static void main(String[] args) {
      WordSearchGame game = WordSearchGameFactory.createGame();
      game.loadLexicon("wordfiles/words.txt");
      // game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H", 
         //                         "N", "B", "O", "Q", "T", "T", "Y"});
      game.setBoard(new String[]{"O","Y","D","D","T","P","N","R","A","H","E","L","C","S","B","P","S","U","B","G","U","P","Y","H","R","R","X","R","E","F","H","D","H","T","K","X","K","O","Z","F","W","Y","H","Y","T","C","H","M","V","P","R","T","A","K","N","E","S","I","B","T","M","V","Y","Q","E","U","O","E","F","A","K","J","C","W","I","K","I","U","K","T","P","O","F","E","G","Z","T","X","O","Z","T","H","K","B","M","G","D","P","P","P","G","U","E","S","C","J","C","B","Q","F","T","R","I","P","N","I","E","W","P","K","H","K","G","B","B","L","Y","J","P","J","E","O","N","Q","V","N","B","S","H","R","N","Z","R","G","A","E","W","P","L","L","Z","R","G","I","E","T","U","N","R","L","I","K","T","J","K","J","F","C","I","T","M","R","D","T","R","E","G","L","J","G","I","K","H","L","C","V","P","P","D","S","Q","E","W","O","C","R","L","V","L","P","T","A","T","N","O","R","M","W","K","O","D","O","U","O","V","F","M","H","V","V","S","I","X","Z","L","O","T","Z","L","B","R","G","F","Q","P","A","Y","P","D","L","B","K","S","N","C","H","O","P","Y","K","H","C","R","R","I","C","S","B","J","X","R","F","I","Y","R","H","B","Z","I","P","C","K","I","N","O","E","C","C","U","C","P","I","J","R","E","Y","E","Z","U","R","R","M","F","S","M","R","N","J","I","B","T","Q","O","C","V","R","O","T","X","H","C","R","W","S","A","V","T","N","U","I","O","W","X","C","O","R","X","Q","A","S","A","S","S","E","M","B","L","Y","O","Z","F","P","L","S","C","I","T","L","U","M","O","N","I","T","O","R","J","W","I","N","L","L","L","E","L","J","R","R","E","M","M","O","B","D","X","I","J","D","S","R","L","C","H","S","H","Y","U","L","P","M","O","U","S","E","C","B","I","I","U","I",});
      //System.out.println(game.getBoard());
      System.out.print("LENT is on the board at the following positions: ");
      System.out.println(game.isOnBoard("LENT"));
      System.out.print("POPE is not on the board: ");
      System.out.println(game.isOnBoard("POPE"));
      System.out.println("All words of length 6 or more: ");
      System.out.println(game.getAllValidWords(10));
   }
}

/*

RUNTIME OUTPUT:

LENT is on the board at the following positions: [5, 6, 9, 13]
POPE is not on the board: []
All words of length 6 or more:
[ALEPOT, BENTHAL, PELEAN, TOECAP]

 */
