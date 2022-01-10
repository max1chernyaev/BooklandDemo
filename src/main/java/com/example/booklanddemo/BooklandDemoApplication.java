package com.example.booklanddemo;

import com.example.booklanddemo.domain.Author;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class BooklandDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooklandDemoApplication.class, args);
    }

    public static interface AuthorRepository extends JpaRepository<Author, Integer> {
    }
}
