<%--
  Created by IntelliJ IDEA.
  User: xuhongda
  Date: 2020/3/17
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<!-- isELIgnored属性用于指定是否忽略EL表达式 -->
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>test page</h1>
${sessionScope}

<hr>
<p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}</p>
<p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</p>
<p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.password}</p>
<p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.enabled}</p>
<p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.accountNonExpired}</p>
<p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.accountNonLocked}</p>
<p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.accountNonLocked}</p>
    <p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.authorities}</p>
    <c:forEach items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.authorities}" var="authorities">
       <p>${authoritie}</p>
    </c:forEach>

</body>
</html>
