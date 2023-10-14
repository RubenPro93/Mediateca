package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

import java.io.Serial;

/**
 * Class encoding return failure.
 */
public class WorkNotBorrowedByUserException extends DialogException {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 200510291601L;

    /**
     * Bad user id.
     */
    private final int _idUser;

    /**
     * Bad work id.
     */
    private final int _idWork;

    /**
     * @param idWork;
     * @param idUser;
     */
    public WorkNotBorrowedByUserException(int idWork, int idUser) {
        _idWork = idWork;
        _idUser = idUser;
    }

    @Override
    public String getMessage() {
        return Message.workNotBorrowedByUser(_idWork, _idUser);
    }

}