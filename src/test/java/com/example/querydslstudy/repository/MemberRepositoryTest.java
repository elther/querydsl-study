package com.example.querydslstudy.repository;

import com.example.querydslstudy.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired MemberRepository MemberRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    public void basicTest(){
        Member member = new Member("member1", 10);
        MemberRepository.save(member);
        Member findMember = MemberRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> result1 = MemberRepository.findAll();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = MemberRepository.findByUsername("member1");
        assertThat(result2).containsExactly(member);
    }
}