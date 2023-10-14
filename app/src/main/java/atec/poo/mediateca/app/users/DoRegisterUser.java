package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.Constantes;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.2.1. Registar novo Utente.
 */
public class DoRegisterUser extends Comando<LibraryManager> {
    private final LerString nome;
    private final LerString email;

    /**
     * @param receiver;
     */
    public DoRegisterUser(LibraryManager receiver) {
        super(receiver, Label.REGISTER_USER);
        this.nome = new LerString(Message.requestUserName(), null);
        this.email = new LerString(Message.requestUserEMail(), Constantes.EMAIL_REGEX);
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.nome);
        ui.lerInput(this.email);
        int id = this.getReceptor().registarUser(this.nome.getValor(), this.email.getValor());
        ui.escreveLinha(Message.userRegistrationSuccessful(id));
    }
}