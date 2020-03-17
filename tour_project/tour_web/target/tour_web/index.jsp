<%--
  Created by IntelliJ IDEA.
  User: xuhongda
  Date: 2020/3/17
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<!-- isELIgnored属性用于指定是否忽略EL表达式 -->
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:forward page="${pageContext.request.contextPath}/pages/main.jsp"></jsp:forward>
</body>
</html>
