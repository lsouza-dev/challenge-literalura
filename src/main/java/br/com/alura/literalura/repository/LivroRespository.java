package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autores;
import br.com.alura.literalura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRespository extends JpaRepository<Livros,Long> {

    @Query("SELECT a FROM Livros l JOIN l.autores a")
    List<Autores> obterAutores();
}
