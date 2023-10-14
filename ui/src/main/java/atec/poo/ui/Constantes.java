package atec.poo.ui;

public final class Constantes {
    /** How do you say "yes"? */
    public final static String BOOLEAN_WORD_YES = "sim";

    /** How do you say "no"? */
    public final static String BOOLEAN_WORD_NO = "nao";

    /** How do you say "yes"? */
    public final static char BOOLEAN_CHAR_YES = 's';

    /** How do you say "no"? */
    public final static char BOOLEAN_CHAR_NO = 'n';

    /**
     * Expressão que considera os quatros casos de matrícula em Portugal
     * LL-NN-NN
     * NN-NN-LL
     * NN-LL-NN
     * LL-NN-LL
     **/
    public final static String MATRICULA_REGEX="^(([A-Z]{2}-\\d{2}-(\\d{2}|[A-Z]{2}))|(\\d{2}-(\\d{2}-[A-Z]{2}|[A-Z]{2}-\\d{2})))?$";

    /**
     * Formato de um Cartão de Cidadão
     */
    public final static String CC_REGEX="\\b\\d{8}\\b";

    /**
     * Formato de um email
     */
    public final static String NIF_REGEX="\\b\\d{9}\\b";

    /**
     * Formato
     */
    public final static String EMAIL_REGEX="^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
}
