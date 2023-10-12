package com.example.wantedpreonboardingbackend.company.domain;

import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Company {
    private Long id;
    private String name;
    private String nation;
    private String address;

    @Builder
    public Company(Long id, String name, String nation, String address) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.address = address;
    }

    public Recruitment createRecruitment(RegisterRequest request) {
        return Recruitment.of(this, request);
    }

}
