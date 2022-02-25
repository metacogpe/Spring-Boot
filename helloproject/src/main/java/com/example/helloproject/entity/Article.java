package com.example.helloproject.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity  //DB가 해당 객체 인식이 가능!
@AllArgsConstructor   // 리팩토링 개선
@ToString             // 리팩토링 개선
public class Article {

    @Id // 대표값 지정
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

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
