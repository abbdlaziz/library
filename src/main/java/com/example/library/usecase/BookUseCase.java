package com.example.library.usecase;

import com.example.library.model.rq.BookRequest;
import com.example.library.model.rs.BookResponse;
import com.example.library.model.usecase.book.Author;
import com.example.library.model.usecase.book.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
        return toResponse(book);
    }

    public BookResponse create(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException("Author not found"));

        Book book = Book.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .publishingYear(request.getPublishingYear())
                .author(author)
                .build();

        return toResponse(bookRepository.save(book));
    }

    public BookResponse update(Long id, BookRequest request) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException("Author not found"));

        existing.setTitle(request.getTitle());
        existing.setCategory(request.getCategory());
        existing.setPublishingYear(request.getPublishingYear());
        existing.setAuthor(author);

        return toResponse(bookRepository.save(existing));
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    private BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .category(book.getCategory())
                .publishingYear(book.getPublishingYear())
                .authorId(book.getAuthor().getId())
                .authorName(book.getAuthor().getName())
                .build();
    }
}