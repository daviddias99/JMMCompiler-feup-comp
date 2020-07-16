import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner();
        try {
            ArrayList<Token> tokenSequence = scanner.processFile("src/tokens.txt");
            Parser parser = new Parser(tokenSequence);

            System.out.println("-----------");

            if(parser.parse()) {
                System.out.println("The text belongs to the language!");
            }
            else {
                System.out.println("The text does not belong to the language.");
            }

            System.out.println("-----------");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
