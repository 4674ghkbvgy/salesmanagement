package com.dgut.dao;

import com.dgut.entity.Stock;
import com.dgut.entity.User;
import com.dgut.util.MyUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao {

    @Override
    public int addStock(Stock stock) {
        // 建立数据库连接
        Connection conn = MyUtil.getConnection();
        // 定义SQL语句
        String sql = "insert into stock(product_id, quantity) values(?, ?)";
        // 创建预处理对象
        PreparedStatement pstmt = null;
        // 定义影响行数
        int row = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            // 设置占位符的值
            pstmt.setInt(1, stock.getProductId());
            pstmt.setInt(2, stock.getQuantity());
            // 执行SQL语句
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭预处理对象
            // 关闭数据库连接
            MyUtil.close(conn, pstmt);
        }
        return row;
    }

    @Override
    public boolean updateStock(Stock stock) {
        // 建立数据库连接
        Connection conn = MyUtil.getConnection();
        // 定义SQL语句
        String sql = "update stock set product_id=?, quantity=? where id=?";
        // 创建预处理对象
        PreparedStatement pstmt = null;
        // 定义影响行数
        int row = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            // 设置占位符的值
            pstmt.setInt(1, stock.getProductId());
            pstmt.setInt(2, stock.getQuantity());
            pstmt.setInt(3, stock.getId());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int deleteStock(int id) throws SQLException {
        String sql = "DELETE FROM stock WHERE id=?";
        try (Connection conn = MyUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }

    public Stock findStockById(int id) throws SQLException {
        String sql = "SELECT * FROM stock WHERE id=?";
        try (Connection conn = MyUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Stock(
                            rs.getInt("id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")
                    );
                } else {
                    return null;
                }
            }
        }
    }
    public Stock findStockByPId(int id) throws SQLException {
        String sql = "SELECT * FROM stock WHERE product_id=?";
        try (Connection conn = MyUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Stock(
                            rs.getInt("id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")
                    );
                } else {
                    return null;
                }
            }
        }
    }
    public List<Stock> findAllStock() throws SQLException {
        String sql = "SELECT * FROM stock";
        try (Connection conn = MyUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<Stock> stocks = new ArrayList<>();
            while (rs.next()) {
                stocks.add(new Stock(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                ));
            }
            return stocks;
        }
    }

}





