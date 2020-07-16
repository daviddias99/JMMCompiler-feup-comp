import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner();
        try {
            scanner.processFile("src/tokens.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
