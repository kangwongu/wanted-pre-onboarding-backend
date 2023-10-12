package com.example.wantedpreonboardingbackend.application.infrastructure;

import com.example.wantedpreonboardingbackend.application.domain.Application;
import com.example.wantedpreonboardingbackend.application.service.port.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;

    @Override
    public Application save(Application application) {
        return applicationJpaRepository.save(ApplicationEntity.from(application)).toModel();
    }

    @Override
    public List<Application> findAllByMemberId(long memberId) {
        return applicationJpaRepository.findAllByMemberId(memberId)
                .stream().map(a -> a.toModel())
                .collect(Collectors.toList());
    }
}
