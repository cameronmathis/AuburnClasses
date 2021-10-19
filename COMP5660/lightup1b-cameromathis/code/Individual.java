/**
 * Author - Cameron Mathis
 * Date - 9/13/20
 *
 * This class is used to produce individuals for Assignment 1B.
 */
public class Individual implements Comparable<Individual>{
    // Instance Fields
    int[][] genotype;
    int fitnessValue;

    // Setter Methods
    public Individual setGenotype(int[][] genotype) {
        this.genotype = genotype;
        return this;
    }

    public Individual setFitnessValue(int fitnessValue) {
        this.fitnessValue = fitnessValue;
        return this;
    }

    @Override
    public int compareTo(Individual other) {
        return Integer.compare(this.fitnessValue, other.fitnessValue);
    }
}
