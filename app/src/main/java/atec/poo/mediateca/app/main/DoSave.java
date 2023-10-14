package atec.poo.mediateca.app.main;

import atec.poo.mediateca.app.exceptions.FileOpenFailedException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

import java.io.IOException;

/**
 * 4.1.2. Guardar o estado da aplicação
 */
public class DoSave extends Comando<LibraryManager> {
    private final LerString ficheiro;

    /**
     * @param receiver;
     */
    public DoSave(LibraryManager receiver) {
        super(receiver, Label.SAVE);
        this.ficheiro = new LerString(Message.newSaveAs(), null);
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.ficheiro);
        try {
            this.getReceptor().save(this.ficheiro.getValor());
        } catch (IOException e) {
            throw new FileOpenFailedException(this.ficheiro.getValor());
        }
    }
}