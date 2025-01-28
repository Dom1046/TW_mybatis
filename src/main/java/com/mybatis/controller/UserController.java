package com.mybatis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.mybatis.config.SqlSessionFactoryManager;
import com.mybatis.domain.User_Dto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController extends HttpServlet {
    //로그
    private final Logger logger = LogManager.getLogger(UserController.class);
    //sql 세션 만들기
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet메서드 실행");
        //1. 요청 파라미터 받기
        String action = req.getParameter("action");

        //2. 비즈니스 로직 수행
//        String message = "Hello World"; 예시
        StringBuilder sb = new StringBuilder();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // mybatis로 전체 user 호출
            List<User_Dto> list = session.selectList("com.mybatis.mapper.UserMapper.selectAll");
            logger.info("조회된 user 수: " + list.size());

            for (User_Dto user : list) {
                sb.append("유저: ").append(user).append("\n");
            }
        }
        String message = sb.toString();

        //3. 응답생성
        if ("view".equals(action)) {
            logger.info("doPost메서드 실행");
            //JSP 페이지로 포워딩
            req.setAttribute("message", message);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/user.jsp");
            rd.forward(req, resp);
        } else {
            logger.info("doPost메서드 직접입력");
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
