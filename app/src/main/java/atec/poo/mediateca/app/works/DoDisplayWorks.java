package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.ui.Comando;

import java.util.ArrayList;

/**
 * 4.3.2. Listar Obras
 */
public class DoDisplayWorks extends Comando<LibraryManager> {

    /**
     * @param receiver;
     */
    public DoDisplayWorks(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORKS);
    }

    @Override
    public final void executar() {
        ArrayList<Obra> obras = this.getReceptor().listObrasByID();
        for (Obra o : obras) {
            ui.escreveLinha(o.toString());
        }
    }
}