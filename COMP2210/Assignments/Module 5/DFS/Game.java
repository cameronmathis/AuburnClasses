import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Provides a word search game. 
 *
 * @author Cameron Mathis (clm0081@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 11/8/2018
 */
public class Game implements WordSearchGame {

   private TreeSet<String> lexicon;
   private boolean isLexiconLoaded;
   private int length;
   private String[][] gameBoard;
   private Boolean[][] isVisited;
   private int minLength;
   private SortedSet<String> validWords;
   private List<Integer> path;
   private List<Integer> masterPath;
   


   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
   
      if (fileName == null) {
         throw new IllegalArgumentException("Error reading from file.");
      }
      
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new FileReader(fileName));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error reading from file.");
      }
      
      isLexiconLoaded = true;
   }
   
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
   
      if (letterArray == null) {
         throw new IllegalArgumentException("Error when setting board.");
      }
      
      double dimension = Math.sqrt(letterArray.length);
   
      if (dimension % 1 > 0) {
         throw new IllegalArgumentException("Error when setting board.");
      }
      else {
         length = (int) dimension;
         gameBoard = new String[length][length];
         isVisited = new Boolean[length][length];
         int count = 0;
         for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
               isVisited[y][x] = false;
               gameBoard[y][x] = letterArray[count].toLowerCase();
               count++;
            }
         }
      }
   }
   
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
   
      String result = "";
      
      for (String[] s1: gameBoard) {
         for (String s2: s1) {
         
            result = result + s2;
         }
      }
   
      return result;
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
   
      minLength = minimumWordLength;
      validWords = new TreeSet<String>();
      
      if (!isLexiconLoaded) {
         throw new IllegalStateException("Lexicon is not loaded.");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Number is invalid.");
      }
      
      for (int y = 0; y < length; y++) {
         for (int x = 0; x < length; x++) {
         
            findWord(gameBoard[y][x], y, x);
         }
      }
      
      return validWords;
   }
   
   public void findWord(String word, int y, int x) {
   
      if (!isValidPrefix(word)) {
         return;
      }
   
      isVisited[y][x] = true;
   
      if (isValidWord(word) && word.length() >= minLength) {
         validWords.add(word.toUpperCase());
      }
   
      for (int y2 = -1; y2 <= 1; y2++) {
         for (int x2 = -1; x2 <= 1; x2++) {
         
            if ((y + y2) <= (length - 1)
               && (x + x2) <= (length - 1)
               && (y + y2) >= 0 && (x + x2) >= 0 && !isVisited[y + y2][x + x2]) {
               
               isVisited[y + y2][x + x2] = true;
               findWord(word + gameBoard[y + y2][x + x2], y + y2, x + x2);
               isVisited[y + y2][x + x2] = false;
            }
         }
      }
      
      isVisited[y][x] = false;
   }
   
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
   
      if (!isLexiconLoaded) {
         throw new IllegalStateException("Lexicon is not loaded.");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Number is invalid.");
      }
   
      int score = 0;
   
      for (String word: words) {
         int size = word.length();
         score += 1 + (size - minimumWordLength);
      }
   
      return score;
   }
   
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
   
      if (!isLexiconLoaded) {
         throw new IllegalStateException("Lexicon is not loaded.");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Word is null.");
      }
   
      return lexicon.contains(wordToCheck.toLowerCase());
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
   
      if (!isLexiconLoaded) {
         throw new IllegalStateException("Lexicon is not loaded.");
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("Word is null.");
      }
      
      String s = lexicon.ceiling(prefixToCheck);
      if (s == null) {
         return false;
      }
      
      return s.startsWith(prefixToCheck);
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
   
      if (!isLexiconLoaded) {
         throw new IllegalStateException("Lexicon is not loaded.");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Word is null.");
      }
      
      path = new ArrayList<Integer>();
      masterPath = new ArrayList<Integer>();
   
      for (int y = 0; y < (int) length; y++) {
         for (int x = 0; x < length; x++) {
            if (Character.toUpperCase(gameBoard[y][x].charAt(0))
               == Character.toUpperCase(wordToCheck.charAt(0))) {
               int value = x + (y * length);
               path.add(value);
               findNext(wordToCheck, gameBoard[y][x], y, x);
               if (!masterPath.isEmpty()) {
                  return masterPath;
               }
               path = new ArrayList<Integer>();
               masterPath = new ArrayList<Integer>();
            }
         }
      }
      
      return path;
   }
   
   public void findNext(String wordToCheck, String current, int y, int x) {
   
      isVisited[y][x] = true;
      
      if (!(startWith(wordToCheck, current))) {
         return;
      }
      if (!(isValidPrefix(current))) {
         return;
      }
      if (current.toUpperCase().equals(wordToCheck.toUpperCase())) {
         masterPath = new ArrayList<Integer>(path);
         return;
      }
      
      for (int y2 = -1; y2 <= 1; y2++) {
         for (int x2 = -1; x2 <= 1; x2++) {
            if (current.equals(wordToCheck)) {
               return;
            }
            if ((y + y2) <= (length - 1)
               && (x + x2) <= (length - 1)
               && (y + y2) >= 0 && (x + x2) >= 0 && !isVisited[y + y2][x + x2]) {
               
               isVisited[y + y2][x + x2] = true;
               int value = (y + y2) * length + x + x2;
               path.add(value);
               findNext(wordToCheck, current
                  + gameBoard[y + y2][x + x2], y + y2, x + x2);
               isVisited[y + y2][x + x2] = false;
               path.remove(path.size() - 1);
            }
         }
      }
      isVisited[y][x] = false;
      return;
   }
   
   public boolean startWith(String wordToCheck, String current) {
   
      if (!isLexiconLoaded) {
         throw new IllegalStateException("Lexicon is not loaded.");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Word is null.");
      }
      if (current == null) {
         throw new IllegalArgumentException("Word is null.");
      }
      
      int i = 0;
      for (char ch: current.toCharArray()) {
         if (Character.toLowerCase(ch) != Character.toLowerCase((wordToCheck.charAt(i)))) {
            return false;
         }
         i++;
      }
      return true;
   }
}
