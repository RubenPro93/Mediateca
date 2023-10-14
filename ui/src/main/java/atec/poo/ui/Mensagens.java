package atec.poo.ui;

public final class Mensagens {

    public static String ERROR_INTEGER = "Por favor insira um Inteiro ";

    public static String ERROR_DOUBLE = "Por favor insira um Double ";

    public static String ERROR_BOOLEAN = String.format(
            "É esperado uma resposta de sim/não. Formatos válidos: %s/%s ou %c/%c", Constantes.BOOLEAN_WORD_YES,
            Constantes.BOOLEAN_WORD_NO, Constantes.BOOLEAN_CHAR_YES, Constantes.BOOLEAN_CHAR_NO);

    public static String ERROR_STRING = "A String não corresponde ao formato esperado!";

    /* Para o menu*/
    public static String MENU_SELECT_OPTION = "Escolha uma opção ";
    public static String MENU_EXIT="0 - Sair";
    public static String MENU_INVALID_OPTION="Opção inválida!";

    public static String getErrorMessageOptions(String[] opt){
        return String.format("(Opções esperadas: %s ) %s",String.join("|",opt), ERROR_STRING);
    }

}
