package atec.poo.ui.exceptions;

public abstract class DialogException extends Exception {
    /**
     * Serial number for serialization.
     */
    static final long serialVersionUID = 200610291601L;

    /**
     * This method is made abstract so that meaningful descriptive messages are
     * provided by each subclass, instead of using the general implementation.
     *
     * @see Throwable#getMessage()
     */
    @Override
    public abstract String getMessage();

    /**
     * Subclasses are not allowed to redefine this method.
     *
     * @see Throwable#toString()
     */
    @Override
    public final String toString() {
        return ErrorMessages.invalidOperation(getMessage());
    }

}
