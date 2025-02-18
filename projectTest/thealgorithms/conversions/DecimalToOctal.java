package projectTest.thealgorithms.conversions;

/**
 * This class converts Decimal numbers to Octal Numbers
 */
public final class DecimalToOctal {

    /**
     * Converts a decimal number to its octal equivalent.
     *
     * @param decimal The decimal number to convert.
     * @return The octal equivalent as an integer.
     * @throws IllegalArgumentException if the decimal number is negative.
     */
    public static int convertToOctal(int decimal) {
        if (decimal < 0) {
            return -1;
        }

        int octal = 0;
        int placeValue = 1;

        while (decimal != 0) {
            int remainder = decimal % 8;
            octal += remainder * placeValue;
            decimal /= 8;
            placeValue *= 10;
        }

        return octal;
    }
}
