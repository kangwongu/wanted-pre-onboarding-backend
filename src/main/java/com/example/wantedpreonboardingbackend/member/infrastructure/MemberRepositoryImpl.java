package com.example.wantedpreonboardingbackend.member.infrastructure;

import com.example.wantedpreonboardingbackend.member.domain.Member;
import com.example.wantedpreonboardingbackend.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member getById(long memberId) {
        return memberJpaRepository.findById(memberId)
                .map(m -> m.toModel())
                .orElseThrow(() -> new IllegalStateException("Not Exist Member"));
    }

}
