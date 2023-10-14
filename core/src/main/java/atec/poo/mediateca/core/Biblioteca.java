package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;
import atec.poo.mediateca.core.utilidades.CompareObraByID;

import java.io.File;
import java.io.IOException;
import java.io.Serial;

import java.io.Serializable;
import java.util.*;

public class Biblioteca implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private final HashMap<Integer, User> users;
    private final HashMap<Integer, Obra> obras;
    private final HashMap<Integer, Requisicao> requisicoes;
    private int nextUserID;
    private int nextObraID;
    private int nextReqID;
    private int data;
    private static final int multaDiaria = 5;

    /**
     * Construtor
     */
    public Biblioteca() {
        this.users = new HashMap<>();
        this.obras = new HashMap<>();
        this.requisicoes = new HashMap<>();
        this.nextUserID = 1;
        this.nextObraID = 1;
        this.nextReqID = 1;
        this.data = 0;
    }

    /**
     * Mostra a data atual
     *
     * @return Data atual
     */
    public int getData() {
        return data;
    }

    /**
     * Define a data atual
     * E faz uma verificação das multa para ser aplicada em usuario
     * que ainda não entrego no prazo estipulado
     * @param data data
     */
    public void setData(int data) {
        if (data > 0)
            this.data += data;
        for (Requisicao req : requisicoes.values()) {
            if (getData() > req.getDataEntrega()) {
                verificarDataEntrega(req.getUserID(), req);
            }
        }
    }

    /**
     * Registra um novo utente
     *
     * @param nome  nome utente
     * @param email email utente
     * @return Cria um novo utente
     */
    public int registarUser(String nome, String email) {
        User u = new User(this.nextUserID, nome, email);
        this.users.put(u.getId(), u);
        this.nextUserID++;
        return u.getId();
    }

    /**
     * Obtém informações sobre um utente específico
     *
     * @param id id utente
     * @return Informações do utente pretendido
     * @throws UserNotFoundException Verificar se o utente existe ou não
     */
    public String mostrarUtente(int id) throws UserNotFoundException {
        if (this.users.containsKey(id))
            return this.users.get(id).toString();
        throw new UserNotFoundException(id);
    }

    /**
     * Mostra informações sobre todos os utentes
     *
     * @return Informações de todos os utentes
     */
    public ArrayList<User> listUsers() {
        ArrayList<User> users_array = new ArrayList<>(this.users.values());
        Collections.sort(users_array);
        return users_array;
    }

    /**
     * Obtém notificações de uma obra específica
     * para um Usuario que tiver interesse.
     * Após a visualização da notificação
     * a caixa de notificação do cliente é limpa
     *
     * @param userID id utente
     * @return Notificações da obra para o Usuario interessado
     */
    public ArrayList<String> mostrarNotificacao(int userID) {
        User user = this.users.get(userID);

        if (!user.NotificacaoObra.isEmpty()) {

            ArrayList<String> obra_notificacao = new ArrayList<>(user.getNotificacaoObra());
            user.NotificacaoObra.clear();
            return obra_notificacao;

        }
        return new ArrayList<>();
    }

    /**
     * Se o cliente quiser ser notificado, entra na função
     * ela verifica se ele ja tem notificação ativa para a obra especifica
     * caso não, ele entra na condição e adiciona o userID para acompanhar
     * o registro da OBRA
     * @param userID ID DO USUARIO
     * @param obraID ID DA OBRA
     */
    public void NotificacaoStock(int userID, int obraID) {
        Obra obra = this.obras.get(obraID);
        if(!obra.UserIDRegistro.contains(userID)){
            obra.UserIDRegistro.add(userID);
        }
    }


    /**
     * Paga a multa de um utente especifico
     * E chama a função devolver DevolverObraMulta
     * caso o usuario ainda esta com a obra ela é devolvida
     * @param userID id utente
     */
    public void pagarMulta(int userID) throws ActiveUserException {
        User user = this.users.get(userID);

        if (user.getEstado().equals(Estado.SUSPENSO)) {
            user.setMulta(0);
            user.setEstado(Estado.ACTIVO);
            devolverObraMulta(userID);
            return;
        }
        throw new ActiveUserException(userID);
    }

    /** O Usuario chama a função para pagar a multa da entrega atrasada
     * caso esteja com alguma OBRA que passou do prazo de entrega
     * ela é devolvida assim que saldar toda a multa
     * OBS: Esta função é diferente da devolver obra, nessa o USUARIO
     * pode já ter devolvido a OBRA
     * @param userID; ID DO USUARIO
     */
    public void devolverObraMulta(int userID) {
        User user = this.users.get(userID);

        List<Requisicao> obraDivida = new ArrayList<>();

        for (Integer reqID : user.requisicaoID) {
            Requisicao req = this.requisicoes.get(reqID);
            if (getData() > req.getDataEntrega()) {
                obraDivida.add(this.requisicoes.get(reqID));
            }
        }

        for (Requisicao reqCliente : obraDivida) {
            Obra obra = this.obras.get(reqCliente.getObraID());

            //remove o id da requisição da pessoa que pegou
            user.requisicaoID.remove(Integer.valueOf(reqCliente.getId()));
            this.requisicoes.values().remove(reqCliente);
            user.numRequisicoes--;

            user.requisicao.remove(Integer.valueOf(reqCliente.getObraID()));
            int novoStock = obra.getStock() + 1;
            obra.setStock(novoStock);
            verificarTempoEntrega(userID, reqCliente);
        }
    }

    /**
     * Registra um novo livro na biblioteca.
     *
     * @param titulo     titulo livro.
     * @param autor      autor livro.
     * @param preco      preço livro.
     * @param categoria  categoria livro.
     * @param isbn       valor de ISBN livro.
     * @param exemplares Nº de exemplares livro.
     */
    public void registarLivro(String titulo, String autor, Double preco, String categoria, String isbn, int exemplares) {
        Livro l = new Livro(this.nextObraID, titulo, autor, preco, categoria, isbn, exemplares);
        this.obras.put(l.getId(), l);
        this.nextObraID++;
    }

    /**
     * Registra um novo dvd na biblioteca
     *
     * @param titulo     titulo dvd
     * @param realizador realizador dvd
     * @param preco      preço dvd
     * @param categoria  categoria dvd
     * @param igac       valor de igac dvd
     * @param exemplares Nº de exemplares dvd
     */
    public void registarDVD(String titulo, String realizador, Double preco, String categoria, String igac, int exemplares) {
        DVD d = new DVD(this.nextObraID, titulo, realizador, preco, categoria, igac, exemplares);
        this.obras.put(d.getId(), d);
        this.nextObraID++;
    }

    /**
     * Obtém informações sobre uma obra específica
     *
     * @param id id obra
     * @return Informações da obra pretendido
     * @throws WorkNotFoundException Verificar se a obra existe ou não
     */
    public String mostrarObra(int id) throws WorkNotFoundException {
        if (this.obras.containsKey(id))
            return this.obras.get(id).toString();
        throw new WorkNotFoundException(id);
    }

    /**
     * Mostra informações sobre todas as obras por ordem crescente do ID da obra
     * @return Informações de todas as obras (ordem crescente id obra)
     */
    public ArrayList<Obra> listObrasByID() {
        ArrayList<Obra> obras_array = new ArrayList<>(this.obras.values());
        obras_array.sort(new CompareObraByID());
        return obras_array;
    }

    /**
     * Descobrir se o cliente tem a obra,
     * usado no DoRequestWork para quando a obra não tiver stock
     * Mas o usuario coloca um id que ja tenha a obra, obtenha o erro de
     * usuario não pode requisitar a mesma obra
     * @param userID ID USUARIO
     * @param obraID ID DA OBRA
     * @return Se a obra estiver com o Usuario retorna true
     */
    public boolean userObra (int userID, int obraID){
        User user = this.users.get(userID);
        return user.getObraID(obraID);
    }

    /**
     * Registra uma nova Requisicão
     * @param userID         id utente
     * @param obraID         id obra
     * @param dataRequisicao data requisicao obra
     * @param dataEntrega    data entregue obra
     */
    public int registarRequisicao(int userID, int obraID, int dataRequisicao, int dataEntrega) {
        Requisicao req = new Requisicao(this.nextReqID, userID, obraID, dataRequisicao, dataEntrega);
        this.requisicoes.put(req.getId(), req);
        return this.nextReqID++;
    }


    /**
     * Requisita obra especifica para um utente especifico
     *
     * @param userID id utente
     * @param obraID id obra
     * @throws RuleException Mostra cada erro especifico
     */
    public void requisitarObra(int userID, int obraID) throws RuleException {
        Obra obra = this.obras.get(obraID);
        User user = this.users.get(userID);

        if (obra.getStock() <= 0 && !user.getObraID(obraID))
            NotificacaoStock(userID, obraID);

        if (obra.getCategoria().equals("REFERENCE"))
            throw new RuleException(userID, obraID, 5);

        if (obra.getPreco() > 25.00 && !user.getComportamento().equals(Comportamento.CUMPRIDOR))
            throw new RuleException(userID, obraID, 6);

        if (!user.getObraID(obraID)) {
            Comportamento comportamento = user.getComportamento();
            int requisicaoLimite = switch (comportamento) {
                case NORMAL -> 3;
                case CUMPRIDOR -> 5;
                case FALTOSO -> 1;
            };

            if (user.numRequisicoes < requisicaoLimite) {
                user.numRequisicoes++;
                user.requisicao.add(obraID);
                int dataRequisicao = getData();
                int dataEntrega = getData() + calcularDataEntrega(userID, obraID);
                int reqID = registarRequisicao(userID, obraID, dataRequisicao, dataEntrega);
                user.requisicaoID.add(reqID);

                int novoStock = obra.getStock() - 1;
                obra.setStock(novoStock);

                if (!obra.UserIDRegistro.isEmpty()) {
                    if (obra.UserIDRegistro.contains(userID)) {
                        obra.UserIDRegistro.remove(Integer.valueOf(userID));
                    }
                    for (Integer userIDnot : obra.UserIDRegistro) {
                        User userNot = this.users.get(userIDnot);
                        userNot.NotificacaoObra.add("REQUISIÇÃO: " + obra);
                    }
                }
            } else {
                throw new RuleException(userID, obraID, 4);
            }
        } else {
            throw new RuleException(userID, obraID, 1);
        }
    }

    /**
     * Verifica se o Utente está suspenso
     *
     * @param userID user id;
     * @param obraID obra id;
     * @throws RuleException Mostra o erro especifico para utentes suspensos
     */
    public void verificarSuspensao(int userID, int obraID) throws RuleException {
        User user = this.users.get(userID);
        if (user.getEstado().equals(Estado.SUSPENSO)) {
            throw new RuleException(userID, obraID, 2);
        }
    }

    /**
     * Verifica se o Stock acabou
     *
     * @param obraID obra id
     * @return Retorna TRUE se a obra tem stock e FALSE quando não tem
     */
    public boolean verificarStock(int obraID) {
        Obra obra = this.obras.get(obraID);
        return obra.getStock() > 0;
    }

    /**
     * Procura a quantidade de dias para devolver a obra (comportamento e a quantidade de exemplares da obra)
     *
     * @param userID id utente
     * @param obraID id obra
     * @return Retorna a data de entrega da Obra Requisitada
     */
    public int calcularDataEntrega(int userID, int obraID) {
        User user = users.get(userID);
        Obra obra = obras.get(obraID);
        if (obra.getExemplares() > 5) {
            return switch (user.getComportamento()) {
                case CUMPRIDOR -> 30;
                case NORMAL -> 15;
                case FALTOSO -> 2;
            };
        } else if (obra.getExemplares() > 1 && obra.getExemplares() <= 5) {
            return switch (user.getComportamento()) {
                case CUMPRIDOR -> 15;
                case NORMAL -> 8;
                case FALTOSO -> 2;
            };
        } else if (obra.getExemplares() == 1) {
            return switch (user.getComportamento()) {
                case CUMPRIDOR -> 8;
                case NORMAL -> 3;
                case FALTOSO -> 2;
            };
        }
        return 0;
    }

    /**
     * Faz a devolução da Obra que foi requisitada
     *
     * @param userID id utente
     * @param obraID id obra
     */
    public void devolverObra(int userID, int obraID) throws BorrowException {
        verificarUtenteObra(userID, obraID);

        User user = this.users.get(userID);
        Obra obra = this.obras.get(obraID);

        int devolverObra = procurarRequisicao(userID, obraID);

        //remove o id da requisição da pessoa que pegou
        user.requisicaoID.remove(Integer.valueOf(devolverObra));

        //pegar o objeto REQUISICAO, logo em seguida remover ele da class para liberar memoria
        Requisicao req = this.requisicoes.get(devolverObra);
        this.requisicoes.values().remove(req);
        user.requisicao.remove(Integer.valueOf(obraID));

        user.numRequisicoes--;
        int novoStock = obra.getStock() + 1;
        obra.setStock(novoStock);
        verificarTempoEntrega(userID, req);

        //REGISTRO das notificações para outros usuario sobre a devolução
        for (Integer userIDnot : obra.UserIDRegistro) {
            User userNot = this.users.get(userIDnot);
            userNot.NotificacaoObra.add("ENTREGA: " + obra);
        }
    }

    /**
     * Verificar se o Utente possui aquela Obra
     *
     * @param userID id utente
     * @param obraID id obra
     */
    public void verificarUtenteObra(int userID, int obraID) throws BorrowException {
        User user = this.users.get(userID);
        if (user.getObraID(obraID)) {
            return;
        }
        throw new BorrowException(userID, obraID);
    }

    /**
     * Procura requisicões feitas por um Utente especifico
     *
     * @param userID user id;
     * @param obraID obra id;
     * @return Retorna o ID da requisições feitas pelo o Utente
     */
    public int procurarRequisicao(int userID, int obraID) {
        User user = this.users.get(userID);
        for (Integer req : user.requisicaoID) {
            Requisicao reqID = this.requisicoes.get(req);
            if (reqID.getObraID() == obraID) {
                return reqID.getId();
            }
        }
        return 0;
    }

    /**
     * Gere a mundança de Comportamento do Utente consoante as devoluções a Tempo ou Fora do Tempo das Obras
     * @param userID user id
     */
    public void gerirComportamento(int userID) {
        User user = this.users.get(userID);

        if (user.multaAtrasada >= 3) {
            user.setComportamento(Comportamento.FALTOSO);
        } else if (user.multaNoTempo == 3 || user.getComportamento().equals(Comportamento.FALTOSO)) {
            user.setComportamento(Comportamento.NORMAL);
        } else if (user.multaNoTempo >= 5) {
            user.setComportamento(Comportamento.CUMPRIDOR);
        }
    }

    /**
     * Verifica se a Obra foi entregue dentro ou fora da Data de Entrega
     * @param userID user id
     * @param reqID requisição id
     */
    public void verificarTempoEntrega(int userID, Requisicao reqID) {
        User user = this.users.get(userID);

        if (getData() > reqID.getDataEntrega()) {
            user.multaNoTempo = 0;
            user.multaAtrasada++;
            gerirComportamento(userID);
        } else if (getData() < reqID.getDataEntrega()) {
            user.multaAtrasada = 0;
            user.multaNoTempo++;
            gerirComportamento(userID);
        }
    }

    /**
     * Verificar a Data de Entrega de uma Obra Requisitada
     *
     * @param userID user id;
     * @param reqID requisição id;
     */
    public void verificarDataEntrega(int userID, Requisicao reqID) {
        User user = this.users.get(userID);

        reqID.setDiasSemEntregar(getData() - reqID.getDataEntrega());
        user.setMulta(reqID.getDiasSemEntregar() * multaDiaria + user.getMulta());
        user.setEstado(Estado.SUSPENSO);

    }

    /**
     * Procura a multa de um utente específico
     *
     * @param userID utente id
     * @return Retorna a multa do utente
     */
    public int mostrarMulta(int userID) {
        User user = this.users.get(userID);
        return user.getMulta();
    }

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * @throws BadEntrySpecificationException A especificação do ficheiro não é correta
     * @throws IOException                    Erro na abertura e/ou Leitura do ficheiro
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Scanner s = new Scanner(new File(filename));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] elementos = line.split(":", 0);
            switch (elementos[0]) {
                case "USER" -> this.registarUser(elementos[1], elementos[2]);
                case "BOOK" -> this.registarLivro(elementos[1], elementos[2], Double.parseDouble(elementos[3]), elementos[4], elementos[5], Integer.parseInt(elementos[6]));
                case "DVD" -> this.registarDVD(elementos[1], elementos[2], Double.parseDouble(elementos[3]), elementos[4], elementos[5], Integer.parseInt(elementos[6]));
                default -> throw new BadEntrySpecificationException("Unknow type of category");
            }
        }
        s.close();
    }
}