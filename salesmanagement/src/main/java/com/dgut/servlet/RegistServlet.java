package com.dgut.servlet;

import com.dgut.dao.UserDao;
import com.dgut.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {

	@Serial
	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if(username==null||username.trim().isEmpty()){
			request.setAttribute("registError", "用户名不能为空");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}
		if(password==null||password.trim().isEmpty()){
			request.setAttribute("registError", "密码不能为空");
			request.setAttribute("username", username);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}
		if(!password.equals(password2)){
			request.setAttribute("registError", "密码不一致");
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}

		UserDao dao = new UserDao();
		User user= new User();
		user.setName(username);
		user.setPassword(password);
		if(dao.selectName(username)){
            request.setAttribute("registError", "注册失败，该用户名已存在");
            request.getRequestDispatcher("regist.jsp").forward(request, response);
        }else {
            if(dao.insertUser(user)){
                response.sendRedirect("login.jsp");
            }else {
                request.setAttribute("registError", "注册失败，发生未知错误");
                request.getRequestDispatcher("regist.jsp").forward(request, response);
            }
        }
        }

	}


