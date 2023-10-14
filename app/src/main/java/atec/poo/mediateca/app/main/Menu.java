package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.1. Menu Principal
 */
public class Menu extends atec.poo.ui.Menu {

    /**
     * @param receiver;
     */
    public Menu(LibraryManager receiver) {
        super(Label.TITLE, new Comando<?>[]{ //4.1
                new DoOpen(receiver), //
                new DoSave(receiver), //
                new DoDisplayDate(receiver), //
                new DoAdvanceDate(receiver), //
                new DoOpenUsersMenu(receiver), //
                new DoOpenWorksMenu(receiver), //
                new DoOpenRequestsMenu(receiver), //
        });
    }
}