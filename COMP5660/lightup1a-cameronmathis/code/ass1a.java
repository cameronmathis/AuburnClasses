import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.Random;

/**
 * Author - Cameron Mathis
 * Date - 8/30/20
 *
 * This is the main class for Assignment 1A.
 */
public class ass1a {
    // Global Variables
    private static String randomBased;
    private static int seedNumber;
    private static Random rand;
    private static String searchAlgorithm;
    private static int numberOfRuns;
    private static int numberOfFitnessEvaluations;
    private static String problemFilePath;
    private static String solutionFilePath;
    private static String logFilePath;
    private static boolean doCheckBlackCells;
    private static int[] originalProblemTable;
    private static int[] problemTable;
    private static int tableSize = 0;
    private static int tableX = 0;
    private static int tableY = 0;
    private static int numberOfBlackCells = 0;
    private static int runCount = 1;
    private static int evaluationCount = 1;
    private static int bestRun;
    private static int[] bestSolution;
    private static int runMaxFitnessScore = 0;
    private static int experimentMaxFitnessScore = 0;
    private static int absoluteMaxFitnessScore;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            problemFilePath = args[0];
        }
        // set config path in case it is not specified
        String configFilePath = "configFiles/configGeneral.properties";
        if (args.length >= 2) {
            configFilePath = args[1];
        }
        readConfigFile(configFilePath);
        // set seed number for experiment
        if (randomBased.equals("time")) {
            seedNumber = (int) System.currentTimeMillis();
        }
        rand = new Random(seedNumber);
        // read in the problem table
        originalProblemTable = readProblemFile();
        createResultLog();

        calculateAbsoluteMaxFitnessScore();
        // conduct set number of runs
        for (; runCount <= numberOfRuns; runCount++) {
            try {
                FileWriter writer = new FileWriter(logFilePath, true);
                writer.write("\r\n\nRun " + runCount);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resetRunData();
            conductRun();
        }
        System.out.println("Best Run: " + bestRun);
        createSolutionFile();
    }

    /**
     * Read in the config file and set appropriate variable values.
     *
     * @param filePath the file path+name to the appropriate config.properties file
     */
    private static void readConfigFile(String filePath) {
        Properties prop = new Properties();
        FileInputStream ip;
        try {
            ip = new FileInputStream(filePath);
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        randomBased = prop.getProperty("randomBased");
        if (randomBased.equals("seed")) {
            seedNumber = Integer.parseInt(prop.getProperty("seedNumber"));
        }
        searchAlgorithm = prop.getProperty("searchAlgorithm");
        numberOfRuns = Integer.parseInt(prop.getProperty("numberRuns"));
        numberOfFitnessEvaluations = Integer.parseInt(prop.getProperty("numberFitnessEvaluations"));
        problemFilePath = prop.getProperty("problemFilePath");
        solutionFilePath = prop.getProperty("solutionFilePath");
        logFilePath = prop.getProperty("logFilePath");
        doCheckBlackCells = Boolean.parseBoolean(prop.getProperty("doCheckBlackCells"));
    }

    /**
     * Reads in the problem file and then creates an array with 100 in the white slots and the appropriate number on
     * the black spots.
     *
     * @return the problem table.
     */
    private static int[] readProblemFile() {
        int[] result = new int[0];
        try {
            File problemFile = new File(problemFilePath);
            Scanner myReader = new Scanner(problemFile);
            if (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tableX = Integer.parseInt(data);
                data = myReader.nextLine();
                tableY = Integer.parseInt(data);
            }
            result = new int[tableX * tableY];
            tableSize = (tableX * tableY) - 1;
            for (int i = 0; i <= tableSize; i++) {
                result[i] = 100;
            }
            while (myReader.hasNextLine()) {
                int x = myReader.nextInt();
                int y = myReader.nextInt();
                int z = myReader.nextInt();
                // calculate the array index to put black cell number
                int index = (((tableY - y) * tableX) + x) - 1;
                result[index] = z;
                numberOfBlackCells++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Calculate the absolute max fitness score for a problem.
     */
    private static void calculateAbsoluteMaxFitnessScore() {
        // max fitness score equals the number of white cells
        absoluteMaxFitnessScore = 1 + tableSize - numberOfBlackCells;
    }

    /**
     * Conduct a run for the appropriate number of evaluations. Produces the log and solution files.
     */
    private static void conductRun() {
        // conduct set number of evaluations
        for (; evaluationCount <= numberOfFitnessEvaluations; evaluationCount++) {
            int fitnessScore;
            resetEvaluationData();
            solveProblem(problemTable);
            fitnessScore = evaluateSolution(problemTable, tableSize, doCheckBlackCells);
            if (fitnessScore > runMaxFitnessScore) {
                runMaxFitnessScore = fitnessScore;
                if (fitnessScore > experimentMaxFitnessScore) {
                    experimentMaxFitnessScore = fitnessScore;
                    bestRun = runCount;
                    bestSolution = Arrays.copyOf(problemTable, problemTable.length);
                }
                addResultToLog(fitnessScore);
            } else if (evaluationCount == 1) {
                // log first attempt of experiment as best solution just in case no solution is found
                if (runCount == 1) {
                    bestSolution = Arrays.copyOf(problemTable, problemTable.length);
                }
                addResultToLog(fitnessScore);
            }
        }
    }

    /**
     * Attempts to solve the problem by randomly placing bulbs.
     *
     * @param table the problem table.
     */
    private static void solveProblem(int[] table) {
        int r = rand.nextInt(1 + tableSize - numberOfBlackCells);
        r++;
        for (int i = 0; i < r; i++) {
            int n = rand.nextInt(1 + tableSize);
            if (table[n] == 100) {
                table[n] = 99;
            } else {
                i--;
            }
        }
    }

    /**
     * Reset all necessary data to perform another evaluation.
     */
    private static void resetEvaluationData() {
        problemTable = Arrays.copyOf(originalProblemTable, originalProblemTable.length);
    }

    /**
     * Reset all necessary data to perform another run.
     */
    private static void resetRunData() {
        evaluationCount = 1;
        runMaxFitnessScore = 0;
        problemTable = Arrays.copyOf(originalProblemTable, originalProblemTable.length);
    }

    /**
     * Checks to see if the problem solution is valid. If the solution is valid then it returns a fitness which equals
     * the number of total white cells minus the total number of bulbs.
     *
     * @param table                    the problem table.
     * @param length                   the length of the problem table array.
     * @param checkBlackCellConstraint whether or not to check if the black cell constraints were satisfied.
     * @return the number of lit cells, assuming the solution is valid. If the solution is not valid then return 0.
     */
    private static int evaluateSolution(int[] table, int length, boolean checkBlackCellConstraint) {
        boolean valid = true;
        int[] litCells = Arrays.copyOf(originalProblemTable, originalProblemTable.length);
        int numberOfCellsLit = 0;

        for (int i = 0; i < length; i++) {
            // checks to how many cells are lit
            if (table[i] == 99) {
                // count cells with bulb
                litCells[i] = 88;
                // count cells underneath
                for (int y = (i - tableX); y >= 0; y = (y - tableX)) {
                    if (table[y] > 5) {
                        litCells[y] = 88;
                    } else {
                        break;
                    }
                }
                // count cells to the left
                for (int x = (i - 1); (((x + 1) % tableX) != 0) && (x >= 0); x--) {
                    if (table[x] > 5) {
                        litCells[x] = 88;
                    } else {
                        break;
                    }
                }
                // count cells to the right
                for (int x = (i + 1); ((x % tableX) != 0) && (x <= length); x++) {
                    if (table[x] > 5) {
                        litCells[x] = 88;
                    } else {
                        break;
                    }
                }
                // count cells above
                for (int y = (i + tableX); y <= length; y = (y + tableX)) {
                    if (table[y] > 5) {
                        litCells[y] = 88;
                    } else {
                        break;
                    }
                }
            }

            // check to see if all the black cell constraints are satisfied
            if ((table[i] <= 4) && checkBlackCellConstraint) {
                if (checkBlackCell(i) != table[i]) {
                    valid = false;
                }
            }

            // check to see if two bulbs are shinning on each other
            if (table[i] == 99) {
                // checks for another bulb shinning on it from the bottom
                for (int y = (i - tableX); y >= 0; y = (y - tableX)) {
                    if (table[y] != 99) {
                        if (table[y] != 100) {
                            break;
                        }
                    } else {
                        valid = false;
                    }
                }
                // checks for another bulb shinning on it from the left
                for (int x = (i - 1); (((x + 1) % tableX) != 0) && (x >= 0); x--) {
                    if (table[x] != 99) {
                        if (table[x] != 100) {
                            break;
                        }
                    } else {
                        valid = false;
                    }
                }
                // checks for another bulb shinning on it from the right
                for (int x = (i + 1); ((x % tableX) != 0) && (x <= length); x++) {
                    if (table[x] != 99) {
                        if (table[x] != 100) {
                            break;
                        }
                    } else {
                        valid = false;
                    }
                }
                // checks for another bulb shinning on it from the top
                for (int y = (i + tableX); y <= length; y = (y + tableX)) {
                    if (table[y] != 99) {
                        if (table[y] != 100) {
                            break;
                        }
                    } else {
                        valid = false;
                    }
                }
            }
        }

        // if the solution is not valid return 0
        if (!valid) {
            return 0;
        }

        // iterate over lit cells array to count the total number of lit cells
        for (int i = 0; i <= tableSize; i++) {
            if (litCells[i] == 88) {
                numberOfCellsLit++;
            }
        }

        return numberOfCellsLit;
    }

    /**
     * Checks to see the number of bulbs around a black cell.
     *
     * @param cell the cell that is black.
     * @return the number of bulbs around a black cell.
     */
    private static int checkBlackCell(int cell) {
        int numOfBulbsAlready = 0;

        // checks to see if the cell above exist and has a bulb
        if ((cell >= tableX) && (problemTable[cell - tableX] == 99)) {
            numOfBulbsAlready++;
        }
        // checks to see if the cell to the left exist and has a bulb
        if (((cell % tableX) != 0) && (problemTable[cell - 1] == 99)) {
            numOfBulbsAlready++;
        }
        // checks to see if the cell to the right exist and has a bulb
        if ((((cell + 1) % tableX) != 0) && (problemTable[cell + 1] == 99)) {
            numOfBulbsAlready++;
        }
        // checks to see if the cell below exist and has a bulb
        if ((cell < ((tableX * tableY) - tableX)) && (problemTable[cell + tableX] == 99)) {
            numOfBulbsAlready++;
        }

        return numOfBulbsAlready;
    }

    /**
     * Create the solution file.
     */
    private static void createSolutionFile() {
        try {
            if (Files.exists(Paths.get(solutionFilePath))) {
                Files.delete(Paths.get(solutionFilePath));
            }
            Files.copy(Paths.get(problemFilePath), Paths.get(solutionFilePath));
            FileWriter writer = new FileWriter(solutionFilePath, true);
            writer.write("\r\n" + experimentMaxFitnessScore); // write the number of cells lit
            for (int i = 0; i <= tableSize; i++) {
                // calculates the x-coordinate
                int x = (i % tableX) + 1;
                // calculates the y-coordinate
                int y;
                if (((tableY - ((i + tableX) / tableX) % tableY) + 1) <= tableY) {
                    y = (tableY - ((i + tableX) / tableX) % tableY) + 1;
                } else {
                    y = 1;
                }
                // writes each coordinate to the solution file
                if (bestSolution[i] == 99) {
                    writer.write("\r\n" + x + " " + y);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the result log file with appropriate starting information.
     */
    private static void createResultLog() {
        try {
            FileWriter writer = new FileWriter(logFilePath);
            writer.write("Result Log\r\n");
            writer.write("Problem File Path+Name: " + problemFilePath + "\r\n");
            writer.write("Solution File Path+Name: " + solutionFilePath + "\r\n");
            writer.write("Random Number Seed: " + seedNumber + "\r\n");
            writer.write("Algorithm parameters:\r\n");
            writer.write("\tRandom Number Based Upon: " + randomBased + "\r\n");
            writer.write("\tNumber of Runs: " + numberOfRuns + "\r\n");
            writer.write("\tNumber of Fitness Evaluations: " + numberOfFitnessEvaluations + "\r\n");
            writer.write("\tSearch Algorithm: " + searchAlgorithm + "\r\n");
            writer.write("\tChecking Solutions Against Black Cell Constraints: " + doCheckBlackCells);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a result to the log file.
     *
     * @param fitnessScore the fitness score of the evaluation being logged.
     */
    private static void addResultToLog(int fitnessScore) {
        try {
            FileWriter writer = new FileWriter(logFilePath, true);
            writer.write("\r\n" + evaluationCount + "\t" + fitnessScore);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
