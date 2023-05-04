package com.example.board.domain.comment;

import com.example.board.common.paging.Pagination;
import com.example.board.common.paging.PagingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CommentService {

  private final CommentMapper mapper;

  public CommentService(CommentMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 댓글 저장
   *
   * @param params - 댓글 저장
   * @return Generated PK
   */
  @Transactional
  public Long saveComment(final CommentRequest params) {
    mapper.save(params);
    return params.getId();
  }

  /**
   * 댓글 상세정보 조회
   *
   * @param id - PK
   * @return 댓글 상세정보
   */
  public CommentResponse findCommentById(final Long id) {
    return mapper.findById(id);
  }

  /**
   * 댓글 수정
   *
   * @param params - 댓글 정보
   * @return PK
   */
  @Transactional
  public Long updateComment(final CommentRequest params) {
    mapper.update(params);
    return params.getId();
  }

  /**
   * 댓글 삭제
   *
   * @param id - PK
   * @return PK
   */
  @Transactional
  public Long deleteComment(final Long id) {
    mapper.deleteById(id);
    return id;
  }

  /**
   * 댓글 리스트 조회
   *
   * @param params - Search conditions
   * @return list & pagination infomation
   */
  public PagingResponse<CommentResponse> findAllComment(final CommentSearchDto params) {
    int count = mapper.count(params);
    if (count < 1) {
      return new PagingResponse<>(Collections.emptyList(), null);
    }
    Pagination pagination = new Pagination(count, params);
    List<CommentResponse> list = mapper.findAll(params);
    return new PagingResponse<>(list, pagination);

    /*
     * PostService의 findAllPost( )와 거의 동일한 로직입니다. findAllComment( )는 params에 계산된 페이지 정보(pagination)를 저장하는 로직이 없는데요.
     *  이 로직은 페이징이 필요한 모든 기능에서 공통으로 사용되는 로직이기 때문에 Pagination 생성자에서 처리해 주는 게 나을 듯합니다.
     * */

  }
}

// 댓글 상세정보는 뭘 말하는거지?