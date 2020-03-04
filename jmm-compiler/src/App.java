import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws ParseException {

        InputStream in = null;
        try {
            in = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println(args[0] + " file not found");
            return;
        }

        Parser parser = new Parser(in);
        parser.Program();
    }
}
