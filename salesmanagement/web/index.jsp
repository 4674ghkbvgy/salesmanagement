<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.dgut.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dgut.entity.Goods" %>
<%@ page import="com.dgut.entity.Contract" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.dgut.entity.PurchaseOrder" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.JsonArray" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="mycss/index.css?v=2">
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Index Page</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
    List<User> salespersonList = (List<User>) request.getAttribute("salespersonList");
    List<User> userList = (List<User>) request.getAttribute("userList");
    List<Contract> contractList = (List<Contract>) request.getAttribute("contractList");
//    String jsonData = (String)request.getAttribute("SalesByCustomer");
//    JsonObject salesByCustomer = new Gson().fromJson(jsonData, JsonObject.class);

    String jsonData = (String)request.getAttribute("SalesByCustomer");
    JsonArray salesByCustomer = new Gson().fromJson(jsonData, JsonArray.class);
//    Goods[] goodsArray = goodsList.toArray(new Goods[0]);
%>
<script type="text/javascript">

<%--    function submitOrder() {--%>
<%--        // 获取选中的商品数量和价格--%>
<%--        var totalQuantity = 0;--%>
<%--        var totalPrice = 0;--%>
<%--        <% for (int i = 0; i < goodsList.size(); i++) { %>--%>
<%--        var quantity_<%= i %> = parseInt(document.getElementById("quantity_<%= i %>").value);--%>
<%--        var price_<%= i %> = <%= goodsList.get(i).getPrice() %>;--%>
<%--        totalQuantity += quantity_<%= i %>;--%>
<%--        totalPrice += quantity_<%= i %> * price_<%= i %>;--%>
<%--        <% } %>--%>
<%--        // 构造表单并提交--%>
<%--        var form = document.createElement("form");--%>
<%--        form.action = "/process-payment"; // set the URL--%>
<%--        form.method = "POST"; // set the HTTP method--%>

<%--        var input1 = document.createElement("input");--%>
<%--        input1.type = "hidden";--%>
<%--        input1.name = "contractId";--%>
<%--        input1.value = "123"; // replace with the actual contract ID--%>
<%--        form.appendChild(input1);--%>

<%--        var selectedItems = [1, 3, 5]; // replace with an array of selected item IDs--%>
<%--        for (var i = 0; i < selectedItems.length; i++) {--%>
<%--            var input2 = document.createElement("input");--%>
<%--            input2.type = "checkbox";--%>
<%--            input2.name = "selectedItems";--%>
<%--            input2.value = selectedItems[i];--%>
<%--            form.appendChild(input2);--%>
<%--        }--%>

<%--        var submitButton = document.createElement("button");--%>
<%--        submitButton.type = "submit";--%>
<%--        submitButton.textContent = "Pay Now";--%>
<%--        form.appendChild(submitButton);--%>

<%--        document.body.appendChild(form);--%>
<%--    }--%>
</script>

