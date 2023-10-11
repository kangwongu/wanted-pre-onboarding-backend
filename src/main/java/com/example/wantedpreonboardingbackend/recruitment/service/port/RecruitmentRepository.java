package com.example.wantedpreonboardingbackend.recruitment.service.port;

import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;

public interface RecruitmentRepository {
    Recruitment save(Recruitment recruitment);
}
