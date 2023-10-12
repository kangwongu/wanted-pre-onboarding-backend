package com.example.wantedpreonboardingbackend.recruitment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecruitmentJpaRepository extends JpaRepository<RecruitmentEntity, Long> {

    @Query("select r from RecruitmentEntity r " +
            "join fetch r.company")
    List<RecruitmentEntity> findAll();

    @Query("select r from RecruitmentEntity r " +
            "join fetch r.company c " +
            "where r.id = :recruitmentId")
    Optional<RecruitmentEntity> findById(@Param("recruitmentId") long recruitmentId);

    List<RecruitmentEntity> findAllByCompanyId(long companyId);

    @Query("select r from RecruitmentEntity r " +
            "join fetch r.company c " +
            "where r.company.name like %:query% " +
            "or r.position like %:query% " +
            "or r.tech like %:query%")
    List<RecruitmentEntity> search(@Param("query") String query);
}
