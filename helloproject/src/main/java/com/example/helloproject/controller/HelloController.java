package com.example.helloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hi")  // 접속할 url
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "홍길동");  // 모델 통해서 username 변수 등록
        return "greetings"; //  templates/greetings.mustache --> 브라우저로 전송!
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }
}
