package com.example.demo.member;

import com.example.demo.QueryDslConfig;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(QueryDslConfig.class)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    void createTest(){
        Member member = Member.builder()
                .age(10)
                .createdDate(LocalDateTime.now())
                .description("desc")
                .roleType(RoleType.ADMIN)
                .username("lee")
                .build();
        memberRepository.save(member);
    }
}