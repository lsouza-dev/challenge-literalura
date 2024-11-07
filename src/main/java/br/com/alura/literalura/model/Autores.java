package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalescimento;
    @ManyToOne
    private Livros livro;


    public Autores(){};

    public Autores(String nome, Integer anoNascimento, Integer anoFalescimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalescimento = anoFalescimento;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoFalescimento() {
        return anoFalescimento;
    }

    public void setAnoFalescimento(int anoFalescimento) {
        this.anoFalescimento = anoFalescimento;
    }

    public Livros getLivro() {
        return livro;
    }

    public void setLivro(Livros livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Id=" + Id +
                "\tNome=" + nome +
                "\tAnoNascimento=" + anoNascimento +
                "\tAnoFalescimento=" + anoFalescimento +
                "\tLivro=" + livro.getTitulo() ;
    }
}
