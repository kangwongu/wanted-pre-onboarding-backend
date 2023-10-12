package com.example.wantedpreonboardingbackend.recruitment.domain;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.recruitment.dto.UpdateRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RecruitmentTest {

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

}
