package broccoli.hellospring.repository;

import broccoli.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach     // 매 테스트 끝날 때마다 실행
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");  // 멤버를 "spring" 으로 설정

        repository.save(member);  // 멤버 저장하기

        Member result = repository.findById(member.getId()).get(); //저장한 멤버 찾아오기기

        //assertions 사용해서 확인
        assertThat(member).isEqualTo(result);
   }

   @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

       Member member2 = new Member();
       member2.setName("spring2");
       repository.save(member2);

       Member result = repository.findByName("spring1").get();

       assertThat(result).isEqualTo(member1);
   }

   @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

       Member member2 = new Member();
       member2.setName("spring2");
       repository.save(member2);

       List<Member> result =  repository.findAll();

       assertThat(result.size()).isEqualTo(2);
   }
}
