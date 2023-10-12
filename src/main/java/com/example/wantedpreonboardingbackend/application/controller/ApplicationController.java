package com.example.wantedpreonboardingbackend.application.controller;

import com.example.wantedpreonboardingbackend.application.dto.ApplyRequest;
import com.example.wantedpreonboardingbackend.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/recruitment/apply")
    public ResponseEntity<Void> apply(@RequestBody ApplyRequest request) {
        applicationService.apply(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
