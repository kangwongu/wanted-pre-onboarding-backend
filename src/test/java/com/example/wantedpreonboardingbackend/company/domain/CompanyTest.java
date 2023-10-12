package com.example.wantedpreonboardingbackend.company.domain;

import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.recruitment.dto.UpdateRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CompanyTest {

    @Test
    public void Company는_RegisetRequest로_채용공고를_생성할_수_있다() {
        // given
        Company company = Company.builder()
                .id(1L)
                .name("좋은 회사")
                .nation("한국")
                .address("서울")
                .build();

        String position = "백엔드 개발자";
        int compensation = 10000000;
        String contents = "멋진 백엔드 개발자를 채용합니다. 지원...";
        String tech = "JAVA/Spring/JPA";

        RegisterRequest request = new RegisterRequest();
        request.setPosition(position);
        request.setCompensation(compensation);
        request.setContents(contents);
        request.setTech(tech);

        // when
        Recruitment recruitment = company.createRecruitment(request);

        // then
        assertThat(recruitment.getCompany().getName()).isEqualTo("좋은 회사");
        assertThat(recruitment.getPosition()).isEqualTo("백엔드 개발자");
        assertThat(recruitment.getCompensation()).isEqualTo(10000000);
        assertThat(recruitment.getContents()).isEqualTo("멋진 백엔드 개발자를 채용합니다. 지원...");
        assertThat(recruitment.getTech()).isEqualTo("JAVA/Spring/JPA");
    }

    @Test
    public void Company는_UpdateRequest로_기존_채용공고를_수정할_수_있다() {
        // given
        String position = "백엔드 개발자";
        int compensation = 1000000;
        String contents = "멋진 백엔드 개발자를 채용합니다. 지원...";
        String tech = "JAVA/Spring/JPA";

        UpdateRequest request = new UpdateRequest();
        request.setPosition(position);
        request.setCompensation(compensation);
        request.setContents(contents);
        request.setTech(tech);

        Company company = Company.builder()
                .id(1L)
                .name("좋은 회사")
                .nation("한국")
                .address("서울")
                .build();

        Recruitment recruitment = Recruitment.builder()
                .id(1L)
                .company(company)
                .position("백엔드 개발자")
                .compensation(500000)
                .contents("채용합니다")
                .tech("Python/Django")
                .build();

        // when
        Recruitment updateRecruitment = company.updateRecruitment(recruitment, request);

        // then
        assertThat(updateRecruitment.getId()).isEqualTo(1L);
        assertThat(updateRecruitment.getPosition()).isEqualTo("백엔드 개발자");
        assertThat(updateRecruitment.getCompensation()).isEqualTo(1000000);
        assertThat(updateRecruitment.getContents()).isEqualTo("멋진 백엔드 개발자를 채용합니다. 지원...");
        assertThat(updateRecruitment.getTech()).isEqualTo("JAVA/Spring/JPA");
    }

    @Test
    public void 다른_Company의_채용공고를_수정할_수_없다() {
        // given
        String position = "백엔드 개발자";
        int compensation = 1000000;
        String contents = "멋진 백엔드 개발자를 채용합니다. 지원...";
        String tech = "JAVA/Spring/JPA";

        UpdateRequest request = new UpdateRequest();
        request.setPosition(position);
        request.setCompensation(compensation);
        request.setContents(contents);
        request.setTech(tech);

        Company company1 = Company.builder()
                .id(1L)
                .name("좋은 회사")
                .nation("한국")
                .address("서울")
                .build();

        Company company2 = Company.builder()
                .id(2L)
                .name("회사")
                .nation("일본")
                .address("도쿄")
                .build();

        Recruitment recruitment = Recruitment.builder()
                .id(1L)
                .company(company1)
                .position("백엔드 개발자")
                .compensation(500000)
                .contents("채용합니다")
                .tech("Python/Django")
                .build();

        // when
        // then
        assertThatThrownBy(() -> company2.updateRecruitment(recruitment, request))
                .isInstanceOf(IllegalStateException.class);

    }

}
