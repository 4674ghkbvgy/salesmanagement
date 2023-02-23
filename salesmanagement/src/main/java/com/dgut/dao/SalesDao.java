package com.dgut.dao;

import com.dgut.entity.Contract;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;
import com.dgut.entity.User;
import com.dgut.util.MyUtil;
import org.mariadb.jdbc.Statement;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesDao {
    private final Connection connection;

    public SalesDao() {
        this.connection = MyUtil.getConnection();
    }

    // 查询不同客户的销售额
    public Map<String, Double> getSalesByCustomer() throws SQLException {
        Map<String, Double> salesByCustomer = new HashMap<>();

        try (Statement statement = (Statement) connection.createStatement()) {
            String query = "SELECT users.name, SUM(contract.amount) AS total_sales " +
                    "FROM contract " +
                    "INNER JOIN users ON contract.customer_id = users.id " +
                    "GROUP BY users.name";

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String customerName = resultSet.getString("name");
                Double totalSales = resultSet.getDouble("total_sales");
                salesByCustomer.put(customerName, totalSales);
            }
        }

        return salesByCustomer;
    }
    public Map<String, Double> getSalesByGoods() throws SQLException {
        Map<String, Double> salesByCustomer = new HashMap<>();

        try (Statement statement = (Statement) connection.createStatement()) {
            String query = "SELECT goods.name, SUM(purchase_list_item.quantity * goods.price) AS sales_amount " +
                    "FROM purchase_list_item " +
                    "JOIN goods ON purchase_list_item.goods_id = goods.id " +
                    "GROUP BY goods.name";

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String customerName = resultSet.getString("name");
                Double totalSales = resultSet.getDouble("sales_amount");
                salesByCustomer.put(customerName, totalSales);
            }
        }
        return salesByCustomer;
    }



}

