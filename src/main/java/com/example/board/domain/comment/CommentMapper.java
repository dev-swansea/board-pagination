package com.example.board.domain.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

  /**
   * 댓글 저장
   *
   * @param param - 댓글 정보
   */
  void save(CommentRequest params);

  /**
   * 댓글 상세정보 조회
   *
   * @param id - PK
   * @return 댓글 상세정보
   */
  CommentResponse findById(Long id);

  /**
   * 댓글 수정
   *
   * @param params - 댓글 정보
   */
  void update(CommentRequest params);

  /**
   * 댓글 삭제
   *
   * @param id - PK
   */
  void deleteById(Long id);

  /**
   * 댓글 리스트 조회
   *
   * @param postId - 게시글 번호(FK)
   * @return 댓글 리스트
   */
  List<CommentResponse> findAll(Long postId);

  /**
   * 댓글 수 카운팅
   * 당장은 사용되지 않으며, 게시글 때와 마찬가지로 추후에 페이징을 적용하면서 사용합니다.
   *
   * @param postId - 게시글 번호(FK)
   * @return 댓글 수
   */
  int count(Long postId);
}

/*
 * 테이블에서 실제로 데이터를 DELETE 하지 않고, 삭제 여부(delete_yn) 칼럼의 상태 값을 0(false)에서 1(true)로 업데이트합니다.
 * SELECT 쿼리의 조건절에 delete_yn = 0 조건을 주게 되면, 삭제된(delete_yn = 1) 데이터는 조회되지 않습니다. 즉, 물리 삭제가 아닌 논리 삭제 방식을 이용합니다.
 * */