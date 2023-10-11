package com.example.wantedpreonboardingbackend.mock;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import com.example.wantedpreonboardingbackend.company.service.port.CompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class FakeCompanyRepository implements CompanyRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<Company> data = new ArrayList<>();

    public Company save(Company company) {
        if (company.getId() == null || company.getId() == 0) {
            Company newCompany = Company.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .name(company.getName())
                    .nation(company.getNation())
                    .address(company.getAddress())
                    .build();
            data.add(newCompany);
            return newCompany;
        }
        data.removeIf(d -> Objects.equals(d.getId(), company.getId()));
        data.add(company);
        return company;
    }

    @Override
    public Company getById(long companyId) {
        return data.stream()
                .filter(d -> d.getId().equals(companyId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Not Exist Company"));
    }
}
