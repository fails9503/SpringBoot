package JunggyuOh.hellospring.service;

import JunggyuOh.hellospring.domain.Member;
import JunggyuOh.hellospring.repository.MemberRepository;
import JunggyuOh.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {


    // 인터페이스 = 클래스로 다운캐스팅(다형성)
    private final MemberRepository memberRepository;

    // 기존방식
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 인젝션 주입
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */

    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원
        // 요새는 null일 가능성이 있으면 optional로 감싸주는 편임
        // findByName 메소드의 return값은 Optional이므로 바로 Optional 메소드를 사용할 수 있음
        checkDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void checkDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m-> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /*
     * 전체 회원 조회
     */

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    /*
     * ID를 이용해 한명 조회
     */

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
