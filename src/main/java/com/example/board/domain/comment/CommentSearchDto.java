package com.example.board.domain.comment;

import com.example.board.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSearchDto extends SearchDto {
  private Long postId; // 게시글 번호(FK)
}

/*
 * 댓글은 tb_comment 테이블의 post_id를 기준으로 SELECT 하기 때문에 게시글 번호를 필수 파라미터로 가져가야 합니다.
 * */