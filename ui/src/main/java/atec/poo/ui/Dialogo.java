package atec.poo.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import atec.poo.ui.exceptions.DialogException;
import atec.poo.ui.exceptions.FormatoInvalidoException;
import atec.poo.ui.exceptions.TipoEntradaInvalidoException;

public class Dialogo {

    public static Dialogo IO=new Dialogo();
    private Scanner leitor;
    private InputStream in = System.in;
    private PrintStream out = System.out;


    public Dialogo() {
        this.leitor = new Scanner(in);
    }

    public void escreve(String msg) {
        out.print(msg);
    }

    public void escreveLinha(String msg) {
        out.println(msg);
    }

    public void menu(Menu m){
        int escolha;
        LerInteiro li=new LerInteiro(Mensagens.MENU_SELECT_OPTION);

        while (true) {
            this.escreveLinha( m.getTitulo());
            for (int i = 0; i < m.size(); i++) {
                this.escreveLinha(i+1 +" - "+m.getComando(i).getTitulo());
            }
            this.escreveLinha(Mensagens.MENU_EXIT);
            this.lerInput(li);
            escolha=li.getValor();
            if(escolha==0)
                return;
            if (escolha<0 || escolha >m.size()) {
                this.escreveLinha(Mensagens.MENU_INVALID_OPTION);
                continue;
            }
            try{
                m.getComando(escolha-1).executar();
            }catch(DialogException e){
                System.out.println(m.getComando(escolha-1).getTitulo()+ ": " + e);
            }
        }

    }

    public void lerInput(Ler<?> l) {
        this.escreve(l.getPedido()+"->");
        while (true) {
            try {
                l.parse(leitor.nextLine());
                return;
            } catch (FormatoInvalidoException e) {
                this.escreveLinha(e.getMsg());
                this.escreve(l.getPedido()+"->");

            } catch (TipoEntradaInvalidoException e) {
                this.escreveLinha(e.getMsg());
                this.escreve(l.getPedido()+"->");
            }
        }
    }

    public void close() {
        try {
            this.in.close();
            this.out.close();
            this.leitor.close();
        } catch (Exception e) {
        }

    }
}
