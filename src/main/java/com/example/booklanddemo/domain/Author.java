package com.example.booklanddemo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "author_id", nullable = false, insertable = false, updatable = false)
    private Integer authorId;

    private String firstName;
    private String lastName;
    private String country;
    private Date birthDate;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Reward> rewards = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_fk", nullable = false)
    private Publisher publisher;

}
