<%--
  Created by IntelliJ IDEA.
  User: Don
  Date: 2025-01-28
  Time: 오후 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>입력 및 결과 표시</title>
</head>
<body>

<%-- 입력 양식 --%>
<form action="usercontroller" method="post">
    <label for="name">이름:</label>
    <input type="text" name="name" id="name" required><br><br>

    <label for="age">나이:</label>
    <input type="number" name="age" id="age" required><br><br>

    <label for="job">직업</label>
    <input type="text" name="job" id="job" required><br><br>

    <label for="sex">성별</label>
    <input type="text" name="sex" id="sex" required><br><br>

    <labe for ="pet">애완동물</labe>
    <input type="text" name="pet" id="pet" required><br><br>

    <input type="submit" value="제출">
</form>

<%-- 서블릿에서 전달된 결과 표시 --%>
<%
    String result = (String) request.getAttribute("result");
    if (result != null) {
        out.println("<p>결과: " + result + "</p>");
    }
%>
<a type="button" href="/usercontroller">회원정보 보기</a>
</body>
</html>
