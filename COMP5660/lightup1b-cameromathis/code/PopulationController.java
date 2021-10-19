import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Author - Cameron Mathis
 * Date - 9/13/20
 *
 * This class handles the population for Assignment 1B.
 */
public class PopulationController {
    // Global Variables
    private static Individual[] population;
    private static Individual[] parents;
    private static Individual[] offspring;
    private static int runBestFitness = 0;
    private static int[][] bestSolution;
    private static int absoluteBestFitness = 0;

    /**
     * Initialize the population for the experiment.
     *
     * @param originalProblemTable the original problem table.
     * @param rand the Random object initialize with a seed.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     */
    static void initializePopulation(Random rand, int[][] originalProblemTable,
                                     int tableX, int tableY) {
        // get mu and lambda value from config controller
        int mu = ConfigController.getMu();
        int lambda = ConfigController.getLambda();
        // set lengths for arrays
        population = new Individual[mu + lambda];
        parents = new Individual[lambda];
        offspring = new Individual[lambda];
        // generate mu individuals and store them
        for (int i = 0; i < mu; i++) {
            int[][] problemTable = ExperimentController.cloneArray(originalProblemTable);
            // perform either Uniform Random Search or Validity Forced Uniform Random Search
            if (ConfigController.getInitializationAlgorithm().equals("uniformRandomSearch")) {
                ExperimentController.uniformRandomSearch(rand, problemTable, tableX, tableY,
                        countNumberOfBlackCells(originalProblemTable, tableX, tableY));
            } else if (ConfigController.getInitializationAlgorithm().equals("validityForcedUniformRandomSearch")) {
                ExperimentController.validityForcedUniformRandomSearch(rand, problemTable, tableX, tableY,
                        countNumberOfBlackCells(originalProblemTable, tableX, tableY));
            }
            // calculate fitness of generated individual
            int fitnessScore = ExperimentController.evaluateFitness(originalProblemTable, problemTable, tableX, tableY,
                    ConfigController.getDoCheckBlackCells());
            // store individual in the population
            Individual individual = new Individual();
            individual.setGenotype(problemTable).setFitnessValue(fitnessScore);
            addToPopulation(individual, i);
            // log first individual's solution in case no valid solution is found
            if (i == 0) {
                bestSolution = individual.genotype;
            }
        }
    }

    /**
     * Counts the number of black cells in a problem table.
     *
     * @param table the problem table we are counting black cells in.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     * @return the number of black cells in the problem table.
     */
    static int countNumberOfBlackCells(int[][] table, int tableX, int tableY) {
        int numberOfBlackCells = 0;
        for (int x = 0; x < tableX; x++) {
            for (int y = 0; y < tableY; y++) {
                if (table[x][y] <= 5) {
                    numberOfBlackCells++;
                }
            }
        }
        return numberOfBlackCells;
    }

    /**
     * Add an individual to the population array.
     *
     * @param individual the individual to be added.
     * @param index the index to add the individual.
     */
    private static void addToPopulation(Individual individual, int index) {
        population[index] = individual;
    }

    /**
     * Select parents and store them in an array.
     *
     * @param rand the Random object initialize with a seed.
     */
    static void selectParents(Random rand) {
        if (ConfigController.getParentSelectionAlgorithm().equals("FPS")) {
            fitnessProportionalSelection(rand);
        } else if (ConfigController.getParentSelectionAlgorithm().equals("tournament")) {
            for (int i = 0; i < parents.length; i++) {
                parents[i] = parentSelectionTournament(rand, population, ConfigController.getMu(), ConfigController.getKParent());
            }
        }
    }

    /**
     * This is a fitness proportional selection algorithm based upon the one on page 83 in the book.
     * Modification: Instead of having the min and max fitness values be [0,1], I calculated the max myself.
     *
     * @param rand the Random object initialize with a seed.
     */
    private static void fitnessProportionalSelection(Random rand) {
        Individual[] temp = new Individual[ConfigController.getMu()];
        System.arraycopy(population, 0, temp, 0, temp.length);
        Arrays.sort(temp);
        int totalFitness = 0;
        for (Individual individual : temp) {
            totalFitness += individual.fitnessValue;
        }
        int currentIndividual = 0;
        while (currentIndividual < ConfigController.getLambda()) {
            int r = rand.nextInt(totalFitness + 1);
            int tempFitnessSum = 0;
            int i = 0;
            while (tempFitnessSum < r) {
                tempFitnessSum += temp[i].fitnessValue;
                i++;
            }
            // subtract 1 because it increments 1 to far in the above while loop
            if (r != 0) {
                i--;
            }
            parents[currentIndividual] = temp[i];
            currentIndividual++;
        }
    }

