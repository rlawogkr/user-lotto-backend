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
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String queryString = httpRequest.getQueryString();

        // 허용된 특수문자: ? & = : //
        if (queryString != null && queryString.matches(".*[^\\w\\?&=://].*")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "특수문자가 포함된 요청은 허용되지 않습니다.");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
