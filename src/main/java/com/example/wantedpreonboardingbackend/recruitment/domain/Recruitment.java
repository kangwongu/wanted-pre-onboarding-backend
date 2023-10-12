package com.example.wantedpreonboardingbackend.recruitment.domain;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.recruitment.dto.UpdateRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

// 채용공고
@Getter
public class Recruitment {
    private final Long id;
    private final Company company;
    private final String position;
    private final Integer compensation;
    private final String contents;
    private final String tech;

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

    public long getCompanyId() {
        return company.getId();
    }

    public Recruitment update(UpdateRequest request) {
        return Recruitment.builder()
                .id(id)
                .company(company)
                .position(request.getPosition())
                .compensation(request.getCompensation())
                .contents(request.getContents())
                .tech(request.getTech())
                .build();
    }

    public boolean isDuplicateApplication(List<Long> appliedRecruitmentIds) {
        for (Long appliedRecruitmentId : appliedRecruitmentIds) {
            if (Objects.equals(id, appliedRecruitmentId)) {
                return true;
            }
        }
        return false;
    }
}
