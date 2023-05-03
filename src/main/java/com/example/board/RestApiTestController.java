package com.example.board;

import com.example.board.common.dto.SearchDto;
import com.example.board.common.paging.PagingResponse;
import com.example.board.domain.post.PostResponse;
import com.example.board.domain.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiTestController {

  @Autowired
  private PostService service;

 /* @GetMapping("/members")
  //@ResponseBody // return 타입 앞에 붙혀도 된다.
  public @ResponseBody List<Map<String, Object>> findAllMember() {
    List<Map<String, Object>> members = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      Map<String, Object> map = new HashMap<>();
      map.put("id", i);
      map.put("name", i + "번 개발자");
      map.put("age", 10 + i);
      members.add(map);
    }
    return members;
    //@ResponseBody가 붙으면,
// 스프링의 메시지 컨버터(Message Converter)에 의해 화면(HTML)이 아닌 리턴 타입에 해당하는 데이터 자체를 리턴합니다.
  }*/

  @GetMapping("/posts")
  public PagingResponse<PostResponse> findAllPost() {
    return service.findAllPost(new SearchDto());
  }

}

