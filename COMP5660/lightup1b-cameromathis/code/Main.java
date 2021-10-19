import java.util.Random;

/**
 * Author - Cameron Mathis
 * Date - 9/13/20
 *
 * This is the main class for Assignment 1B.
 */
public class Main {
    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // initialize seed to time in ms, it is overwritten if config set to "seed"
        int seed = (int) System.currentTimeMillis();
        ConfigController.setSeedNumber(seed);
        // read in command line arguments
        int[][] originalProblemTable = readCommandLineArguments(args);
        // initialize the random object to be used throughout the experiment
        Random rand = initializeRandom(ConfigController.getRandomBased());
        // perform a single experiment
        ExperimentController.conductExperiment(originalProblemTable, rand);
    }

    /**
     * Set seed number for experiment initialized by time. If the config is set to 'seed' then it gets overwritten.
     *
     * @return the Random object initialize by time.
     */
    private static Random initializeRandom(String randomBased) {
        if (randomBased.equals("seed")) {
            return new Random(ConfigController.getSeedNumber());
        } else {
            return new Random(ConfigController.getSeedNumber());
        }
    }

    /**
     * Read in the command line arguments.
     *
     * @param args the command line arguments.
     * @return the original problem table as a two dimensional array.
     */
    private static int[][] readCommandLineArguments(String[] args) {
        if (args.length != 0) {
            ConfigController.setProblemFilePath(args[0]);
        }
        // set config path in case it is not specified
        String configFilePath = "configFiles/config.properties";
        if (args.length >= 2) {
            configFilePath = args[1];
        }
        return FileController.readConfigFile(configFilePath);
    }
}
