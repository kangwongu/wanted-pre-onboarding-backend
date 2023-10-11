package com.example.wantedpreonboardingbackend.company;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.company.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.company.service.CompanyService;
import com.example.wantedpreonboardingbackend.mock.FakeCompanyRepository;
import com.example.wantedpreonboardingbackend.mock.FakeRecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompanyServiceTest {

    private CompanyService companyService;
    private FakeRecruitmentRepository recruitmentRepository;

    @BeforeEach
    void init() {
        recruitmentRepository = new FakeRecruitmentRepository();
        FakeCompanyRepository companyRepository = new FakeCompanyRepository();
        companyService = new CompanyService(companyRepository, recruitmentRepository);

        companyRepository.save(Company.builder()
                .id(1L)
                .name("좋은 회사")
                .nation("한국")
                .address("서울")
                .build()
        );
    }

    @Test
    public void 회사는_채용공고를_등록할_수_있다() {
        // given
        RegisterRequest request = new RegisterRequest();
        request.setCompanyId(1L);
        request.setPosition("백엔드 개발자");
        request.setCompensation(1000000);
        request.setContents("백엔드 개발자 채용합니다. 지원어쩌구저쩌구");
        request.setTech("JAVA/Spring/JPA");

        // when
        companyService.registerRecruitment(request);

        // then
        assertThat(recruitmentRepository.getById(1L).getCompany().getId()).isEqualTo(1L);
        assertThat(recruitmentRepository.getById(1L).getPosition()).isEqualTo("백엔드 개발자");
        assertThat(recruitmentRepository.getById(1L).getCompensation()).isEqualTo(1000000);
        assertThat(recruitmentRepository.getById(1L).getContents()).isEqualTo("백엔드 개발자 채용합니다. 지원어쩌구저쩌구");
        assertThat(recruitmentRepository.getById(1L).getTech()).isEqualTo("JAVA/Spring/JPA");
    }
}
