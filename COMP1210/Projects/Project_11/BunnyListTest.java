import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
/**
 * @author Cameron Mathis
 * @version 9 April 2018
 */
public class BunnyListTest {

   private String file1 = "bunnies2.txt";


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   /**
    * Test readBunnyFile().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void readBunnyFileTest()
      throws FileNotFoundException {
      BunnyList bL = new BunnyList();
      bL.readBunnyFile(file1);
      Assert.assertEquals("Bunny Collection", bL.getListName());
   }
   
   /**
    * Test getListName().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void getListNameTest()
      throws FileNotFoundException {
      BunnyList bL = new BunnyList();
      bL.readBunnyFile(file1);
      Assert.assertEquals("Bunny Collection", bL.getListName());
   }
   
   /**
    * Test setListName().
    */
   @Test public void setListNameTest() {
      BunnyList bL = new BunnyList();
      bL.setListName("Not Bunny Collection");
      Assert.assertEquals("Not Bunny Collection", bL.getListName());
   }
   
   /**
    * Test getBunnyList.
    * @throws FileNotFoundException if file not found.
    * @throws NegativeValueException if value is negative.
    */
   @Test public void getBunnyList()
      throws FileNotFoundException, NegativeValueException {
      BunnyList bL1 = new BunnyList();
      bL1.addBunny(new PetBunny("Floppy", "Holland Lop", 3.5));
      bL1.addBunny(new HouseBunny("Spot", "Really Mixed", 5.8, 0.15));
      bL1.addBunny(new JumpingBunny("Speedy", "English", 6.3, 25.0));
      bL1.addBunny(new ShowBunny("Bigun", "Flemish Giant", 14.6, 22.0));
      BunnyList bL2 = new BunnyList();
      bL2.readBunnyFile(file1);
      Assert.assertArrayEquals(bL1.getBunnyList(), bL2.getBunnyList());
   }
   
   /**
    * Test getExcludedRecords.
    */
   @Test public void getExcludedRecords() {
      BunnyList bL = new BunnyList();
      bL.addExcludedRecord("Spots");
      String[] eR = new String[1];
      eR[0] = "Spots";
      Assert.assertArrayEquals(eR, bL.getExcludedRecords());
   }
   
   /**
    * Test addBunny().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void addBunnyTest()  throws NegativeValueException {
      BunnyList bL = new BunnyList();
      PetBunny pB1 = new PetBunny("Floppy", "Holland Lop", 3.5);
      bL.addBunny(new PetBunny("Floppy", "Holland Lop", 3.5));
      Bunny b2 = bL.getBunnyList()[0];
      Assert.assertEquals(pB1, b2);
   }
   
   /**
    * Test addExcludedRecords().
    */
   @Test public void addExcludedRecords() {
      BunnyList bL = new BunnyList();
      bL.addExcludedRecord("Spots");
      String[] eR = new String[1];
      eR[0] = "Spots";
      Assert.assertArrayEquals(eR, bL.getExcludedRecords());
   }
   
   /**
    * Test toString().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void toStringTest()  throws NegativeValueException {
      BunnyList bL = new BunnyList();
      bL.addBunny(new PetBunny("Floppy", "Holland Lop", 3.5));
      String expected = "\nFloppy (PetBunny)   Breed: Holland Lop   Weight: 3.5"
         + "\nEstimated Monthly Cost: $6.48\n";
      Assert.assertEquals(expected, bL.toString());
   }
   
   /**
    * Test totalEstimatedMonthlyCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void totalEstimatedMonthlyCostTest()
      throws NegativeValueException {
      BunnyList bL = new BunnyList();
      bL.addBunny(new PetBunny("Floppy", "Holland Lop", 3.5));
      Assert.assertEquals(6.48, bL.totalEstimatedMonthlyCost(), .01);
   }
   
   /**
    * Test summary().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void summaryTest() throws NegativeValueException {
      BunnyList bL = new BunnyList();
      bL.addBunny(new PetBunny("Floppy", "Holland Lop", 3.5));
      String expected = "------------------------------\n"
         + "Summary for not yet assigned\n"
         + "------------------------------\nNumber of Bunnies: " 
         + "1\nTotal Estimated Monthly Cost: $6.48\n\n";
      Assert.assertEquals(expected, bL.summary());
   }
   
   /**
    * Test listByName().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void listByNameTest() throws NegativeValueException {
      BunnyList bL = new BunnyList();
      bL.addBunny(new PetBunny("Floppy", "Holland Lop", 3.5));
      String expected = "------------------------------\n"
         + "Bunnies by Name\n------------------------------\n\n"
         + "Floppy (PetBunny)   Breed: Holland Lop   Weight: 3.5"
         + "\nEstimated Monthly Cost: $6.48\n\n";
      Assert.assertEquals(expected, bL.listByName());
   }
   
   /**
    * Test listByCost().
    * @throws NegativeValueException if value is negative.
    */
   @Test public void listByCostTest() throws NegativeValueException {
      BunnyList bL = new BunnyList();
      bL.addBunny(new PetBunny("Floppy", "Holland Lop", 3.5));
      String expected = "------------------------------\n"
         + "Bunnies by Cost\n------------------------------\n\n"
         + "Floppy (PetBunny)   Breed: Holland Lop   Weight: 3.5"
         + "\nEstimated Monthly Cost: $6.48\n\n";
      Assert.assertEquals(expected, bL.listByCost());
   }
   
   /**
    * Test excludedRecordsList().
    */
   @Test public void excludedRecordsListTest() {
      BunnyList bL = new BunnyList();
      bL.addExcludedRecord("mouse Bunny, Spots, Mixed, 0.8, 0.15");
      String expected = "------------------------------\n"
         + "Excluded Records\n------------------------------\n\n"
         + "mouse Bunny, Spots, Mixed, 0.8, 0.15\n\n";
      Assert.assertEquals(expected, bL.excludedRecordsList());
   }
}