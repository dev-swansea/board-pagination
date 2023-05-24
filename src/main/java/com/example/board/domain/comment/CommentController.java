package com.example.board.domain.comment;


import com.example.board.common.paging.PagingResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

  private final CommentService service;

  public CommentController(CommentService service) {
    this.service = service;
  }


  @PostMapping("/posts/{postId}/comments")
  public CommentResponse saveComment(@PathVariable final Long postId, @RequestBody final CommentRequest params) {
    System.out.println();
    Long id = service.saveComment(params);
    return service.findCommentById(id);
  }

  // 댓글 리스트 조회
  @GetMapping("/posts/{postId}/comments")
  public PagingResponse<CommentResponse> findAllComment(@PathVariable final long postId, final CommentSearchDto params) {
    return service.findAllComment(params);
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

  //  댓글 삭제
  @DeleteMapping("/posts/{postId}/comments/{id}")
  public Long deleteComment(@PathVariable final Long id, @PathVariable final Long postId) {
    return service.deleteComment(id);
    /*
     * REST API 설계 규칙에서 다큐먼트(Document)에 해당되는 기능으로,
     * 특정 게시글(postId)에 등록된 모든 댓글 중 PK(id)에 해당되는 댓글을 삭제합니다.
     * 삭제 프로세스가 완료되면 삭제된 댓글의 PK(id)를 리턴합니다.
     *  @PathVariable final Long postId 없어도 되긴 하네?
     *
     * 게시글과 마찬가지로 delete_yn 칼럼의 상태값을 변경하는 논리 삭제 방식이며,
     * 삭제가 완료된 후 findAllComment( )로 댓글을 다시 조회합니다.
     * */
  }
}
