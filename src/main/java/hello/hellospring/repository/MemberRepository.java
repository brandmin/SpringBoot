package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // Member 객체를 저장하고 저장된 결과를 Member객체로 반환
    Member save(Member member); // save하면 Member가 저장
    // optional: null을 처리하는 방법
    // Optional<Member>을 사용한 이유: 아이디에 해당하는 회원이 존재하지 않을 수 있기 때문에 Optional을 사용하여 null방지
    Optional<Member> findById(Long Id);
    Optional<Member> findByName(String name);
    // 저장된 모든 리스트 반환
    List<Member> findAll();
}
