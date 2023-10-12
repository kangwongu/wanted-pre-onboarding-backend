package com.example.wantedpreonboardingbackend.member.domain;

import com.example.wantedpreonboardingbackend.application.domain.Application;
import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberTest {

    @Test
    public void Member는_Application을_생성할_수_있다() {
        // given
        Member member = Member.builder()
                .id(1L)
                .name("지원자1")
                .build();

        Recruitment recruitment = Recruitment.builder()
                .id(1L)
                .company(Company.builder()
                        .id(1L)
                        .name("좋은 회사")
                        .nation("한국")
                        .address("서울")
                        .build())
                .position("백엔드 개발자")
                .compensation(500000)
                .contents("채용합니다")
                .tech("Python/Django")
                .build();

        // when
        Application application = member.apply(recruitment, List.of());

        // then
        assertThat(application.getRecruitment().getId()).isEqualTo(1L);
        assertThat(application.getRecruitment().getCompany().getName()).isEqualTo("좋은 회사");
        assertThat(application.getRecruitment().getPosition()).isEqualTo("백엔드 개발자");
        assertThat(application.getMember().getId()).isEqualTo(1L);
        assertThat(application.getMember().getName()).isEqualTo("지원자1");
    }

    @Test
    public void 이미_지원한_곳에_Application을_생성할_수_없다() {
        // given
        Member member = Member.builder()
                .id(1L)
                .name("지원자1")
                .build();

        Recruitment recruitment = Recruitment.builder()
                .id(1L)
                .company(Company.builder()
                        .id(1L)
                        .name("좋은 회사")
                        .nation("한국")
                        .address("서울")
                        .build())
                .position("백엔드 개발자")
                .compensation(500000)
                .contents("채용합니다")
                .tech("Python/Django")
                .build();

        // when
        // then
        assertThatThrownBy(() -> member.apply(recruitment, List.of(1L)))
                .isInstanceOf(IllegalStateException.class);

    }

}
