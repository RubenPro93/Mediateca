package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.3. Menu Obras.
 */
public class Menu extends atec.poo.ui.Menu {

    /**
     * @param receiver;
     */
    public Menu(LibraryManager receiver) {
        super(Label.TITLE, new Comando<?>[]{ //
                new DoDisplayWork(receiver), //
                new DoDisplayWorks(receiver), //
                new DoPerformSearch(receiver), //
        });
    }
}