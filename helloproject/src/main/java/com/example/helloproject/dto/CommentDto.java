package com.example.helloproject.dto;

import com.example.helloproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {      // 코멘트를 담는 그릇
    private Long id;
    private Long articleId;   //코멘트의 아티클 아이디
    private String nickname;  // 코멘트의 닉네임
    private String body;

    public static CommentDto createCommentDto(Comment comment) {   //static 메소드
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
