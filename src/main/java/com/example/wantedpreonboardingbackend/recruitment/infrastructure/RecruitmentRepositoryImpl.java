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

    @Override
    public Recruitment getById(long recruitmentId) {
        return recruitmentJpaRepository.findById(recruitmentId)
                .map(r -> r.toModel())
                .orElseThrow(()-> new IllegalStateException("Not Exist Recruitment"));
    }

    @Override
    public void deleteById(long recruitmentId) {
        recruitmentJpaRepository.deleteById(recruitmentId);
    }
}
