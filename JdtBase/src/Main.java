import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        int x = 8;
        int y = 9;
        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
        System.out.println(decimalFormat.format((double) x / y));
    }
}
