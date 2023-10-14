package atec.poo.ui.exceptions;

public class FormatoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    private String msg;

    /**
     * Apresenta a Mensagem de erro quando se verifica Formato inválido
     * @return String - Mensagem de Erro
     */
    public String getMsg() {
        return "Formato Inválido. " + this.msg;
    }

    public FormatoInvalidoException(String msg) {
        this.msg = msg;
    }

}
