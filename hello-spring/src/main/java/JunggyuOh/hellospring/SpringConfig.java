package JunggyuOh.hellospring;

import JunggyuOh.hellospring.repository.MemberRepository;
import JunggyuOh.hellospring.repository.MemoryMemberRepository;
import JunggyuOh.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //이것만 바꿔주면됨
        return new MemoryMemberRepository();
//        return new DBMemberRepository();
    }

}
