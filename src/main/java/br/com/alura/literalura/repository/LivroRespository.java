package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRespository extends JpaRepository<Livros,Long> {
}
