package com.example.board.domain.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

  private final CommentService service;

  public CommentController(CommentService service) {
    this.service = service;
  }


  @PostMapping("/posts/{postId}/comments")
  public CommentResponse saveComment(@PathVariable final Long postId, @RequestBody final CommentRequest params) {
    Long id = service.saveComment(params);
    // 새로운 댓글을 생성한 후 생성된 댓글의 상세정보(응답 객체)를 리턴합니다.
    /*
     * 서버에 요청을 보내면, @PathVariable 파라미터인 postId는 게시글의 PK(32356)를 수집하고,
     * JSON 문자열로 넘어온 댓글 정보는 @RequestBody에 의해 CommentRequest 클래스의 객체인 params에 매핑됩니다.
     * 이때 JSON 문자열의 각 key와 클래스의 멤버 변수명은 동일해야 합니다.
     * */
    return service.findCommentById(id);
  }

  // CommentApiController에 다음의 메서드를 추가해 주세요.
  // 해당 메서드는 이전 글에서 설명드린 REST API 설계 규칙에서 컬렉션(Collection)에 해당되는 기능으로, 특정 게시글(postId)에 등록된 모든 댓글을 조회합니다.
  @GetMapping("/posts/{postId}/comments")
  public List<CommentResponse> findAllComment(@PathVariable final long postId) {
    return service.findAllComment(postId);
  }
}

/*
 * REST Controller는 화면(HTML)이 아닌 데이터 자체를 리턴합니다.
 * 댓글 데이터의 CRUD는 전부 게시글 상세 페이지에서 이루어지기 때문에 화면을 따로 구성할 필요 없이 데이터만 주고받으면 됩니다.
 * */