    /**
     * Perform a selection tournament with replacement in order to select parents.
     *
     * @param rand the Random object initialize with a seed.
     * @param population the population array.
     * @param parentsLength the length of the parents array.
     * @param k the number of participants in the tournament.
     * @return the winning individual.
     */
    private static Individual parentSelectionTournament(Random rand, Individual[] population,
                                                        int parentsLength, int k) {
        Individual bestIndividual = null;
        int[] selectedParticipants = new int[k];
        for (int i = 0; i < k; i++) {
            int r = rand.nextInt(parentsLength);
            boolean notSelected = true;
            // check to see if the individual has been selected before
            for (int y = 0; y < k; y++) {
                if (selectedParticipants[y] == (r)) {
                    notSelected = false;
                    break;
                }
            }
            // if it has not been selected then add it to the tournament
            if (notSelected) {
                selectedParticipants[i] = r;
                if (i == 0) {
                    bestIndividual = population[r];
                } else if (population[r].fitnessValue > bestIndividual.fitnessValue) {
                    bestIndividual = population[r];
                }
            } else {
                i--;
            }
        }
        return bestIndividual;
    }

    /**
     * Generates and stores the offsprings of two randomly selected parents
     *
     * @param rand the Random object initialize with a seed.
     * @param tableX the x length of the problem table.
     * @param tableY the y length of the problem table.
     */
    public static void generateOffspring(Random rand, int[][] originalProblemTable, int tableX, int tableY) {
        for (int i = 0; i < offspring.length; i += 2) {
            // generate 2 random integers between 0 and the number of parents
            int r1 = rand.nextInt(parents.length);
            int r2 = rand.nextInt(parents.length);
            // loop until r1 != r2
            while (r1 == r2) {
                r2 = rand.nextInt(parents.length);
            }
            // select parents
            Individual parent1 = parents[r1];
            Individual parent2 = parents[r2];
            // initialize offspring
            Individual offspring1 = new Individual().setGenotype(new int[tableX][tableY]);
            Individual offspring2 = new Individual().setGenotype(new int[tableX][tableY]);
            // parents create offspring
            for (int x = 0; x < tableX; x++) {
                if (x % 2 == 0) {
                    offspring1.genotype[x] = parent1.genotype[x];
                    offspring2.genotype[x] = parent2.genotype[x];
                } else {
                    offspring1.genotype[x] = parent2.genotype[x];
                    offspring2.genotype[x] = parent1.genotype[x];
                }
            }

            // evaluate offspring1 fitness
            int fitness = ExperimentController.evaluateFitness(originalProblemTable, offspring1.genotype,
                    tableX, tableY, ConfigController.getDoCheckBlackCells());
            // check if solution is best
            if (fitness > runBestFitness) {
                runBestFitness = fitness;
                if (fitness > absoluteBestFitness) {
                    absoluteBestFitness = fitness;
                    bestSolution = offspring1.genotype;
                }
            }
            // store fitness value
            offspring1.setFitnessValue(fitness);
            // store offspring
            addToOffspring(offspring1, i);

            // if lambda is set to an odd number then disregard the last offspring2
            if (i + 1 < offspring.length) {
                // evaluate offspring2 fitness
                fitness = ExperimentController.evaluateFitness(originalProblemTable, offspring2.genotype,
                        tableX, tableY, ConfigController.getDoCheckBlackCells());
                // check if solution is best
                if (fitness > runBestFitness) {
                    runBestFitness = fitness;
                    if (fitness > absoluteBestFitness) {
                        absoluteBestFitness = fitness;
                        bestSolution = offspring2.genotype;
                    }
                }
                // store fitness value
                offspring2.setFitnessValue(fitness);
                // store offspring
                addToOffspring(offspring2, i + 1);
            }
        }

        // store both parent and offspring arrays into one population array
        combineParentsAndOffspring();
    }

    /**
     * Add an individual to the offspring array.
     *
     * @param individual the individual to be added.
     * @param index the index to add the individual.
     */
    private static void addToOffspring(Individual individual, int index) {
        offspring[index] = individual;
    }

    /**
     * Combine the parents and offspring arrays into one population array.
     */
    private static void combineParentsAndOffspring() {
        // add parents to population
        System.arraycopy(parents, 0, population, 0, parents.length);
        // add offspring to population
        System.arraycopy(offspring, 0, population, ConfigController.getMu(), offspring.length);
    }

