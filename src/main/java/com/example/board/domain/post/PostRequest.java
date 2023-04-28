package com.example.board.domain.post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequest {

  private Long id; // pk
  private String title; // 제목
  private String content; // 내용
  private String writer; // 작성자
  private Boolean noticeYn; // 공지글 여부

}
