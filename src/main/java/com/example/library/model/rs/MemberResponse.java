package com.example.library.model.rs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
}