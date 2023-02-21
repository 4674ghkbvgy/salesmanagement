package com.dgut.dao;

import com.dgut.entity.Contract;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;
import com.dgut.entity.User;
import com.dgut.util.MyUtil;
import org.mariadb.jdbc.Statement;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.dgut.util.MyUtil.getConnection;

public class ContractDaoImpl implements ContractDao {

    public void createContractAndPurchaseList(Contract contract, PurchaseList purchaseList, List<PurchaseListItem> purchaseListItems) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            // 启动事务
            connection.setAutoCommit(false);
            // 创建合同

            // 创建采购清单
            String sql2 = "insert into purchase_list () values ()";
            pstmt2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            pstmt2.executeUpdate();
            rs = pstmt2.getGeneratedKeys();
            if (rs.next()) {
                int purchaseListId = rs.getInt(1);
                // 创建采购清单项目
                for (PurchaseListItem purchaseListItem : purchaseListItems) {
                    String sql3 = "insert into purchase_list_item (purchase_list_id, goods_id, quantity) values (?,?,?)";
                    pstmt2 = connection.prepareStatement(sql3);
                    pstmt2.setInt(1, purchaseListId);
                    pstmt2.setInt(2, purchaseListItem.getGoodsId());
                    pstmt2.setInt(3, purchaseListItem.getQuantity());
                    pstmt2.executeUpdate();
                }
                String sql1 = "insert into contract (customer_id, salesperson_id, start_date, end_date, amount, status, purchase_list_id) values (?,?,?,?,?,?,?)";
                pstmt1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                pstmt1.setInt(1, contract.getCustomerId());
                pstmt1.setInt(2, contract.getSalespersonId());
                pstmt1.setDate(3, new java.sql.Date(contract.getStartDate().getTime()));
                pstmt1.setDate(4, new java.sql.Date(contract.getEndDate().getTime()));
                pstmt1.setDouble(5, contract.getAmount());
                pstmt1.setString(6, contract.getStatus().toString());
                pstmt1.setInt(7, purchaseListId);
                pstmt1.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();

            connection.rollback();
            throw new SQLException("创建合同失败！");
        } finally {
            MyUtil.close(connection, pstmt1);
        }


    }

    @Override
    public List<Contract> findAll() {

        List<Contract> contractList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM contract";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int salespersonId = rs.getInt("salesperson_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                int purchaseListId = rs.getInt("purchase_list_id");

                Contract contract = new Contract(id, customerId, salespersonId, purchaseListId, startDate, endDate, amount, status);

                contractList.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyUtil.close(conn, ps);
        }return contractList;
    }

    @Override
    public List<Contract> findByUser(User user) {

        List<Contract> contractList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM contract WHERE customer_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int salespersonId = rs.getInt("salesperson_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                int purchaseListId = rs.getInt("purchase_list_id");

                Contract contract = new Contract(id, customerId, salespersonId, purchaseListId, startDate, endDate, amount, status);

                contractList.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyUtil.close(conn, ps);
        }
        return contractList;
    }

    @Override
    public int save(Contract contract) {
        return 0;
    }

    @Override
    public int update(Contract contract) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }


    public Contract getContractById(int contractId) {
        List<Contract> contractList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        Contract contract = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM contract WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, contractId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int salespersonId = rs.getInt("salesperson_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                Double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                int purchaseListId = rs.getInt("purchase_list_id");

                contract = new Contract(id, customerId, salespersonId, purchaseListId, startDate, endDate, amount, status);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyUtil.close(conn, ps);
        }return contract;
    }
}
