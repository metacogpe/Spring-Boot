package com.example.helloproject.dto;

import com.example.helloproject.entity.Article;

public class ArticleForm_before {

    private String title;
    private String content;

    public ArticleForm_before(String title, String content) {
        this.title = title;
        this.content = content;
    }
    // 데이터가 폼을 통해서 잘 받아졌는지 확인하기 위해서 toString()메소드 추가
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
