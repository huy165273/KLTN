package projectTest.thealgorithms.maths;

/**
 * <a href="https://en.wikipedia.org/wiki/Collatz_conjecture">...</a>
 */
public class CollatzConjecture {

    /**
     * Calculate the next number of the sequence.
     *
     * @param n current number of the sequence
     * @return next number of the sequence
     */
    public static int nextNumber( int n) {
        if (n % 2 == 0) {
            return n / 2;
        }
        return 3 * n + 1;
    }

}
