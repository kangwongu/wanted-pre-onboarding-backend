package com.example.wantedpreonboardingbackend.recruitment.dto;

import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DetailResponse {
    private Long id;
    private String companyName;
    private String nation;
    private String address;
    private String position;
    private Integer compensation;
    private String tech;
    private String contents;
    private List<Long> otherRecruitment;

    public static DetailResponse of(Recruitment r, List<Long> otherRecruitment) {
        DetailResponse response = new DetailResponse();
        response.setId(r.getId());
        response.setCompanyName(r.getCompany().getName());
        response.setNation(r.getCompany().getNation());
        response.setAddress(r.getCompany().getAddress());
        response.setPosition(r.getPosition());
        response.setCompensation(r.getCompensation());
        response.setTech(r.getTech());
        response.setContents(r.getContents());
        response.setOtherRecruitment(otherRecruitment);

        return response;
    }
}
