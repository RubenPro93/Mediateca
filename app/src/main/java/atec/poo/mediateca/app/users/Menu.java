package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.2. Menu Utentes.
 */
public class Menu extends atec.poo.ui.Menu {

    /**
     * @param receiver;
     */
    public Menu(LibraryManager receiver) {
        super(Label.TITLE, new Comando<?>[]{ //
                new DoRegisterUser(receiver), //
                new DoShowUser(receiver), //
                new DoShowUsers(receiver), //
                new DoShowUserNotifications(receiver), //
                new DoPayFine(receiver), //
        });
    }
}