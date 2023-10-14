package atec.poo.mediateca.app.works;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.3.1. Mostrar Obra.
 */
public class DoDisplayWork extends Comando<LibraryManager> {
    private final LerInteiro id;

    /**
     * @param receiver;
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORK);
        this.id = new LerInteiro(Message.requestWorkId());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);
        try {
            String obra = this.getReceptor().mostrarObra(this.id.getValor());
            ui.escreveLinha(obra);
        } catch (WorkNotFoundException e) {
            throw new NoSuchWorkException(e.getObraID());
        }
    }
}