<%--
  Created by IntelliJ IDEA.
  User: xuhongda
  Date: 2020/3/11
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<!-- isELIgnored属性用于指定是否忽略EL表达式 -->
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>诊断文件信息</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">诊断信息列表</h1>
    <%--查询--%>
    <form class="form-inline" action="${pageContext.request.contextPath}/findByPage" method="get">
        <div class="form-group">
            <label class="sr-only" for="sncode">搜索</label>
            <input type="text" class="form-control" id="sncode" name="sncode" placeholder="输入SN号">
        </div>
        <button type="submit" class="btn btn-default">查询</button>
    </form>
    <table class="table table-bordered table-striped text-center">
        <thead>
            <tr>
                <th>序列号</th>
                <th>上传时间</th>
                <th>文件名称</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pageInfo.list}" var="fileInfo">
                <tr>
                    <td>${fileInfo.snCode}</td>
                    <td>${fileInfo.uploadTimeStr}</td>
                    <td>${fileInfo.fileName}</td>
                    <td><a href="${pageContext.request.contextPath}/downloadFile?fid=${fileInfo.fid}" class="btn btn-primary">下载</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="${pageContext.request.contextPath}/findByPage?page=${pageInfo.prePage}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                <li class="${pageInfo.pageNum == i?'active':''}"><a href="${pageContext.request.contextPath}/findByPage?page=${i}">${i}</a></li>
            </c:forEach>
            <li>
                <a href="${pageContext.request.contextPath}/findByPage?page=${pageInfo.nextPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
    <p class="hidden" id="rootUrl">${pageContext.request.contextPath}/</p>

</div>
<script>
</script>
</body>
</html>
