package projectTest.thealgorithms.maths;

/**
 * Find the number of digits in a number.
 */
public final class NumberOfDigits {
    /**
     * Find the number of digits in a number.
     *
     * @param number number to find
     * @return number of digits of given number
     */
    public static int numberOfDigits(int number) {
        int digits = 0;
        do {
            digits++;
            number /= 10;
        } while (number != 0);
        return digits;
    }
}
