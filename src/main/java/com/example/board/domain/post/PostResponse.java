package com.example.board.domain.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponse {

  private Long id; // pk
  private String title; // 제목
  private String content; // 내용
  private String writer; // 작성자
  private int viewCnt;
  private Boolean noticeYn; // 공지글 여부
  private Boolean deleteYn; // 공지글 여부
  private LocalDateTime createdDate; // 생성 일시
  private LocalDateTime modifiedDate; // 최종 수정일시


}
