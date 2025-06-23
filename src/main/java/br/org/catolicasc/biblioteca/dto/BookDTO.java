package br.org.catolicasc.biblioteca.dto;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.bean.Book;

public class BookDTO {

    private Long id;
    private String title;
    private String isbn;
    private int quantity;
    private AuthorDTO author;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String isbn, int quantity, AuthorDTO author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.quantity = quantity;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public static Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setQuantity(dto.getQuantity());

        if (dto.getAuthor() != null) {
            book.setAuthor(AuthorDTO.toEntity(dto.getAuthor()));
        }

        return book;
    }

    public static BookDTO fromEntity(Book book) {
        AuthorDTO authorDTO = null;
        if (book.getAuthor() != null) {
            authorDTO = AuthorDTO.fromEntity(book.getAuthor());
        }

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getQuantity(),
                authorDTO
        );
    }

}
