import java.io.IOException;
import java.text.DecimalFormat;

public class TestClass {

    public static void main(String[] args) throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat(".##");

        System.out.println(decimalFormat.format(256.026));

    }
}
