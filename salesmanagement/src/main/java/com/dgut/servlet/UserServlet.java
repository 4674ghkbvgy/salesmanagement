package com.dgut.servlet;
import com.dgut.dao.UserDao;
import com.dgut.entity.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            // 处理编辑操作
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            int type = Integer.parseInt(request.getParameter("type"));
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            User user = new User(id, name, password, type, email, phone, address);
            userDao.updateUser(user);

            response.sendRedirect("index.jsp");
        } else if ("delete".equals(action)) {
            // 处理删除操作
            int id = Integer.parseInt(request.getParameter("id"));
            userDao.deleteUser(id);

            response.sendRedirect("index.jsp");
        } else if ("add".equals(action)) {
            // 处理添加操作
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            int type = Integer.parseInt(request.getParameter("type"));
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            User user = new User(name, password, type, email, phone, address);
            userDao.insertUser(user);

            response.sendRedirect("index.jsp");
        } else {
            // 处理其他操作或无效操作
            response.sendRedirect("index.jsp");
        }
    }

}
