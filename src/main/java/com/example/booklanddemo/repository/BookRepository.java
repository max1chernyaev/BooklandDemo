package com.example.booklanddemo.repository;

import com.example.booklanddemo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
