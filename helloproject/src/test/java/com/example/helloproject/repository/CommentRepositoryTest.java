package com.example.helloproject.repository;

import com.example.helloproject.entity.Article;
import com.example.helloproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // jpa와 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;  // repository테스트를 위해 만든 레포지토리를 당겨 옴

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")  // 테스트 명칭을 표시
    void findByArticleId() {
        // case 1: 4번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(4L, "당신의 인생 영화", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "PARK", "GOOD WILL");
            Comment b = new Comment(2L, article, "KIM", "I AM SAM");
            Comment c = new Comment(3L, article, "CHOI", "SHOW");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }

        // case 2: 1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 1L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번은 댓글이 없음");
        }

        // case 3: 9번 게시글의 모든 댓글 조회


        // case 4: 9999번 게시글의 모든 댓글 조회


        // case 5: -1번 게시글의 모든 댓글 조회
    }


    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // case 1: "PARK"의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = "PARK";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상하기
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화", "댓글 ㄱ"), nickname, "GOOD WILL");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드", "댓글 ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미", "댓글 ㄱㄱㄱ"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "PARK의 모든 댓글을 출력");
        }

        // case 2: "KIM"의 모든 댓글 조회

        // case 3: null의 모든 댓글 조회

        // case 4: ""의 모든 댓글 조회

        // case 5: "i"의 모든 댓글 조회

    }

}