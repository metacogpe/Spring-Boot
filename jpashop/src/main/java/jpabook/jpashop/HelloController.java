package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.MalformedObjectNameException;

@Controller
public class HelloController {

    @GetMapping("hello")  // localhost:8080/hello 로 접속
    public String hello(Model model) {
        model.addAttribute("data","hello!!!");  // hello.html에 ${data} 정의
        return "hello"; // 화면 이름이 hello -> hello.html
    }
}
