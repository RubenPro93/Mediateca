package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.RuleFailedException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.RuleException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerBoolean;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * 4.4.1. Requisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {
    private final LerInteiro userID;
    private final LerInteiro obraID;
    private final LerBoolean lerAvisoStock;

    /**
     * @param receiver;
     */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.userID = new LerInteiro(Message.requestUserId());
        this.obraID = new LerInteiro(Message.requestWorkId());
        this.lerAvisoStock = new LerBoolean(Message.requestReturnNotificationPreference());
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
            this.getReceptor().verificarSuspensao(this.userID.getValor(), this.obraID.getValor());
        } catch (RuleException e) {
            throw new RuleFailedException(e.getUserID(), e.getObraID(), e.getRuleID());
        }

        if (!this.getReceptor().verificarStock(this.obraID.getValor()) && !this.getReceptor().userObra(userID.getValor(),obraID.getValor())) {
            ui.escreveLinha(atec.poo.mediateca.app.works.Message.semExemplares(obraID.getValor()));
            ui.lerInput(lerAvisoStock);
            if (lerAvisoStock.getValor()) {
                this.getReceptor().NotificacaoStock(userID.getValor(), obraID.getValor());
            }
            return;
        }

        try {
            int tempoEntrega = this.getReceptor().calcularDataEntrega(userID.getValor(), obraID.getValor()) + this.getReceptor().getData();
            this.getReceptor().requisitarObra(this.userID.getValor(), this.obraID.getValor());
            ui.escreveLinha(Message.workReturnDay(obraID.getValor(), tempoEntrega));
        } catch (RuleException e) {
            throw new RuleFailedException(e.getUserID(), e.getObraID(), e.getRuleID());
        }
    }
}