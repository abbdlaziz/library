package com.example.library.model.rq;

import lombok.Data;

@Data
public class MemberRequest {
    private String name;
    private String email;
    private String phone;
}