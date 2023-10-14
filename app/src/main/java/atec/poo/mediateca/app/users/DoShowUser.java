package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.2.2. Mostrar Utente.
 */
public class DoShowUser extends Comando<LibraryManager> {

    private final LerInteiro id;

    /**
     * @param receiver;
     */
    public DoShowUser(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER);
        this.id = new LerInteiro(Message.requestUserId());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);
        try {
            String user = this.getReceptor().mostrarUtente(this.id.getValor());
            ui.escreveLinha(user);
        } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getUserID());
        }
    }
}