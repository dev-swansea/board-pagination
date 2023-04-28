package com.example.board;

import com.example.board.domain.post.PostMapper;
import com.example.board.domain.post.PostRequest;
import com.example.board.domain.post.PostResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostMapperTest {

  @Autowired
  PostMapper mapper;

  @Test
  void save() {
    PostRequest params = new PostRequest();
    params.setTitle("1번 게시글 제목");
    params.setContent("1번 게시글 내용");
    params.setWriter("테스터");
    params.setNoticeYn(false);
    mapper.save(params);

    List<PostResponse> posts = mapper.findAll();
    System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
  }

  @Test
  void findById() {
    PostResponse post = mapper.findById(1L);
    try {
      // Jackson 라이브러리 이용해 결과를 json으로 가져온다
      //객체는 디버깅을 해보지 않는 이상 확인이 까다롭기에 JSON 문자열로 변경해서 콘솔에 출력해 보았습니다. 라고 한다.
      String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
      System.out.println(postJson);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Test
  void update() {
    // 오호.. 요청과 응답이 다르니 이렇게 하는구나

    /*
     * 게시글 수정은 생성과 마찬가지로 테이블에 데이터를 저장(Save)하는 개념입니다. 단지, 없었던 데이터를 생성하는 것인지, 기존 데이터를 수정하는 것인지의 차이만을 가집니다.
     * 이 미묘한 차이는 PK인 id를 통해 구분할 수 있는데요. 테이블에 새로 생성되는 게시글은 auto_increment에 의해 자동 생성되지만, 게시글을 수정하기 위해서는 수정할 게시글의 PK인 id를 파라미터로 전달해 주어야 합니다.
     * 만약 WHERE 조건 없이 UPDATE 쿼리가 실행된다면 모든 데이터가 동일한 값으로 UPDATE 되어버릴 테고,
     * */
    PostRequest params = new PostRequest();
    params.setId(1L);
    params.setTitle("제목 수정");
    params.setContent("본문 수정");
    params.setWriter("작가 수정");
    params.setNoticeYn(true);
    mapper.update(params);

    // 게시글 조회
    PostResponse post = mapper.findById(1L);
    try {
      String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
      System.out.println("update post 1 => " + postJson);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Test
  void delete() {
    /*
     * 앞에서 말씀드렸듯이, 테이블에서 물리적으로 데이터를 삭제하지 않고, 삭제 여부(delete_yn) 상태 값을 0(false)에서 1(true)로 UPDATE 합니다. 추후에 게시글 리스트 페이지에는 delete_yn이 *
     * 0(false)인 데이터만 사용자에게 노출합니다.
     * i 진짜로 삭제하지 않는다는게 중요!!
     * */
    System.out.println("삭제 이전의 전체 게시글 수 : " + mapper.findAll());
    mapper.deleteById(1L);
    System.out.println("삭제 이후 전체 게시글 수 : " + mapper.findAll());
  }

  @Test
  void findAll() {
    List<PostResponse> posts = mapper.findAll();
  }
}
