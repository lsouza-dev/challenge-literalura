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
    private String linguagem;
    private int quantidadeDeDownloads;

    public Livros(){};

    public Livros(DadosLivros livroRecord) {

        if(livroRecord.titulo() != null){
            this.titulo = String.valueOf(livroRecord.titulo());
            this.linguagem = livroRecord.linguagens().getFirst();
            this.quantidadeDeDownloads = livroRecord.qtdDownloads();
        }else throw new RuntimeException("Não foi encontrado um livro com o título inserido.");
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

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
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
            """, getTitulo(), getAutores().stream().map(Autores::getNome).collect(Collectors.joining(", ")), getLinguagem(), getQuantidadeDeDownloads());}
}
