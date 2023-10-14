package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;

/**
 * 4.1.3. Mostrar data atual
 */
public class DoDisplayDate extends Comando<LibraryManager> {

    /**
     * @param receiver;
     */
    public DoDisplayDate(LibraryManager receiver) {
        super(receiver, Label.DISPLAY_DATE);
    }

    @Override
    public final void executar() {
        ui.escreveLinha(Message.currentDate(this.getReceptor().getData()));
    }
}