package projectTest.thealgorithms.maths;

/**
 *  This class provides methods to compute the Greatest Common Divisor (GCD) of two or more integers.
 *
 * The Greatest Common Divisor (GCD) of two or more integers is the largest positive integer that divides each of the integers without leaving a remainder.
 *
 * The GCD can be computed using the Euclidean algorithm, which is based on the principle that the GCD of two numbers also divides their difference.
 *
 * For more information, refer to the
 * <a href="https://en.wikipedia.org/wiki/Greatest_common_divisor">Greatest Common Divisor</a> Wikipedia page.
 *
 * <b>Example usage:</b>
 * <pre>
 * int result1 = GCD.gcd(48, 18);
 * System.out.println("GCD of 48 and 18: " + result1); // Output: 6
 *
 * int result2 = GCD.gcd(48, 18, 30);
 * System.out.println("GCD of 48, 18, and 30: " + result2); // Output: 6
 * </pre>
 * @author Oskar Enmalm 3/10/17
 */
public final class GCD {
    /**
     * get the greatest common divisor
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return gcd
     */
    public static int gcd(int num1, int num2) {
        if ((num1 < 0) || (num2 < 0)) {
            return -1;
        }

        if ((num1 == 0) || (num2 == 0)) {
            return (num1 - num2);
        }

        while (num1 % num2 != 0) {
            int remainder = num1 % num2;
            num1 = num2;
            num2 = remainder;
        }
        return num2;
    }
}
