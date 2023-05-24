package com.example.board.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberRequest {

  private Long id; // pk(회원 번호)
  private String loginId; // 로그인ID
  private String password; // 비밀번호
  private String name; // 이름
  private Gender gender; // 성별
  private LocalDate birthday; // 생일

  public void encodingPwd(PasswordEncoder passwordEncoder) {
    if (StringUtils.isEmpty(password)) {
      return;
    }
    password = passwordEncoder.encode(password);
  }

}
