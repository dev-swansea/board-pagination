package com.example.board.domain.member;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService service;

  // 로그인 페이지
  @GetMapping("/login.do")
  public String openLogin() {
    return "member/login";
  }

  // 회원 정보 저장(회원 가입)
  @PostMapping("/members")
  @ResponseBody
  public Long saveMember(@RequestBody final MemberRequest params) {
    service.saveMember(params);
    return params.getId();
  }

  // 회원 상세정보 조회
  @GetMapping("/members/{loginId}")
  @ResponseBody
  public MemberResponse findMemberByLoginId(@PathVariable String loginId) {
    return service.findMemberByLoginId(loginId);
  }

  // 회원 정보 수정
  @PatchMapping("/members/{id}")
  @ResponseBody
  public Long updateMember(@PathVariable final Long id, @RequestBody final MemberRequest params) {
    // ? 쿼리스트링으로 넘어오는 id 어따가 써?
    return service.updateMember(params);
  }

  // 회원 삭제(탈퇴)
  @DeleteMapping("/members/{id}")
  @ResponseBody
  public Long deleteMemberById(final Long id) {
    return service.deleteMemberById(id);
  }

  // 회원 수 카운팅(id 중복 체크)
  @GetMapping("/member-count")
  @ResponseBody
  public int count(@RequestParam final String loginId) {
    // ?loginId 이렇게 안해줘도 되네?
    return service.countMemberByLoginId(loginId);

  }

}
