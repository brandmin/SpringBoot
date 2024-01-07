package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // new를 사용하면 기능이 없기 때문에 생성할 필요x
    // 컨테이너를 따로 생성하여 new연산자 제거
    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 멤버서비스를 가져옴.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
