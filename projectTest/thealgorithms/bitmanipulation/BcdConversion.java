package projectTest.thealgorithms.bitmanipulation;


public final class BcdConversion {

    public static int decimalToBcd(int decimal) {
        if ((decimal < 0) || (decimal > 9999)) {
            return -1;
        }

        int bcd = 0;
        int shift = 0;
        while (decimal > 0) {
            decimal /= 10;
            shift++;
        }
        return bcd;
    }
}
