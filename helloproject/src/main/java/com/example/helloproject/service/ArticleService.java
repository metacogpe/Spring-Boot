package com.example.helloproject.service;

import com.example.helloproject.dto.ArticleForm;
import com.example.helloproject.entity.Article;
import com.example.helloproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
            return articleRepository.save(article);
        }
        return null;
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

    @Transactional // 해당 메소드를 트랜잭션으로 묶는다
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList =  dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );

        // 결과값 반환
        return null;
    }
} // ArticleService End
