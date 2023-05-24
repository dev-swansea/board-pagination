package com.example.board.domain.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  /**
   * 회원 정보 저장(회원가입)
   *
   * @param params - 회원정보(MemberRequest)
   */
  void save(MemberRequest params);

  /**
   * 회원 상세정보 조회
   *
   * @param - UK(LoginId)
   * @return - 회원 상세정보(MemberResponse)
   */
  MemberResponse findByLoginId(String loginId);

  /**
   * 회원 정보 수정
   *
   * @param - 회원정보(MemberRequest)
   */
  void update(MemberRequest params);

  /**
   * 회원 정보 삭제(탈퇴)
   *
   * @param id-PK
   */
  void deleteById(Long id);

  /**
   * 회원 수 카운팅(ID 중복체크)
   *
   * @param loginId - UK
   * @return 회원 수(int)
   */
  int countByLoginId(String loginId);
}
