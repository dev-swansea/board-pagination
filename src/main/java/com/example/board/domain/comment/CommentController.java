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
    return service.findCommentById(id);
  }

  @GetMapping("/posts/{postId}/comments")
  public List<CommentResponse> findAllComment(@PathVariable final long postId) {
    return service.findAllComment(postId);
  }

  // 댓글 상세정보 조회
  @GetMapping("/posts/{postId}/comments/{id}")
  public CommentResponse findCommentById(@PathVariable final Long postId, @PathVariable final Long id) {
    return service.findCommentById(id);
    /*
     * REST API 설계 규칙에서 다큐먼트(Document)에 해당되는 기능으로,
     * 특정 게시글(postId)에 등록된 모든 댓글 중 PK(id)에 해당되는 댓글을 조회합니다.
     * 댓글을 수정할 때 사용자에게 기존 댓글 정보를 보여주는 용도로 사용됩니다.
     * */
  }

  //  기존 댓글 수정
  @PatchMapping("/posts/{postId}/comments/{id}")
  public CommentResponse updateComment(@PathVariable final Long postId, @PathVariable final Long id, @RequestBody final CommentRequest params) {
    service.updateComment(params);
    return service.findCommentById(id); // 아 여기서 반환된 값이 @RequestBody 때문에 params에 담기는구나
    /*
     * REST API 설계 규칙에서 다큐먼트(Document)에 해당되는 기능으로,
     * 특정 게시글(postId)에 등록된 모든 댓글 중 PK(id)에 해당되는 댓글을 수정합니다.
     * 댓글 수정이 완료되면 수정된 댓글 정보(객체)를 리턴해 주는데요.
     * saveComment( )와 마찬가지로 @RequestBody를 이용해서 JSON 문자열로 넘어오는 댓글 정보를
     * CommentRequest 객체의 각 멤버 변수에 매핑(바인딩)합니다.
     * */
  }
}
