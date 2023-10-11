package com.example.wantedpreonboardingbackend.company.infrastructure;

import com.example.wantedpreonboardingbackend.company.domain.Company;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nation;

    private String address;

    public Company toModel() {
        return Company.builder()
                .id(id)
                .name(name)
                .nation(nation)
                .address(address)
                .build();
    }

    public static CompanyEntity from(Company company) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.id = company.getId();
        companyEntity.name = company.getName();
        companyEntity.nation = company.getNation();
        companyEntity.address = company.getAddress();

        return companyEntity;
    }
}
