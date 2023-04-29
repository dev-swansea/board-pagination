package com.example.board.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MessageDto {

  private String message; // 사용자에게 전달할 메시지
  private String redirectUri; // 리다이렉트 URI
  private RequestMethod method; // HTTP 요청 메서드
  private Map<String, Object> data; // 화면(view)로 전달할 데이터(파라미터)
}
/*
* 2) message
사용자에게 전달할 메시지입니다.

3) redirectUri
리다이렉트 할 URI입니다.

4) method
HTTP 요청 메서드입니다. RequestMethod는 spring-web 라이브러리에 포함된 enum(상수 처리용) 클래스입니다.

5) data
화면(HTML)으로 전달할 파라미터입니다. 페이지별로 전달할 파라미터의 개수는 랜덤 하기 때문에 여러 데이터를 key-value 형태(구조)로 담을 수 있는 Map을 이용합니다.

* 예를 들어, 페이징 처리가 되어있다고 가정해 보겠습니다. 3페이지에 있는 25번 게시글을 수정했다면, 수정이 완료된 후 다시 3페이지를 유지해야 합니다. 이때, 이전 페이지 정보(page=3)를 data에 담아 리다이렉트 하면 3페이지를 유지할 수 있습니다.

*  */