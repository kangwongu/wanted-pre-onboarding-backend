package com.example.wantedpreonboardingbackend.recruitment.dto;

import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ListResponse {
    private Long id;
    private String companyName;
    private String nation;
    private String address;
    private String position;
    private Integer compensation;
    private String tech;

    public static ListResponse from(Recruitment r) {
        ListResponse response = new ListResponse();
        response.setId(r.getId());
        response.setCompanyName(r.getCompany().getName());
        response.setNation(r.getCompany().getNation());
        response.setAddress(r.getCompany().getAddress());
        response.setPosition(r.getPosition());
        response.setCompensation(r.getCompensation());
        response.setTech(r.getTech());

        return response;
    }
}
