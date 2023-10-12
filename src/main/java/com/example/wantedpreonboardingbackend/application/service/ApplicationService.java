package com.example.wantedpreonboardingbackend.application.service;


import com.example.wantedpreonboardingbackend.application.domain.Application;
import com.example.wantedpreonboardingbackend.application.dto.ApplyRequest;
import com.example.wantedpreonboardingbackend.application.service.port.ApplicationRepository;
import com.example.wantedpreonboardingbackend.member.domain.Member;
import com.example.wantedpreonboardingbackend.member.service.port.MemberRepository;
import com.example.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.recruitment.service.port.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final MemberRepository memberRepository;

    public void apply(ApplyRequest request) {
        Recruitment recruitment = recruitmentRepository.getById(request.getRecruitmentId());
        Member member = memberRepository.getById(request.getMemberId());
        List<Long> appliedRecruitmentIds = applicationRepository.findAllByMemberId(member.getId())
                .stream()
                .map(a -> a.getRecruitment().getId())
                .collect(Collectors.toList());

        Application application = member.apply(recruitment, appliedRecruitmentIds);
        applicationRepository.save(application);
    }

}
