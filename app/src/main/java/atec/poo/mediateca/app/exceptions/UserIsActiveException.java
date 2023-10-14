package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

import java.io.Serial;

/**
 * Class encoding reference to an invalid user state.
 */
public class UserIsActiveException extends DialogException {

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
    public UserIsActiveException(int id) {
        _id = id;
    }


    @Override
    public String getMessage() {
        return Message.userNotSuspended(_id);
    }

}