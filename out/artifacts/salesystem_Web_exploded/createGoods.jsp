<%@ page import="com.dgut.entity.Goods" %>
<%@ page import="com.dgut.dao.GoodsDaoImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    String name = request.getParameter("name");
    double price = Double.parseDouble(request.getParameter("price"));
    String description = request.getParameter("description");

    Goods goods = new Goods(name, price, description);

    GoodsDaoImpl goodsDao = new GoodsDaoImpl();
    goodsDao.create(goods);
%>
<h1>商品添加成功！</h1>
<a href="index.jsp">返回首页</a>
</body>
</html>
