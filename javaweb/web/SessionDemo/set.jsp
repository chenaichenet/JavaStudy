<%--
  Created by IntelliJ IDEA.
  User: 嘉平十七
  Date: 2020/6/24
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设置Session中数据</title>
</head>
<body>
<h1>向session域中保存数据</h1>
<%
    session.setAttribute("session1","这里是session中保存的数据");
%>
</body>
</html>
