package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// @Bean 메서드 제공, Spring 컨테이너에 의해 관리되는 구성클래스
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        // 멤버 서비스는 멤버리파지토리를 연결함.
        return new MemberService(memberRepository()); // 이 로직을 호출해서 스프링 빈 등록
    }

    @Bean
    public MemberRepository memberRepository() {
       // return new MemoryMemberRepository(); // 메모리멤버리파지토리를 위의 서비스를 엮어야함.
        return new JdbcMemberRepository(dataSource);
    }
}
