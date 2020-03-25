<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<html>
<body>
<jsp:forward page="/pages/main.jsp"></jsp:forward>
<%
    // 重定向到新地址
//    String site = new String("/pages/main.jsp");
//    response.setStatus(response.SC_MOVED_TEMPORARILY);
//    response.setHeader("Location", site);
%>
</body>
</html>
