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

        String op = req.getParameter("operator");
        String[] nums = req.getParameterValues("num");

        int result = 0;
        if (nums != null || nums.length != 0) {
            if (op.equals("더하기")) {
                for (String num : nums) {
                    result += Integer.parseInt(num);
                }
            } else {
                result = Integer.parseInt(nums[0]);
                for (int i = 1; i < nums.length; i++) {
                    result -= Integer.parseInt(nums[i]);
                }
            }
            out.println(result);
        }
    }
}
