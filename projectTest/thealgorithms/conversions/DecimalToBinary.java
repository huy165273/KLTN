package projectTest.thealgorithms.conversions;

/**
 * This class provides methods to convert a decimal number to a binary number.
 */
final class DecimalToBinary {


    /**
     * Converts a decimal number to a binary number using a conventional algorithm.
     * @param decimalNumber the decimal number to convert
     * @return the binary representation of the decimal number
     */
    public static int convertUsingConventionalAlgorithm(int decimalNumber) {
        int binaryNumber = 0;
        int position = 1;

        while (decimalNumber > 0) {
            int remainder = decimalNumber % 2;
            binaryNumber += remainder * position;
            position *= 10;
            decimalNumber /= 2;
        }

        return binaryNumber;
    }

    /**
     * Converts a decimal number to a binary number using a bitwise algorithm.
     * @param decimalNumber the decimal number to convert
     * @return the binary representation of the decimal number
     */
    public static int convertUsingBitwiseAlgorithm(int decimalNumber) {
        int binaryNumber = 0;
        int position = 1;

        while (decimalNumber > 0) {
            int leastSignificantBit = decimalNumber & 1;
            binaryNumber += leastSignificantBit * position;
            position *= 10;
            decimalNumber /= 2;
        }
        return binaryNumber;
    }
}
