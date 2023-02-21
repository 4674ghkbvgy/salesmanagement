package com.dgut.servlet;
import com.dgut.dao.*;
import com.dgut.entity.*;

import java.io.IOException;
import java.io.Serial;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncreaseStockServlet extends HttpServlet {

    private final StockDaoImpl stockDao = new StockDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Stock stock = null;
        try {
            stock = stockDao.findStockById(goodsId);
            if (stock == null) {
                throw new IllegalArgumentException("找不到商品");
            } else {
                stock.setQuantity(stock.getQuantity() + quantity);
                stockDao.updateStock(stock);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": true}");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}