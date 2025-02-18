package projectTest.thealgorithms.bitmanipulation;

/**
 * Is number power of 2
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class IsPowerTwo {

    public static boolean isPowerTwo(int number) {
        if (number <= 0) {
            return false;
        }
        int ans = (number - 1);
        return ans == 0;
    }
}
