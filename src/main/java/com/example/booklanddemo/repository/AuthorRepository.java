package com.example.booklanddemo.repository;

import com.example.booklanddemo.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
