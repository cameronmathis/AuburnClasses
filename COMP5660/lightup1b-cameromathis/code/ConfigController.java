/**
 * Author - Cameron Mathis
 * Date - 9/13/20
 *
 * This class handles all the config information for Assignment 1B.
 */
public class ConfigController {
    // Global Variables
    private static String randomBased;
    private static int seedNumber;
    private static String initializationAlgorithm;
    private static int numberOfRuns;
    private static int mu;
    private static int lambda;
    private static String parentSelectionAlgorithm;
    private static int kParent;
    private static String survivalSelectionAlgorithm;
    private static int kSurvival;
    private static String terminationCriterion;
    private static int numberOfFitnessEvaluations;
    private static int nForTermination;
    private static String problemFilePath;
    private static String solutionFilePath;
    private static String logFilePath;
    private static boolean doCheckBlackCells;

    // setter methods
    static void setRandomBased(String s) {
        randomBased = s;
    }

    static void setSeedNumber(int i) {
        seedNumber = i;
    }

    static void setInitializationAlgorithm(String s) {
        initializationAlgorithm = s;
    }

    static void setNumberOfRuns(int i) {
        numberOfRuns = i;
    }

    static void setMu(int i) {
        mu = i;
    }

    static void setLambda(int i) {
        lambda = i;
    }

    static void setParentSelectionAlgorithm(String s) {
        parentSelectionAlgorithm = s;
    }

    static void setKParent(int i) {
        kParent = i;
    }

    static void setSurvivalSelectionAlgorithm(String s) {
        survivalSelectionAlgorithm = s;
    }

    static void setKSurvival(int i) {
        kSurvival = i;
    }

    static void setTerminationCriterion(String s) { terminationCriterion = s; }

    static void setNumberOfFitnessEvaluations(int i) {
        numberOfFitnessEvaluations = i;
    }

    static void setNForTermination(int i) {
        nForTermination = i;
    }

    static void setProblemFilePath(String s) {
        problemFilePath = s;
    }

    static void setSolutionFilePath(String s) {
        solutionFilePath = s;
    }

    static void setLogFilePath(String s) {
        logFilePath = s;
    }

    static void setDoCheckBlackCells(boolean b) {
        doCheckBlackCells = b;
    }

    // getter methods
    static String getRandomBased() {
        return randomBased;
    }

    static int getSeedNumber() {
        return seedNumber;
    }

    static String getInitializationAlgorithm() {
        return initializationAlgorithm;
    }

    static int getNumberOfRuns() {
        return numberOfRuns;
    }

    static int getMu() {
        return mu;
    }

    static int getLambda() {
        return lambda;
    }

    static String getParentSelectionAlgorithm() {
        return parentSelectionAlgorithm;
    }

    static int getKParent() {
        return kParent;
    }

    static String getSurvivalSelectionAlgorithm() {
        return survivalSelectionAlgorithm;
    }

    static int getKSurvival() {
        return kSurvival;
    }

    static String getTerminationCriterion() {
        return terminationCriterion;
    }

    static int getNumberOfFitnessEvaluations() {
        return numberOfFitnessEvaluations;
    }

    static int getNForTermination() {
        return nForTermination;
    }

    static String getProblemFilePath() {
        return problemFilePath;
    }

    static String getSolutionFilePath() {
        return solutionFilePath;
    }

    static String getLogFilePath() {
        return logFilePath;
    }

    static boolean getDoCheckBlackCells() {
        return doCheckBlackCells;
    }
}
