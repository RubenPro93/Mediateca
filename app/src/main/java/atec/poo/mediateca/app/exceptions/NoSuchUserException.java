package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

import java.io.Serial;

/**
 * Class encoding reference to an invalid user id.
 */
public class NoSuchUserException extends DialogException {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 201901091828L;

    /**
     * Bad user id.
     */
    private final int _id;

    /**
     * @param id;
     */
    public NoSuchUserException(int id) {
        _id = id;
    }

    @Override
    public String getMessage() {
        return Message.noSuchUser(_id);
    }

}