package com.example.booklanddemo.controller;



import com.example.booklanddemo.domain.Author;
import com.example.booklanddemo.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    private final AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author author) {
        return repository.save(author);
    }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author findAuthorById(@PathVariable Integer id) {

        Author author = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));

        return author;
    }

    @PutMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@PathVariable ("id") Integer authorId, @RequestBody Author author){

        return repository.findById(authorId).map(entity -> {
            entity.setFirstName(author.getFirstName());
            entity.setLastName(author.getLastName());
            entity.setCountry(author.getCountry());
            entity.setBirthDate(author.getBirthDate());
            return repository.save(author);
        })
        .orElseThrow(() -> new EntityNotFoundException("Car not found with id = " + authorId));
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAuthorById(@PathVariable Integer id) {
        Author author = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));
        repository.delete(author);
    }
}
