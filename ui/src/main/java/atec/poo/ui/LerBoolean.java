package atec.poo.ui;

import atec.poo.ui.exceptions.FormatoInvalidoException;
import atec.poo.ui.exceptions.TipoEntradaInvalidoException;

public class LerBoolean extends Ler<Boolean> {

    public LerBoolean(String pedido) {
        super(pedido, null);

    }

    @Override
    public void parse(String in) throws FormatoInvalidoException, TipoEntradaInvalidoException {
        if (in.length() == 1) {
            switch (in.charAt(0)) {
                case Constantes.BOOLEAN_CHAR_YES:
                    this.valor = true;
                    break;
                case Constantes.BOOLEAN_CHAR_NO:
                    this.valor = false;
                    break;
                default:
                    throw new FormatoInvalidoException(Mensagens.ERROR_BOOLEAN);
            }
        } else {
            switch (in) {
                case Constantes.BOOLEAN_WORD_YES:
                    this.valor = true;
                    break;
                case Constantes.BOOLEAN_WORD_NO:
                    this.valor = false;
                    break;
                default:
                    throw new FormatoInvalidoException(Mensagens.ERROR_BOOLEAN);
            }

        }

    }
}