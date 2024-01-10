package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional // 이 어노테이션을 테스트 코드에 반영하면 테스트 시작 전에 트랜잭션을 하고 테스트가 끝나면 데이터가 롤백됨
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    // 테스트코드는 한글로 적어도 무방함.
    void 회원가입() {
        //given(무언가 주어졌는데)
        Member member = new Member();
        member.setName("spring");

        //when(이렇게 실행했는데)
        Long saveId = memberService.join(member);

        //then(결과가 이렇게 나옴)
        // 반환된 id를 findOne 메서드를 통해 조회
        Member findMember = memberService.findOne(saveId).get();
        // 저장한 멤버와 조회한 멤버 이름이 일치하는지 검증
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        // 첫번째 멤버를 저장하고, 두번째 멤버를 저장할때 중복회원예외가 발생하는지 검증
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch(IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then
    }
}