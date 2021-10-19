import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-10-22
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   
   TreeSet<String> lexicon;

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
   
   /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      int i = 0;
      int result = 0; 
      
      while (i < str1.length()) { 
         if (str1.charAt(i) != str2.charAt(i)) {
            result++; 
         }
         i++; 
      } 
      
      return result; 
   }
   
   
   /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      List<String> result = new ArrayList<String>();
      
      if ((start.length() != end.length()) || !isWord(start) || !isWord(end)) {
         return result;
      }
      else if (start.equals(end)) {
         result.add(start);
         return result;
      } 
     
      Deque<Node> queue = new ArrayDeque<>();
      TreeSet<String> visited = new TreeSet<>();
    
      visited.add(start);
      queue.addLast(new Node(start, null));
      
      while (!queue.isEmpty()) {
         Node n = queue.removeFirst();
         String str = n.str;
          
         for (String neighbor : getNeighbors(str)) {
            if (!visited.contains(neighbor)) {
               visited.add(neighbor);
               queue.addLast(new Node(neighbor, n));
            }
            if (neighbor.equals(end)) {
               Node m = queue.removeLast();
               
               while (m != null) {
                  result.add(0, m.str);
                  m = m.prev;
               }
               return result;
            }
         }
      }    
            
      result = new ArrayList<String>();
      return result;
   }
   
      
   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      List<String> result = new ArrayList<String>();
      TreeSet<String> set = new TreeSet<String>();
       
      if (word == null) {
         return result;
      }
      
      for (int i = 0; i < word.length(); i++) {
         char[] wordChar = word.toCharArray();
         
         for (char y = 'a'; y <= 'z'; y++) {
            wordChar[i] = y;
            String word1 = new String(wordChar);
            
            if ((isWord(word1)) && (!result.contains(word1)) && (!word1.equals(word))) {
               result.add(word1);
            }
         }
      }
      
      return result;
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      int result = lexicon.size();
      
      return result;
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      if (lexicon.contains(str)) {
         return true;
      }
      
      return false;
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      int result = 0;
      
      if ((sequence == null) || (sequence.isEmpty())) {
         return false;
      }
      
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (isWord(sequence.get(i)) == false || isWord(sequence.get(i + 1)) == false 
            || (getHammingDistance(sequence.get(i), sequence.get(i + 1)) != 1)) {
            result++;  
         }
      }
      
      return (result == 0);
   }
   
   
   private class Node {
      String str;
      Node prev;
   
      public Node(String s, Node p) {
         str = s;
         prev = p;
      }
   }
}

