package atec.poo.ui;

import atec.poo.ui.exceptions.FormatoInvalidoException;
import atec.poo.ui.exceptions.TipoEntradaInvalidoException;

public class LerDouble extends Ler<Double> {

    public LerDouble(String pedido) {
        super(pedido, null);
    }

    @Override
    public void parse(String in) throws FormatoInvalidoException, TipoEntradaInvalidoException {
        try {
            this.valor=Double.parseDouble(in);
        } catch (NullPointerException | NumberFormatException e) {
            throw new TipoEntradaInvalidoException(Mensagens.ERROR_DOUBLE);
        }
    }

}
