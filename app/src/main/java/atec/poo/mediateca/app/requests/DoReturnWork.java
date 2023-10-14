package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.UserIsActiveException;
import atec.poo.mediateca.app.exceptions.WorkNotBorrowedByUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.ActiveUserException;
import atec.poo.mediateca.core.exceptions.BorrowException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.*;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Comando<LibraryManager> {
    private final LerInteiro userID;
    private final LerInteiro obraID;
    private final LerBoolean lerMulta;

    /**
     * @param receiver;
     */
    public DoReturnWork(LibraryManager receiver) {
        super(receiver, Label.RETURN_WORK);
        this.userID = new LerInteiro(Message.requestUserId());
        this.obraID = new LerInteiro(Message.requestWorkId());
        this.lerMulta = new LerBoolean(Message.requestFinePaymentChoice());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(userID);
        ui.lerInput(obraID);

        try {
            this.getReceptor().mostrarUtente(userID.getValor());
            this.getReceptor().mostrarObra(obraID.getValor());
        } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getUserID());
        } catch (WorkNotFoundException e) {
            throw new NoSuchWorkException(e.getObraID());
        }

        try {
            this.getReceptor().devolverObra(userID.getValor(), obraID.getValor());
        } catch (BorrowException e) {
            throw new WorkNotBorrowedByUserException(e.getUserID(), e.getObraID());
        }

        int multa = this.getReceptor().mostrarMulta(userID.getValor());

        if (multa > 0) {
            ui.escreveLinha(Message.showFine(userID.getValor(), multa));
            ui.lerInput(lerMulta);

            if (lerMulta.getValor()) {
                try {
                    this.getReceptor().pagarMulta(userID.getValor());
                } catch (ActiveUserException e) {
                    throw new UserIsActiveException(e.getUserID());
                }
            }
        }
    }
}