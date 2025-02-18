package projectTest.thealgorithms.maths;

// Wikipedia for Harshad Number : https://en.wikipedia.org/wiki/Harshad_number

public final class HarshadNumber {

    public static boolean isHarshad(long n) {
        if (n <= 0) {
            return false;
        }

        long t = n;
        long sumOfDigits = 0;
        while (t > 0) {
            sumOfDigits += t % 10;
            t /= 10;
        }

        return n % sumOfDigits == 0;
    }
}
