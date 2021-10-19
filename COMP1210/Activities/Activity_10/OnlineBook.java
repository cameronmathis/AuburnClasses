/**
  * @author Cameron Mathis
  * @version 2 April 2018
  */
public class OnlineBook extends OnlineTextItem {
 // instance variables
   protected String author;
   
   // constructor
   /**
    * @param nameIn the name.
    * @param priceIn the price.
    */
   public OnlineBook(String nameIn, double priceIn) {
      super(nameIn, priceIn);
      author = "Author Not Listed";
   }
   
   /**
    * @return result the String.
    */
   public String toString() {
      String result = name + " - " + author + ": $" + calculateCost();
      return result;
   }
   
   /**
    * @param authorIn the author.
    */
   public void setAuthor(String authorIn) {
      author = authorIn;
   }
}