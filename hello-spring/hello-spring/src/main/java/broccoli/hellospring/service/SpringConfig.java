package broccoli.hellospring.service;

import broccoli.hellospring.repository.JdbcMemberRepository;
import broccoli.hellospring.repository.JdbcTemplateMemberRepository;
import broccoli.hellospring.repository.JpaMemberRepository;
import broccoli.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;  // jpa를 위해 위의 dataSource 를 remark 하고 EntityManager 사용

    @Bean // 스프링빈에 memberService 를 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링빈에 memberRepository 를 등록
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();   // 기존 방식 : DB 미연결
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
