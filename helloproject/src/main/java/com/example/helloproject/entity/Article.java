package com.example.helloproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity  //DB가 해당 객체 인식이 가능!
@AllArgsConstructor   // 리팩토링 개선
@NoArgsConstructor    // default constructor 추가(롬복 활용성 : arg가 없는 생성자 생성)
@ToString             // 리팩토링 개선
@Getter
public class Article {

    @Id // 대표값 지정
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

//    getter를 직접 작성하지 않고 롬복의 @Getter 사용함
//    public Long getId() {
//        return id;
//    }

//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
