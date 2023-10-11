package com.example.wantedpreonboardingbackend.company.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Company {
    private Long id;
    private String name;
    private String nation;
    private String address;

    @Builder
    public Company(Long id, String name, String nation, String address) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.address = address;
    }
}
