package com.example.wantedpreonboardingbackend.application.infrastructure;

import com.example.wantedpreonboardingbackend.application.domain.Application;
import com.example.wantedpreonboardingbackend.member.infrastructure.MemberEntity;
import com.example.wantedpreonboardingbackend.recruitment.infrastructure.RecruitmentEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 지원내역
@Entity
@Table(name = "application")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private RecruitmentEntity recruitment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    public static ApplicationEntity from(Application application) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.id = application.getId();
        applicationEntity.recruitment = RecruitmentEntity.from(application.getRecruitment());
        applicationEntity.member = MemberEntity.from(application.getMember());

        return applicationEntity;
    }

    public Application toModel() {
        return Application.builder()
                .id(id)
                .recruitment(recruitment.toModel())
                .member(member.toModel())
                .build();
    }
}
