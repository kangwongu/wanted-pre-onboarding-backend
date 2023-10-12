package com.example.wantedpreonboardingbackend.application.service;

import com.example.wantedpreonboardingbackend.application.domain.Application;
import com.example.wantedpreonboardingbackend.application.dto.ApplyRequest;
import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.member.domain.Member;
import com.example.wantedpreonboardingbackend.mock.FakeApplicationRepository;
import com.example.wantedpreonboardingbackend.mock.FakeMemberRepository;
import com.example.wantedpreonboardingbackend.mock.FakeRecruitmentRepository;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ApplicationServiceTest {

    private ApplicationService applicationService;
    private FakeApplicationRepository applicationRepository;

    @BeforeEach
    void init() {
        applicationRepository = new FakeApplicationRepository();
        FakeRecruitmentRepository recruitmentRepository = new FakeRecruitmentRepository();
        FakeMemberRepository memberRepository = new FakeMemberRepository();

        applicationService = new ApplicationService(applicationRepository, recruitmentRepository, memberRepository);

        Recruitment recruitment1 = recruitmentRepository.save(Recruitment.builder()
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
                .build()
        );
        Recruitment recruitment2 = recruitmentRepository.save(Recruitment.builder()
                .id(2L)
                .company(Company.builder()
                        .id(1L)
                        .name("좋은 회사")
                        .nation("한국")
                        .address("서울")
                        .build())
                .position("백엔드 시니어 개발자")
                .compensation(1000000)
                .contents("채용할거에요")
                .tech("Python/Django")
                .build()
        );
        Member member = memberRepository.save(Member.builder()
                .id(1L)
                .name("지원자1")
                .build()
        );
        applicationRepository.save(Application.builder()
                .id(1L)
                .recruitment(recruitment1)
                .member(member)
                .build()
        );
    }

    @Test
    public void 채용공고에_지원할_수_있다() {
        // given
        ApplyRequest request = new ApplyRequest();
        request.setRecruitmentId(2L);
        request.setMemberId(1L);

        // when
        applicationService.apply(request);

        // then
        assertThat(applicationRepository.getById(1L).getRecruitment().getId()).isEqualTo(1L);
        assertThat(applicationRepository.getById(1L).getMember().getId()).isEqualTo(1L);
    }

    @Test
    public void 동일한_채용공고에_중복_지원할_수_없다() {
        // given
        ApplyRequest request = new ApplyRequest();
        request.setRecruitmentId(1L);
        request.setMemberId(1L);

        // when
        // then
        assertThatThrownBy(() -> applicationService.apply(request))
                .isInstanceOf(IllegalStateException.class);
    }
}
