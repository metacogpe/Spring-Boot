package broccoli.hellospring.controller;

import broccoli.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 생성자 호출 시 멤버서비스를 가져다 연결, MemberController가 기동 시 membereService 객체를 주입(DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }




}
