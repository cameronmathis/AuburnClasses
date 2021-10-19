import javax.swing.JOptionPane;  
 /**
  * @author Cameron Mathis
  * @version 16 April 2018
  */
public class DivisionDriver  {

  /**
   * @param args the file of bunnies.
   */
   public static void main(String[] args) {
   
      String numInput = JOptionPane.showInputDialog("Enter the numerator:");
      String denomInput = JOptionPane.showInputDialog("Enter the denominator:");
      
      try {
         int num = Integer.parseInt(numInput);
         int denom = Integer.parseInt(denomInput);
         
         String result = "Integer division: \n" 
            + Division.intDivide(num, denom)
            + "\n\nFloating point division: \n" 
            + Division.decimalDivide(num, denom);
         
         JOptionPane.showMessageDialog(null, result, "Result", 
            JOptionPane.PLAIN_MESSAGE);
      }
      
      catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, "Invalid input: "
            + "enter numerical integer values only.", "Error", 
            JOptionPane.ERROR_MESSAGE);
      }
      
      catch (IllegalArgumentException e) {
         JOptionPane.showMessageDialog(null, e, "Error", 
            JOptionPane.ERROR_MESSAGE);
      }
   }
}
