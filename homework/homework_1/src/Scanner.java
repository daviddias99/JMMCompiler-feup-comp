import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that represents the scanner.
 */
public class Scanner {

    private DFA dfa; /** scanner DFA */

    public Scanner() {
        dfa = new DFA();
    }

    /**
     * Process a file.
     */
    public void processFile(String fileName) throws IOException {
        String tokens = Files.readString(Paths.get(fileName));

        int currentTokenPos = 0;
        char currentToken;
        boolean skipToken = false;
        DFA.State nextState;
        String tokenString = "";

        while (currentTokenPos < tokens.length()) { // while there are tokens to read
            currentToken = tokens.charAt(currentTokenPos);

            if (currentToken == '\n' || currentToken == '\r' ||currentToken == ' ' || currentToken == '\t') {
                currentTokenPos++;
                skipToken = true;
                continue;
            }

            nextState = dfa.processChar(currentToken);

            if (((nextState == DFA.State.DEAD) && dfa.isFinalState()) || skipToken) {
                dfa.printStateName(tokenString);
                tokenString = "";
                dfa.setCurrentState(DFA.State.START);
            }
            else {
                tokenString += currentToken;
                dfa.setCurrentState(nextState);

                if (dfa.getCurrentState() == DFA.State.DEAD) {
                    System.out.println("Error in char number " + currentTokenPos);
                    System.exit(1);
                }

                currentTokenPos++;
            }

            skipToken = false;
        }

        if (dfa.isFinalState()) {
            dfa.printStateName(tokenString);
        }
    }
}
