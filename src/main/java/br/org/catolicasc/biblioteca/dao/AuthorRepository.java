package br.org.catolicasc.biblioteca.dao;

import br.org.catolicasc.biblioteca.bean.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
