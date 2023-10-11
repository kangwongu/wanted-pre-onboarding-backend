package com.example.wantedpreonboardingbackend.recruitment.infrastructure;

import com.example.wantedpreonboardingbackend.company.infrastructure.CompanyEntity;
import com.example.wantedpreonboardingbackend.member.infrastructure.MemberEntity;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 채용공고
@Entity
@Table(name = "recruitment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private String position;

    private Integer compensation;

    private String contents;

    private String tech;

    public static RecruitmentEntity from(Recruitment recruitment) {
        RecruitmentEntity recruitmentEntity = new RecruitmentEntity();
        recruitmentEntity.id = recruitment.getId();
        recruitmentEntity.company = CompanyEntity.from(recruitment.getCompany());
        recruitmentEntity.position = recruitment.getPosition();
        recruitmentEntity.compensation = recruitment.getCompensation();
        recruitmentEntity.contents = recruitment.getContents();
        recruitmentEntity.tech = recruitment.getTech();

        return recruitmentEntity;
    }

    public Recruitment toModel() {
        return Recruitment.builder()
                .id(id)
                .company(company.toModel())
                .position(position)
                .compensation(compensation)
                .contents(contents)
                .tech(tech)
                .build();
    }
}
