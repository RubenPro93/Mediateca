package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.User;
import atec.poo.ui.Comando;

import java.util.ArrayList;

/**
 * 4.2.4. Mostrar Todos os utentes.
 */
public class DoShowUsers extends Comando<LibraryManager> {

    /**
     * @param receiver;
     */
    public DoShowUsers(LibraryManager receiver) {
        super(receiver, Label.SHOW_USERS);
    }

    @Override
    public final void executar() {
        ArrayList<User> users = this.getReceptor().listUsers();
        for (User u : users) {
            ui.escreveLinha(u.toString());
        }
    }
}