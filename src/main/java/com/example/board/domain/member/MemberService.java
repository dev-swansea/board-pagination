package com.example.board.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMapper mapper;
  private final PasswordEncoder passwordEncoder;

  /**
   * 회원 정보 저장(회원가입)
   *
   * @param params - 회원 정보(RequestMember)
   * @return PK
   */
  @Transactional
  public Long saveMember(final MemberRequest params) {
    params.encodingPwd(passwordEncoder);
    mapper.save(params);
    return params.getId();
  }


  /**
   * 회원 상세정보 조회
   *
   * @return 회원 상세정보(ResponseMember)
   * @params loginId - UK
   */
  public MemberResponse findMemberByLoginId(final String loginId) {
    return mapper.findByLoginId(loginId);
  }

  /**
   * 회원 정보 수정
   *
   * @param params - 회원 정보(MemberRequest)
   * @return PK
   */
  @Transactional
  public Long updateMember(final MemberRequest params) {
    mapper.update(params);
    return params.getId();
  }

  /**
   * 회원 정보 삭제(탈퇴)
   *
   * @param params - PK
   * @return PK
   */
  @Transactional
  public Long deleteMemberById(final Long id) {
    mapper.deleteById(id);
    return id;
  }

  /**
   * 회원 수 카운팅(Id 중복 체크)
   *
   * @param loginId - UK
   * @return 회원수
   */
  public int countMemberByLoginId(final String loginId) {
    return mapper.countByLoginId(loginId);
  }
}
