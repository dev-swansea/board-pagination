package com.example.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Aspect // 	AOP 기능을 하는 클래스의 클래스 레벨에 선언하는 어노테이션입니다.
@Slf4j
@Component
public class LoggerAspect {

  // JoinPoint - 어드바이스를 적용할 위치를 의미합니다
  @Around("execution(* com.example.board.domain..*Controller.*(..))||execution(* com.example.board.domain..*Service.*(..))" +
          "||execution(* com.example.board.domain..*Mapper.*(..))")
  public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
    // signature 객체는 클래스와 메서드에 대한 정보를 담고 있는 객체
    /*
     *  name에는 대상 파일의 경로(패키지 + 파일명)가 저장되며,
     * 결과적으로 printLog( )는 signature 객체가 가진 정보를 이용해서,
     * 어떤 클래스의 어떤 메서드가 호출되었는지를 로그로 출력하는 기능을 합니다.
     * */
    // getSignature: 실행되는 대상 객체의 메서드에 대한 정보를 가지고 옵니다.
    String name = joinPoint.getSignature().getDeclaringTypeName();
    String type =
            StringUtils.contains(name, "Controller") ? "Controller ===>" :
                    StringUtils.contains(name, "Service") ? "Service ===>" :
                            StringUtils.contains(name, "Mapper") ? "Mapper ===>" : "";

    log.info(type + name + "." + joinPoint.getSignature().getName() + "()");
    return joinPoint.proceed();

  }
}
