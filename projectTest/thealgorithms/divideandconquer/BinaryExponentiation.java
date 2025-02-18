package projectTest.thealgorithms.divideandconquer;

// Java Program to Implement Binary Exponentiation (power in log n)

// Reference Link: https://en.wikipedia.org/wiki/Exponentiation_by_squaring

/*
 * Binary Exponentiation is a method to calculate a to the power of b.
 * It is used to calculate a^n in O(log n) time.
 *
 * Reference:
 * https://iq.opengenus.org/binary-exponentiation/
 */

public class BinaryExponentiation {


    // iterative function to calculate a to the power of b
    static long power(long n, long m) {
        long power = n;
        long sum = 1;
        while (m > 0) {
            if ((m & 1) == 1) {
                sum *= power;
            }
            power = power * power;
            m /= 2;
        }
        return sum;
    }
}
