<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>删除商品信息</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    String sql = "DELETE FROM goods WHERE id = " + id;
%>
<sql:update dataSource="${dataSource}" var="result" sql="<%=sql%>" />
<c:if test="${result.updateCount > 0}">
    商品删除成功！
</c:if>
<c:if test="${result.updateCount == 0}">
    商品删除失败！
</c:if>
</body>
</html>