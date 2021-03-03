package JunggyuOh.hellospring.service;

import JunggyuOh.hellospring.domain.Member;
import JunggyuOh.hellospring.repository.MemberRepository;
import JunggyuOh.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given - 무언가가 주어졌는데
        Member member = new Member();
        member.setName("Hello");

        // when - 뭔가를 실행했는데
        Long saveId = memberService.join(member);

        // then - 이게 나온다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");


        //        assertThrows(NullPointerException.class, () -> memberService.join(member2));

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1233");
//        }



        // then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}