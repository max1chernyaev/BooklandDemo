package com.example.booklanddemo.controller;


import com.example.booklanddemo.domain.Book;
import com.example.booklanddemo.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/books")
    public Book saveBook(@RequestBody Book book){
        return repository.save(book);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book findBookById(@PathVariable Integer id) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id = " + id));

        return book;
    }

    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable ("id") Integer bookId, @RequestBody Book book){

        return repository.findById(bookId)
                .map(entity -> {
                    entity.setName(book.getName());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id = " + bookId));
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookById(@PathVariable Integer id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id = " + id));
        repository.delete(book);
    }

}
