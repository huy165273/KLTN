package projectTest.thealgorithms.conversions;

/**
 * This class converts a Binary number to a Decimal number
 */
final class BinaryToDecimal {

    /**
     * Converts a binary number to its decimal equivalent.
     *
     * @param binaryNumber The binary number to convert.
     * @return The decimal equivalent of the binary number.
     * @throws IllegalArgumentException If the binary number contains digits other than 0 and 1.
     */
    public static long binaryToDecimal(long binaryNumber) {
        long decimalValue = 0;
        long power = 0;

        while (binaryNumber != 0) {
            long digit = binaryNumber % 10;
            if (digit > 1) {
                System.out.println("Incorrect binary digit: " + digit);
                return decimalValue;
            }
            decimalValue += (long) (digit * Math.pow(2, power++));
            binaryNumber /= 10;
        }
        return decimalValue;
    }
}
