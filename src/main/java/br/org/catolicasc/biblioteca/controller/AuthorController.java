package br.org.catolicasc.biblioteca.controller;

import br.org.catolicasc.biblioteca.dto.AuthorDTO;
import br.org.catolicasc.biblioteca.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> authorList() {
        List<AuthorDTO> authors = authorService.findAllAuthor();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorDTO createAuthor(){
        return null;
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(){
        return null;
    }

    @DeleteMapping("/{id}")
    public AuthorDTO deleteAuthor() {
        return null;
    }
}
