package atec.poo.ui;

import atec.poo.ui.exceptions.FormatoInvalidoException;
import atec.poo.ui.exceptions.TipoEntradaInvalidoException;

public class LerString extends Ler<String> {

    public LerString(String pedido, String regex) {
        super(pedido, regex);
    }

    @Override
    public void parse(String in) throws FormatoInvalidoException, TipoEntradaInvalidoException {
        if(this.getRegex()==null){
            this.valor=in;
            return;
        }

        if (in.matches(this.getRegex()))
            this.valor=in;
        else
            throw new FormatoInvalidoException(Mensagens.ERROR_STRING);
    }

}
