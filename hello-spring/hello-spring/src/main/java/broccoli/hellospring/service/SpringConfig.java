package broccoli.hellospring.service;

import broccoli.hellospring.repository.MemberRepository;
import broccoli.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // 스프링빈에 memberService 를 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링빈에 memberRepository 를 등록
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
