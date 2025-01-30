<%--
  Created by IntelliJ IDEA.
  User: Don
  Date: 2025-01-28
  Time: 오전 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>가입완료</title>
</head>
<body>
<p>회원가입완료</p>
<p><%= request.getAttribute("result") %></p>
<a href="/webapps/mybatis">회원입력창으로 돌아가기</a>
</body>
</html>
