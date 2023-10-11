package com.example.wantedpreonboardingbackend.recruitment.infrastructure;

import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.recruitment.service.port.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentRepository {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    @Override
    public Recruitment save(Recruitment recruitment) {
        return recruitmentJpaRepository.save(RecruitmentEntity.from(recruitment)).toModel();
    }
}
