package com.example.library.controller;


import com.example.library.model.rq.MemberRequest;
import com.example.library.model.rs.MemberResponse;
import com.example.library.usecase.MemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberUseCase.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberUseCase.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}