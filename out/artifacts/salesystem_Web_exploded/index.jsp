<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.dgut.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dgut.entity.Goods" %>
<%@ page import="com.dgut.entity.Contract" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="mycss/index.css?v=2">
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Index Page</title>

    <script type="text/javascript">
        // 计算总价
        <%--function calcTotal() {--%>
        <%--    let total = 0;--%>
        <%--    let goodsCheckboxes = document.querySelectorAll(".goods-checkbox");--%>
        <%--    let goodsCountInputs = document.querySelectorAll(".goods-count");--%>
        <%--    let goodsTotalTds = document.querySelectorAll(".goods-total");--%>
        <%--    for (let i = 0; i < goodsCheckboxes.length; i++) {--%>
        <%--        if (goodsCheckboxes[i].checked) {--%>
        <%--            // 计算该商品的总价--%>
        <%--            let goodsCount = goodsCountInputs[i].value;--%>
        <%--            let goodsPrice = <%= goodsList.get(i).getPrice() %>;--%>
        <%--            let goodsTotal = goodsCount * goodsPrice;--%>
        <%--            // 累加到合同总价中--%>
        <%--            total += goodsTotal;--%>
        <%--            // 更新该商品行的小计--%>
        <%--            goodsTotalTds[i].innerHTML = goodsTotal;--%>
        <%--        } else {--%>
        <%--            goodsTotalTds[i].innerHTML = "";--%>
        <%--        }--%>
        <%--    }--%>
        <%--}--%>

    <%--function updateTotal() {--%>
        <%--    // // 获取表单中所有的商品数量输入框--%>
        <%--    // let inputs = document.querySelectorAll('input[name="quantity"]');--%>
        <%--    // let total = 0;--%>
        <%--    // // 遍历每个输入框，累加其对应的价格--%>
        <%--    // inputs.forEach(function(input) {--%>
        <%--    //     let tr = input.parentElement.parentElement;--%>
        <%--    //     let price = parseFloat(tr.querySelector('td[name="price"]').innerText);--%>
        <%--    //     let quantity = parseInt(input.value);--%>
        <%--    //     total += price * quantity;--%>
        <%--    // });--%>
        <%--    // // 显示合同总价--%>
        <%--    // document.querySelector('#total').innerText = total;--%>

        <%--    let total = 0;--%>
        <%--    let goodsList = document.querySelectorAll('input[name="goods"]:checked');--%>
        <%--    goodsList.forEach(function(goods) {--%>
        <%--        let goodsId = goods.value;--%>
        <%--        alert("${goodsId}");--%>
        <%--        let goodsPrice = document.querySelector('input[name="price-${goodsId}"]').value;--%>
        <%--        let quantity = document.querySelector('input[name="quantity-${goodsId}"]').value;--%>
        <%--        out.print(goodsPrice);--%>
        <%--        out.print(quantity);--%>
        <%--        total += goodsPrice * quantity;--%>
        <%--    });--%>
        <%--    document.querySelector('#total').innerText = total;--%>
        <%--}--%>

    </script>

</head>
<body >
<%--style=" background: url(img/page.png);background-size: cover;"--%>
<p> </p>
<h1>公司销售管理系统</h1>

<p> </p>
<a href="login.jsp">登录页面</a> <a href="regist.jsp">注册页面</a>
<p> </p>
<%
    User user = (User) session.getAttribute("user");
%>
<%
    List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
    List<User> salespersonList= (List<User>) request.getAttribute("salespersonList");
    List<Contract> contractList =(List<Contract>) request.getAttribute("contractList");

