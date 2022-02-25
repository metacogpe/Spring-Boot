package com.example.helloproject.controller;

import com.example.helloproject.dto.ArticleForm;
import com.example.helloproject.entity.Article;
import com.example.helloproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동 연결해 줌 즉, DI!
    private ArticleRepository articleRepository;  // new로 객체생성 불필요 (DI : 스프링 부투)

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString());

        // 1. dto를 변환해서 Entity만들기
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        // 2. repository에게 entity를 DB안에 저장하기
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "";
    }
}
