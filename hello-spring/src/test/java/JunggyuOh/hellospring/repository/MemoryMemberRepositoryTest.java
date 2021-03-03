package JunggyuOh.hellospring.repository;

import JunggyuOh.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 클래스 단위로도 돌릴 수 있고
// 메소드 단위로도 돌릴 수 있음
// 왼쪽에 아이콘 확인
public class MemoryMemberRepositoryTest {

//    MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    // 순서가 상관이 없도록 해줌
    // 테스트는 순서가 상관없이 즉 의존관계가 없이 설계가 되어야하므로
    // 하나의 테스트케이스가 끝날대마다 공용부분이 삭제되어야 함
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // 이렇게 하면 테스트 화면에서 초록색 v표
        // assertThat(member).isEqualTo(null); // 이렇게 하면 테스트 화면에서 주황색 x표

    }

    // findAll 테스트케이스를 만들기 전에는 findByName에 오류가 발생하지 않았다.
    // 근데 findAll을 만든뒤 클래스단위로 테스트케이스를 돌리면 오류가 발생함 이건 왜일까?
    // 순서가 findAll()->findByName()->save() 순서로 작동되기 때문!
    // findAll에서 이미 repository에 save를 해두었기 때문...
    // 이를 해결하기위해 테스트가 끝날때마다 코드를 깔끔하게 지우는 코드를 작성해야함

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
