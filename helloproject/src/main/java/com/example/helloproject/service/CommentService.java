package com.example.helloproject.service;

import com.example.helloproject.dto.CommentDto;
import com.example.helloproject.entity.Comment;
import com.example.helloproject.repository.ArticleRepository;
import com.example.helloproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service  // 서비스 컨트롤러임을 선언
public class CommentService {
    @Autowired  // 일꾼에게 시키기, Service의 보조일군인 Repository와 협업 : 코멘트레파지토리(댓글 데이터) 당겨와 주어야 함
    private CommentRepository commentRepository;

    @Autowired  // 일꾼에게 시키기, Service의 보조일군인 Repository와 협업 : 아티클레파지토리(아티클 데이터) 당겨와 주어야 함
    private ArticleRepository articleRepository;

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
}
