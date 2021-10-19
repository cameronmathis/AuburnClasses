import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cameron Mathis
 * @version 19 March 2018
 */
public class BankLoanTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
     
   /**
    * Test isAmountValidCreated().
    */
   @Test public void isAmountValidTest1() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", true, BankLoan.isAmountValid(0));
   }
  
   /**
    * Test isAmountValidCreated().
    */
   @Test public void isAmountValidTest2() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", false, BankLoan.isAmountValid(-1));
   }
   
   /**
    * Test isInDebt().
    */
   @Test public void isInDebtTest1() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.borrowFromBank(1000.00);
      Assert.assertEquals(" ", true, BankLoan.isInDebt(loan1));
   }
   
   /**
    * Test isInDebt().
    */
   @Test public void isInDebtTest2() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", false, BankLoan.isInDebt(loan1));
   }

   /**
    * Test getLoansCreated().
    */
   @Test public void getLoansCreatedTest() {
      BankLoan.resetLoansCreated();
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", 1, BankLoan.getLoansCreated(), .000001);
   }
         
   /**
    * Test resetLoansCreated().
    */
   @Test public void resetLoansCreatedTest() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      BankLoan.resetLoansCreated();
      Assert.assertEquals(" ", 0, BankLoan.getLoansCreated(), .000001);
   }
   
   /**
    * Test borrowFromBank().
    */
   @Test public void borrowFromBankTest1() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", true, loan1.borrowFromBank(1000.00));
   }
   
   /**
    * Test borrowFromBank().
    */
   @Test public void borrowFromBankTest2() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", false, loan1.borrowFromBank(100000.01));
   }
   
   /**
    * Test borrowFromBank().
    */
   @Test public void borrowFromBankTest3() {
      BankLoan loan1 = new BankLoan("Jane", .10); 
      loan1.borrowFromBank(1000.00);
      Assert.assertEquals(" ", 1000, loan1.getBalance(), .000001);
   }
   
   /**
    * Test borrowFromBank().
    */
   @Test public void borrowFromBankTest4() {
      BankLoan loan1 = new BankLoan("Jane", .10); 
      loan1.borrowFromBank(100000.01);
      Assert.assertEquals(" ", 0, loan1.getBalance(), .000001);
   }

       /**
    * Test payBank().
    */
   @Test public void payBankTest1() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.borrowFromBank(1000.00);
      Assert.assertEquals(" ", 0, loan1.payBank(1000.00), .000001);
   }
   
   /**
    * Test payBank().
    */
   @Test public void payBankTest2() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.borrowFromBank(1000.00);
      Assert.assertEquals(" ", 100, loan1.payBank(1100.00), .000001);
   }
   
   /**
    * Test getBalance().
    */
   @Test public void getBalanceTest() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", 0, loan1.getBalance(), .000001);
   }
               
   /**
    * Test setInterestRate().
    */
   @Test public void setInterestRateTest1() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", true, loan1.setInterestRate(.20));
   }
    
   /**
    * Test setInterestRate().
    */
   @Test public void setInterestRateTest2() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", false, loan1.setInterestRate(1.20));
   }
   
   /**
    * Test setInterestRate().
    */
   @Test public void setInterestRateTest3() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", false, loan1.setInterestRate(-1));
   }
   
   /**
    * Test setInterestRate().
    */
   @Test public void setInterestRateTest4() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.setInterestRate(.20);
      Assert.assertEquals(" ", .20, loan1.getInterestRate(), .000001);
   }

   /**
    * Test setInterestRate().
    */
   @Test public void setInterestRateTest5() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.setInterestRate(1.20);
      Assert.assertEquals(" ", .10, loan1.getInterestRate(), .000001);
   }
   
   /**
    * Test setInterestRate().
    */
   @Test public void setInterestRateTest6() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.setInterestRate(-1);
      Assert.assertEquals(" ", .10, loan1.getInterestRate(), .000001);
   }

   /**
    * Test getInterestRate().
    */
   @Test public void getInterestRateTest() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      Assert.assertEquals(" ", .10, loan1.getInterestRate(), .000001);
   }
   
   /**
    * Test chargeInterest().
    */
   @Test public void chargeInterestTest() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.borrowFromBank(1000.00);
      loan1.chargeInterest();
      Assert.assertEquals(" ", 1100, loan1.getBalance(), .000001);
   }
   
   /**
    * Test toString().
    */
   @Test public void toStringTest() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.borrowFromBank(1000.00);
      String expected = "Name: " + "Jane" + "\r\n" 
         + "Interest rate: " + loan1.getInterestRate() + "%\r\n" 
         + "Balance: $" + loan1.getBalance() + "\r\n";
      Assert.assertEquals(" ", expected, loan1.toString());
   }
}
