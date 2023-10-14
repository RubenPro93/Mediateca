package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;

/**
 * 4.2.3. Mostrar Notificações do Utente
 */
public class DoShowUserNotifications extends Comando<LibraryManager> {
    private final LerInteiro id;

    /**
     * @param receiver;
     */
    public DoShowUserNotifications(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER_NOTIFICATIONS);
        this.id = new LerInteiro(Message.requestUserId());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);

        try { // Verifica se o Utente existe, se não lança uma exceção especifica
            this.getReceptor().mostrarUtente(this.id.getValor());
        } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getUserID());
        }

        ArrayList<String> notificacaoObra = this.getReceptor().mostrarNotificacao(this.id.getValor());

        if (!notificacaoObra.isEmpty()) { // Imprimindo cada elemento do arraylist criado para mostrar cada registro de requisicao e devolucao
            for (String elemento : notificacaoObra) {
                ui.escreveLinha(elemento);
            }
        }
    }
}