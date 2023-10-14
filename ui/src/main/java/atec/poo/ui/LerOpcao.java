package atec.poo.ui;

import atec.poo.ui.exceptions.FormatoInvalidoException;
import atec.poo.ui.exceptions.TipoEntradaInvalidoException;

public class LerOpcao extends Ler<String> {
    String[] opcoes;

    public LerOpcao(String pedido, String[] opcoes) {
        super(pedido + String.format(" (%s)", String.join("|", opcoes)), null, opcoes);
        this.opcoes = opcoes;
    }

    @Override
    public void parse(String in) throws FormatoInvalidoException, TipoEntradaInvalidoException {
        if (this.getRegex() == null) {
            this.valor = in;
            return;
        }

        if (in.matches(this.getRegex()))
            this.valor = in;
        else
            throw new FormatoInvalidoException(Mensagens.ERROR_STRING);
    }
}
