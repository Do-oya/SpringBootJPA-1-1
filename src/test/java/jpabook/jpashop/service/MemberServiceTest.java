package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager entityManager;

    @Test
    @Rollback(false)
    @DisplayName("회원가입 테스트")
    public void join() throws Exception {
        // given
        Member member = new Member();
        member.setName("Park");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("중복 회원 예외 테스트")
    public void joinThrow() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("Park");

        Member member2 = new Member();
        member2.setName("Park");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생해야 함");
    }

}
