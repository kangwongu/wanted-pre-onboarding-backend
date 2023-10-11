package com.example.wantedpreonboardingbackend.company.infrastructure;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.company.service.port.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public Company getById(long companyId) {
        return companyJpaRepository.findById(companyId)
                .map(c -> c.toModel())
                .orElseThrow(() -> new IllegalStateException("Not Exist Company"));
    }
}