    /**
     * Select survivors and store them in an array.
     *
     * @param rand the Random object initialize with a seed.
     */
    static void selectSurvivors(Random rand) {
        if (ConfigController.getSurvivalSelectionAlgorithm().equals("truncation")) {
            truncation(ConfigController.getMu());
        } else if (ConfigController.getSurvivalSelectionAlgorithm().equals("tournament")) {
            Individual[] tournamentPool = new Individual[population.length];
            System.arraycopy(population, 0, tournamentPool, 0, population.length);
            population = new Individual[population.length];
            for (int i = 0; i < ConfigController.getMu(); i++) {
                int indexOfWinningIndividual = survivalSelectionTournament(rand, tournamentPool, tournamentPool.length, ConfigController.getKSurvival());
                addToPopulation(tournamentPool[indexOfWinningIndividual], i);
                // remove the selected individual from tournamentPool so that it cannot entire another tournament
                Individual[] temp = new Individual[tournamentPool.length];
                System.arraycopy(tournamentPool, 0, temp, 0, tournamentPool.length);
                tournamentPool = new Individual[tournamentPool.length - 1];
                System.arraycopy(removeIndividual(temp, indexOfWinningIndividual), 0, tournamentPool, 0, tournamentPool.length);
            }
        }
    }

    /**
     * Performs truncation to select survivors.
     *
     * @param numberToSelect the number of individuals to survive.
     */
    private static void truncation(int numberToSelect) {
        Arrays.sort(population, Collections.reverseOrder());
        Individual[] temp = new Individual[population.length];
        System.arraycopy(population, 0, temp, 0,temp.length);
        population = new Individual[population.length];
        if (numberToSelect >= 0) {
            System.arraycopy(temp, 0, population, 0, numberToSelect);
        }
    }

    /**
     * Perform a selection tournament with replacement in order to select survivors.
     *
     * @param rand the Random object initialize with a seed.
     * @param population the population array.
     * @param populationLength the length of the population array.
     * @param k the number of participants in the tournament.
     * @return the winning individual's index.
     */
    private static int survivalSelectionTournament(Random rand, Individual[] population, int populationLength, int k) {
        Individual bestIndividual = null;
        int indexOfBestIndividual = 0;
        int[] selectedParticipants = new int[k];
        for (int i = 0; i < k; i++) {
            int r = rand.nextInt(populationLength);
            boolean notSelected = true;
            // check to see if the individual has been selected before
            for (int y = 0; y < k; y++) {
                if (selectedParticipants[y] == (r)) {
                    notSelected = false;
                    break;
                }
            }
            // if it has not been selected then add it to the tournament
            if (notSelected && population[r] != null) {
                selectedParticipants[i] = r;
                if (i == 0) {
                    bestIndividual = population[r];
                    indexOfBestIndividual = r;
                } else if (population[r].fitnessValue > bestIndividual.fitnessValue) {
                    bestIndividual = population[r];
                    indexOfBestIndividual = r;
                }
            } else {
                i--;
            }
        }
        return indexOfBestIndividual;
    }

    /**
     * Remove an individual from an array of individuals.
     *
     * @param groupOfIndividuals the array of individuals.
     * @param indexOfIndividualToRemove the index of the individual to be removed.
     * @return the new array of individuals.
     */
    private static Individual[] removeIndividual(Individual[] groupOfIndividuals, int indexOfIndividualToRemove) {
        Individual[] newGroupOfIndividuals = new Individual[groupOfIndividuals.length - 1];
        int j = 0;
        for (int i = 0; i < groupOfIndividuals.length; i++) {
            if (i != indexOfIndividualToRemove) {
                newGroupOfIndividuals[j] = groupOfIndividuals[i];
                j++;
            }
        }
        return newGroupOfIndividuals;
    }

    /**
     * Calculates the average fitness of a population.
     *
     * @return the average population fitness.
     */
    static double calculateAveragePopulationFitness() {
        double fitnessSum = 0;
        for (int i = 0; i < ConfigController.getMu(); i++) {
            fitnessSum += population[i].fitnessValue;
        }
        return fitnessSum / population.length;
    }

    /**
     * Get the best solution of an experiment.
     *
     * @return the best solution of an experiment.
     */
    static int[][] getBestSolution() {
        return bestSolution;
    }

    /**
     * Return the best fitness of the current run.
     *
     * @return the best fitness of the run.
     */
    static int getRunBestFitness() {
        return runBestFitness;
    }

    /**
     * Return the best fitness of the experiment.
     *
     * @return the best fitness of the experiment.
     */
    static int getAbsoluteBestFitness() {
        return absoluteBestFitness;
    }

    /**
     * Reset the runBestFitness to 0.
     */
    static void resetRunBestFitness() {
        runBestFitness = 0;
    }
}
