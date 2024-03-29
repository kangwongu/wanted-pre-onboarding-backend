package com.example.wantedpreonboardingbackend.mock;

import com.example.wantedpreonboardingbackend.application.domain.Application;
import com.example.wantedpreonboardingbackend.application.service.port.ApplicationRepository;
import com.example.wantedpreonboardingbackend.company.domain.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class FakeApplicationRepository implements ApplicationRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<Application> data = new ArrayList<>();

    @Override
    public Application save(Application application) {
        if (application.getId() == null || application.getId() == 0) {
            Application newApplication = Application.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .recruitment(application.getRecruitment())
                    .member(application.getMember())
                    .build();
            data.add(newApplication);
            return newApplication;
        }
        data.removeIf(d -> Objects.equals(d.getId(), application.getId()));
        data.add(application);
        return application;
    }

    @Override
    public List<Application> findAllByMemberId(long memberId) {
        return data.stream()
                .filter(a -> a.getMember().getId().equals(memberId))
                .collect(Collectors.toList());
    }

    public Application getById(long applicationId) {
        return data.stream()
                .filter(d -> d.getId().equals(applicationId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Not Exist Company"));
    }
}
