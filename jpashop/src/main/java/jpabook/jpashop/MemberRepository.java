package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;  // EntityManager를 주입해 줌

@Repository  // 컴포넌트 스캔의 대상이 되어 자동으로 빈에 등록
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 저장하는 코드
    public Long save(Member member) {
        em.persist(member);
        return member.getId();  // 멤버가 아닌 id를 반환
    }

    // 조회하는 코드
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
