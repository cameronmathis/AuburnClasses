import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Author - Cameron Mathis
 * Date - 9/13/20
 *
 * This class handles all the necessary methods to perform an experiment in Assignment 1B.
 */
public class ExperimentController {
    // Final Variables
    public static final int EMPTY = 100;
    public static final int BULB = 99;
    public static final int LIT = 88;

    /**
     * Conduct an experiment for the appropriate number of runs. Produces the solution file.
     *
     * @param originalProblemTable the original problem table.
     * @param rand the Random object initialize with a seed.
     */
    static void conductExperiment(int[][] originalProblemTable, Random rand) {
        int tableX = originalProblemTable.length;
        int tableY = originalProblemTable[0].length;
        for (int runCount = 1; runCount <= ConfigController.getNumberOfRuns(); runCount++) {
            // Write the run number to the log file
            try {
                FileWriter writer = new FileWriter(ConfigController.getLogFilePath(), true);
                writer.write("\r\n\nRun " + runCount);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // perform a single run
            conductRun(originalProblemTable, rand, tableX, tableY);
        }
        // create the solution file
        FileController.createSolutionFile(ConfigController.getSolutionFilePath(), ConfigController.getProblemFilePath(),
                PopulationController.getBestSolution(), PopulationController.getAbsoluteBestFitness(), tableX, tableY);
    }

    /**
     * Conduct a run for the appropriate number of evaluations.
     *
     * @param originalProblemTable the original problem table.
     * @param rand the Random object initialize with a seed.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     */
    static void conductRun(int[][] originalProblemTable, Random rand, int tableX, int tableY) {
        // make sure the run best fitness starts at 0
        PopulationController.resetRunBestFitness();

        // create variables to remember the run's best fitness
        double currentRunBestFitness;
        double previousRunBestFitness = 0.0;
        int runWithoutChangeInBestFitness = 1;

        // initialize population at start of run
        PopulationController.initializePopulation(rand, originalProblemTable, tableX, tableY);

        // conduct set number of evaluations
        for (int evaluationCount = 1; evaluationCount <= ConfigController.getNumberOfFitnessEvaluations() ||
                (ConfigController.getTerminationCriterion().equals("convergence")); evaluationCount++) {

            // perform a single evaluation
            conductEvaluation(rand, originalProblemTable, tableX, tableY, evaluationCount);

            // keep track of number of evaluations with no change in the run's best fitness
            currentRunBestFitness = PopulationController.getRunBestFitness();
            if (evaluationCount != 1 && currentRunBestFitness == previousRunBestFitness) {
                runWithoutChangeInBestFitness++;
            } else {
                runWithoutChangeInBestFitness = 1;
            }
            previousRunBestFitness = currentRunBestFitness;

            // check to see if there have been n evaluations where the run's best fitness did not increase
            if (ConfigController.getTerminationCriterion().equals("convergence") && runWithoutChangeInBestFitness == ConfigController.getNForTermination()) {
                break;
            }
        }
    }

    /**
     * Conduct a single evaluation and log it if the evaluation count equals lambda.
     *
     * @param rand the Random object initialize with a seed.
     * @param originalProblemTable the original problem table.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     * @param evaluationCount the evaluation number.
     */
    private static void conductEvaluation(Random rand, int[][] originalProblemTable, int tableX, int tableY, int evaluationCount) {
        PopulationController.selectParents(rand);
        PopulationController.generateOffspring(rand, originalProblemTable, tableX, tableY);
        PopulationController.selectSurvivors(rand);
        // add result to log every lambda evaluations
        if (evaluationCount % ConfigController.getLambda() == 0) {
            FileController.addResultToLog(evaluationCount, PopulationController.calculateAveragePopulationFitness(), PopulationController.getRunBestFitness());
        }
    }


    /**
     * Attempts to solve the problem by randomly placing bulbs.
     *
     * @param rand the Random object initialize with a seed.
     * @param table the problem table.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     * @param numberOfBlackCells the total number of black cells on the table.
     */
    static void uniformRandomSearch(Random rand, int[][] table, int tableX, int tableY, int numberOfBlackCells) {
        int r = rand.nextInt((tableX * tableY) - numberOfBlackCells);
        r++;
        for (int i = 0; i < r; i++) {
            int x = rand.nextInt(tableX);
            int y = rand.nextInt(tableY);
            if (table[x][y] == EMPTY) {
                table[x][y] = BULB;
            } else {
                i--;
            }
        }
    }

    /**
     * Attempts to solve the problem by placing bulbs around black boxed where it can only be performed one way and then
     * by randomly placing bulbs.
     *
     * @param rand the Random object initialize with a seed.
     * @param table the problem table.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     * @param numberOfBlackCells the total number of black cells on the table.
     */
    static void validityForcedUniformRandomSearch(Random rand, int[][] table, int tableX, int tableY,
                                                  int numberOfBlackCells) {
        int r = rand.nextInt((tableX * tableY) - numberOfBlackCells);
        r++;

        boolean bulbsArePlaceable= true;
        while (bulbsArePlaceable) {
            bulbsArePlaceable = false;
            for (int x = 0; x < tableX; x++) {
                for (int y = 0; y < tableY; y++) {
                    if (table[x][y] <= 5) {
                        // check to see if the number of white cells corresponds to the number on the black cell
                        if (table[x][y] == countCellsAround(table, tableX, tableY, x, y, EMPTY)) {
                            // reduce r since they number of available white cells is less for the uniform random search
                            r = r - table[x][y];
                            // place bulbs around the black cell
                            // checks to see if the cell above exist, is empty, and if a bulb can be placed there
                            if ((y > 0) && (table[x][y - 1] == EMPTY) && canPlaceBulb(table, tableX, tableY, x, y)) {
                                table[x][y - 1] = BULB;
                                bulbsArePlaceable = true;
                            }
                            // checks to see if the cell to the left exist, is empty, and if a bulb can be placed there
                            if ((x > 0) && (table[x - 1][y] == EMPTY) && canPlaceBulb(table, tableX, tableY, x, y)) {
                                table[x - 1][y] = BULB;
                                bulbsArePlaceable = true;
                            }
                            // checks to see if the cell to the right exist, is empty, and if a bulb can be placed there
                            if ((x < tableX - 1) && (table[x + 1][y] == EMPTY) && canPlaceBulb(table, tableX, tableY, x, y)) {
                                table[x + 1][y] = BULB;
                                bulbsArePlaceable = true;
                            }
                            // checks to see if the cell below exist, is empty, and if a bulb can be placed there
                            if ((y < tableY - 1) && (table[x][y + 1] == EMPTY) && canPlaceBulb(table, tableX, tableY, x, y)) {
                                table[x][y + 1] = BULB;
                                bulbsArePlaceable = true;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            int x = rand.nextInt(tableX);
            int y = rand.nextInt(tableY);
            if (table[x][y] == EMPTY) {
                table[x][y] = BULB;
            } else {
                i--;
            }
        }
    }

    /**
     * Checks to see the number of a specified parameter around a given cell.
     *
     * @param table the problem table.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     * @param x the x-value of the cell to check.
     * @param y the y-value of the cell to check.
     * @param checkFor what we are checking the cells around for.
     * @return the number of empty cells around a black cell.
     */
    private static int countCellsAround(int[][] table, int tableX, int tableY, int x, int y, int checkFor) {
        int number = 0;

        // checks to see if the cell above exist and is what is specified
        if ((y > 0) && (table[x][y - 1] == checkFor)) {
            number++;
        }
        // checks to see if the cell to the left exist and is what is specified
        if ((x > 0) && (table[x - 1][y] == checkFor)) {
            number++;
        }
        // checks to see if the cell to the right exist and is what is specified
        if ((x < tableX - 1) && (table[x + 1][y] == checkFor)) {
            number++;
        }
        // checks to see if the cell below exist and is what is specified
        if ((y < tableY - 1) && (table[x][y + 1] == checkFor)) {
            number++;
        }

        return number;
    }

    /**
     * Checks to see if a bulb can be placed in a cell without invalidating constraints.
     *
     * @param table the problem table.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     * @param x the x-value of the cell to check.
     * @param y the y-value of the cell to check.
     * @return the if a bulb can be placed.
     */
    private static boolean canPlaceBulb(int[][] table, int tableX, int tableY, int x, int y) {
        // check if there is a 0 black cell adjacent
        if (countCellsAround(table, tableX, tableY, x, y, 0) != 0) {
            return false;
        }

        // check to see if there is a black cell around that already has max bulbs
        if ((y > 0) && (table[x][y - 1] < 5)) {
            if (countCellsAround(table, tableX, tableY, x, y - 1, BULB) == table[x][y - 1]) {
                return false;
            }
        }
        if ((x > 0) && (table[x - 1][y] < 5)) {
            if (countCellsAround(table, tableX, tableY, x - 1, y, BULB) == table[x - 1][y]) {
                return false;
            }
        }
        if ((x < tableX) && (table[x + 1][y] < 5)) {
            if (countCellsAround(table, tableX, tableY, x + 1, y, BULB) == table[x + 1][y]) {
                return false;
            }
        }
        if ((y < tableY) && (table[x][y + 1] < 5)) {
            if (countCellsAround(table, tableX, tableY, x, y + 1, BULB) == table[x][y + 1]) {
                return false;
            }
        }

        // checks to see if there is already a bulb shinning on the cell
        // checks for another bulb shinning on it from the bottom
        for (int y2 = y + 1; y2 < tableY; y2++) {
            if (table[x][y2] != BULB) {
                if (table[x][y2] != EMPTY) {
                    break;
                }
            } else {
                return false;
            }
        }
        // checks for another bulb shinning on it from the left
        for (int x2 = x - 1; x2 >= 0; x2--) {
            if (table[x2][y] != BULB) {
                if (table[x2][y] != EMPTY) {
                    break;
                }
            } else {
                return false;
            }
        }
        // checks for another bulb shinning on it from the right
        for (int x2 = x + 1; x2 < tableX; x2++) {
            if (table[x2][y] != BULB) {
                if (table[x2][y] != EMPTY) {
                    break;
                }
            } else {
                return false;
            }
        }
        // checks for another bulb shinning on it from the top
        for (int y2 = y - 1; y2 >= 0; y2--) {
            if (table[x][y2] != BULB) {
                if (table[x][y2] != EMPTY) {
                    break;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks to see if the problem solution is valid. If the solution is valid then it returns a fitness which equals
     * the number of total white cells minus the total number of bulbs.
     *
     * @param originalTable the original problem table.
     * @param table the problem table.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     * @param checkBlackCellConstraint whether or not to check if the black cell constraints were satisfied.
     * @return the number of lit cells, assuming the solution is valid. If the solution is not valid then return 0.
     */
    static int evaluateFitness(int[][] originalTable, int[][] table, int tableX, int tableY, boolean checkBlackCellConstraint) {
        int[][] litCells = cloneArray(originalTable);
        int numberOfCellsLit = 0;

        for (int x = 0; x < tableX; x++) {
            for (int y = 0; y < tableY; y++) {
                // check to see if all the black cell constraints are satisfied
                if ((table[x][y] <= 4) && checkBlackCellConstraint) {
                    if (countCellsAround(table, tableX, tableY, x, y, BULB) != table[x][y]) {
                        return 0;
                    }
                }

                // check to see if two bulbs are shinning on each other
                if (table[x][y] == BULB) {
                    // checks for another bulb shinning on it from the bottom
                    for (int y2 = y + 1; y2 < tableY; y2++) {
                        if (table[x][y2] != BULB) {
                            if (table[x][y2] != EMPTY) {
                                break;
                            }
                        } else {
                            return 0;
                        }
                    }
                    // checks for another bulb shinning on it from the left
                    for (int x2 = x - 1; x2 >= 0; x2--) {
                        if (table[x2][y] != BULB) {
                            if (table[x2][y] != EMPTY) {
                                break;
                            }
                        } else {
                            return 0;
                        }
                    }
                    // checks for another bulb shinning on it from the right
                    for (int x2 = x + 1; x2 < tableX; x2++) {
                        if (table[x2][y] != BULB) {
                            if (table[x2][y] != EMPTY) {
                                break;
                            }
                        } else {
                            return 0;
                        }
                    }
                    // checks for another bulb shinning on it from the top
                    for (int y2 = y - 1; y2 >= 0; y2--) {
                        if (table[x][y2] != BULB) {
                            if (table[x][y2] != EMPTY) {
                                break;
                            }
                        } else {
                            return 0;
                        }
                    }
                }

                // checks to how many cells are lit
                if (table[x][y] == BULB) {
                    // count cells with bulb
                    litCells[x][y] = LIT;
                    // count cells underneath
                    for (int y2 = y + 1; y2 < tableY; y2++) {
                        if (table[x][y2] > 5) {
                            litCells[x][y2] = LIT;
                        } else {
                            break;
                        }
                    }
                    // count cells to the left
                    for (int x2 = x - 1; x2 >= 0; x2--) {
                        if (table[x2][y] > 5) {
                            litCells[x2][y] = LIT;
                        } else {
                            break;
                        }
                    }
                    // count cells to the right
                    for (int x2 = x + 1; x2 < tableX; x2++) {
                        if (table[x2][y] > 5) {
                            litCells[x2][y] = LIT;
                        } else {
                            break;
                        }
                    }
                    // count cells above
                    for (int y2 = y - 1; y2 >= 0; y2--) {
                        if (table[x][y2] > 5) {
                            litCells[x][y2] = LIT;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        // iterate over lit cells array to count the total number of lit cells
        for (int x = 0; x < tableX; x++) {
            for (int y = 0; y < tableY; y++)
                if (litCells[x][y] == LIT) {
                    numberOfCellsLit++;
                }
        }

        return numberOfCellsLit;
    }

    /**
     * Clones the provided array.
     *
     * @param source the array being copied.
     * @return a new clone of the provided array.
     */
    public static int[][] cloneArray(int[][] source) {
        int length = source.length;
        int[][] target = new int[length][source[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(source[i], 0, target[i], 0, source[i].length);
        }
        return target;
    }
}
