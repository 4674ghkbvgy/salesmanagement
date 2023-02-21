package com.dgut.dao;


import com.dgut.entity.PurchaseListItem;
import com.dgut.entity.PurchaseOrder;
import com.dgut.util.MyUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;



public class PurchaseOrderDao {

    public int insertPurchaseOrder(int goodsId, int quantity, Date purchaseDate) throws SQLException {
        int id = -1;

        String sql = "INSERT INTO purchase_order (goods_id, quantity, purchase_date) VALUES (?, ?, ?)";
        try (Connection conn = MyUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, goodsId);
            ps.setInt(2, quantity);
            ps.setDate(3, new java.sql.Date(purchaseDate.getTime()));
            ps.executeUpdate();

            // 获取插入记录的自动生成id
            try (var rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        }
        return id;
    }
    public List<PurchaseOrder> findAll() throws SQLException {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        try (Connection conn = MyUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM purchase_order")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int goodsId = rs.getInt("goods_id");
                int quantity = rs.getInt("quantity");
                Date purchaseDate = rs.getDate("purchase_date");

                PurchaseOrder purchaseOrder = new PurchaseOrder(id, goodsId, quantity, purchaseDate);
                purchaseOrders.add(purchaseOrder);
            }
        }

        return purchaseOrders;
    }


}
