package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
//@Service // 서비스 어노테이션 안에 컴포넌트 어노테이션이 존재한다. 컨트롤러 포함
public class MemberService {

    private final MemberRepository memberRepository;

    // 외부에서 데이터를 삽입해줌.
//    @Autowired
    // 멤버 클래스를 가져올때 스프링 빈에 등록되어 있는 멤버 서비스 객체를 넣어줌(의존성 주입)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
