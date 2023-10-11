package com.example.wantedpreonboardingbackend.recruitment.domain;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

// 채용공고
@Getter
public class Recruitment {
    private Long id;
    private Company company;
    private String position;
    private Integer compensation;
    private String contents;
    private String tech;

    @Builder
    public Recruitment(Long id, Company company, String position, Integer compensation, String contents, String tech) {
        this.id = id;
        this.company = Objects.requireNonNull(company);
        this.position = Objects.requireNonNull(position);
        this.compensation = Objects.requireNonNull(compensation);
        this.contents = Objects.requireNonNull(contents);
        this.tech = Objects.requireNonNull(tech);
    }

    public static Recruitment of(Company company, RegisterRequest request) {
        return Recruitment.builder()
                .company(company)
                .position(request.getPosition())
                .compensation(request.getCompensation())
                .contents(request.getContents())
                .tech(request.getTech())
                .build();
    }
}
