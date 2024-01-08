package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 만약, 컨트롤러에 파일이 있다면 컨트롤러 파일을 업로드하고 없으면 정적 메소드 파일로 이동
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
