package br.org.catolicasc.biblioteca.service;

import br.org.catolicasc.biblioteca.bean.Author;
import br.org.catolicasc.biblioteca.dao.AuthorRepository;
import br.org.catolicasc.biblioteca.dto.AuthorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> findAllAuthor(){
        return authorRepository.findAll()
                .stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));
        return new AuthorDTO(author.getId(), author.getName());
    }

    public AuthorDTO createAuthor(AuthorDTO dto){
        Author author = AuthorDTO.toEntity(dto);
        Author saved = authorRepository.save(author);
        return AuthorDTO.fromEntity(saved);
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com o ID: " + id));

        author.setName(dto.getName());

        Author updated = authorRepository.save(author);
        return AuthorDTO.fromEntity(updated);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Autor não encontrado com o ID: " + id);
        }
        authorRepository.deleteById(id);
    }

}
