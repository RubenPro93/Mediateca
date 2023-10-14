package atec.poo.mediateca.app.main;

import atec.poo.mediateca.app.works.Menu;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.1.5. Gestão e consulta de dados da aplicação (Abre o Menu Obras)
 */
public class DoOpenWorksMenu extends Comando<LibraryManager> {

    /**
     * @param receiver;
     */
    public DoOpenWorksMenu(LibraryManager receiver) {
        super(receiver, Label.OPEN_WORKS_MENU);
    }

    @Override
    public final void executar() {
        Menu menu = new Menu(this.getReceptor());
        menu.open();
    }
}