package com.mybatis.controller;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculator")
public class CalculatorController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ServletContext application = req.getServletContext();
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();

        PrintWriter out = resp.getWriter();

        String op = req.getParameter("operator");
        String v_ = req.getParameter("v");

        int v = 0;
        if (!v_.equals("")) v = Integer.parseInt(v_);

        int x = 0;
        if (op.equals("=")) {
            for (Cookie c : cookies) {
                if (c.getName().equals("value")) {
                    x = Integer.parseInt(c.getValue());
                    break;
                }
            }
            int y = v;

            String operator = "";
            for (Cookie c : cookies) {
                if (c.getName().equals("op")) {
                    operator = c.getValue();
                    break;
                }
            }
            int result = 0;

            if (operator.equals("+")) result = x + y;
            else result = x - y;

            resp.getWriter().printf("result is %d\n", result);
        } else {
            Cookie valueCookie = new Cookie("value", String.valueOf(v));
            Cookie opCookie = new Cookie("op", op);
            valueCookie.setPath("/");
            valueCookie.setMaxAge(60);
            opCookie.setPath("/");
            opCookie.setMaxAge(60);//초단위
            resp.addCookie(valueCookie);
            resp.addCookie(opCookie);
            resp.sendRedirect("calculator.html");
        }
    }
}
