package atec.poo.mediateca.core;

public class DVD extends Obra {
    private final String realizador;
    private final String igac;

    public DVD(int id, String titulo, String realizador, Double preco, String categoria, String igac, int exemplares) {
        super(id, titulo, preco, categoria, exemplares);
        this.realizador = realizador;
        this.igac = igac;
        this.setTipo(Tipo.DVD);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + this.realizador + " - " + this.igac;
    }

    @Override
    public String nomeCriador() {
        return this.realizador;
    }
}