import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Class representing the parser (syntactic analysis).
 */
public class Parser {
    private ArrayList<Token> tokenSequence; /** Sequence of tokens originated by the scanner. */
    private ListIterator<Token> tokenIterator; /** Iterator for the token list. */

    /**
     * Constructor of the class.
     * @param tokenSequence - Sequence of tokens originated by the scanner.
     */
    public Parser(ArrayList<Token> tokenSequence) {
        this.tokenSequence = tokenSequence;
        this.tokenIterator = this.tokenSequence.listIterator(0);
    }


    /**
     * Main function of the parser. Returns a boolean indicating if the token sequence
     * (and therefore, the content of the file) belongs to the language or not.
     *
     * CFG used:
     * S ->  VAR EQ S'
     * S' -> INT SMICOLON S | E
     * E ->  Rhs SMICOLON
     * Rhs -> VAR Rhs' | LPAR Rhs RPAR
     * Rhs' -> PLUS Rhs Rhs' | MUL Rhs Rhs' | DIV Rhs Rhs' | SUB Rhs Rhs' | ε
     */
    public boolean parse() {
        return S() && !this.tokenIterator.hasNext();
    }


    /**
     * Start production.
     * S ->  VAR EQ S'
     */
    private boolean S() {
        Token token;

        if(!tokenIterator.hasNext())
            return false;

        token = tokenIterator.next();

        if (token.getType() != TokenType.VAR)
            return false;


        if(!tokenIterator.hasNext())
            return false;

        token = tokenIterator.next();
        if (token.getType() != TokenType.EQ)
            return false;


        return SPrime();
    }


    /**
     * Start prime production.
     * S' -> INT SMICOLON S | E
     */
    private boolean SPrime() {
        Token token;

        if(!tokenIterator.hasNext())
            return false;

        token = tokenIterator.next();
        if (token.getType() == TokenType.INT) { // should follow S' -> INT SMICOLON S

            if(!tokenIterator.hasNext())
                return false;

            token = tokenIterator.next();
            if (token.getType() == TokenType.SMICOLON) {
                return S();
            }
            else {
                return false;
            }

        }
        else { // should follow S' -> E
            tokenIterator.previous();
            return E();
        }
    }


    /**
     * Expression production.
     * E ->  Rhs SMICOLON
     */
    private boolean E() {
        Token token;

        if (Rhs()) {
            if (!tokenIterator.hasNext())
                return false;

            token = tokenIterator.next();
            return token.getType() == TokenType.SMICOLON;
        }
        return false;
    }


    /**
     * Right hand side production.
     * Rhs -> VAR Rhs' | LPAR Rhs RPAR
     */
    private boolean Rhs() {
        Token token;

        if (!tokenIterator.hasNext())
            return false;

        token = tokenIterator.next();
        if (token.getType() == TokenType.VAR)
            return RhsPrime();

        if (token.getType() == TokenType.LPAR) {
            if (! Rhs())
                return false;

            if (!tokenIterator.hasNext())
                return false;

            token = tokenIterator.next();

            return token.getType() == TokenType.RPAR;
        }

        return false;
    }

    /***
     * Right hand side prime production.
     * Rhs' -> PLUS Rhs Rhs' | MUL Rhs Rhs' | DIV Rhs Rhs' | SUB Rhs Rhs' | ε
     */
    private boolean RhsPrime() {
        Token token;

        if (!tokenIterator.hasNext())
            return false;

        token = tokenIterator.next();

        if (token.getType() == TokenType.PLUS || token.getType() == TokenType.MUL || token.getType() == TokenType.SUB || token.getType() == TokenType.DIV) {
            if (Rhs())
                return RhsPrime();
            else
                return false;
        }

        tokenIterator.previous(); // should follow Rhs' -> ε
        return true;
    }
}
