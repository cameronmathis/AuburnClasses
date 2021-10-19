/** 
 * Displays the user's info.
 *
 * @author Cameron Mathis
 * @version 5 February 2017
 */
public class UserInfo {
   // instance variables
   private String firstName;
   private String lastName;
   private String location;
   private int age;
   private int status;
   private static final int OFFLINE = 0, ONLINE = 1;
   
  // constructor
   /**
    * Displays the user's info.
    *     
    * @param firstNameIn the first name.
    * @param lastNameIn the last name.
    */
   public UserInfo(String firstNameIn, String lastNameIn) {
         
      firstName = firstNameIn;
      lastName = lastNameIn;
      location = "Not specified";
      age = 0;
      status = OFFLINE;
   }
   
  //methods
   /**
    * Displays string.
    *     
    * @return output.
    */
   public String toString() {
      String output = "Name: " + firstName + " "
         + lastName + "\n";
      output += "Location: " + location + "\n";
      output += "Age: " + age + "\n";
      output += "Status: ";
      if (status == OFFLINE) {
         output += "Offline";
      }
      else {
         output += "Online";
      }
      
      return output;
   }
   
   /**
    * Sets the location.
    *     
    * @param locationIn the location.
    */
   public void setLocation(String locationIn) {
      location = locationIn;
   }
   
   /**
    * Sets the age.
    *     
    * @param ageIn the age.
    * @return age.
    */
   public boolean setAge(int ageIn) {
      boolean isSet = false;
      if (ageIn > 0) {
         age = ageIn;
         isSet = true;
      }
      return isSet;
   }
   
   /**
    * Gets the age.
    * 
    * @return age.
    */
   public int getAge() {
      return age;
   }
   
   /**
    * Gets the location.
    *
    * @return location.
    */
   public String getLocation() {
      return location;
   }
   
   /**
    * Log off. 
    */
   public void logOff() {
      status = OFFLINE;
   }
   
   /**
    * Log on.
    */
   public void logOn() {
      status = ONLINE;
   }
}
