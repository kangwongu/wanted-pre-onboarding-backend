package com.example.wantedpreonboardingbackend.recruitment.service;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.company.service.port.CompanyRepository;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.recruitment.dto.UpdateRequest;
import com.example.wantedpreonboardingbackend.recruitment.service.port.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final CompanyRepository companyRepository;
    private final RecruitmentRepository recruitmentRepository;

    public void registerRecruitment(RegisterRequest request) {
        Company company = companyRepository.getById(request.getCompanyId());

        Recruitment recruitment = company.createRecruitment(request);
        recruitmentRepository.save(recruitment);
    }

    public void updateRecruitment(long companyId, long recruitmentId, UpdateRequest request) {
        Company company = companyRepository.getById(companyId);
        Recruitment recruitment = recruitmentRepository.getById(recruitmentId);

        Recruitment updateRecruitment = company.updateRecruitment(recruitment, request);
        recruitmentRepository.save(updateRecruitment);
    }

    public void deleteRecruitment(long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.getById(recruitmentId);

        recruitmentRepository.deleteById(recruitment.getId());
    }
}