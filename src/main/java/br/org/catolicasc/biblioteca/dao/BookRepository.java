package br.org.catolicasc.biblioteca.dao;

import br.org.catolicasc.biblioteca.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
