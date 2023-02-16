package com.dgut.servlet;

import com.dgut.dao.GoodsDaoImpl;
import com.dgut.dao.UserDao;
import com.dgut.entity.Goods;
import com.dgut.entity.User;

import java.io.IOException;
import java.io.Serial;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshGoodsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GoodsDaoImpl goodsDao = new GoodsDaoImpl();
        List<Goods> goodsList = goodsDao.findAll();
        request.setAttribute("goodsList", goodsList);

        UserDao userDao= new UserDao();
        List<User> salespersonList = userDao.findAllSalesperson();  // 从数据库或其他地方获取销售员信息
        request.setAttribute("salespersonList", salespersonList);
//        request.getRequestDispatcher("./index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}