package com.mybatis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/calculator3")
public class CalculatorController3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String operator = req.getParameter("operator");
        String value = req.getParameter("value");
        String dot = req.getParameter("dot");

        String exp = "";
        if (cookies != null)
            for (Cookie c : cookies) {
                if (c.getName().equals("exp")) {
                    exp = c.getValue();
                    break;
                }
            }

        if (operator !=null && operator.equals("C")) {
            // C를 눌렀을 때 쿠키 삭제
            exp = "";
        }else if (operator !=null && operator.equals("<-")) {
            // 백스페이스 처리
            if (!exp.isEmpty()) {
                exp = exp.substring(0, exp.length() - 1);
            }
        }else if (operator !=null && operator.equals("=")) {
            try {
                exp = String.valueOf(ExpressionEvaluator.evaluate(exp));
            } catch (Exception e) {
                exp = "Error";
            }
        } else {
            exp += (operator ==null)? "" : operator;
            exp += (value == null) ? "" : value;
            exp += (dot == null) ? "" : dot;
        }
        Cookie expCookie = new Cookie("exp", exp);
        expCookie.setPath("/");
        resp.addCookie(expCookie);

        resp.sendRedirect("calculator2");
    }
}
