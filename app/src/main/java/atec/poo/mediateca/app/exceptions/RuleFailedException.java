package atec.poo.mediateca.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

import java.io.Serial;

/**
 * Class encoding the failure of borrowing requests.
 */
public class RuleFailedException extends DialogException {

    /**
     * Serial number for serialization.
     */
    @Serial
    private static final long serialVersionUID = 200510291601L;

    /**
     * User id.
     */
    int _idUser;

    /**
     * Work id.
     */
    int _idWork;

    /**
     * Index of failed rule.
     */
    int _ruleIndex;

    /**
     * @param idUser;
     * @param idWork;
     * @param ruleIndex;
     */
    public RuleFailedException(int idUser, int idWork, int ruleIndex) {
        _idUser = idUser;
        _idWork = idWork;
        _ruleIndex = ruleIndex;
    }

    /**
     * @return index of failed rule
     */
    public int getRuleIndex() {
        return _ruleIndex;
    }

    /**
     * @return work id
     */
    public int getWork() {
        return _idWork;
    }

    /**
     * @return user id
     */
    public int getUser() {
        return _idUser;
    }

    /**
     * )
     */
    @Override
    public String getMessage() {
        return Message.ruleFailed(_idUser, _idWork, _ruleIndex);
    }

}