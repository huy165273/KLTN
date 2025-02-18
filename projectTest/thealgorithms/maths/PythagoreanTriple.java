package projectTest.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Pythagorean_triple
 */
public final class PythagoreanTriple {

    /**
     * Check if a,b,c are a Pythagorean Triple
     *
     * @param a x/y component length of a right triangle
     * @param b y/x component length of a right triangle
     * @param c hypotenuse length of a right triangle
     * @return boolean <tt>true</tt> if a, b, c satisfy the Pythagorean theorem,
     * otherwise
     * <tt>false</tt>
     */
    public static boolean isPythagTriple(int a, int b, int c) {
        if ((a <= 0) || (b <= 0) || (c <= 0)) {
            return false;
        } else {
            return (a * a) + (b * b) == (c * c);
        }
    }
}
