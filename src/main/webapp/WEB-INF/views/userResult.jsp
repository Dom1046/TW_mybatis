<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 정보</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>회원 목록</h1>

<%-- 회원 목록이 비어있을 경우 메시지 표시 --%>
<% if (request.getAttribute("result") == null || request.getAttribute("result").toString().isEmpty()) { %>
<p>등록된 회원이 없습니다.</p>
<% } else { %>
<%-- 테이블 시작 --%>
<table>
    <thead>
    <tr>
        <th>회원번호</th>
        <th>이름</th>
        <th>나이</th>
        <th>직업</th>
        <th>애완동물</th>
    </tr>
    </thead>
    <tbody>
    <%-- request.getAttribute("result")는 String 타입이므로, <br> 태그를 기준으로 split하여 배열로 저장 --%>
    <% String[] userList = request.getAttribute("result").toString().split("<br>"); %>
    <% for (String user : userList) { %>
    <%-- 각 회원 정보는 "회원번호: 1 이름: 홍길동 ..." 형태이므로, " "를 기준으로 split --%>
    <% String[] userInfo = user.split(" "); %>
    <tr>
        <td><%= userInfo[1].replace("회원번호:", "") %></td> <%-- 회원번호 --%>
        <td><%= userInfo[3].replace("이름:", "") %></td> <%-- 이름 --%>
        <td><%= userInfo[5].replace("나이:", "") %></td> <%-- 나이 --%>
        <td><%= userInfo[7].replace("직업:", "") %></td> <%-- 직업 --%>
        <td><%= userInfo[9].replace("애완동물:", "") %></td> <%-- 애완동물 --%>
    </tr>
    <% } %>
    </tbody>
</table>
<% } %>

<a href="/webapps/mybatis">회원입력창으로 돌아가기</a>

</body>
</html>