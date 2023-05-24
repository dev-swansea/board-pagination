package com.example.board.domain.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) //access 속성을 이용해서 객체 생성을 protected로 제한합니다.
public class CommentRequest {
  private Long id;
  private Long postId;
  private String content;
  private String writer;
}
/*
 *게시글 요청 클래스인 PostRequest에는 @Getter와 @Setter를 선언해서 사용했는데요. 이전에 말씀드렸듯이 요청 클래스의 각 멤버 변수는 HTML의 폼(form) 태그에 선언된 필드(input, textarea 등)의 name 값을 기준으로 파라미터를 전송하며,
 * 전송된 파라미터는 요청 클래스의 set( ) 메서드에 의해 값이 매핑됩니다.
 * 하지만, 일반적인 REST API 방식에서는 데이터를 등록/수정 할 때 폼(form) 자체를 전송하지 않고,
 * key-value 구조로 이루어진 JSON이라는 문자열 포맷으로 데이터를 전송하기 때문에 set( ) 메서드가 필요하지 않습니다.
 * (파일을 전송하는 경우는 제외) 자세한 내용은 컨트롤러를 처리하는 과정에서 설명드리겠습니다. 지금은 "JSON이라는 포맷으로 데이터를 전달하는구나!" 정도로 이해해 주시면 되겠습니다.
 * */