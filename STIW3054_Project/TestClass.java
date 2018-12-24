import java.io.IOException;
import java.text.DecimalFormat;

public class TestClass {

    public static void main(String[] args) throws IOException {
        double[][] value = new double[5][5];
        value[0][0] = 1;
        value[0][1] = 2;
        value[1][0] = 3;

        System.out.println(value[1]);

    }
}
