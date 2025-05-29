package com.example.library.model.rq;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String category;
    private int publishingYear;
    private Long authorId;
}