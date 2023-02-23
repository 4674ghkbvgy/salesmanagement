package com.dgut.dao;

import com.dgut.entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dgut.entity.Stock;
import com.dgut.util.MyUtil;

public class GoodsDaoImpl implements GoodsDao {
    private Connection connection;

    public GoodsDaoImpl() {
        this.connection = MyUtil.getConnection();;
    }

    @Override
    public List<Goods> findAll() {
        List<Goods> goodsList = new ArrayList<>();
        try {
            // 查询商品和对应的库存信息
            String sql = "SELECT g.*, s.quantity AS stock_quantity FROM goods g LEFT JOIN stock s ON g.id = s.product_id";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setPrice(rs.getDouble("price"));
                goods.setDescription( rs.getString("description"));
                // 设置库存
                goods.setStock(rs.getInt("stock_quantity"));
                goodsList.add(goods);
            }

//            String sql = "SELECT * FROM goods";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                double price = resultSet.getDouble("price");
//                String description = resultSet.getString("description");
//                goodsList.add(new Goods(id, name, price, description));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public Goods findById(int id) {
        Goods goods = null;
        try {
            String sql = "SELECT * FROM goods WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                goods = new Goods(id, name, price, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public int create(Goods goods) {
        int result = 0;
        try {
            String sql = "INSERT INTO goods (name, price, description) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, connection.createStatement().RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setDouble(2, goods.getPrice());
            preparedStatement.setString(3, goods.getDescription());
            result = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                Stock stock = new Stock();
                stock.setProductId(id);
                stock.setQuantity(0);
                StockDaoImpl stockDao = new StockDaoImpl();
                result = stockDao.addStock(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Goods goods) {
        PreparedStatement preparedStatement = null;
        int row = 0;
        try {
            String sql = "UPDATE goods SET name=?, price=?, description=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setDouble(2, goods.getPrice());
            preparedStatement.setString(3, goods.getDescription());
            preparedStatement.setInt(4, goods.getId());
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delete(int id) {
        PreparedStatement preparedStatement = null;
        int row = 0;
        try {
            String sql = "DELETE FROM goods WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }


    public void decreaseStock(int goodsId, int quantity) throws SQLException {
        String sql = "UPDATE stock SET quantity = quantity - ? WHERE product_id = ? AND quantity >= ?";
        try (Connection conn = MyUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, goodsId);
            stmt.setInt(3, quantity);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Failed to decrease stock for goods id " + goodsId);
            }
        }
    }
}
