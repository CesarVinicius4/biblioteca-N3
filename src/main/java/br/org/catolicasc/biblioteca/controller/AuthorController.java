package br.org.catolicasc.biblioteca.controller;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.dto.AuthorDTO;
import br.org.catolicasc.biblioteca.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    public List<AuthorDTO> authorList() {
        return service.findAllAuthor();
    }
}
