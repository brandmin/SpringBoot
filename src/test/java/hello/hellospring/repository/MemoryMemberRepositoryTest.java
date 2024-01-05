package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트를 끝날때마다 코드 초기화
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        // 저장된 멤버를 아이디를 통해 조회하고, 저장한 멤버와 조회한 멤버가 동일한지 검증
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        // 이름이 spring1인 멤버의 이름을 조회하고, 조회한 멤버가 첫번째로 지정한 멤버와 동일한지 검증
        assertThat(result).isEqualTo(member1);
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
        // 모든 멤버를 조회하고, 조회한 멤버 리스트의 크기가 2인지를 검증
        assertThat(result.size()).isEqualTo(2);
    }


}
