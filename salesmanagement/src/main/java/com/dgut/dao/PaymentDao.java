package com.dgut.dao;
import com.dgut.entity.Contract;
import com.dgut.entity.Goods;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dgut.entity.Payment;
import com.dgut.entity.Stock;
import com.dgut.util.MyUtil;

import static com.dgut.util.MyUtil.getConnection;

public class PaymentDao {
    private final Connection conn;

    public PaymentDao() {
        this.conn = MyUtil.getConnection();
    }


//    public void insertPayment(Map<Integer, Integer> goodsMap,int contractId, int purchaseListId, double amount, Date paymentDate) throws SQLException {
//
//        try {
//            conn.setAutoCommit(false);
//            ContractDaoImpl contractDao = new ContractDaoImpl();
//            // Check if contract and purchase list belong to the same user
////            Contract contract = contractDao.findById(contractId) ;
////            PurchaseList purchaseList = getPurchaseListById(purchaseListId);
////            if (contract.getCustomerId() != purchaseList.getCustomerId()) {
////                throw new IllegalArgumentException("Contract and purchase list do not belong to the same customer.");
////            }
//            // Check if payment amount is within contract amount
////            double totalAmount = getContractAmountById(contractId);
////            double paidAmount = getAmountPaidByContractId(contractId);
////            if (paidAmount + amount > totalAmount) {
////                throw new IllegalArgumentException("Payment amount exceeds contract total amount.");
////            }
//            // Insert payment record
//            try (PreparedStatement stmt = conn.prepareStatement(
//                    "INSERT INTO payment (contract_id, purchase_list_id, amount, payment_date) VALUES (?, ?, ?, ?)")) {
//                stmt.setInt(1, contractId);
//                stmt.setInt(2, purchaseListId);
//                stmt.setDouble(3, amount);
//                stmt.setObject(4, paymentDate);
//                stmt.executeUpdate();
//            }
//            // Create new purchase list if all items have been paid for
//            if (checkPurchaseListItems(contractId,purchaseListId,goodsMap)) {
//                int newPurchaseListId = insertNewPurchaseList(purchaseList.getCustomerId());
//                setContractPurchaseListId(contractId, newPurchaseListId);
//            }
//            conn.commit();
//        } catch (SQLException e) {
//            conn.rollback();
//            throw e;
//        } finally {
//            conn.setAutoCommit(true);
//            conn.close();
//        }
//    }


    //应该移到PurchaseListItemsDao
    public boolean insertPurchaseListItems(int purchaseListId, Map<Integer, Integer> goodsMap) throws SQLException {
        boolean success = false;

        String query = "INSERT INTO purchase_list_item (purchase_list_id, goods_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (Map.Entry<Integer, Integer> entry : goodsMap.entrySet()) {
                int goodsId = entry.getKey();
                int quantity = entry.getValue();
                statement.setInt(1, purchaseListId);
                statement.setInt(2, goodsId);
                statement.setInt(3, quantity);
                statement.addBatch();
            }

            int[] rows = statement.executeBatch();
            int totalRows = Arrays.stream(rows).sum();
            if (totalRows == goodsMap.size()) {
                success = true;
            }
        }

        return success;
    }

    public boolean insertPayment(Payment payment) throws SQLException {
        boolean success = false;
        Connection connection = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
        String sql = "INSERT INTO payment (id ,contract_id ,purchase_list_id , amount, payment_date) VALUES (?, ?, ?, ?, ?)";
        pstmt1 = connection.prepareStatement(sql, org.mariadb.jdbc.Statement.RETURN_GENERATED_KEYS);
        pstmt1.setInt(1,payment.getId());
        pstmt1.setInt(2, payment.getContractId());
        pstmt1.setInt(3, payment.getPurchaseListId());
        pstmt1.setDouble(4, payment.getAmount());
        pstmt1.setDate(5, Date.valueOf(LocalDate.now()));
        pstmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean checkPurchaseListItems(int contractId, int purchaseListId, Map<Integer, Integer> goodsMap) throws SQLException {
        boolean success = false;

        String query = "SELECT COUNT(*) FROM purchase_list_item " +
                "JOIN goods ON purchase_list_item.goods_id = goods.id " +
                "WHERE purchase_list_id = ? AND goods_id = ? " +
                "AND quantity > (" +
                "SELECT COUNT(*) FROM payment " +
                "WHERE contract_id = ? AND purchase_list_id = ?" +
                ")";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (Map.Entry<Integer, Integer> entry : goodsMap.entrySet()) {
                int goodsId = entry.getKey();
                int quantity = entry.getValue();
                statement.setInt(1, purchaseListId);
                statement.setInt(2, goodsId);
                statement.setInt(3, contractId);
                statement.setInt(4, purchaseListId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count == 1) {
                        success = true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return success;
    }
}
