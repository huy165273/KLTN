package projectTest.thealgorithms.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
public final class Fibonacci {


    /**
     * This method finds the nth fibonacci number using bottom up
     *
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth fibonacci number
     * <p>
     * This is optimized version of Fibonacci Program. Without using Hashmap and
     * recursion. It saves both memory and time. Space Complexity will be O(1)
     * Time Complexity will be O(n)
     * <p>
     * Whereas , the above functions will take O(n) Space.
     * @throws IllegalArgumentException if n is negative
     * @author Shoaib Rayeen (https://github.com/shoaibrayeen)
     */
    public static int fibOptimized(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        int prev = 0;
        int res = 1;
        int next;
        for (int i = 2; i <= n; i++) {
            next = prev + res;
            prev = res;
            res = next;
        }
        return res;
    }
}
