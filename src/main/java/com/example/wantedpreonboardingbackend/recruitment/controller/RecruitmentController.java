package com.example.wantedpreonboardingbackend.recruitment.controller;

import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/recruitment")
    public ResponseEntity<Void> registerRecruitment(@RequestBody RegisterRequest request) {
        recruitmentService.registerRecruitment(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
