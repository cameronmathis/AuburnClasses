/**
 * @author Cameron Mathis
 * @version 26 March 2018
 */
public class Customer implements Comparable<Customer> {

// instance variables
   private String name;
   private String location;
   private double balance;     
   
   // constructor
   /**
    * @param nameIn the customer's name.
    */
   public Customer(String nameIn) {
   
      name = nameIn; // sets name to parameter input
      location = ""; // sets location to empty string
      balance = 0; // sets balance to 0
   }
   
   // instance methods
   /**
    * Sets location variable. 
    *
    * @param locationIn the customer's location.
    */
   public void setLocation(String locationIn) {
   
      location = locationIn;
   }
   
   /**
    * Sets location variable. 
    *
    * @param city the customer's city.
    * @param state the customer's state.
    */
   public void setLocation(String city, String state) {
   
      location = city + ", " + state;
   }
   
   /**
    * Add amount to balance. 
    *
    * @param amount the amount added.
    */
   public void changeBalance(double amount) {
      
      balance += amount;
   }
      
   /**
    * Returns variable for location. 
    *
    * @return the customer's location.
    */
   public String getLocation() {
      
      return location;
   }
      
   /**
    * Returns variable for balance. 
    *
    * @return the customer's balance.
    */
   public double getBalance() {
      
      return balance;
   }
   
   /**
    * @return info the info.
    */
   public String toString() {
   
      String info = name + "\n" + location + "\n$" + balance;
      return info;
   }
   
   /**
    * Add Compares customer objects. 
    *
    * @param obj the customer object entered.
    * @return an int.
    */
   public int compareTo(Customer obj) {
   
      if (this.getBalance() < obj.getBalance()) {
         return -1; // should return a negative number
      }
      else if (this.getBalance() > obj.getBalance()) {
         return 1; // should retrn a positive number
      }
      else {
         return 0; // consider them equal and return 0
      }
   }
}