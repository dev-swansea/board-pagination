package com.example.board.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 컨트롤러의 메서드에 매핑된 특정 URI가 호출됐을 때 실행되는 메서드
    // 컨트롤러를 경유(접근)하기 직전에 실행되는 메서드입니다. => 컨트롤러 어댑터 이후(?)
    // 사용자가 어떠한 기능을 수행했는지 파악하기 위해, 해당 메서드(기능)와 매핑된 URI 정보가 로그로 출력되도록 처리합니다.
    log.info("==================");
    log.info("======Request begin=======");
    log.info("Request URI ==> " + request.getRequestURI());
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    // 컨트롤러를 경유(접근) 한 후
    // 화면(View)으로 결과를 전달하기 전에 실행되는 메서드입니다.
    log.info("==================");
    log.info("======Request end=======");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

  }
}
