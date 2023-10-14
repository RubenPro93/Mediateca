package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.*;
import java.util.ArrayList;

public class LibraryManager {
    private Biblioteca _biblioteca;
    public LibraryManager() {
        this._biblioteca = new Biblioteca();
    }

    /**
     * Mostra a data atual
     *
     * @return Data atual
     */
    public int getData() {
        return this._biblioteca.getData();
    }

    /**
     * Define a data atual
     *
     * @param dias dias
     */
    public void setData(int dias) {
        this._biblioteca.setData(dias);
    }

    /**
     * Registra um novo utente
     *
     * @param nome  nome utente
     * @param email emial utente
     * @return Cria um novo utente
     */
    public int registarUser(String nome, String email) {
        return this._biblioteca.registarUser(nome, email);
    }

    /**
     * Obtém informações sobre um utente especifico
     *
     * @param id id utente
     * @return Informações do utente pretendido
     * @throws UserNotFoundException Verificar se o utente existe ou não
     */
    public String mostrarUtente(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarUtente(id);
    }

    /**
     * Mostra informações sobre todos os utentes
     *
     * @return Informações de todos os utentes
     */
    public ArrayList<User> listUsers() {
        return this._biblioteca.listUsers();
    }

    /**
     * Obtém notificações de um utente específico
     *
     * @param userID id utente
     * @return Notificações do utente pretendido
     */
    public ArrayList<String> mostrarNotificacao(int userID) {
        return this._biblioteca.mostrarNotificacao(userID);
    }

    /**
     *  Adiciona ao Usuario a notificação da obra que ele pretendia pegar
     *  essa função só é ativada após o usuario aceitar que quer receber
     *  notificação quando a obra tiver disponivel
     * @param userID ID do Usuario
     * @param obraID ID da Obra
     */
    public void NotificacaoStock(int userID, int obraID){this._biblioteca.NotificacaoStock(userID,obraID);}

    /**
     * Paga a multa de um utente especifico
     * @param userID id utente
     */
    public void pagarMulta(int userID) throws ActiveUserException {
        this._biblioteca.pagarMulta(userID);
    }

    /**
     * Obtém informações sobre uma obra específica
     * @param id id obra
     * @return Informações da obra pretendido
     * @throws WorkNotFoundException Verificar se a obra existe ou não
     */
    public String mostrarObra(int id) throws WorkNotFoundException {
        return this._biblioteca.mostrarObra(id);
    }

    /**
     * Mostra informações sobre todas as obras por ordem crescente do ID da obra
     *
     * @return Informações de todas as obras (ordem crescente id obra)
     */
    public ArrayList<Obra> listObrasByID() {
        return this._biblioteca.listObrasByID();
    }

    /**
     * @param userID id user
     * @param obraID id obra
     * @return true ou false
     */
    public boolean userObra (int userID, int obraID){ return this._biblioteca.userObra(userID,obraID);}

    /**
     * Requisita obra especifica para um utente especifico
     * @param userID id utente
     * @param obraID id obra
     * @throws RuleException Mostra cada erro especifico
     */
    public void requisitarObra(int userID, int obraID) throws RuleException {
        this._biblioteca.requisitarObra(userID, obraID);
    }

    /**
     * Verifica se o Utente está suspenso
     *
     * @param userID userid;
     * @param obraID obra id;
     * @throws RuleException Mostra o erro especifico para utentes suspensos
     */
    public void verificarSuspensao(int userID, int obraID) throws RuleException {
        this._biblioteca.verificarSuspensao(userID, obraID);
    }

    /**
     * Verifica se o Stock acabou
     * @param obraID obra id
     * @return Retorna TRUE se a obra tem stock e FALSE quando não tem
     */
    public Boolean verificarStock(int obraID) {
        return this._biblioteca.verificarStock(obraID);
    }

    /**
     * Procura a quantidade de dias para devolver a obra (comportamento e a quantidade de exemplares da obra)
     *
     * @param userID id utente
     * @param obraID id obra
     * @return Retorna a data de entrega da Obra Requisitada
     */
    public int calcularDataEntrega(int userID, int obraID) {
        return this._biblioteca.calcularDataEntrega(userID, obraID);
    }

    /**
     * Faz a devolução da Obra que foi requisitada
     * @param userID id utente
     * @param obraID id obra
     */
    public void devolverObra(int userID, int obraID) throws BorrowException {
        this._biblioteca.devolverObra(userID, obraID);
    }

    /**
     * Procura a multa de um utente especifico
     * @param userID utente id
     * @return Retorna a multa do utente
     */
    public int mostrarMulta(int userID) {
        return this._biblioteca.mostrarMulta(userID);
    }

    /**
     * Executa o Save da Aplicação
     *
     * @param ficheiro ficheiro
     * @throws IOException Erro na abertura e/ou Leitura do ficheiro
     */
    public void save(String ficheiro) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ficheiro)));
        oos.writeObject(this._biblioteca);
        oos.close();
    }

    /**
     * Executa o Load da Aplicação
     *
     * @param ficheiro ficheiro
     * @throws IOException            Erro na abertura e/ou Leitura do ficheiro
     * @throws ClassNotFoundException Erro se a classe não for encontrada
     */
    public void load(String ficheiro) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ficheiro)));
        this._biblioteca = ((Biblioteca) ois.readObject());
        ois.close();
    }

    /**
     * Recebe ficheiro de entrada
     *
     * @param datafile Ficheiro de dados
     * @throws ImportFileException A importação do ficheiro deu erro
     */
    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }
}