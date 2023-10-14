package atec.poo.mediateca.app.main;

import atec.poo.mediateca.app.requests.Menu;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.1.5. Gestão e consulta de dados da aplicação (Abre Menu Requisições)
 */
public class DoOpenRequestsMenu extends Comando<LibraryManager> {

    /**
     * @param receiver;
     */
    public DoOpenRequestsMenu(LibraryManager receiver) {
        super(receiver, Label.OPEN_REQUESTS_MENU);
    }

    @Override
    public final void executar() {
        Menu menu = new Menu(this.getReceptor());
        menu.open();
    }
}