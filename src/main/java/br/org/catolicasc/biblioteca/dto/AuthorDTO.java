package br.org.catolicasc.biblioteca.dto;


import br.org.catolicasc.biblioteca.bean.Author;

public class AuthorDTO {
    private Long id;
    private String name;

    public AuthorDTO() {}

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AuthorDTO fromEntity(Author author) {
        return new AuthorDTO(author.getId(), author.getName());
    }

    public static Author toEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        return author;
    }
}
