<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Goods</title>
</head>
<body>
<h1>Update Goods</h1>
<form action="updateGoods.jsp" method="post">
    <input type="hidden" name="id" value="${goods.id}" />
    <label>Name:</label>
    <input type="text" name="name" value="${goods.name}" />
    <br />
    <label>Price:</label>
    <input type="text" name="price" value="${goods.price}" />
    <br />
    <label>Description:</label>
    <textarea name="description">${goods.description}</textarea>
    <br />
    <input type="submit" value="Update" />
</form>
</body>
</html>