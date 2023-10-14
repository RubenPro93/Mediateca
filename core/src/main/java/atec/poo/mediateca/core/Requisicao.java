package atec.poo.mediateca.core;

import java.io.Serial;
import java.io.Serializable;

public class Requisicao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int id;
    private final int userID;
    private final int obraID;
    private final int dataRequisicao;
    private final int dataEntrega;
    private int diasSemEntregar;

    public Requisicao(int id, int userID, int obraID, int dataRequisicao, int dataEntrega) {
        this.id = id;
        this.userID = userID;
        this.obraID = obraID;
        this.dataRequisicao = dataRequisicao;
        this.dataEntrega = dataEntrega;
        this.diasSemEntregar = 0;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getObraID() {
        return obraID;
    }

    public int getDataEntrega() {
        return dataEntrega;
    }

    public int getDiasSemEntregar() {
        return diasSemEntregar;
    }

    public void setDiasSemEntregar(int diasSemEntregar) {
        this.diasSemEntregar = diasSemEntregar;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " - UserID: " + this.userID + " - ObraID: " + this.obraID + " - DataRequisicao: " + this.dataRequisicao + " - DataEntrega: " + this.dataEntrega;
    }
}