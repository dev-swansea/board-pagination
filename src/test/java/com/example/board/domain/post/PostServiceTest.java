package com.example.board.domain.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

  @Autowired
  PostService service;

  @Test
  void savePost() {
    PostRequest param = new PostRequest();
    param.setTitle("service 테스트2");
    param.setContent("PK 받아보기");
    param.setWriter("suwan");
    param.setNoticeYn(false);
    Long id = service.savePost(param);
    // useGeneratedKeys 해줘야 auto_increment값을 받는다. => 왜 그래도 널이지
    // 아.. keyProperty도 지정해줘야됨.
    //i useGeneratedKeys 옵션을 true로 설정하면 생성된 게시글의 PK가 parameterType에 선언된 요청 객체(params)에 저장되며, keyProperty에 선언된 id에 값이 매핑(바인딩)됩니다.
    System.out.println("생성된 게시글 ID : " + id);
  }
}