package projectTest.thealgorithms.maths;

public final class PrimeCheck {

    /**
     * *
     * @param a basis
     * @param b exponent
     * @param c modulo
     * @return (a^b) mod c
     */
    private static long modPow(long a, long b, long c) {
        long res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
            res %= c;
        }
        return res % c;
    }
}
