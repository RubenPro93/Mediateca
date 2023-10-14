package atec.poo.mediateca.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Obra implements Comparable<Obra>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String titulo;
    private final Double preco;
    private final String categoria;
    private int stock;
    private final int exemplares;
    private Tipo tipo;
    List<Integer> UserIDRegistro = new ArrayList<>();

    public Obra(int id, String titulo, Double preco, String categoria, int exemplares) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.categoria = categoria;
        this.stock = exemplares;
        this.exemplares = exemplares;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getExemplares() {
        return exemplares;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.stock + " de " + this.exemplares + " - " + this.tipo + " - " + this.titulo + " - " + this.preco + " - " + this.categoria;
    }

    @Override
    public int compareTo(Obra o) {
        if (this.titulo.equals(o.getTitulo()))
            return this.id - o.getId();
        return this.titulo.compareTo(o.getTitulo());
    }

    public abstract String nomeCriador();
}