package projectTest.thealgorithms.maths;

/**
 * @author https://github.com/shellhub/
 */
public final class GCDRecursion {
    /**
     * get greatest common divisor
     *
     * @param a the first number
     * @param b the second number
     * @return gcd
     */
    public static int gcdRecursion(int a, int b) {
        if ((a < 0) || (b < 0)) {
            return -1;
        }

        if ((a == 0) || (b == 0)) {
            return a - b;
        }

        if (a % b == 0) {
            return b;
        } else {
            return -1;
        }
    }
}
