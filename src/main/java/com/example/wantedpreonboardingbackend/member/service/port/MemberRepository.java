package com.example.wantedpreonboardingbackend.member.service.port;

import com.example.wantedpreonboardingbackend.member.domain.Member;

public interface MemberRepository {
    Member getById(long memberId);
}
