package com.example.library.model.rq;

import lombok.Data;

@Data
public class BorrowedBookRequest {
    private Long bookId;
    private Long memberId;
    private String borrowDate;  // use yyyy-MM-dd
    private String returnDate;
}