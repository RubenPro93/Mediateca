package atec.poo.mediateca.app.main;

import atec.poo.mediateca.app.users.Menu;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.1.5. Gestão e consulta de dados da aplicação (Abre Menu Utentes)
 */
public class DoOpenUsersMenu extends Comando<LibraryManager> {

    /**
     * @param receiver;
     */
    public DoOpenUsersMenu(LibraryManager receiver) {
        super(receiver, Label.OPEN_USERS_MENU);
    }

    @Override
    public final void executar() {
        Menu menu = new Menu(this.getReceptor());
        menu.open();
    }
}