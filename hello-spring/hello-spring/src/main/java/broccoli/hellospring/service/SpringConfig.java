package broccoli.hellospring.service;

import broccoli.hellospring.repository.JdbcMemberRepository;
import broccoli.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // 스프링빈에 memberService 를 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링빈에 memberRepository 를 등록
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();   // 기존 방식 : DB 미연결
        return new JdbcMemberRepository(dataSource);
    }
}
