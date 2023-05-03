package com.example.board.domain.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
   * @param postId - 게시글 번호(FK)
   * @return 특정 게시글에 등록된 댓글 리스트
   */
  public List<CommentResponse> findAllComment(final Long postId) {
    return mapper.findAll(postId);
  }
}

// 댓글 상세정보는 뭘 말하는거지?