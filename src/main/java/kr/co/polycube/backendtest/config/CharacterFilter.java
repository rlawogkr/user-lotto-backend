package kr.co.polycube.backendtest.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CharacterFilter implements Filter {
    // 2.2 TODO: 쿼리스트링에 특수문자가 포함되어 있을 경우, 접속을 제한
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest; // HttpServletRequest로 캐스팅
        String queryString = httpServletRequest.getQueryString(); // 쿼리스트링 추출
        if (queryString != null && queryString.matches(".*[\\$#@!%^&*()].*")) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "? & = : //를 제외한 특수문자가 포함되어 있을경우 접속을 제한합니다.");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
