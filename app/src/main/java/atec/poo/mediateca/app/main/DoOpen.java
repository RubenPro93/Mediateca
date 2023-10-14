package atec.poo.mediateca.app.main;

import atec.poo.mediateca.app.exceptions.FileOpenFailedException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

import java.io.IOException;

/**
 * 4.1.1 Abrir um ficheiro de dados
 */
public class DoOpen extends Comando<LibraryManager> {
    private final LerString ficheiro;

    /**
     * @param receiver;
     */
    public DoOpen(LibraryManager receiver) {
        super(receiver, Label.OPEN);
        this.ficheiro = new LerString(Message.openFile(), null);
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.ficheiro);
        try {
            this.getReceptor().load(this.ficheiro.getValor());
        } catch (IOException | ClassNotFoundException e) {
            ui.escreveLinha(e.getMessage());
            throw new FileOpenFailedException(this.ficheiro.getValor());
        }
    }
}