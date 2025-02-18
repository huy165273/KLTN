package projectTest.thealgorithms.conversions;

/**
 * Converts any Hexadecimal Number to Octal
 *
 * @author Tanmay Joshi
 */
public final class HexToOct {

    /**
     * Converts a Decimal number to an Octal number.
     *
     * @param decimal The Decimal number as an integer.
     * @return The Octal equivalent as an integer.
     */
    public static int decimalToOctal(int decimal) {
        int octalValue = 0;
        int placeValue = 1;

        while (decimal > 0) {
            int remainder = decimal % 8;
            octalValue += remainder * placeValue;
            decimal /= 8;
            placeValue *= 10;
        }

        return octalValue;
    }
}
