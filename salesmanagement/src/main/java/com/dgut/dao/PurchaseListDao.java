package com.dgut.dao;

import com.dgut.entity.PurchaseListItem;
import com.dgut.util.MyUtil;

import static com.dgut.util.MyUtil.getConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseListDao {
    private Connection connection;

    public PurchaseListDao() {
        this.connection = getConnection();
    }

    /**
     * Get all purchase list items for a given purchase list id.
     *
     * @param purchaseListId id of the purchase list
     * @return a list of PurchaseListItem objects
     * @throws SQLException if an error occurs while accessing the database
     */
    public List<PurchaseListItem> getPurchaseListItemsByPurchaseListId(int purchaseListId) throws SQLException {
        List<PurchaseListItem> purchaseListItems = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT goods_id, quantity FROM purchase_list_item WHERE purchase_list_id = ?")) {
            statement.setInt(1, purchaseListId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int goodsId = resultSet.getInt("goods_id");
                int quantity = resultSet.getInt("quantity");


                PurchaseListItem item = new PurchaseListItem(purchaseListId,goodsId, quantity);
                purchaseListItems.add(item);
            }
        }

        return purchaseListItems;
    }

    public PurchaseListItem getPurchaseListItemByPurchaseListIdAndGoodsId(int purchaseListId, int goodsId) {
        List<PurchaseListItem> purchaseListItems = new ArrayList<>();
        PurchaseListItem item=null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT goods_id, quantity FROM purchase_list_item WHERE purchase_list_id = ? and goods_id = ?")) {
            statement.setInt(1, purchaseListId);
            statement.setInt(2, goodsId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int quantity = resultSet.getInt("quantity");

                item = new PurchaseListItem(purchaseListId,goodsId, quantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    //只为支付在使用
    public int insertPurchaseListItem(List<PurchaseListItem> purchaseListItems) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        int purchaseListId=-1;
        try {
            connection = getConnection();
            // 启动事务
            connection.setAutoCommit(false);
            // 创建合同

            // 创建采购清单
            String sql2 = "insert into purchase_list () values ()";
            pstmt2 = connection.prepareStatement(sql2, org.mariadb.jdbc.Statement.RETURN_GENERATED_KEYS);
            pstmt2.executeUpdate();
            rs = pstmt2.getGeneratedKeys();

            if (rs.next()) {
                purchaseListId = rs.getInt(1);
                    // 创建采购清单项目
                    for (PurchaseListItem purchaseListItem : purchaseListItems) {
                        String sql3 = "insert into purchase_list_item (purchase_list_id, goods_id, quantity) values (?,?,?)";
                        pstmt2 = connection.prepareStatement(sql3);
                        pstmt2.setInt(1, purchaseListId);
                        pstmt2.setInt(2, purchaseListItem.getGoodsId());
                        pstmt2.setInt(3, purchaseListItem.getQuantity());
                        pstmt2.executeUpdate();
                    }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            throw new SQLException("创建支付清单失败！");
        } finally {
            MyUtil.close(connection, pstmt2);
        }
        return purchaseListId;
    }
}
