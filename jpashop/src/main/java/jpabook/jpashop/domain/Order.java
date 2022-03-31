package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")  // 이름을 orders로
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne  // 여러 개의 주문은 한 명의 고객에 속하게 됨, Oder테이블이 연관관계의 주인이 됨, 멤버의 값을 변경할 수 있음
    @JoinColumn(name = "member_id") // 외부 테이블 참조를 위한 FK명이 member_id 가 됨
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;  // JAVA8에서 지원

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]



}
