package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

import java.io.Serial;

/**
 * Class encoding problems processing input files.
 */
public class FileOpenFailedException extends DialogException {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 201901091828L;

    /**
     * Bad file name.
     */
    private final String _name;

    /**
     * @param name;
     */
    public FileOpenFailedException(String name) {
        _name = name;
    }

    @Override
    public String getMessage() {
        return Message.fileNotFound(_name);
    }

}