package com.mybatis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController extends HttpServlet {
    //로그
    private final Logger logger = LogManager.getLogger(UserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet메서드 실행");
        //1. 요청 파라미터 받기
        String action = req.getParameter("action");

        //2. 비즈니스 로직 수행
        String message = "Hello World";

        //3. 응답생성
        if ("view".equals(action)) {
            logger.info("doGet메서드 실행");
            //JSP 페이지로 포워딩
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/user.jsp");
            rd.forward(req, resp);
        } else {
            logger.info("doGet메서드 직접입력");
            // 직접 입력
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //POST 요청 처리
        doGet(req, resp);
    }

}
