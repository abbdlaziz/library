package com.example.library.usecase;


import com.example.library.model.rq.MemberRequest;
import com.example.library.model.rs.MemberResponse;
import com.example.library.model.usecase.book.Member;
import com.example.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class MemberUseCase {

    private final MemberRepository memberRepository;

    public List<MemberResponse> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Member not found"));
        return toResponse(member);
    }

    public MemberResponse create(MemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());

        Member saved = memberRepository.save(member);
        return toResponse(saved);
    }

    public MemberResponse update(Long id, MemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Member not found"));

        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());

        Member updated = memberRepository.save(member);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new NoSuchElementException("Member not found");
        }
        memberRepository.deleteById(id);
    }

    private MemberResponse toResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }
}
