package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.4. Requests menu.
 */
public class Menu extends atec.poo.ui.Menu {

    /**
     * @param receiver;
     */
    public Menu(LibraryManager receiver) {
        super(Label.TITLE, new Comando<?>[]{
                new DoRequestWork(receiver),
                new DoReturnWork(receiver),
        });
    }
}