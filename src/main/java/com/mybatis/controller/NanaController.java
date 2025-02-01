package com.mybatis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class NanaController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        int cnt = Integer.parseInt(req.getParameter("cnt"));

        PrintWriter out = resp.getWriter();
        for (int i = 0; i < cnt; i++) {
            out.println("<p>"+i+" 안녕"+"<p>");
        }
    }
}
