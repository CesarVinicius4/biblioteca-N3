package br.org.catolicasc.biblioteca.service;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.bean.Book;
import br.org.catolicasc.biblioteca.dao.AuthorRepository;
import br.org.catolicasc.biblioteca.dao.BookRepository;
import br.org.catolicasc.biblioteca.dto.BookDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
    }

    public BookDTO createBook(BookDTO dto) {
        Author author = authorRepository.findById(dto.getAuthor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado: " + dto.getAuthor().getId()));

        Book book = BookDTO.toEntity(dto);
        book.setAuthor(author);

        Book saved = bookRepository.save(book);
        return BookDTO.fromEntity(saved);
    }

    public BookDTO updateBook(Long id, BookDTO dto) {
        return bookRepository.findById(id)
                .map(book -> {
                    Author author = authorRepository.findById(dto.getAuthor().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com o id: " + dto.getAuthor().getId()));

                    book.setTitle(dto.getTitle());
                    book.setIsbn(dto.getIsbn());
                    book.setQuantity(dto.getQuantity());
                    book.setAuthor(author);

                    Book updated = bookRepository.save(book);
                    return BookDTO.fromEntity(updated);
                }).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com o id: " + id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
