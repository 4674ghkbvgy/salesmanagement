package com.dgut.servlet;

import com.dgut.dao.ContractDaoImpl;
import com.dgut.dao.UserDao;
import com.dgut.entity.Contract;
import com.dgut.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "user", urlPatterns = "/user")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        this.login(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取账号和密码
        String userName = req.getParameter("uname");
        String passWord = req.getParameter("password");
        HttpSession session = req.getSession();


        UserDao dao = new UserDao();

        boolean result = dao.selectName(userName);
        if(result){
            User user= dao.getUserByUsername(userName);
            String realPassword=user.getPassword();
            if (Objects.equals(realPassword, passWord)) {
                if (session.isNew()) {
                    session.setAttribute("user", user);
                }
                req.getSession().setAttribute("user", user);

                ContractDaoImpl contractDaoImpl =new ContractDaoImpl();
                List<Contract> contractList = contractDaoImpl.findByUser(user);
                req.getSession().setAttribute("contractUserList", contractList);

                resp.sendRedirect("index.jsp");
            } else {
                //账号密码不匹配，返回登录页面
                req.setAttribute("loginError", "账号密码不匹配");
                req.setAttribute("username", userName);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }else {
            //登录失败
            req.setAttribute("loginError", "用户名不存在");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);

        }

    }
}