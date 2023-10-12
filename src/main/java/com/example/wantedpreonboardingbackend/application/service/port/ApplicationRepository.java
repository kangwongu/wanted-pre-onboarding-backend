package com.example.wantedpreonboardingbackend.application.service.port;

import com.example.wantedpreonboardingbackend.application.domain.Application;

import java.util.List;

public interface ApplicationRepository {
    Application save(Application application);

    List<Application> findAllByMemberId(long id);

}
