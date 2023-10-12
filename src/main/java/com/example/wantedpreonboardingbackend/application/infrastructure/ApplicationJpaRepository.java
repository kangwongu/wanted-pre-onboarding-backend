package com.example.wantedpreonboardingbackend.application.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, Long> {

    @Query("select a from ApplicationEntity a " +
            "join fetch a.recruitment r " +
            "join fetch a.member m " +
            "where m.id = :memberId")
    List<ApplicationEntity> findAllByMemberId(@Param("memberId") long memberId);
}
