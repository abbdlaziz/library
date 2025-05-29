package com.example.library.usecase;

import com.example.library.model.rq.BorrowedBookRequest;
import com.example.library.model.rs.BorrowedBookResponse;
import com.example.library.model.usecase.book.Book;
import com.example.library.model.usecase.book.BorrowedBook;
import com.example.library.model.usecase.book.Member;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowedBookRepository;
import com.example.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BorrowedBookUseCase {

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public List<BorrowedBookResponse> findAll() {
        return borrowedBookRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<BorrowedBookResponse> findByMemberId(Long memberId) {
        return borrowedBookRepository.findByMemberId(memberId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BorrowedBookResponse create(BorrowedBookRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("Member not found"));

        BorrowedBook borrowedBook = BorrowedBook.builder()
                .book(book)
                .member(member)
                .borrowDate(LocalDate.parse(request.getBorrowDate()))
                .returnDate(LocalDate.parse(request.getReturnDate()))
                .build();

        return toResponse(borrowedBookRepository.save(borrowedBook));
    }

    public void delete(Long id) {
        borrowedBookRepository.deleteById(id);
    }

    private BorrowedBookResponse toResponse(BorrowedBook b) {
        return BorrowedBookResponse.builder()
                .id(b.getId())
                .bookId(b.getBook().getId())
                .bookTitle(b.getBook().getTitle())
                .memberId(b.getMember().getId())
                .memberName(b.getMember().getName())
                .borrowDate(b.getBorrowDate().toString())
                .returnDate(b.getReturnDate().toString())
                .build();
    }
}