//package com.example.wantedpreonboardingbackend.company.controller;
//
//import com.example.wantedpreonboardingbackend.company.service.CompanyService;
//import com.example.wantedpreonboardingbackend.recruitment.dto.RegisterRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/company")
//public class CompanyController {
//
//    private final CompanyService companyService;
//
//    @PostMapping("/recruitment")
//    public ResponseEntity<Void> registerRecruitment(@RequestBody RegisterRequest request) {
//        companyService.registerRecruitment(request);
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//}