<script>
    $(document).ready(function () {
        var jsonData = '${SalesByCustomer}'; // 获取JSON数据
        var data = JSON.parse(jsonData); // 将JSON字符串转换为JavaScript对象

        // 获取表格并清空
        var table = document.getElementById("myTable1");
        table.innerHTML = "";

        // 在表格中添加表头
        var header = table.createTHead();
        var row = header.insertRow(0);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        cell1.innerHTML = "<b>Customer Name</b>";
        cell2.innerHTML = "<b>Sales Amount</b>";

        // 将数据插入到表格中
        for (var i = 0; i < data.length; i++) {
            var row = table.insertRow(i + 1);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            cell1.innerHTML = data[i].name;
            cell2.innerHTML = data[i].sales;
        }
    // });
    // $(document).ready(function () {

        var jsonData2 = '${SalesByGoods}'; // 获取JSON数据
        var data2 = JSON.parse(jsonData2); // 将JSON字符串转换为JavaScript对象

        // 获取表格并清空
        var table = document.getElementById("myTable2");
        table.innerHTML = "";
        // 在表格中添加表头
        var header = table.createTHead();
        var row = header.insertRow(0);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        cell1.innerHTML = "<b>Goods Name</b>";
        cell2.innerHTML = "<b>Sales Amount</b>";

        // 将数据插入到表格中
        for (var i = 0; i < data2.length; i++) {
            var row = table.insertRow(i + 1);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            cell1.innerHTML = data2[i].name;
            cell2.innerHTML = data2[i].sales;
        }
    });
    $(document).ready(function () {
        var jsonData = '${Unpaid}'; // 获取JSON数据
        var data = JSON.parse(jsonData); // 将JSON字符串转换为JavaScript对象

        // 获取表格并清空
        var table = document.getElementById("unpaidTable");
        table.innerHTML = "";

        // 在表格中添加表头
        var header = table.createTHead();
        var row = header.insertRow(0);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        cell1.innerHTML = "<b>商品id</b>";
        cell2.innerHTML = "<b>待支付数量</b>";

        // 将数据插入到表格中
        for (var i = 0; i < data.length; i++) {
            var row = table.insertRow(i + 1);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            cell1.innerHTML = data[i].id;
            cell2.innerHTML = data[i].stock;
        }

    });
</script>


<%--style=" background: url(img/page.png);background-size: cover;"--%>
<p></p>
<h1>公司销售管理系统</h1>



<p></p>
<a href="login.jsp">登录页面</a> <a href="regist.jsp">注册页面</a>
<p></p>

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
            <td><%= contract.getId() %>
            </td>
            <td><%= contract.getCustomerId() %>
            </td>
            <td><%= contract.getSalespersonId() %>
            </td>
            <td><%= contract.getStartDate() %>
            </td>
            <td><%= contract.getEndDate() %>
            </td>
            <td><%= contract.getAmount() %>
            </td>
            <td><%= contract.getStatus() %>
            </td>
            <td><%= contract.getPurchaseListId() %>
            </td>
            <td>
                <a href="edit_contract.jsp?id=<%= contract.getId() %>">Edit</a>
                <a href="delete_contract.jsp?id=<%= contract.getId() %>">Delete</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>


<%--    <h2>录入合同</h2>--%>

<%--    <form action="./contract/submit" method="post">--%>
<%--        <!-- 客户信息 -->--%>
<%--        <div>--%>
<%--            <label for="customer_id">客户ID:</label>--%>
<%--            <input type="text" id="customer_id_" name="customer_id">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="salesperson_id">销售员ID:</label>--%>
<%--            <select id="salesperson_id_" name="salesperson_id">--%>
<%--                <!-- 销售员选项列表 -->--%>
<%--                <!-- 从后端获取，遍历显示 -->--%>
<%--                <c:forEach var="salesperson" items="${salespersonList}">--%>
<%--                    <option value="${salesperson.id}">${salesperson.name}</option>--%>
<%--                </c:forEach>--%>
<%--            </select>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="start_date">开始日期:</label>--%>
<%--            <input type="date" id="start_date_" name="start_date">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="end_date">结束日期:</label>--%>
<%--            <input type="date" id="end_date_" name="end_date">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <label for="status">状态:</label>--%>
<%--            <select id="status_" name="status">--%>
<%--                <option value="Signed">Signed</option>--%>
<%--                <option value="InProgress">InProgress</option>--%>
<%--                <option value="Completed">Completed</option>--%>
<%--            </select>--%>
<%--        </div>--%>
<%--        <!-- 商品信息 -->--%>
<%--        <table id="goods-table_">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>商品名称</th>--%>
<%--                <th>商品价格</th>--%>
<%--                <th>选择数量</th>--%>
<%--                <th>小计</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <!-- 循环遍历 goodsList，生成商品行 -->--%>
<%--            <% for (int i = 0; i < goodsList.size(); i++) { %>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <%= goodsList.get(i).getName() %>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <%= goodsList.get(i).getPrice() %>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <!-- 增加一个 checkbox，用来标识该商品是否被选中 -->--%>
<%--                    <input type="checkbox" id="checkbox_" name="selectedGoodsIndex" class="goods-checkbox"--%>
<%--                           value="<%= i %>" data-index="<%= i %>" onclick="calcTotal()"/>--%>
<%--                    <input type="number" id="number_" name="goodsCount" class="goods-count" data-index="<%= i %>"--%>
<%--                           value="0" min="0" onchange="calcTotal()"/>--%>
<%--                </td>--%>
<%--                <td class="goods-total" data-index="<%= i %>"></td>--%>
<%--            </tr>--%>
<%--            <% } %>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--        <!-- 显示合同总价 -->--%>
<%--        <div>--%>
<%--            合同总价：<span id="total_">0</span>--%>
<%--        </div>--%>
<%--        <!-- 提交按钮 -->--%>
<%--        <div>--%>
<%--            <input type="submit" value="提交">--%>
<%--        </div>--%>
<%--    </form>--%>

