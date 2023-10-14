package atec.poo.mediateca.app;

import atec.poo.mediateca.app.main.Menu;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.ImportFileException;

import static atec.poo.ui.Dialogo.IO;

/**
 * Main driver for the library management application.
 */
public class App {

    /**
     * @param args;
     */
    public static void main(String[] args) {

        LibraryManager mgr = new LibraryManager();
        // Para carregar o ficheiro de dados via atributo import
        String datafile = System.getProperty("import"); //$NON-NLS-1$
        if (datafile != null) {
            try {
                mgr.importFile(datafile);
            } catch (ImportFileException e) {
                e.printStackTrace();
            }
        }

        try {
            Menu menu = new Menu(mgr);
            menu.open();
        } finally {
            IO.close();
        }
    }
}