package com.mybatis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculator")
public class CalculatorController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        String num1 = req.getParameter("fNum");
        String num2 = req.getParameter("sNum");

        if (num1 == null || num2 == null || num2.isEmpty() || num1.isEmpty()) {
            out.println("숫자를 입력해주세요.");
        } else {
            int fNum = Integer.parseInt(num1);
            int sNum = Integer.parseInt(num2);

            if (req.getParameter("operator").equals("더하기")) {
                int sum = fNum + sNum;
                out.println(sum);
            } else {
                int sum = fNum - sNum;
                out.println(sum);
            }
        }
    }
}
