package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id "))    // 다대다 관계에서 조인테이블 필요, 다대다 관계에서 중간 테이블로 매핑 가능
    private List<Item> items = new ArrayList<>();

    // 셀프로 양방향 관계 설정
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    // 셀프로 양방향 관계 설정
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }


}
