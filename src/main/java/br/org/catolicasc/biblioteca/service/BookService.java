package br.org.catolicasc.biblioteca.service;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.bean.Book;
import br.org.catolicasc.biblioteca.dao.AuthorRepository;
import br.org.catolicasc.biblioteca.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public Book createBook(Book book){
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com o id: " + book.getAuthor().getId()));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book newBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            Author author = authorRepository.findById(book.getAuthor().getId())
                            .orElseThrow(() -> new RuntimeException("Autor não encontrado com o id: " + book.getAuthor().getId()));
            book.setAuthor(author);
            book.setIsbn(newBook.getIsbn());
            book.setQuantity(newBook.getQuantity());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado com o id: " + id));
    }
}
