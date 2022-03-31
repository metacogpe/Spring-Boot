package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")  // single table 이라 구분할 수 있는 값 부여 필요
@Getter @Setter
public class Movie extends Item {

    private String director;
    private String actor;
}
