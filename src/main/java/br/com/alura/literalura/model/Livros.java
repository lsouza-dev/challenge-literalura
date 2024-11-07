package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "livro",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Autores> autores;
    private List<String> languages;
    private int quantidadeDeDownloads;

    public Livros(){};

    public Livros(DadosLivros livroRecord) {

        this.titulo = String.valueOf(livroRecord.titulo());
        this.languages = livroRecord.linguagens();
        this.quantidadeDeDownloads = livroRecord.qtdDownloads();
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getQuantidadeDeDownloads() {
        return quantidadeDeDownloads;
    }

    public void setQuantidadeDeDownloads(int quantidadeDeDownloads) {
        this.quantidadeDeDownloads = quantidadeDeDownloads;
    }

    @Override public String toString() {
        return String.format(""" 
            ****** LIVRO ******
            Título: %s
            Autor: %s
            Idiomas: %s
            Número de Downloads: %d 
            ******************* 
            """, getTitulo(), getAutores().stream().map(Autores::getNome).collect(Collectors.joining(", ")), getLanguages(), getQuantidadeDeDownloads());}
}