//    Goods[] goodsArray = goodsList.toArray(new Goods[0]);
%>
<c:if test="${empty user}">
    <p>您还没登录</p>
    <!-- 显示管理员相关页面 -->

    <%-- 用于显示所有商品的表格 --%>
    <h2>商品列表</h2>
    <table class="table table-bordered table-striped" border="1">
        <thead>
        <tr>
            <th>商品 ID</th>
            <th>商品名称</th>
            <th>价格</th>
            <th>描述</th>
            <th>库存</th>
                <%--                <th>操作</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${goodsList}" var="good">
            <tr>
                <td>${good.id}</td>
                <td>${good.name}</td>
                <td>${good.price}</td>
                <td>${good.description}</td>
                <td>${good.stock}</td>
                    <%--                    <td>--%>
                    <%--                        <a href="updateGoods.jsp?id=${good.id}">编辑</a>--%>
                    <%--                        <a href="deleteGoods.jsp?id=${good.id}">删除</a>--%>
                    <%--                    </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>合同列表</h2>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Customer ID</th>
            <th>Salesperson ID</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Purchase List ID</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% for (Contract contract : contractList) { %>
        <tr>
            <td><%= contract.getId() %></td>
            <td><%= contract.getCustomerId() %></td>
            <td><%= contract.getSalespersonId() %></td>
            <td><%= contract.getStartDate() %></td>
            <td><%= contract.getEndDate() %></td>
            <td><%= contract.getAmount() %></td>
            <td><%= contract.getStatus() %></td>
            <td><%= contract.getPurchaseListId() %></td>
            <td>
                <a href="edit_contract.jsp?id=<%= contract.getId() %>">Edit</a>
                <a href="delete_contract.jsp?id=<%= contract.getId() %>">Delete</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

<h2>录入合同</h2>

<form action="./contract/submit" method="post">
    <!-- 客户信息 -->
    <div>
        <label for="customer_id">客户ID:</label>
        <input type="text" id="customer_id" name="customer_id">
    </div>
    <div>
        <label for="salesperson_id">销售员ID:</label>
        <select id="salesperson_id" name="salesperson_id">
            <!-- 销售员选项列表 -->
            <!-- 从后端获取，遍历显示 -->
            <c:forEach var="salesperson" items="${salespersonList}">
                <option value="${salesperson.id}">${salesperson.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="start_date">开始日期:</label>
        <input type="date" id="start_date_" name="start_date">
    </div>
    <div>
        <label for="end_date">结束日期:</label>
        <input type="date" id="end_date_" name="end_date">
    </div>
    <div>
        <label for="status">状态:</label>
        <select id="status_" name="status">
            <option value="Signed">Signed</option>
            <option value="InProgress">InProgress</option>
            <option value="Completed">Completed</option>
        </select>
    </div>
    <!-- 商品信息 -->
    <table id="goods-table_">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>商品价格</th>
            <th>选择数量</th>
            <th>小计</th>
        </tr>
        </thead>
        <tbody>
        <!-- 循环遍历 goodsList，生成商品行 -->
        <% for (int i = 0; i < goodsList.size(); i++) { %>
        <tr>
            <td>
                <%= goodsList.get(i).getName() %>
            </td>
            <td>
                <%= goodsList.get(i).getPrice() %>
            </td>
            <td>
                <!-- 增加一个 checkbox，用来标识该商品是否被选中 -->
                <input type="checkbox" id="checkbox" name="selectedGoodsIndex"  class="goods-checkbox" value="<%= i %>" data-index="<%= i %>" onclick="calcTotal()" />
                <input type="number" id="number" name="goodsCount" class="goods-count" data-index="<%= i %>" value="0" min="0"  onchange="calcTotal()" />
            </td>
            <td class="goods-total" data-index="<%= i %>"></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <!-- 显示合同总价 -->
    <div>
        合同总价：<span id="total_">0</span>
    </div>
    <!-- 提交按钮 -->
    <div>
        <input type="submit" value="提交">
    </div>
</form>

</c:if>

<c:if test="${!empty user}">
    <p>欢迎登录： ${sessionScope.user.name}</p>
<%--    <a href="/logout" >注销</a>--%>
    <button id="logout-button">注销登录</button>
    <script>
        // 获取注销按钮
        const logoutButton = document.getElementById("logout-button");
        // 给注销按钮绑定点击事件
        logoutButton.addEventListener("click", function() {
            // 发送请求到后端进行注销登录
            fetch("./logout", {
                method: "POST"
            }).then(function(response) {
                // 如果请求成功，跳转到登录页面response.status === 200
                if (1==1) {
                    window.location.href = "./";
                }
            });
        });
    </script>
    <!-- 显示相关页面 -->
</c:if>
<c:if test="${sessionScope.user.type eq 1}">
<p>您是销售管理员用户</p>
<!-- 显示管理员相关页面 -->
</c:if>
<c:if test="${sessionScope.user.type eq 2}">
<p>您是仓库管理员</p>
<!-- 显示仓库管理员相关页面 -->
</c:if>
<c:if test="${sessionScope.user.type eq 0}">
<p>您是普通用户</p>
<!-- 显示普通用户相关页面 -->
<h2>录入合同</h2>

<form action="./contract/submit" method="post">

    <!-- 客户信息 -->
    <div>
        <label for="customer_id">客户ID:</label>
        <input type="text" id="customer_id_" name="customer_id" value="${salesperson.id}">

    </div>
    <div>
        <label for="salesperson_id">销售员ID:</label>

        <select id="_salesperson_id" name="salesperson_id">
            <!-- 销售员选项列表 -->
            <!-- 从后端获取，遍历显示 -->
            <c:forEach var="salesperson" items="${salespersonList}">
                <option value="${salesperson.id}">${salesperson.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="start_date">开始日期:</label>
        <input type="date" id="start_date" name="start_date">
    </div>
    <div>
        <label for="end_date">结束日期:</label>
        <input type="date" id="end_date" name="end_date">
    </div>
    <div>
        <label for="status">状态:</label>
        <select id="status" name="status">
            <option value="Signed">Signed</option>
            <option value="InProgress">InProgress</option>
            <option value="Completed">Completed</option>
        </select>
    </div>
    <!-- 商品信息 -->
    <table id="goods-table">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>商品价格</th>
            <th>选择数量</th>
            <th>小计</th>
        </tr>
        </thead>
        <tbody>
        <!-- 循环遍历 goodsList，生成商品行 -->
        <% for (int i = 0; i < goodsList.size(); i++) { %>
        <tr>
            <td>
                <%= goodsList.get(i).getName() %>
            </td>
            <td>
                <%= goodsList.get(i).getPrice() %>
            </td>
            <td>
                <!-- 增加一个 checkbox，用来标识该商品是否被选中 -->
                <input type="checkbox" class="goods-checkbox" data-index="<%= i %>" onclick="calcTotal()" />
                <input type="number" class="goods-count" data-index="<%= i %>" value="0" min="0"  onchange="calcTotal()" />
            </td>
            <td class="goods-total" data-index="<%= i %>"></td>
        </tr>
        <% } %>
        </tbody>
    </table>


    <!-- 显示合同总价 -->
    <div>
        合同总价：<span id="total">0</span>
    </div>


    <!-- 提交按钮 -->
    <div>
        <input type="submit" value="提交">
    </div>
</form>
    <%-- 用于显示所有商品的表格 --%>
    <a href="./refresh">刷新</a>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>商品 ID</th>
            <th>商品名称</th>
            <th>价格</th>
            <th>描述</th>
            <th>库存</th>
                <%--                <th>操作</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${goodsList}" var="good">
            <tr>
                <td>${good.id}</td>
                <td>${good.name}</td>
                <td>${good.price}</td>
                <td>${good.description}</td>
                <td>${good.stock}</td>
                    <%--                    <td>--%>
                    <%--                        <a href="updateGoods.jsp?id=${good.id}">编辑</a>--%>
                    <%--                        <a href="deleteGoods.jsp?id=${good.id}">删除</a>--%>
                    <%--                    </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
            <%-- 用于分页显示的分页栏 --%>

        <div class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="index.jsp?page=${i}" class="${page == i ? 'active' : ''}">${i}</a>
            </c:forEach>
        </div>
            <%-- 用于创建新商品的表单 --%>

        <form action="createGoods.jsp" method="post">
            <div class="form-group">
                <label for="name">商品名称</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="price">价格</label>
                <input type="number" class="form-control" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="description">描述</label>
                <textarea class="form-control" id="description" name="description"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">创建</button>
        </form>
<%--        接下来是分页显示的部分：--%>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>商品描述</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="good" items="${goods}">
                <tr>
                    <td>${good.name}</td>
                    <td>${good.price}</td>
                    <td>${good.description}</td>
                    <td>
                        <a href="updateGood?id=${good.id}" class="btn btn-primary btn-sm">修改</a>
                        <a href="deleteGood?id=${good.id}" class="btn btn-danger btn-sm">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

<%--    </c:if>--%>


</body>
</html>



<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%  response.sendRedirect("login.jsp");  %>--%>
