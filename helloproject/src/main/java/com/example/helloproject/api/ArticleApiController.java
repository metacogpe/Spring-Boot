package com.example.helloproject.api;

import com.example.helloproject.dto.ArticleForm;
import com.example.helloproject.entity.Article;
import com.example.helloproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController // restapi 용 컨트롤러! 데이터(json)를 반환!!!
public class ArticleApiController {
    @Autowired // 스프링부트에서 가져오기 즉, DI(외부에서 가져오기)
    private ArticleRepository articleRepository;

    // get
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                          @RequestBody ArticleForm dto) {
        // 1: 수정용 엔터티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2: 대상 엔터티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if(target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

        // 4: 업데이트 및 정상 응답(200)
        target.patch(article);  // 업데이트 위해 조회해 온 target을 업데이트 하려는 정보로 새롭게 붙여(patch)
        Article updated = articleRepository.save(target);  // 새로운 정보로 업데이트 된 정보인 target을 save
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();  // body()와 build()는 같은 의미임
    }

}
