<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>注册页面</title>
  </head>
  <body>
    <h2 style="text-align: center">注册页面</h2>
    <div style="text-align: center">
      <form action="./regist" method="post" id="loginForm">
        姓名<input type="text" name="uname" id="uname" value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/><br />
        密码<input type="text" name="password" id="password" value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"/><br />
        再次输入密码<input type="text" name="password2" id="password2" /><br />
        <button type="button" id="loginBtn">regist</button>
        <br />
        <span id="msg" style="font-size: 16px; color: coral"></span>
        <span class="errorMsg">
	      <%=request.getAttribute("registError")==null?"":request.getAttribute("registError") %>
        </span>
      </form>
    </div>
  </body>
</html>

<body>
  <%@ page pageEncoding="UTF-8"%>
  <script type="text/javascript" src="js/jquery-3.6.1.js"></script>

  <script type="text/javascript">
    $("#loginBtn").click(function (e) {
      var uname = $("#uname").val();
      var password = $("#password").val();
      var password2 = $("#password").val();
      if (isEmpty(uname)) {
        $("#msg").html("用户名为空");
        return;
      }
      if (isEmpty(password)) {
        $("#msg").html("密码为空");
        return;
      }
      $("#loginForm").submit();
    });

    function isEmpty(str) {
      const str1 = str.toString();
      if (str1 == null || str1.trim() == "") return true;
    }
  </script>
</body>
