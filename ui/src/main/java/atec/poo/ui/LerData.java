package atec.poo.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import atec.poo.ui.exceptions.FormatoInvalidoException;
import atec.poo.ui.exceptions.TipoEntradaInvalidoException;

public class LerData extends Ler<LocalDate> {

    public LerData(String pedido) {
        super(pedido, null);
    }

    @Override
    public void parse(String in) throws FormatoInvalidoException, TipoEntradaInvalidoException {
        try {
            this.valor=LocalDate.parse(in);
        } catch (DateTimeParseException e) {
            throw new FormatoInvalidoException("Formato inválido (yyyy-mm-dd): ");
        }

    }

}
