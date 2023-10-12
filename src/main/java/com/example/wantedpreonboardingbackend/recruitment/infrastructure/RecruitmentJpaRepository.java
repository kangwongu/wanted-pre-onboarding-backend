package com.example.wantedpreonboardingbackend.recruitment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitmentJpaRepository extends JpaRepository<RecruitmentEntity, Long> {

    @Query("select r from RecruitmentEntity r " +
            "join fetch r.company")
    List<RecruitmentEntity> findAll();
}
