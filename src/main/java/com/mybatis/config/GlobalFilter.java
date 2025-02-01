package com.mybatis.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        //모든 요청에 이제 아래 내용 적용됨
        req.setCharacterEncoding("UTF-8");//UTF-8 인코딩 설정

        System.out.println("before filter");
        chain.doFilter(req,resp);
        System.out.println("after filter");
    }
}
