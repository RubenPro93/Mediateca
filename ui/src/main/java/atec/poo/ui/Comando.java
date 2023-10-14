package atec.poo.ui;

import static atec.poo.ui.Dialogo.IO;

import atec.poo.ui.exceptions.DialogException;

public abstract class Comando<Receptor> {

    private Receptor receptor;
    private Menu menu;
    private String titulo;
    public static final Dialogo ui=IO;

    public Receptor getReceptor() {
        return this.receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getTitulo() {
        return this.titulo;
    }
//helo helo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Comando(Receptor receptor, String titulo) {
        this.receptor = receptor;
        this.titulo = titulo;
    }

    /* Executa o comando escolhido e lança uma exceção caso algo corra mal*/
    public abstract void executar() throws DialogException;
}
