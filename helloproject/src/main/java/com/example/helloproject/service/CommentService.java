package com.example.helloproject.service;

import com.example.helloproject.dto.CommentDto;
import com.example.helloproject.entity.Article;
import com.example.helloproject.entity.Comment;
import com.example.helloproject.repository.ArticleRepository;
import com.example.helloproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service  // 서비스 컨트롤러임을 선언
public class CommentService {
    @Autowired  // 일꾼에게 시키기, Service의 보조일군인 Repository와 협업 : 코멘트레파지토리(댓글 데이터) 당겨와 주어야 함
    private  CommentRepository commentRepository;

    @Autowired  // 일꾼에게 시키기, Service의 보조일군인 Repository와 협업 : 아티클레파지토리(아티클 데이터) 당겨와 주어야 함
    private  ArticleRepository articleRepository;

    // stream 문법으로 구현
    public List<CommentDto> comments(Long articleId) {
        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
    // for문을 사용하여 구현 시
//    public List<CommentDto> comments(Long articleId) {
//        // 조회 : 댓글 목록 조회하기 위해 일꾼인 commentRepository 에게 시키기
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // 변환 : 조회한 코멘트를 변환; 엔티티 -> DTO 형태로 반환 필요 즉,CommentDto
//        List<CommentDto> dtos =  new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);  // CommentDto를 하나씩 조회해서 dtos로 추가
//            dtos.add(dto);
//        }
//
//        // 반환
//        return dtos;
//
//    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {

        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);
        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);

}

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);
        // DB 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔터티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional   // 잘못되었을 경우 롤백 발생 필요로 트랜잭션널
    public CommentDto delete(Long id) {
        // 댓글 조회(
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다. "));
        // 댓글을 DB에서 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
