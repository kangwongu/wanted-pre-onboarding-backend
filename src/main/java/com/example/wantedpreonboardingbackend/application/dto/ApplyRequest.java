package com.example.wantedpreonboardingbackend.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplyRequest {
    private Long recruitmentId;
    private Long memberId;
}
