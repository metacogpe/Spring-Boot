package com.example.helloproject.service;

import com.example.helloproject.dto.ArticleForm;
import com.example.helloproject.entity.Article;
import com.example.helloproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service  // 서비스 선언 (서비스 객체를 스프링부트에 생성)
public class ArticleService {
    // 해당 ArticleService가 ArticleRepository와 협업 할 수 있도록 선언
    @Autowired  // DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
         // 1: 수정용 엔터티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2: 대상 엔터티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if(target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4: 업데이트
        target.patch(article);  // 업데이트 위해 조회해 온 target을 업데이트 하려는 정보로 새롭게 붙여(patch)
        Article updated = articleRepository.save(target);  // 새로운 정보로 업데이트 된 정보인 target을 save
        return updated;

    }

    public Article delete(Long id) {
        // 1:대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2:잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 3:대상 삭제
        articleRepository.delete(target);
        return target;
    }
} // ArticleService End
