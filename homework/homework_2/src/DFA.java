/**
 * Class that represents the scanner DFA.
 */
public class DFA {

    /**
     * Enum with all the states that the DFA has.
     */
    enum State {
        DEAD(0),
        START(1),
        SUB(2),
        PLUS(3),
        DIV(4),
        MUL(5),
        RPAR(6),
        LPAR(7),
        EQ(8),
        SMICOLON(9),
        INT(10),
        VAR(11);

        private final int value;
        State(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    private boolean[] finalState; /** Array with values for each state; 1 if it is final, 0 otherwise */
    private State[][] automaton; /** Array of arrays representing the automaton */
    private State currentState; /** Current state */

    /**
     * Constructor of the DFA class.
     */
    public DFA() {
        finalState = new boolean[]{false, false, true, true, true, true, true, true, true, true, true, true};
        currentState = State.START;
        generateAutomaton();
    }


    /**
     * Returns the current state.
     */
    public State getCurrentState() {
        return currentState;
    }


    /**
     * Sets the current state.
     */
    public void setCurrentState(State state) {
        currentState = state;
    }


    /**
     * Function that resets the DFA.
     */
    public void resetDFA() {
        currentState = State.START;
    }


    /**
     * Checks if the current state is final or not.
     */
    public boolean isFinalState() {
        return finalState[currentState.getValue()];
    }


    /**
     * Generates the automaton for token scanning.
     */
    private void generateAutomaton() {
        /**
         * Chars in the positions:
         * 0 -> "-"
         * 1 -> "+"
         * 2 -> "/"
         * 3 -> "*"
         * 4 -> ")"
         * 5 -> "("
         * 6 -> "="
         * 7 -> ";"
         * 8 -> [0-9]
         * 9 -> [A-Za-z]
         * 10 -> skip ("\n", "\r", " ", "\t")
         * 11 -> other symbols
         */

        automaton = new State[][]{
            /* DEAD      */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* START     */ {State.SUB, State.PLUS, State.DIV, State.MUL, State.RPAR, State.LPAR, State.EQ, State.SMICOLON, State.INT, State.VAR, State.DEAD, State.DEAD},
            /* SUB       */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* PLUS      */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* DIV       */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* MUL       */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* RPAR      */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* LPAR      */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* EQ        */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* SMICOLON  */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD},
            /* INT       */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.INT, State.DEAD, State.DEAD, State.DEAD},
            /* VAR       */ {State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.DEAD, State.VAR, State.VAR, State.DEAD, State.DEAD}
        };
    }


    /**
     * Function that maps a char to a state transition.
     * @param c - Char to map
     */
    private int mapCharToTransition(char c) {
        switch(c) {
            case '-':
                return 0;

            case '+':
                return 1;

            case '/':
                return 2;

            case '*':
                return 3;

            case ')':
                return 4;

            case '(':
                return 5;

            case '=':
                return 6;

            case ';':
                return 7;

            default:
                if (Character.isDigit(c)) return 8;
                if (Character.isLetter(c)) return 9;
                if (c == '\n' || c == '\r' || c == ' ' || c == '\t') return 10;
                return 11;
        }
    }


    /**
     * Function that receives a char, and processes it using the DFA. Returns the next state.
     */
    public State processChar(char c) {
        int transition = mapCharToTransition(c);
        State newState = automaton[currentState.getValue()][transition];
        return newState;
    }


    /**
     * Prints the state name and returns an instance of the token, with the corrent type and image.
     */
    public Token printStateName(String tokenString) {
        Token token;

        switch(currentState) {
            case DEAD:
                System.out.println("DEAD");
                token = null;
                break;

            case START:
                System.out.println("START");
                token = null;
                break;

            case SUB:
                System.out.println("SUB -> " + tokenString.trim());
                token = new Token(TokenType.SUB, tokenString.trim());
                break;

            case PLUS:
                System.out.println("PLUS -> " + tokenString.trim());
                token = new Token(TokenType.PLUS, tokenString.trim());
                break;

            case DIV:
                System.out.println("DIV -> " + tokenString.trim());
                token = new Token(TokenType.DIV, tokenString.trim());
                break;

            case MUL:
                System.out.println("MUL -> " + tokenString.trim());
                token = new Token(TokenType.MUL, tokenString.trim());
                break;

            case RPAR:
                System.out.println("RPAR -> " + tokenString.trim());
                token = new Token(TokenType.RPAR, tokenString.trim());
                break;

            case LPAR:
                System.out.println("LPAR -> " + tokenString.trim());
                token = new Token(TokenType.LPAR, tokenString.trim());
                break;

            case EQ:
                System.out.println("EQ -> " + tokenString.trim());
                token = new Token(TokenType.EQ, tokenString.trim());
                break;

            case SMICOLON:
                System.out.println("SMICOLON -> " + tokenString.trim());
                token = new Token(TokenType.SMICOLON, tokenString.trim());
                break;

            case INT:
                System.out.println("INT -> " + tokenString.trim());
                token = new Token(TokenType.INT, tokenString.trim());
                break;

            case VAR:
                System.out.println("VAR -> " + tokenString.trim());
                token = new Token(TokenType.VAR, tokenString.trim());
                break;

            default:
                token = null;
                break;
        }

        return token;
    }

}
