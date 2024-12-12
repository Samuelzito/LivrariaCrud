package edu.livraria.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javafx.beans.property.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String genero;

    // Construtor padrão
    public Livro() {}

    // Propriedades JavaFX para TableView
    public LongProperty idProperty() {
        return new SimpleLongProperty(id);
    }

    public StringProperty tituloProperty() {
        return new SimpleStringProperty(titulo);
    }

    public StringProperty autorProperty() {
        return new SimpleStringProperty(autor);
    }

    public IntegerProperty anoPublicacaoProperty() {
        return new SimpleIntegerProperty(anoPublicacao);
    }

    public StringProperty generoProperty() {
        return new SimpleStringProperty(genero);
    }

    // Getters e Setters padrão
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
