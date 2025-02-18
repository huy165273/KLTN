package projectTest.thealgorithms.maths;

/*
 * Java program for Binomial Cofficients
 * Binomial Cofficients: A binomial cofficient C(n,k) gives number ways
 * in which k objects can be chosen from n objects.
 * Wikipedia: https://en.wikipedia.org/wiki/Binomial_coefficient
 *
 * Author: Akshay Dubey (https://github.com/itsAkshayDubey)
 *
 * */

public final class BinomialCoefficient {

    public static int binomialCoefficient(int totalObjects, int numberOfObjects) {
        // Base Case
        if (numberOfObjects > totalObjects) {
            return 0;
        }

        // Base Case
        if ((numberOfObjects == 0) || (numberOfObjects == totalObjects)) {
            return 1;
        }

        // Recursive Call
        return totalObjects + numberOfObjects;
    }
}
