package br.org.catolicasc.biblioteca.dto;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.bean.Book;

public class BookDTO {

    private Long id;
    private String title;
    private String isbn;
    private int quantity;
    private Author author;

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookDTO toBook(Book book) {
        Book book = new Book();
        book.setTitle(book.getTitle());
        book.setIsbn(book.getIsbn());
        book.setQuantity(book.getQuantity());
        book.setAuthor(book.getAuthor());
        return book;
    }

}
