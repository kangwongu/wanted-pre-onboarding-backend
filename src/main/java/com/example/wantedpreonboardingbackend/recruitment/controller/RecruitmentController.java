package com.example.wantedpreonboardingbackend.recruitment.controller;

import com.example.wantedpreonboardingbackend.recruitment.dto.ListResponse;
import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
import com.example.wantedpreonboardingbackend.recruitment.dto.UpdateRequest;
import com.example.wantedpreonboardingbackend.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/recruitment")
    public ResponseEntity<Void> registerRecruitment(@RequestBody RegisterRequest request) {
        recruitmentService.registerRecruitment(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/company/{companyId}/recruitment/{recruitmentId}")
    public ResponseEntity<Void> updateRecruitment(@PathVariable long companyId,
                                                  @PathVariable long recruitmentId,
                                                  @RequestBody UpdateRequest request) {
        recruitmentService.updateRecruitment(companyId, recruitmentId, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/recruitment/{recruitmentId}")
    public ResponseEntity<Void> deleteRecruitment(@PathVariable long recruitmentId) {
        recruitmentService.deleteRecruitment(recruitmentId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/recruitment")
    public ResponseEntity<List<ListResponse>> getRecruitments() {
        List<ListResponse> response = recruitmentService.getRecruitments();

        return ResponseEntity.ok().body(response);
    }
}
