package exceptions;

/* Generated By:JavaCC: Do not edit this line. ParseException.java Version 6.1 */
/* JavaCCOptions:KEEP_LINE_COLUMN=true */
/**
 * This exception is thrown when parse errors are encountered.
 * You can explicitly create objects of this exception type by
 * calling the method generateParseException in the generated
 * parser.
 *
 * You can modify this class to customize your error reporting
 * mechanisms so long as you retain the public fields.
 */
public class SemanticParseException extends Exception {

    /**
     * The version identifier for this Serializable class.
     * Increment only if the <i>serialized</i> form of the
     * class changes.
     */
    private static final long serialVersionUID = 1L;
    private boolean isError;


    /** Constructor with message. */

    public SemanticParseException(String message) {
        super(message);
        this.isError = true;
    }

    public SemanticParseException(String message, boolean isError) {
        super(message);
        this.isError = isError;
    }

    public SemanticParseException(String message, int line, int column) {
        super(message + " " + line + ":" + column);
        this.isError = true;
    }

    private int errorCount;

    public int getErrorCount() {
        return this.errorCount;
    }

    public SemanticParseException(String message, int errorCount) {
        super('\n' + message);
        this.errorCount = errorCount;
    }

    public boolean isError(){
        return this.isError;
    }

    // public SemanticParseException(ParseException ex, String message, int errorCount) {
    //   this(message + '\n' + ex,errorCount);
    // }

  }