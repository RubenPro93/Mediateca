package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;

/**
 * 4.1.4. Avançar data atual
 */
public class DoAdvanceDate extends Comando<LibraryManager> {

    private final LerInteiro dias;

    /**
     * @param receiver;
     */
    public DoAdvanceDate(LibraryManager receiver) {
        super(receiver, Label.ADVANCE_DATE);
        this.dias = new LerInteiro(Message.requestDaysToAdvance());
    }

    @Override
    public final void executar() {
        ui.lerInput(this.dias);
        this.getReceptor().setData(this.dias.getValor());
        ui.escreveLinha(Message.currentDate(this.getReceptor().getData()));
    }
}