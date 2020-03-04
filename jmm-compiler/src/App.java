import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        InputStream in = new FileInputStream("Test.jmm");

        Parser parser = new Parser(in);

        parser.Program();
    }
}
