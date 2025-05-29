package com.example.library.usecase;

import com.example.library.model.rq.AuthorRequest;
import com.example.library.model.rs.AuthorResponse;
import com.example.library.model.usecase.book.Author;
import com.example.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorUseCase {

    private final AuthorRepository authorRepository;

    public List<AuthorResponse> findAll() {
        return authorRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AuthorResponse findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found"));
        return toResponse(author);
    }

    public AuthorResponse create(AuthorRequest request) {
        Author author = Author.builder()
                .name(request.getName())
                .build();
        return toResponse(authorRepository.save(author));
    }

    public AuthorResponse update(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found"));

        author.setName(request.getName());
        return toResponse(authorRepository.save(author));
    }

    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author not found");
        }
        authorRepository.deleteById(id);
    }

    private AuthorResponse toResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }
}