package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter   //롬복의 겟터와 셋터
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")  //PK
    private Long id;

    private String name;

    @Embedded  // JPA 내장타입을 포함(Address에서는 Embeddable)
    private Address address;

    @OneToMany(mappedBy = "member") //한 명의 회원이 여러 개의 주문, Order테이블의 member필드로 매핑(읽기 전용이 됨)
    private List<Order> orders = new ArrayList<>();  // 컬렉션은 필드에서 바로 초기화 하는 것이 null문제에서 안전
}
