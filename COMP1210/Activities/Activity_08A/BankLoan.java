/**
 * @author Cameron Mathis
 * @version 19 March 2018
 */
public class BankLoan {
	// constant fields
   private static final int MAX_LOAN_AMOUNT = 100000;
   
   // class variables
   private static int loansCreated = 0;

   // instance variables (can be used within the class)
   private String customerName;
   private double balance, interestRate;

   // constructor  
   /**
    * @param customerNameIn the customer's name.
    * @param interestRateIn the interest rate.
    */
   public BankLoan(String customerNameIn, double interestRateIn) { 
      customerName = customerNameIn;
      interestRate = interestRateIn;
      balance = 0;
      loansCreated++;
   }

    // methods 
    /**
    * @param amount the loan amount.
    * @return amount a boolean value.
    */
   public static boolean isAmountValid(double amount) {
         
      return amount >= 0;
   }
   
   /**
    * @param loan the ban loan.
    * @return boolean value depending on the loans balance.
    */
   public static boolean isInDebt(BankLoan loan) {
   
      if (loan.getBalance() > 0) { 
         return true;
      }
      return false;
   }
     
   /**
    * @return loansCreated the number of loans created.
    */ 
   public static int getLoansCreated() {
      return loansCreated;
   } 
     
   /**
    * Reset the number of loans created.
    */ 
   public static void resetLoansCreated() {
      loansCreated = 0;
   } 
         
   /**
    * @param amount the amount borrowed.
    * @return wasLoanMade a boolean value.
    */
   public boolean borrowFromBank(double amount) {
      
      boolean wasLoanMade = false;
      
      if (balance + amount < MAX_LOAN_AMOUNT) {
         wasLoanMade = true;
         balance += amount;
      }
   
      return wasLoanMade;
   }
      
   /**
    * @param amountPaid the amount paid.
    * @return the overcharge.
    */
   public double payBank(double amountPaid) {
      double newBalance = balance - amountPaid;
      if (newBalance < 0) {
         balance = 0;
         // paid too much, return the overcharge
         return Math.abs(newBalance);
      }
      else {
         balance = newBalance;
         return 0;
      }
   }
   
   /**
    * @return balance the balance.
    */
   public double getBalance() {
      return balance;
   }
   
   /**
    * @param interestRateIn the interest rate.
    * @return boolean value depending on if interest rate is set.
    */
   public boolean setInterestRate(double interestRateIn) {
   
      if (interestRateIn >= 0 && interestRateIn <= 1) {
         interestRate = interestRateIn;
         return true;
      }
      else {
         return false;
      }
   }
   
   /**
    * @return interestRate the interest rate.
    */
   public double getInterestRate() {
      return interestRate;
   }
   
   /**
    * Apply the interest.
    */
   public void chargeInterest() {
      balance = balance * (1 + interestRate);
   }
   
   /**
    * @return output the string of info.
    */
   public String toString() {
      String output = "Name: " + customerName + "\r\n" 
         + "Interest rate: " + interestRate + "%\r\n" 
         + "Balance: $" + balance + "\r\n";
      return output;
   }

}
