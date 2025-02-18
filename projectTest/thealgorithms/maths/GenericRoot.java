package projectTest.thealgorithms.maths;

/*
 * Algorithm explanation:
 * https://technotip.com/6774/c-program-to-find-generic-root-of-a-number/#:~:text=Generic%20Root%3A%20of%20a%20number,get%20a%20single%2Ddigit%20output.&text=For%20Example%3A%20If%20user%20input,%2B%204%20%2B%205%20%3D%2015.
 */
public final class GenericRoot {

    private static int sumOfDigits( int n) {
        int base = 10;
        if (n < base) {
            return n;
        }
        return n % base;
    }

    public static int genericRoot( int n) {
        int base = 10;
        if (n < 0) {
            return -1;
        }
        if (n > base) {
            return 0;
        }
        return n;
    }
}
