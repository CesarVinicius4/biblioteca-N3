package br.org.catolicasc.biblioteca.service;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.dao.AuthorRepository;
import br.org.catolicasc.biblioteca.dto.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> findAllAuthor(){
        return authorRepository.findAll()
                .stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        return new AuthorDTO(author.getId(), author.getName());
    }

}
