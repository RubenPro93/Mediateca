package atec.poo.ui.exceptions;

public class TipoEntradaInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;
    private String msg;

    public String getMsg() {
        return "Tipo de dados Inválido. " + this.msg;
    }

    public TipoEntradaInvalidoException(String msg) {
        this.msg = msg;
    }

}
