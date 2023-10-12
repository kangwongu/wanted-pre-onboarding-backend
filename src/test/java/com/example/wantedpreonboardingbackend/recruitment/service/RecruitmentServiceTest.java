package com.example.wantedpreonboardingbackend.recruitment.service;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.recruitment.dto.ListResponse;
import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.mock.FakeCompanyRepository;
import com.example.wantedpreonboardingbackend.mock.FakeRecruitmentRepository;
import com.example.wantedpreonboardingbackend.recruitment.dto.UpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RecruitmentServiceTest {

    private RecruitmentService recruitmentService;
    private FakeRecruitmentRepository recruitmentRepository;

    @BeforeEach
    void init() {
        recruitmentRepository = new FakeRecruitmentRepository();
        FakeCompanyRepository companyRepository = new FakeCompanyRepository();
        recruitmentService = new RecruitmentService(companyRepository, recruitmentRepository);

        Company company = companyRepository.save(Company.builder()
                .id(1L)
                .name("좋은 회사")
                .nation("한국")
                .address("서울")
                .build()
        );
        companyRepository.save(Company.builder()
                .id(2L)
                .name("좋은 회사")
                .nation("일본")
                .address("도쿄")
                .build()
        );
        recruitmentRepository.save(Recruitment.builder()
                .id(2L)
                .company(company)
                .position("백엔드 개발자")
                .compensation(500000)
                .contents("채용합니다")
                .tech("Python/Django")
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
        recruitmentService.registerRecruitment(request);

        // then
        assertThat(recruitmentRepository.getById(1L).getCompany().getId()).isEqualTo(1L);
        assertThat(recruitmentRepository.getById(1L).getPosition()).isEqualTo("백엔드 개발자");
        assertThat(recruitmentRepository.getById(1L).getCompensation()).isEqualTo(1000000);
        assertThat(recruitmentRepository.getById(1L).getContents()).isEqualTo("백엔드 개발자 채용합니다. 지원어쩌구저쩌구");
        assertThat(recruitmentRepository.getById(1L).getTech()).isEqualTo("JAVA/Spring/JPA");
    }

    @Test
    public void 회사는_채용공고를_수정할_수_있다() {
        // given
        UpdateRequest request = new UpdateRequest();
        request.setPosition("백엔드 개발자");
        request.setCompensation(1500000);
        request.setContents("원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..");
        request.setTech("JAVA/Spring/JPA");

        // when
        recruitmentService.updateRecruitment(1, 2, request);

        // then
        assertThat(recruitmentRepository.getById(2L).getCompany().getId()).isEqualTo(1L);
        assertThat(recruitmentRepository.getById(2L).getPosition()).isEqualTo("백엔드 개발자");
        assertThat(recruitmentRepository.getById(2L).getCompensation()).isEqualTo(1500000);
        assertThat(recruitmentRepository.getById(2L).getContents()).isEqualTo("원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..");
        assertThat(recruitmentRepository.getById(2L).getTech()).isEqualTo("JAVA/Spring/JPA");
    }

    @Test
    public void 다른_회사의_채용공고는_수정할_수_없다() {
        // given
        UpdateRequest request = new UpdateRequest();
        request.setPosition("백엔드 개발자");
        request.setCompensation(1500000);
        request.setContents("원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..");
        request.setTech("JAVA/Spring/JPA");

        // when
        // then
        assertThatThrownBy(() -> recruitmentService.updateRecruitment(2, 2, request))
                .isInstanceOf(IllegalStateException.class);

    }

    @Test
    public void 채용공고를_삭제할_수_있다() {
        // given
        Recruitment recruitment = Recruitment.builder()
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
        recruitmentRepository.save(recruitment);

        // when
        recruitmentService.deleteRecruitment(1L);

        // then
        assertThat(recruitmentRepository.findById(1L)).isEmpty();
    }

    @Test
    public void 채용공고_목록을_조회할_수_있다() {
        // given
        // when
        List<ListResponse> recruitments = recruitmentService.getRecruitments();

        // then
        assertThat(recruitments.size()).isEqualTo(1);
        assertThat(recruitments.get(0).getId()).isEqualTo(2L);
        assertThat(recruitments.get(0).getCompanyName()).isEqualTo("좋은 회사");
        assertThat(recruitments.get(0).getNation()).isEqualTo("한국");
        assertThat(recruitments.get(0).getAddress()).isEqualTo("서울");
        assertThat(recruitments.get(0).getPosition()).isEqualTo("백엔드 개발자");
        assertThat(recruitments.get(0).getCompensation()).isEqualTo(500000);
        assertThat(recruitments.get(0).getTech()).isEqualTo("Python/Django");
    }
}
