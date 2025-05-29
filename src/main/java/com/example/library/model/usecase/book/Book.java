package com.example.library.model.usecase.book;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    @Column(name = "publishing_year")
    private int publishingYear;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}