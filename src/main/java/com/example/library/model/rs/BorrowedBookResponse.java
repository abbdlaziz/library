package com.example.library.model.rs;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class BorrowedBookResponse {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Long memberId;
    private String memberName;
    private String borrowDate;
    private String returnDate;
}