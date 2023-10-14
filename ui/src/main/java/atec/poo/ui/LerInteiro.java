package atec.poo.ui;

import atec.poo.ui.exceptions.FormatoInvalidoException;
import atec.poo.ui.exceptions.TipoEntradaInvalidoException;

public class LerInteiro extends Ler<Integer> {

    public LerInteiro(String pedido) {
        super(pedido, null);
    }

    @Override
    public void parse(String in) throws FormatoInvalidoException, TipoEntradaInvalidoException {

        try {
            this.valor=Integer.parseInt(in);
        } catch (NumberFormatException e) {
            throw new TipoEntradaInvalidoException(Mensagens.ERROR_INTEGER);
        }

    }

}
