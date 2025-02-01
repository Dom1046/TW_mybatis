package com.mybatis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
//        req.setCharacterEncoding("UTF-8");//톰캣의 Connector를 건드릴수 없는경우-> 보통 서버여러개를 server.xml에서 관리하기 때문에
//        다른곳에 영향을 주지 않기 위해서 servlet에서만 설정함.

        PrintWriter out = resp.getWriter();

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        out.println(title);
        out.println(content);
    }
}
