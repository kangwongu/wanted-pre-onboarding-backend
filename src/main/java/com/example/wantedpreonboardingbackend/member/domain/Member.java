package com.example.wantedpreonboardingbackend.member.domain;

import com.example.wantedpreonboardingbackend.application.domain.Application;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Member {
    private Long id;
    private String name;

    @Builder
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Application apply(Recruitment recruitment, List<Long> appliedRecruitmentIds) {
        if (recruitment.isDuplicateApplication(appliedRecruitmentIds)) {
            throw new IllegalStateException("Already Applied");
        }
        return Application.of(recruitment, this);
    }
}
