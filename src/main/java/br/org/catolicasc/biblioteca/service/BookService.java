package br.org.catolicasc.biblioteca.service;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.bean.Book;
import br.org.catolicasc.biblioteca.dao.AuthorRepository;
import br.org.catolicasc.biblioteca.dao.BookRepository;
import br.org.catolicasc.biblioteca.dto.BookDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<BookDTO> findAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id){
        return bookRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
    }

    public BookDTO createBook(BookDTO dto){
        Author author = authorRepository.findById(bookDTO.getAuthor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado" + bookDTO.getAuthor()));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setQuantity(dto.getQuantity());
        book.setAuthor(author);

        Book saved = bookRepository.save(book);
        return convertToDTO(saved);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book newBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            Author author = authorRepository.findById(book.getAuthor().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com o id: " + book.getAuthor().getId()));
            book.setAuthor(author);
            book.setIsbn(newBook.getIsbn());
            book.setQuantity(newBook.getQuantity());
            return bookRepository.save(book);
        }).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com o id: " + id));
    }

    
}
