/**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class OnlineArticle extends OnlineTextItem {
 // instance variables
   private int wordCount;
   
   // constructor
   /**
    * @param nameIn the name.
    * @param priceIn the price.
    */
   public OnlineArticle(String nameIn, double priceIn) {
      super(nameIn, priceIn);
      wordCount = 0;
   }
   
   /**
    * @param wordCountIn the wordCount.
    */
   public void setWordCount(int wordCountIn) {
      wordCount = wordCountIn;
   }
}