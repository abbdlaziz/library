package com.example.library.model.rs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String category;
    private int publishingYear;

    private Long authorId;
    private String authorName;
}