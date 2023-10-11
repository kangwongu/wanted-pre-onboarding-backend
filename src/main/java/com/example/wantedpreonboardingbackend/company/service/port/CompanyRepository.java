package com.example.wantedpreonboardingbackend.company.service.port;

import com.example.wantedpreonboardingbackend.company.domain.Company;

public interface CompanyRepository {
    Company getById(long companyId);
}
