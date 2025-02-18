package projectTest.thealgorithms.maths;

/**
 * @see <a href="https://en.wikipedia.org/wiki/Combination">Combination</a>
 */
public final class Combinations {

    /**
     * The above method can exceed limit of long (overflow) when factorial(n) is
     * larger than limits of long variable. Thus even if nCk is within range of
     * long variable above reason can lead to incorrect result. This is an
     * optimized version of computing combinations. Observations: nC(k + 1) = (n
     * - k) * nCk / (k + 1) We know the value of nCk when k = 1 which is nCk = n
     * Using this base value and above formula we can compute the next term
     * nC(k+1)
     *
     * @param n
     * @param k
     * @return nCk
     */
    public static long combinationsOptimized(int n, int k) {
        if ((n < 0) || (k < 0)) {
            return 0;
        }
        if (n < k) {
            return 0;
        }
        // nC0 is always 1
        long solution = 1;
        for (int i = 0; i < k; i++) {
            solution = (n - i) * solution / (i + 1);
        }
        return solution;
    }
}
