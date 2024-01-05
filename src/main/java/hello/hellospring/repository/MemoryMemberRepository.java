package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        // ofNullable: 널이여도 감싸서 처리함
        return Optional.ofNullable(store.get(Id));
    }

    @Override
    // 주어진 이름을 해당하는 회원에 찾아서 Optional로 감싸서 반환.
    // stream()과 filter()를 사용하여 이름에 해당하는 회원찾기. findAny() 로 찾은 회원 중 아무 회원 반환
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 파라미터 name값과 일치하면 필터링
                .findAny(); // 결과값을 찾게되면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
