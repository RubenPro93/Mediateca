package atec.poo.mediateca.core.exceptions;


import java.io.Serial;

/**
 * Class for representing a read error.
 */
public class ImportFileException extends Exception {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 201901101348L;

    /**
     * Default constructor
     */
    public ImportFileException() {
        // do nothing
    }

    /**
     * @param description;
     */
    public ImportFileException(String description) {
        super(description);
    }

    /**
     * @param cause;
     */
    public ImportFileException(Exception cause) {
        super(cause);
    }
}