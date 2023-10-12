package com.example.wantedpreonboardingbackend.member.infrastructure;

import com.example.wantedpreonboardingbackend.member.domain.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static MemberEntity from(Member member) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = member.getId();
        memberEntity.name = member.getName();

        return memberEntity;
    }

    public Member toModel() {
        return Member.builder()
                .id(id)
                .name(name)
                .build();
    }
}
