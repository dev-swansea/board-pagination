package com.example.board.domain.post;

import com.example.board.common.dto.SearchDto;
import com.example.board.common.paging.Pagination;
import com.example.board.common.paging.PagingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
   * @param params - search conditions
   * @return list & pagination information
   */
  public PagingResponse<PostResponse> findAllPost(final SearchDto params) {

    // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
    // 엥 왜 count에 params를 보내지?
    int count = mapper.count(params);
    if (count < 1) {
      return new PagingResponse<>(Collections.emptyList(), null);
    }

    //  Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
    Pagination pagination = new Pagination(count, params);
    params.setPagination(pagination);

    // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
    List<PostResponse> list = mapper.findAll(params);
    return new PagingResponse<>(list, pagination);

  }

}