</c:if>

<c:if test="${!empty user}">

    <p>${sessionScope.user.name}，欢迎回来！ </p>
    <%--    <a href="/logout" >注销</a>--%>
    <button id="logout-button">注销登录</button>
    <script>
        // 获取注销按钮
        const logoutButton = document.getElementById("logout-button");
        // 给注销按钮绑定点击事件
        logoutButton.addEventListener("click", function () {
            // 发送请求到后端进行注销登录
            fetch("./logout", {
                method: "POST"
            }).then(function (response) {
                // 如果请求成功，跳转到登录页面response.status === 200
                // if () {
                    window.location.href = "./";
                // }
            });
        });
    </script>
    <!-- 显示相关页面 -->
</c:if>
<c:if test="${sessionScope.user.type eq 1}">
    <p>您是销售管理员用户</p>
    <!-- 显示管理员相关页面 -->

    <h2>不同客户的销售额</h2>
    <table id="myTable1">
    </table>

    <h2>不同商品的销售额</h2>
    <table id="myTable2">
    </table>


    <h2>User List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Type</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.type}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.phone}"/></td>
                <td><c:out value="${user.address}"/></td>
                <td>
<%--                    <form method="POST" action="users?action=edit">--%>
<%--                        <input type="hidden" name="id" value="${user.id}">--%>
<%--                    </form>--%>
                    <form method="POST" action="users?action=delete">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add/Edit User</h2>
    <form method="POST" action="users?action=edit">
<%--        <c:if test="${not empty user.id}">--%>
            <%--@declare id="password"--%><%--@declare id="type"--%><%--@declare id="email"--%><%--@declare id="phone"--%><%--@declare id="address"--%>
<%--            <input type="hidden" name="id" value="${user.id}">--%>
<%--        </c:if>--%>
    <%--@declare id="id"--%>
    <label for="id">ID:</label>
        <input type="text" name="id" value="${user.id}">
        <label for="name">Name:</label>
        <input type="text" name="name" value="${user.name}">
        <br>
        <label for="password">Password:</label>
        <input type="password" name="password" value="${user.password}">
        <br>
        <label for="type">Type:</label>
        <input type="text" name="type" value="${user.type}">
        <br>
        <label for="email">Email:</label>
        <input type="email" name="email" value="${user.email}">
        <br>
        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="${user.phone}">
        <br>
        <label for="address">Address:</label>
        <input type="text" name="address" value="${user.address}">
        <br>
        <input type="submit" value="edit">
    </form>

    <h2>添加新商品</h2>
    <form accept-charset="UTF-8" action="createGoods.jsp" method="post">
        <div class="form-group">
            <label for="name">商品名称</label>
            <input type="text" class="form-control" id="name1" name="name" required>
        </div>
        <div class="form-group">
            <label for="price">价格</label>
            <input type=type="number" step="0.01"class="form-control" id="price1" name="price" required>
        </div>
        <div class="form-group">
            <label for="description">描述</label>
            <textarea class="form-control" id="description1" name="description"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">创建</button>
    </form>

