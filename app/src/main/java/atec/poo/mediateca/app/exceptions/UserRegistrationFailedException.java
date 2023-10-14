package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

import java.io.Serial;

/**
 * Class encoding user registration failure.
 */
public class UserRegistrationFailedException extends DialogException {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 201901091828L;

    /**
     * Bad user's name.
     */
    private final String _name;

    /**
     * Bad user's email.
     */
    private final String _email;

    /**
     * @param name;
     * @param email;
     */
    public UserRegistrationFailedException(String name, String email) {
        _name = name;
        _email = email;
    }


    @Override
    public String getMessage() {
        return Message.userRegistrationFailed(_name, _email);
    }

}