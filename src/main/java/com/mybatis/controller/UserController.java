package com.mybatis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.mybatis.config.SqlSessionFactoryManager;
import com.mybatis.domain.Pet;
import com.mybatis.domain.User_Dto;
import com.mybatis.dto.UserCreateDto;
import com.mybatis.mapper.UserMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController extends HttpServlet {
    private final Logger logger = LogManager.getLogger(UserController.class);
    private final SqlSessionFactoryManager sessionManager = new SqlSessionFactoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet 메서드 실행");

        try (SqlSession session = sessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            List<User_Dto> users = mapper.selectAll();
            StringBuilder message = new StringBuilder();
            for (User_Dto user : users) {
                message.append("회원번호: ").append(user.getUser_id())
                        .append(" 이름: ").append(user.getName())
                        .append(" 나이: ").append(user.getAge())
                        .append(" 직업: ").append(user.getJob())
                        .append(" 애완동물: ").append(user.getPet()).append("<br>");
            }

            req.setAttribute("message", message.toString());
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/userView.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            logger.error("doGet 에러 발생: ", e.getMessage()); // 예외 로깅
            req.setAttribute("message", "회원 정보를 가져오는 중 오류가 발생했습니다.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/userView.jsp"); // 에러 페이지로 이동
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doPost 메서드 실행");

        req.setCharacterEncoding("UTF-8"); // 요청 문자 인코딩 설정

        String name = req.getParameter("name");
        int age = 0;
        try {
            age = Integer.parseInt(req.getParameter("age"));
        } catch (NumberFormatException e) {
            logger.error("doPost age 파싱 에러: ", e);
            req.setAttribute("errorMessage", "나이는 숫자로 입력해야 합니다.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/error.jsp");
            rd.forward(req, resp);
            return; // 오류 발생 시 메서드 종료
        }

        String job = req.getParameter("job");
        String sex = req.getParameter("sex");
        String pet = req.getParameter("pet");

        try (SqlSession session = sessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            UserCreateDto userCreateDto = new UserCreateDto(name, age, job, sex, Pet.valueOf(pet));
            mapper.insertUser(userCreateDto);
            session.commit();

            String message = name + "님 안녕하세요! \n 회원등록이 완료되었습니다.";
            req.setAttribute("result", message);
            req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

        } catch (Exception e) {
            logger.error("doPost 에러 발생: ", e.getMessage());
            req.setAttribute("errorMessage", "회원 등록 중 오류가 발생했습니다.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/error.jsp");
            rd.forward(req, resp);
        }
    }
}
