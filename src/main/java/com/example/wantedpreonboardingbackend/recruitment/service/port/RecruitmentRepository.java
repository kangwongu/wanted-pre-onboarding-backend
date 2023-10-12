package com.example.wantedpreonboardingbackend.recruitment.service.port;

import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;

import java.util.List;

public interface RecruitmentRepository {
    Recruitment save(Recruitment recruitment);

    Recruitment getById(long recruitmentId);

    void deleteById(long recruitmentId);

    List<Recruitment> findAll();

    List<Recruitment> findAllByCompanyId(long companyId);

    List<Recruitment> search(String query);
}