</c:if>
<c:if test="${sessionScope.user.type eq 2}">
    <p>您是仓库管理员</p>
    <!-- 显示仓库管理员相关页面 -->
    <h2>历史进货单</h2>
    <table id="purchase-order" class="table">
        <thead>
        <tr>
            <th>商品编号</th>
            <th>商品名称</th>
            <th>进货数量</th>
            <th>进货日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <% for (PurchaseOrder po :  (List<PurchaseOrder>) request.getAttribute("PurchaseOrderList")) { %>
        <tr>
            <td><%= po.getProductId() %></td>
            <td><%= goodsList.get(po.getProductId()).getName() %></td>
            <td><%= po.getQuantity() %></td>
            <td><%= po.getPurchaseDate() %></td>
            <td>
                <button class="btn btn-primary btn-sm" onclick="increaseStock(<%= po.getId() %>, <%= po.getQuantity() %>)">增加库存</button>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <h2>提交进货单</h2>
    <form accept-charset="UTF-8" action="./createOrder" method="post">
        <label>商品ID：</label>
        <input type="text" name="product_id" />
        <label>进货数量：</label>
        <input type="text" name="quantity" />
        <label>进货日期：</label>
        <input type="date" name="purchase_date" />
        <input type="submit" value="提交" />
    </form>
    <h2>添加新商品</h2>
    <form accept-charset="UTF-8" action="createGoods.jsp" method="post">
        <div class="form-group">
            <label for="name">商品名称</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="price">价格</label>
            <input type=type="number" step="0.01"class="form-control" id="price" name="price" required>
        </div>
        <div class="form-group">
            <label for="description">描述</label>
            <textarea class="form-control" id="description" name="description"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">创建</button>
    </form>
</c:if>
<c:if test="${sessionScope.user.type eq 0}">
    <p>您是普通用户</p>
    <%
        List<Contract> contractUserList = (List<Contract>) session.getAttribute("contractUserList");
    %>
    <!-- 显示普通用户相关页面 -->


    <h2>您签订的合同列表</h2>
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
        <c:if test="${not empty contractUserList}">
            <% for (Contract contract : contractUserList) { %>
            <tr>
                <td><%= contract.getId() %>
                </td>
                <td><%= contract.getCustomerId() %>
                </td>
                <td><%= contract.getSalespersonId() %>
                </td>
                <td><%= contract.getStartDate() %>
                </td>
                <td><%= contract.getEndDate() %>
                </td>
                <td><%= contract.getAmount() %>
                </td>
                <td><%= contract.getStatus() %>
                </td>
                <td><%= contract.getPurchaseListId() %>
                </td>
                <td>
                    <a href="edit_contract.jsp?id=<%= contract.getId() %>">Edit</a>
                    <a href="delete_contract.jsp?id=<%= contract.getId() %>">Delete</a>
                </td>
            </tr>
            <% } %>
        </c:if>
        </tbody>
    </table>

    <h2>录入合同</h2>

    <form action="./contract/submit" method="post">
        <!-- 客户信息 -->
        <div>
            <label for="customer_id">客户ID:</label>
            <input type="text" id="customer_id" name="customer_id" value="${sessionScope.user.id}">
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
                    <input type="checkbox" id="checkbox" name="selectedGoodsIndex" class="goods-checkbox"
                           value="<%= i %>" data-index="<%= i %>" onclick="calcTotal()"/>
                    <input type="number" id="number" name="goodsCount" class="goods-count" data-index="<%= i %>"
                           value="0" min="0" onchange="calcTotal()"/>
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



    <h2>合同的多次支付</h2>
    <form action="./unpaid" method="get">
    <div>
        <select id="contractsSelect" name="contractsSelect">
            <option value="-1">请选择合同</option>
            <c:forEach items="${contractList}" var="contract">
                <option value="${contract.id}">${contract.id}</option>
            </c:forEach>
        </select>
        <input type="submit" value="显示商品清单">
    </div>
    </form>

    <h2>待支付的清单</h2>
    <table id="unpaidTable">
    </table>


    <%
        Map<Integer, Integer> goodsMap = (Map<Integer, Integer>) request.getAttribute("goodsMap");
    %>
    <form action="./payment" method="post">
        <div>
            <label for="payment_contract_id">合同ID:</label>
            <select id="payment_contract_id" name="payment_contract_id">
                <option value="-1">请选择合同</option>
                <c:forEach items="${contractList}" var="contract">
                    <option value="${contract.id}">${contract.id}</option>
                </c:forEach>
            </select>
        </div>
        <!-- 商品信息 -->
        <table id="payment-table">
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
                    <input type="checkbox" id="payment_checkbox" name="selectedPaymentsIndex" class="goods-checkbox"
                           value="<%= i %>" data-index="<%= i %>" onclick="calcTotal()"/>
                    <input type="number" id="payment_number" name="paymentsCount" class="goods-count" data-index="<%= i %>"
                           value="0" min="0" onchange="calcTotal()"/>
                </td>
                <td class="goods-total" data-index="<%= i %>"></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <!-- 提交按钮 -->
        <div>
            <input type="submit" value="提交">
        </div>
    </form>

</c:if>

<%-- 用于创建新商品的表单 --%>





<%--<table id="contract-goods">--%>
<%--    <thead>--%>
<%--&lt;%&ndash;    <jsp:useBean id="goodsMap" scope="request" type="java.util.Map"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    <jsp:useBean id="goodsId" scope="request" type="java.util.List"/>&ndash;%&gt;--%>
<%--    <tr>--%>
<%--        <th>商品名称</th>--%>
<%--        <th>数量</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${goodsMap}" var="goodsId">--%>
<%--        <tr>--%>
<%--            <td>${goodsMap[goodsId].name}</td>--%>
<%--            <td>${goodsMap[goodsId]}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>

<%--    </thead>--%>
<%--    <tbody>--%>
<%--    </tbody>--%>
<%--</table>--%>
<%
    Map<Integer, Integer> goodsMap = (Map<Integer, Integer>) request.getAttribute("goodsMap");
%>
<script>
    $(document).ready(function () {
        // 显示合同商品清单
        $('#btn-show-contract-goods').click(function () {
            var contractId = $('#contractsSelect').val();
            if (contractId == -1) {
                alert('请选择合同');
                return;
            }
            var goodsMap = <%= goodsMap %>;
            var $tbody = $('#contract-goods tbody');
            $tbody.empty();
            for (var goodsId in goodsMap) {
                var quantity = goodsMap[goodsId];
                // 在表格中添加一行，包含商品名称和数量
                $tbody.append($('<tr>').append($('<td>').text(goodsMap[goodsId].name)).append($('<td>').text(quantity)));
            }
            // 向后端请求合同商品清单
            <%--$.ajax({--%>
            <%--    url: './api/contracts/' + contractId,--%>
            <%--    method: 'GET',--%>
            <%--    dataType: 'json',--%>
            <%--    success: function (data) {--%>
            //                             Map<Integer, Integer> goodsMap = (Map<Integer, Integer> request.getAttribute("goodsMap");
            <%--        var goodsMap = data.goodsMap;--%>
            <%--        var $tbody = $('#contract-goods tbody');--%>
            <%--        $tbody.empty();--%>
            <%--        for (var goodsId in goodsMap) {--%>
            <%--            var quantity = goodsMap[goodsId];--%>
            <%--            // 在表格中添加一行，包含商品名称和数量--%>
            <%--            $tbody.append($('<tr>').append($('<td>').text(goodsMap[goodsId].name)).append($('<td>').text(quantity)));--%>
            <%--        }--%>
            <%--    },--%>
            <%--    error: function (jqXHR, textStatus, errorThrown) {--%>
            <%--        alert('获取商品清单失败: ' + errorThrown);--%>
            <%--    }--%>
            // });
        });

    });

</script>
<%--    </c:if>--%>

</body>
</html>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%  response.sendRedirect("login.jsp");  %>--%>
