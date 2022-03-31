package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")  // single table 이라 구분할 수 있는 값 부여 필요
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;

}
