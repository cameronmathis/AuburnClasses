import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

/**
 * Author - Cameron Mathis
 * Date - 9/13/20
 *
 * This class handles all the reading and writing to files for Assignment 1B.
 */
public class FileController {
    /**
     * Read in the config file and set appropriate variable values.
     *
     * @param filePath the file path+name to the appropriate config.properties file.
     * @return the original problem table as a two dimensional array.
     */
    static int[][] readConfigFile(String filePath) {
        Properties prop = new Properties();
        FileInputStream ip;
        try {
            ip = new FileInputStream(filePath);
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read in config information
        String randomBased = prop.getProperty("randomBased");
        int seedNumber = Integer.parseInt(prop.getProperty("seedNumber"));
        String searchAlgorithm = prop.getProperty("initializationAlgorithm");
        int numberOfRuns = Integer.parseInt(prop.getProperty("numberRuns"));
        int mu = Integer.parseInt(prop.getProperty("mu"));
        int lambda = Integer.parseInt(prop.getProperty("lambda"));
        String parentSelectionAlgorithm = prop.getProperty("parentsSelection");
        int kParent = Integer.parseInt(prop.getProperty("kParent"));
        String survivalSelectionAlgorithm = prop.getProperty("survivalSelection");
        int kSurvival = Integer.parseInt(prop.getProperty("kSurvival"));
        String terminationCriterion = prop.getProperty("terminationCriterion");
        int numberOfFitnessEvaluations = Integer.parseInt(prop.getProperty("numberFitnessEvaluations"));
        int nForTermination = Integer.parseInt(prop.getProperty("n"));
        String solutionFilePath = prop.getProperty("solutionFilePath");
        String logFilePath = prop.getProperty("logFilePath");
        boolean doCheckBlackCells = Boolean.parseBoolean(prop.getProperty("doCheckBlackCells"));

        // store information in ConfigController.java
        ConfigController.setRandomBased(randomBased);
        if (randomBased.equals("seed")) {
            ConfigController.setSeedNumber(seedNumber);
        }
        ConfigController.setInitializationAlgorithm(searchAlgorithm);
        ConfigController.setNumberOfRuns(numberOfRuns);
        ConfigController.setMu(mu);
        ConfigController.setLambda(lambda);
        ConfigController.setParentSelectionAlgorithm(parentSelectionAlgorithm);
        ConfigController.setKParent(kParent);
        ConfigController.setSurvivalSelectionAlgorithm(survivalSelectionAlgorithm);
        ConfigController.setKSurvival(kSurvival);
        ConfigController.setTerminationCriterion(terminationCriterion);
        ConfigController.setNumberOfFitnessEvaluations(numberOfFitnessEvaluations);
        ConfigController.setNForTermination(nForTermination);
        ConfigController.setSolutionFilePath(solutionFilePath);
        ConfigController.setLogFilePath(logFilePath);
        ConfigController.setDoCheckBlackCells(doCheckBlackCells);

        // create the result log
        createResultLog(logFilePath, ConfigController.getProblemFilePath(), solutionFilePath,
                ConfigController.getSeedNumber(), randomBased, numberOfRuns, mu, lambda, parentSelectionAlgorithm, kParent,
                survivalSelectionAlgorithm, kSurvival, terminationCriterion, numberOfFitnessEvaluations, nForTermination, searchAlgorithm, doCheckBlackCells);

        // read the problem table
        return readProblemFile(ConfigController.getProblemFilePath());
    }

    /**
     * Reads in the problem file and then creates an array with 100 in the white slots and the appropriate number on
     * the black spots.
     *
     * @param problemFilePath the relative file path+name of the log file.
     * @return the problem table as a two dimensional array.
     */
    private static int[][] readProblemFile(String problemFilePath) {
        int[][] result = new int[0][0];
        int tableX = 0;
        int tableY = 0;
        try {
            File problemFile = new File(problemFilePath);
            Scanner myReader = new Scanner(problemFile);
            if (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tableX = Integer.parseInt(data);
                data = myReader.nextLine();
                tableY = Integer.parseInt(data);
            }
            result = new int[tableX][tableY];
            for (int x = 0; x < tableX; x++) {
                for (int y = 0; y < tableY; y++) {
                    result[x][y] = ExperimentController.EMPTY;
                }
            }
            while (myReader.hasNextLine()) {
                // subtract 1 since the array index starts at 0 and not 1
                int x = myReader.nextInt() - 1;
                // subtract it from five since array indexing starts at 0 and the top instead of 1 and the bottom
                int y = tableY - myReader.nextInt();
                int z = myReader.nextInt();
                result[x][y] = z;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Creates the result log file with appropriate starting information.
     *
     * @param logFilePath the relative file path+name of the log file.
     * @param problemFilePath the relative file path+name of the problem file.
     * @param solutionFilePath the relative file path+name of the solution file.
     * @param seedNumber the seed number (type int) used in the experiment.
     * @param randomBased a string stating if the seed if based on "time" or a given "seed".
     * @param numberOfRuns the number of runs (type int) to be performed.
     * @param mu individuals in the adult pool.
     * @param lambda offspring in each generation.
     * @param parentSelectionAlgorithm the algorithm used for parent selection.
     * @param kParent tournament size for parent selection.
     * @param survivalSelectionAlgorithm the algorithm used for survival selection.
     * @param kSurvival tournament size for survival selection.
     * @param terminationCriterion the criterion used to determine termination.
     * @param numberOfFitnessEvaluations the number of fitness evaluations (type int) to be performed.
     * @param nForTermination n for termination convergence criterion.
     * @param searchAlgorithm the search algorithm used.
     * @param doCheckBlackCells a boolean value stating if solutions were against black cell constraints for validity.
     */
    static void createResultLog(String logFilePath, String problemFilePath, String solutionFilePath, int seedNumber,
                                String randomBased, int numberOfRuns, int mu, int lambda, String parentSelectionAlgorithm,
                                int kParent, String survivalSelectionAlgorithm, int kSurvival, String terminationCriterion,
                                int numberOfFitnessEvaluations, int nForTermination, String searchAlgorithm, boolean doCheckBlackCells) {
        try {
            FileWriter writer = new FileWriter(logFilePath);
            writer.write("Result Log\r\n");
            writer.write("Problem File Path+Name: " + problemFilePath + "\r\n");
            writer.write("Solution File Path+Name: " + solutionFilePath + "\r\n");
            writer.write("Random Number Seed: " + seedNumber + "\r\n");
            writer.write("Algorithm parameters:\r\n");
            writer.write("\tRandom Number Based Upon: " + randomBased + "\r\n");
            writer.write("\tNumber of Runs: " + numberOfRuns + "\r\n");
            writer.write("\tMu: " + mu + "\r\n");
            writer.write("\tLambda: " + lambda + "\r\n");
            writer.write("\tAlgorithm Used for Parent Selection: " + parentSelectionAlgorithm + "\r\n");
            if (parentSelectionAlgorithm.equals("tournament")) {
                writer.write("\t\tTournament Size for Parent Selection: " + kParent + "\r\n");
            }
            writer.write("\tAlgorithm Used for Survival Selection: " + survivalSelectionAlgorithm + "\r\n");
            if (survivalSelectionAlgorithm.equals("tournament")) {
                writer.write("\t\tTournament Size for Survival Selection: " + kSurvival + "\r\n");
            }
            writer.write("\tCriterion Used for Termination: " + terminationCriterion + "\r\n");
            if (terminationCriterion.equals("numberOfEvals")) {
                writer.write("\t\tNumber of Evaluations Till Termination: " + numberOfFitnessEvaluations + "\r\n");
            }
            if (terminationCriterion.equals("convergence")) {
                writer.write("\t\tN for Termination Convergence Criterion: " + nForTermination + "\r\n");
            }
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
     * @param evaluationCount the current evaluation count being logged.
     * @param fitnessScore the fitness score of the evaluation being logged.
     * @param bestPopulationFitness the best fitness of the experiment.
     */
    static void addResultToLog(int evaluationCount, double fitnessScore, int bestPopulationFitness) {
        try {
            FileWriter writer = new FileWriter(ConfigController.getLogFilePath(), true);
            if (evaluationCount == ConfigController.getLambda()) {
                writer.write("\r\n" + ConfigController.getMu() + "\t" + fitnessScore + "\t" + bestPopulationFitness);
            } else {
                writer.write("\r\n" + evaluationCount + "\t" + fitnessScore + "\t" + bestPopulationFitness);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the solution file.
     *
     * @param solutionFilePath the relative file path+name of the solution file.
     * @param problemFilePath the relative file path+name of the problem file.
     * @param bestSolution the best solution found in the experiment.
     * @param experimentMaxFitnessScore the maximum fitness score found in the experiment.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     */
    static void createSolutionFile(String solutionFilePath, String problemFilePath, int[][] bestSolution,
                                   int experimentMaxFitnessScore, int tableX, int tableY) {
        try {
            if (Files.exists(Paths.get(solutionFilePath))) {
                Files.delete(Paths.get(solutionFilePath));
            }
            Files.copy(Paths.get(problemFilePath), Paths.get(solutionFilePath));
            FileWriter writer = new FileWriter(solutionFilePath, true);
            writer.write("\r\n" + experimentMaxFitnessScore); // write the number of cells lit
            for (int x = 0; x < tableX; x++) {
                for (int y = 0; y < tableY; y++) {
                    // writes each coordinate to the solution file
                    if (bestSolution[x][y] == ExperimentController.BULB) {
                        // add 1 since the array index starts at 0 and not 1
                        int xOut = x + 1;
                        // subtract it from five since array indexing starts at 0 and the top, instead of 1 and the bottom
                        int yOut = tableY - y;
                        writer.write("\r\n" + xOut + " " + yOut);
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
