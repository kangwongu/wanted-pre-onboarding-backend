package com.example.wantedpreonboardingbackend.recruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    private long companyId;
    private String position;
    private int compensation;
    private String contents;
    private String tech;
}
