package JunggyuOh.hellospring.repository;

import JunggyuOh.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 실무에선 동시성 문제를 고려해야함
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 키값 생성해주는 친구

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null일 경우를 위해 optional로 감싸줌
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store의 values들을 arraylist형태로 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        // Map을 비우는 명령어
        store.clear();
    }
}
