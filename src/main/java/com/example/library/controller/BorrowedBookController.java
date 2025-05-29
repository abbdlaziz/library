package com.example.library.controller;

import com.example.library.model.rq.BorrowedBookRequest;
import com.example.library.model.rs.BorrowedBookResponse;
import com.example.library.usecase.BorrowedBookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowed")
@RequiredArgsConstructor
public class BorrowedBookController {

    private final BorrowedBookUseCase useCase;

    @GetMapping
    public ResponseEntity<List<BorrowedBookResponse>> getAll() {
        return ResponseEntity.ok(useCase.findAll());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BorrowedBookResponse>> getByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(useCase.findByMemberId(memberId));
    }

    @PostMapping
    public ResponseEntity<BorrowedBookResponse> create(@RequestBody BorrowedBookRequest request) {
        return ResponseEntity.ok(useCase.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}