package atec.poo.ui;

import static atec.poo.ui.Dialogo.IO;

public class Menu {
    private String titulo;
    private Comando<?>[] comandos;
    private Dialogo ui;

    public Menu(Dialogo ui, String titulo, Comando<?>[] comandos) {
        this.titulo = titulo;
        this.comandos = comandos;
        this.ui = ui;
    }

    public Menu(String titulo, Comando<?>[] comandos) {
        this(IO, titulo, comandos);
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Comando<?>[] getComandos(){
        return this.comandos;
    }

    public Comando<?> getComando(int i){
        return this.comandos[i];
    }

    public int size() {
        return this.comandos.length;
    }

    public void open() {
        ui.menu(this);
    }
}
