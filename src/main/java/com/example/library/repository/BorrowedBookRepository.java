package com.example.library.repository;

import com.example.library.model.usecase.book.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    List<BorrowedBook> findByMemberId(Long memberId);
}