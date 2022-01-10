package com.example.booklanddemo.domain;

import com.example.booklanddemo.domain.Author;
import com.example.booklanddemo.domain.Book;
import com.example.booklanddemo.domain.Publisher;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "rewards")
@Data
@NoArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reward_id", nullable = false)
    private Integer rewardId;

    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "author_fk", nullable = false)
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "book_fk", nullable = false)
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "publisher_fk", nullable = false)
    private Publisher publisher;
}