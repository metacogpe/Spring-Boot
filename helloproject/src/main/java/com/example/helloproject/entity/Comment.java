package com.example.helloproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // 댓글 여러개가 하나의 아티클에 연관
    @JoinColumn(name = "article_id") //조인 컬럼
    private Article article;
    private String nickname;
    private String body;
}
