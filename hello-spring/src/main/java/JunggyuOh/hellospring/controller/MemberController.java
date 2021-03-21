package JunggyuOh.hellospring.controller;

import JunggyuOh.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

// Spring Bean은 스프링이 동작할때 자동으로 생성해줌
// 컴포넌트 스캔을 통해 생성
// 이번 프로젝트의 경우 스프링 컨테이너는
// @Contoller 어노테이션과 SpringConfig파일의 @Bean어노테이션이 붙어있는 객체들을 Bean으로 가지고 있음
// MemberController에서 MemberContorller 생성자에 autowired를 통해 memberService를 주입해주라고 하고 있음.

// Autowired는 스프링 컨테이너가 bean을통해 파라미터 즉 이경우 'MemberService memberService'을 가지고 있을경우 주입해줌
// SpringConfig파일은 아래처럼 생김
// MemberService객체는 생성자를 통해 MemberRepository를 주입받음.
// memberRepository도 @Bean 어노테이션이 있기때문에 spring이 가지고 있음
// 즉, 아래와 같이 동작함
// 1. 컴포넌트 스캔과 SpringConfig 스캔을 통해 스프링 컨테이너는 컨트롤러, MemberService, MemberRepository를 가지고있음
// 2. MemberRepository를 MemberService에 끼움 -> (여기선 memberService에 @Bean을 해주었지만 MemberService 클래스에서 Autowire해도 되나???(질문))
// 3. MemberService를  MemberController에 끼움 -> Autowire로 되어있기 때문

// 주의! @Autowired를 통한 DI는 스프링이 관리하는 객체에서만 동작함. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않음.

/*
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

 */


@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
