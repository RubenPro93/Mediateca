package atec.poo.mediateca.core;

public class Livro extends Obra {
    private final String autor;
    private final String isbn;

    public Livro(int id, String titulo, String autor, Double preco, String categoria, String isbn, int exemplares) {
        super(id, titulo, preco, categoria, exemplares);
        this.autor = autor;
        this.isbn = isbn;
        this.setTipo(Tipo.BOOK);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + this.autor + " - " + this.isbn;
    }

    @Override
    public String nomeCriador() {
        return this.autor;
    }
}