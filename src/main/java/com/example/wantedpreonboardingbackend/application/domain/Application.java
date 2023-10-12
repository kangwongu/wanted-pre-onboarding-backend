package com.example.wantedpreonboardingbackend.application.domain;

import com.example.wantedpreonboardingbackend.member.domain.Member;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import lombok.Builder;
import lombok.Getter;

// 지원내역
@Getter
public class Application {
    private Long id;
    private Recruitment recruitment;
    private Member member;

    @Builder
    public Application(Long id, Recruitment recruitment, Member member) {
        this.id = id;
        this.recruitment = recruitment;
        this.member = member;
    }

    public static Application of(Recruitment recruitment, Member member) {
        return Application.builder()
                .recruitment(recruitment)
                .member(member)
                .build();
    }
}
