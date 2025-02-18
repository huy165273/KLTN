package projectTest.thealgorithms.conversions;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class that provides methods to convert a decimal number to a string representation
 *  in any specified base between 2 and 36.
 *
 * @author Varun Upadhyay (<a href="https://github.com/varunu28">...</a>)
 */
public final class DecimalToAnyBase {

    /**
     * Converts an integer value to its corresponding character in the specified base.
     * This method is used to convert values from 0 to 35 into their appropriate character representation.
     * For example, 0-9 are represented as '0'-'9', and 10-35 are represented as 'A'-'Z'.
     *
     * @param value the integer value to convert (should be less than the base value)
     * @return the character representing the value in the specified base
     */
    private static char convertToChar(int value) {
        if ((value >= 0) && (value <= 9)) {
            return (char) ('0' + value);
        } else {
            return (char) ('A' + value - 10);
        }
    }
}
