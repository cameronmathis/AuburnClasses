import org.junit.Assert;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;

/**
 * @author Cameron Mathis
 * @version 28 March 2018
 */
public class ConicalFrustumList2Test {

   private String file0 = "conical_frustum_data_0.txt";
   private String file1 = "conical_frustum_data_1.txt";

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   
   }

   /**
    * Test getName().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void getNameTest()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals("Conical Frustum Test List", cfL.getName());
   }
   
   /**
    * Test numberOfConicalFrustums().
    */
   @Test public void numberOfConicalFrustumsTest() {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 1);
      Assert.assertEquals(1, cfL.numberOfConicalFrustums());
   }
   
   /**
    * Test totalSurfaceArea().
    */
   @Test public void totalSurfaceAreaTest1() {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(0.0, cfL.totalSurfaceArea(), .001);
   }
   
   /**
    * Test totalSurfaceArea().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void totalSurfaceAreaTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals(407734.026, cfL.totalSurfaceArea(), .001);
   }
   
   /**
    * Test totalVolume().
    */
   @Test public void totalVolumeTest1() {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(0.0, cfL.totalVolume(), .001);
   }
   
   /**
    * Test totalVolume().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void totalVolumeTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals(18023600.645, cfL.totalVolume(), .001);
   }
   
   /**
    * Test averageSurfaceArea().
    */
   @Test public void averageSurfaceAreaTest1() {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(0.0, cfL.averageSurfaceArea(), .001);
   }
   
   /**
    * Test averageSurfaceArea().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void averageSurfaceAreaTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals(135911.342, cfL.averageSurfaceArea(), .001);
   }
   
   /**
    * Test averageVolume().
    */
   @Test public void averageVolumeTest1() {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(0.0, cfL.averageVolume(), .001);
   }
   
   /**
    * Test averageVolume().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void averageVolumeTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals(6007866.882, cfL.averageVolume(), .001);
   }
   
   /**
    * Test toString().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void toStringTest()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      String expected = "Conical Frustum Test List\n"
            + "\nConicalFrustum \"Small\" with radius1 = 0.5, radius2 = 0.75, "
            + "and height = 0.25 has:\n"
            + "\tvolume = 0.311 cubic units\n"
            + "\tslant height = 0.354 units\n"
            + "\tlateral surface area = 1.388 units\n"
            + "\ttotal surface area = 3.941 square units\n"
            + "\nConicalFrustum \"Medium\" with radius1 = 5.1, radius2 = 10.2, "
            + "and height = 15.9 has:\n"
            + "\tvolume = 3,031.546 cubic units\n"
            + "\tslant height = 16.698 units\n"
            + "\tlateral surface area = 802.608 units\n"
            + "\ttotal surface area = 1,211.172 square units\n"
            + "\nConicalFrustum \"Large\" with radius1 = 98.32, radius2 = "
            + "199.0, and height = 250.0 has:\n"
            + "\tvolume = 18,020,568.788 cubic units\n"
            + "\tslant height = 269.512 units\n"
            + "\tlateral surface area = 251,739.485 units\n"
            + "\ttotal surface area = 406,518.914 square units\n";
      Assert.assertEquals(expected, cfL.toString());
   }
   
   /**
    * Test summaryInfo().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void summaryInfoTest()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      String expected = "----- Summary for Conical Frustum Test List -----\n"
            + "Number of ConicalFrustum Objects: 3\n"
            + "Total Surface Area: 407,734.026\n"
            + "Total Volume: 18,023,600.645\n"
            + "Average Surface Area: 135,911.342\n"
            + "Average Volume: 6,007,866.882";
      Assert.assertEquals(expected, cfL.summaryInfo());
   }
   
   /**
    * Test getList().
    */
   @Test public void getListTest() {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertArrayEquals(inputList, cfL.getList());
   }
   
   /**
    * Test readFile().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void readFileTest()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals("Conical Frustum Test List", cfL.getName());
   }
   
   /**
    * Test addConicalFrustum().
    */
   @Test public void addConicalFrustumTest() {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL.addConicalFrustum("New", 1, 2, 3);
      ConicalFrustum cF = new ConicalFrustum("New", 1, 2, 3);
      Assert.assertEquals(cF, inputList[cfL.numberOfConicalFrustums()
         - 1]);
   }
   
   /**
    * Test findConicalFrustum().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumTest1()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals(null, cfL.findConicalFrustum("wow"));
   }
   
   /**
    * Test findConicalFrustum().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      ConicalFrustum cF = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(cF, cfL.findConicalFrustum("smaLL"));
   }
   
   /**
    * Test deleteConicalFrustum().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void deleteConicalFrustumTest1()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertEquals(null, cfL.deleteConicalFrustum("wow"));
   }
   
   /**
    * Test deleteConicalFrustum().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void deleteConicalFrustumTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      ConicalFrustum cF = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(cF, cfL.deleteConicalFrustum("smaLL"));
   }
   
   /**
    * Test editConicalFrustum().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void editConicalFrustumTest1()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertFalse(cfL.editConicalFrustum("wow", 1, 2, 3));
   }
   
   /**
    * Test editConicalFrustum().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void editConicalFrustumTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      Assert.assertTrue(cfL.editConicalFrustum("smaLL",
         0.5, 0.75, 0.25));
   }
   
   /**
    * Test findConicalFrustumWithLeastHeight().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithLeastHeightTest1()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(null, cfL.findConicalFrustumWithLeastHeight());
   }
   
   /**
    * Test findConicalFrustumWithLeastHeight().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithLeastHeightTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      ConicalFrustum cF = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(cF, cfL.findConicalFrustumWithLeastHeight());
   }
   
   /**
    * Test findConicalFrustumWithGreatestHeight().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithGreatestHeightTest1()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(null, cfL.findConicalFrustumWithGreatestHeight());
   }
   
   /**
    * Test findConicalFrustumWithGreatestHeight().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithGreatestHeightTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      ConicalFrustum cF = new ConicalFrustum("Large", 98.32, 199.0, 250.0);
      Assert.assertEquals(cF, cfL.findConicalFrustumWithGreatestHeight());
   }
   
   /**
    * Test findConicalFrustumWithLeastVolume().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithLeastVolumeTest1()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(null, cfL.findConicalFrustumWithLeastVolume());
   }
   
   /**
    * Test findConicalFrustumWithLeastVolume().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithLeastVolumeTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      ConicalFrustum cF = new ConicalFrustum("Small", 0.5, 0.75, 0.25);
      Assert.assertEquals(cF, cfL.findConicalFrustumWithLeastVolume());
   }
   
   /**
    * Test findConicalFrustumWithGreatestVolume().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithGreatestVolumeTest1()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      Assert.assertEquals(null, cfL.findConicalFrustumWithGreatestVolume());
   }
   
   /**
    * Test findConicalFrustumWithGreatestVolume().
    * @throws FileNotFoundException if file not found.
    */
   @Test public void findConicalFrustumWithGreatestVolumeTest2()
      throws FileNotFoundException {
      ConicalFrustum [] inputList = new ConicalFrustum[100];
      ConicalFrustumList2 cfL = new ConicalFrustumList2("listName",
         inputList, 0);
      cfL = cfL.readFile(file1);
      ConicalFrustum cF = new ConicalFrustum("Large", 98.32, 199.0, 250.0);
      Assert.assertEquals(cF, cfL.findConicalFrustumWithGreatestVolume());
   }
}
