package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.UserIsActiveException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.ActiveUserException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.2.5. Pagar Multa
 */
public class DoPayFine extends Comando<LibraryManager> {

    private final LerInteiro id;

    /**
     * @param receiver;
     */
    public DoPayFine(LibraryManager receiver) {
        super(receiver, Label.PAY_FINE);
        this.id = new LerInteiro(Message.requestUserId());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);
        try {
            this.getReceptor().pagarMulta(id.getValor());
        } catch (ActiveUserException e) {
            throw new UserIsActiveException(e.getUserID());
        }
    }
}