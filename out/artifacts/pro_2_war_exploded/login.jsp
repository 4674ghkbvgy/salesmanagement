<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>登陆页面</title>
  </head>
  <body>
    <h2 style="text-align: center">Hello World!</h2>
    <div style="text-align: center">
      <form action="./login" method="post" id="loginForm">
        姓名<input type="text" name="uname" id="uname" /><br />
        密码<input type="text" name="password" id="password" /><br />
        <button type="button" id="loginBtn">login</button>
        <br />
        <span id="msg" style="font-size: 16px; color: coral"></span>
        <span class="errorMsg">
            <%=request.getAttribute("loginError")==null?"":request.getAttribute("loginError") %>
        </span><br/>
          还没有注册帐号？ <br/>
          <a href="regist.jsp">注册</a>
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
