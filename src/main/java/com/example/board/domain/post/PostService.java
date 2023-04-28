package com.example.board.domain.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PostService {

  private final PostMapper mapper;

  public PostService(PostMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * 게시글 저장
   *
   * @param parmas - 게시글 정보
   * @return Generated PK
   */

  @Transactional
  public Long savePost(final PostRequest params) {
    mapper.save(params);
    return params.getId(); // 이걸로 뭐하지?
  }

  /**
   * 게시글 수정
   *
   * @param params - 게시글 정보
   * @return PK
   */
  @Transactional
  public Long updatePost(final PostRequest params) {
    mapper.update(params);
    return params.getId();
  }

  /**
   * @return 게시글 상세정보
   * @parma id - PK
   */
  public PostResponse findPostById(final Long id) {
    return mapper.findById(id);
  }

  /**
   * 게시글 삭제
   *
   * @param id - PK
   * @return PK
   */
  public Long deletePost(final Long id) {
    mapper.deleteById(id);
    return id;
  }

  /**
   * 게시글 리스트 조회
   *
   * @return 게시글 리스트
   */
  public List<PostResponse> findAllPost() {
    return mapper.findAll();
  }

}
