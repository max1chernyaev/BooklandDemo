package com.example.booklanddemo.controller;


import com.example.booklanddemo.domain.Publisher;
import com.example.booklanddemo.repository.PublisherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublisherController {
    private PublisherRepository repository;

    public PublisherController(PublisherRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/publishers")
    public Publisher savePublisher(@RequestBody Publisher publisher){
        return repository.save(publisher);
    }

    @GetMapping("/publishers")
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> findAllPublishers() {
        return repository.findAll();
    }

    @GetMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher findPublisherById(@PathVariable Integer id) {

        Publisher publisher = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found with id = " + id));

        return publisher;
    }

    @PutMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher updatePublisher(@PathVariable ("id") Integer publisherId, @RequestBody Publisher publisher){

        return repository.findById(publisherId)
                .map(entity -> {
            entity.setName(publisher.getName());
            entity.setCountry(publisher.getCountry());
            entity.setCity(publisher.getCity());
            entity.setAddress(publisher.getAddress());
            return repository.save(publisher);
        })
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id = " + publisherId));
    }

    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePublisherById(@PathVariable Integer id) {
        Publisher publisher = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found with id = " + id));
        repository.delete(publisher);
    }
}
