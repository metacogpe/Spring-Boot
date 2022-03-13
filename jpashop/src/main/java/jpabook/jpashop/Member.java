package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter  // 롬복을 사용 중이므로 가능
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;
}
