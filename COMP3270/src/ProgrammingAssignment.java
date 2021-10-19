import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;

/**
 * @author Cameron Mathis
 * @version 11/13/19
 * COMP 3270 Programming Assignment
 * Multiple algorithms used on an
 * array to determine each algorithm's
 * time complexity. Each algorithm computes
 * the sum of the subsequence of numbers array
 * which includes negative numbers.
 */

public class ProgrammingAssignment {
    public static void main(String[] args){
    double start;
    double elapsedTime;
    int[] tenx = new int[10];
    File file = new File("phw_input.txt");

    int count = 0; //Used to make sure 10 integers were read in

    //Read in values from file "phw_input.txt"
    try {
        Scanner scan = new Scanner(file);
        scan.useDelimiter(",");
        int i = 0;

        //Check integer criteria
        if (scan.hasNextInt()) {
            while (scan.hasNextInt()) {
                try {
                    tenx[i++] = scan.nextInt();
                    count++;
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The file phw_input.txt contains more than the 10 integers expected. Only the first 10 integers were obtained.");
                    break;
                }
            }
        }

        else {
            System.out.print("The file phw_input.txt contains types other than integers. Please check the file.");
            return;
        }
    }

    catch (IOException e) {
        System.out.print("Error reading the phw_input.txt file.");
        return;
    }

    //Make sure 10 integers were found.
    if (count != 10) {
        System.out.print("The file did not meet the 10 integer criteria. "
                + "Please check the file to make sure the file holds 10 integers and does not contain non-integer values.");
        return;
    }

    //Run through all four algorithms and print the answer for each.
    int biggestSum = Algorithm_1(tenx);
    System.out.print("algorithm-1: " + biggestSum + "; ");

    int biggestSum2 = Algorithm_2(tenx);
    System.out.print("algorithm-2: " + biggestSum2 + "; ");

    int biggestSum3 = Algorithm_3(tenx, 0, tenx.length - 1);
    System.out.print("algorithm-3: " + biggestSum3 + "; ");

    int biggestSum4 = Algorithm_4(tenx);
    System.out.println("algorithm-4: " + biggestSum4 + "; ");

    Random rand = new Random();

    //Create arrays of size 10 to 100
    int[] ten = new int[10];
    int[] fifteen = new int[15];
    int[] twenty = new int[20];
    int[] twentyfive = new int[25];
    int[] thirty = new int[30];
    int[] thirtyfive = new int[35];
    int[] forty = new int[40];
    int[] fortyfive = new int[45];
    int[] fifty = new int[50];
    int[] fiftyfive = new int[55];
    int[] sixty = new int[60];
    int[] sixtyfive = new int[65];
    int[] seventy = new int[70];
    int[] seventyfive = new int[75];
    int[] eighty = new int[80];
    int[] eightyfive = new int[85];
    int[] ninety = new int[90];
    int[] ninetyfive = new int[95];
    int[] hundred = new int[100];

    //Generate random numbers from -25 to 50
    for (int i = 0; i < 10; i++) {
        ten[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 15; i++) {
        fifteen[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 20; i++) {
        twenty[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 25; i++) {
        twentyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 30; i++) {
        thirty[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 35; i++) {
        thirtyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 40; i++) {
        forty[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 45; i++) {
        fortyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 50; i++) {
        fifty[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 55; i++) {
        fiftyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 60; i++) {
        sixty[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 65; i++) {
        sixtyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 70; i++) {
        seventy[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 75; i++) {
        seventyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 80; i++) {
        eighty[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 85; i++) {
        eightyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 90; i++) {
        ninety[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 95; i++) {
        ninetyfive[i] = rand.nextInt(76) - 25;
    }

    for (int i = 0; i < 100; i++) {
        hundred[i] = rand.nextInt(76) - 25;
    }

    double[][] matrix = new double[19][8];

    double timeAverage = 0;
    int sum; // never actually used

    /*
      Runs arrays from size 10 to 100 through all four algorithms
      and place clock times in their appropriate matrix cell.
      All algorithms were ran 1000 times.
     */

    //Ten
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(ten);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[0][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(ten);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[0][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(ten, 0, ten.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[0][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(ten);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }

    timeAverage = timeAverage/1000;
    matrix[0][3] = timeAverage;
    timeAverage = 0;

    //Fifteen
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(fifteen);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[1][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(fifteen);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[1][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(fifteen, 0, fifteen.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[1][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(fifteen);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[1][3] = timeAverage;
    timeAverage = 0;

    //Twenty
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(twenty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[2][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(twenty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[2][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(twenty, 0, twenty.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[2][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(twenty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[2][3] = timeAverage;
    timeAverage = 0;

    //TwentyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(twentyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[3][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(twentyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[3][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(twentyfive, 0, twentyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[3][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(twentyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[3][3] = timeAverage;
    timeAverage = 0;

    //Thirty
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(thirty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[4][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(thirty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[4][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(thirty, 0, thirty.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[4][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(thirty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[4][3] = timeAverage;
    timeAverage = 0;

    //ThirtyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(thirtyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[5][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(thirtyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[5][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(thirtyfive, 0, thirtyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[5][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(thirtyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[5][3] = timeAverage;
    timeAverage = 0;

    //Forty
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(forty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[6][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(forty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[6][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(forty, 0, forty.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[6][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(forty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[6][3] = timeAverage;
    timeAverage = 0;


    //FortyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(fortyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[7][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(fortyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[7][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(fortyfive, 0, fortyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[7][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(fortyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[7][3] = timeAverage;
    timeAverage = 0;

    //Fifty
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(fifty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[8][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(fifty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[8][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(fifty, 0, fifty.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[8][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(fifty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[8][3] = timeAverage;
    timeAverage = 0;

    //FiftyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(fiftyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[9][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(fiftyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[9][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(fiftyfive, 0, fiftyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[9][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(fiftyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[9][3] = timeAverage;
    timeAverage = 0;

    //Sixty
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(sixty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[10][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(sixty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[10][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(sixty, 0, sixty.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[10][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(sixty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[10][3] = timeAverage;
    timeAverage = 0;

    //SixtyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(sixtyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[11][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(sixtyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[11][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(sixtyfive, 0, sixtyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[11][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(sixtyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[11][3] = timeAverage;
    timeAverage = 0;

    //Seventy
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(seventy);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[12][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(seventy);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[12][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(seventy, 0, seventy.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[12][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(seventy);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[12][3] = timeAverage;
    timeAverage = 0;

    //SeventyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(seventyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[13][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(seventyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[13][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(seventyfive, 0, seventyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[13][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(seventyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[13][3] = timeAverage;
    timeAverage = 0;

    //Eighty
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(eighty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[14][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(eighty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[14][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(eighty, 0, eighty.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[14][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(eighty);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[14][3] = timeAverage;
    timeAverage = 0;

    //EightyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(eightyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[15][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(eightyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[15][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(eightyfive, 0, eightyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[15][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(eightyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[15][3] = timeAverage;
    timeAverage = 0;

    //Ninety
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(ninety);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[16][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(ninety);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[16][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(ninety, 0, ninety.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[16][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(ninety);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[16][3] = timeAverage;
    timeAverage = 0;

    //NinetyFive
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(ninetyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[17][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(ninetyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[17][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(ninetyfive, 0, ninetyfive.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[17][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(ninetyfive);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[17][3] = timeAverage;
    timeAverage = 0;

    //One Hundred
    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_1(hundred);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[18][0] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_2(hundred);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[18][1] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_3(hundred, 0, hundred.length - 1);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[18][2] = timeAverage;
    timeAverage = 0;

    for (int i = 0; i < 1000; i++) {
        start = System.nanoTime();
        sum = Algorithm_4(hundred);
        elapsedTime = (System.nanoTime() - start) / 1000000000d;
        timeAverage += elapsedTime;
    }
    timeAverage = timeAverage/1000;
    matrix[18][3] = timeAverage;

    //Inserting calculated complexities
    int n = 10;
    for(int i = 0; i < 19; i++) {
        //Algorithm 1
        matrix[i][4] = Math.ceil(((11/6)*(Math.pow(n,3)))
                + ((14)*(Math.pow(n,2))) + ((23/6)*n) + (44/6));
        //Algorithm 2
        matrix[i][5] = Math.ceil(((11/2)*(Math.pow(n,2))) + ((17/2)*n) + 4);
        //Algorithm 3
        matrix[i][6] = Math.ceil(n*((Math.log(n))/Math.log(2)));
        //Algorithm 4
        matrix[i][7] = Math.ceil(12*(n) + 5);

        n += 5;
    }

   //Print matrix to .txt file "cameronmathis_phw_output"
    try {
        PrintWriter printWriter = new PrintWriter("cameronmathis_phw_output.txt");
        printWriter.print("algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n),T4(n)");
        printWriter.println();
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 8; j++) {
                printWriter.print(matrix[i][j]);
                printWriter.print(",");
            }
            printWriter.println();
        }

        printWriter.close();
    }

    catch(IOException e){
        System.out.println("Error generating/updating the cameronmathis_phw_output.txt file.");
    }
}

    /**
     *@param X The array the algorithm will find the biggest subset of.
     *@return maxSoFar Returns the biggest subset found.
     */
    private static int Algorithm_1(int[] X) {
        int maxSoFar = 0;
        for(int L = 0; L < X.length; L++){
            for(int U = L; U < X.length; U++) {
                int sum = 0;
                for (int I = L; I <= U; I++) {
                    sum = sum + X[I];
                    maxSoFar = Math.max(maxSoFar, sum);
                    /* sum now contains the sum of X[L..U] */
                }
            }
        }
        return maxSoFar;
    }

    /**
     *@param X The array the algorithm will find the biggest subset of.
     *@return maxSoFar Returns the biggest subset found.
     */
    private static int Algorithm_2(int[] X) {
        int maxSoFar = 0;
        for (int L = 0; L < X.length; L++){
            int sum = 0;
            for (int U = L; U < X.length; U++) {
                sum = sum + X[U];
                maxSoFar = Math.max(maxSoFar, sum);
                /* sum now contains the sum of X[L..U] */
            }
        }
        return maxSoFar;
    }

    /**
     *@param X The array the algorithm will find the biggest subset of.
     *@param L The first index of the array
     *@param U the last index of the array
     *@return Returns the biggest subset found.
     */
    private static int Algorithm_3(int[] X, int L, int U) {
        if (L > U){
            return 0; /* zero-element vector */
        }
        if (L == U) {
            return Math.max(0, X[L]); /* one-element vector */
        }

        int M = (L + U)/2; /* A is X[L..M], B is X[M+1..U] */

        /* Find max crossing to left */
        int sum = 0, maxToLeft = 0;
        for (int I = M; I >= L; I--) {
            sum = sum + X[I];
            maxToLeft = Math.max(maxToLeft, sum);
        }

        /* Find max crossing to right */
        sum = 0;
        int maxToRight = 0;
        for (int I = M + 1; I <= U; I++) {
            sum = sum + X[I];
            maxToRight = Math.max(maxToRight, sum);
        }
        int maxCrossing = maxToLeft + maxToRight;

        int maxInA = Algorithm_3(X, L, M);
        int maxInB = Algorithm_3(X, M + 1, U);
        return Math.max(Math.max(maxCrossing, maxInA), maxInB);

    }

    /**
     *@param X The array the algorithm will find the biggest subset of.
     *@return maxSoFar Returns the biggest subset found.
     *
     */
    private static int Algorithm_4(int[] X) {
        int maxSoFar = 0;
        int maxEndingHere = 0;
        for (int I = 0; I < X.length; I++) {
            maxEndingHere = Math.max(0, maxEndingHere + X[I]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}