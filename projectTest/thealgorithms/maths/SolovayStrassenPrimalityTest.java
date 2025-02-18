package projectTest.thealgorithms.maths;


final class SolovayStrassenPrimalityTest {


    /**
     * Calculates modular exponentiation using the method of exponentiation by squaring.
     *
     * @param base the base number
     * @param exponent the exponent
     * @param mod the modulus
     * @return (base^exponent) mod mod
     */
    private static long calculateModularExponentiation(long base, long exponent, long mod) {
        long x = 1; // This will hold the result of (base^exponent) % mod
        long y = base; // This holds the current base value being squared

        while (exponent > 0) {
            // If exponent is odd, multiply the current base (y) with x
            if (exponent % 2 == 1) {
                x = x * y % mod; // Update result with current base
            }
            // Square the base for the next iteration
            y = y * y % mod; // Update base to be y^2
            exponent = exponent / 2; // Halve the exponent for next iteration
        }

        return x % mod; // Return final result after all iterations
    }

    /**
     * Computes the Jacobi symbol (a/n), which is a generalization of the Legendre symbol.
     *
     * @param a the numerator
     * @param num the denominator (must be an odd positive integer)
     * @return the Jacobi symbol value: 1, -1, or 0
     */
    public static int calculateJacobi(long a, long num) {
        // Check if num is non-positive or even; Jacobi symbol is not defined in these cases
        if ((num <= 0) || (num % 2 == 0)) {
            return 0;
        }

        a = a % num; // Reduce a modulo num to simplify calculations
        int jacobi = 1; // Initialize Jacobi symbol value

        while (a != 0) {
            // While a is even, reduce it and adjust jacobi based on properties of num
            while (a % 2 == 0) {
                a /= 2; // Divide a by 2 until it becomes odd
                long nMod8 = num % 8; // Get num modulo 8 to check conditions for jacobi adjustment
                if ((nMod8 == 3) || (nMod8 == 5)) {
                    jacobi = -jacobi; // Flip jacobi sign based on properties of num modulo 8
                }
            }

            long temp = a; // Temporarily store value of a
            a = num; // Set a to be num for next iteration
            num = temp; // Set num to be previous value of a

            // Adjust jacobi based on properties of both numbers when both are odd and congruent to 3 modulo 4
            if ((a % 4 == 3) &&( num % 4 == 3)) {
                jacobi = -jacobi; // Flip jacobi sign again based on congruences
            }

            a = a % num; // Reduce a modulo num for next iteration of Jacobi computation
        }

        return (num == 1) ? jacobi : 0; // If num reduces to 1, return jacobi value, otherwise return 0 (not defined)
    }
}
