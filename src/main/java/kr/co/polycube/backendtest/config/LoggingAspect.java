package kr.co.polycube.backendtest.config;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
    // 2.2 TODO: user 등록, 조회, 수정 API에 대해 reqeust 시 console에 client agent를 로깅
    @Before("execution(* kr.co.polycube.backendtest.controller.UserController.*(..))")
    public void logClientAgent(JoinPoint joinPoint) {
        // 현재 요청 정보를 가져오기 위해 RequestContextHolder 사용
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if(attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String clientAgent = request.getHeader("User-Agent");
            System.out.println("Client Agent: " + clientAgent);
        }
    }
}
