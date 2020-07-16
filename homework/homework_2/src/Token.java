/**
 * Class representing a token and its main attributes.
 */
public class Token {

    private final TokenType type; /** Type of token (EQ, SMICOLON, ...) */
    private final String image; /** Image value (the actual value of the token) */

    /**
     * Constructor of the class.
     * @param type - Type of the token
     * @param image - Image of the token
     */
    public Token(TokenType type, String image) {
        this.type = type;
        this.image = image;
    }

    /**
     * Retrieves the token type.
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Retrieves the token image.
     */
    public String getImage() {
        return image;
    }
}

/**
 * Enum with all the different token types.
 */
enum TokenType {
    SUB,
    PLUS,
    DIV,
    MUL,
    RPAR,
    LPAR,
    EQ,
    SMICOLON,
    INT,
    VAR
}