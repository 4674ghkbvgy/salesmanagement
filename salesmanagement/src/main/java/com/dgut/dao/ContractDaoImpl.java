package com.dgut.dao;

import com.dgut.entity.Contract;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;
import com.dgut.util.MyUtil;
import org.mariadb.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String sql1 = "insert into contract (customer_id, salesperson_id, start_date, end_date, amount, status) values (?,?,?,?,?,?)";
            pstmt1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            pstmt1.setInt(1, contract.getCustomerId());
            pstmt1.setInt(2, contract.getSalespersonId());
            pstmt1.setDate(3, new java.sql.Date(contract.getStartDate().getTime()));
            pstmt1.setDate(4, new java.sql.Date(contract.getEndDate().getTime()));
            pstmt1.setDouble(5, contract.getAmount());
            pstmt1.setString(6, contract.getStatus().toString());
            pstmt1.executeUpdate();
            rs = pstmt1.getGeneratedKeys();
            if (rs.next()) {
                int contractId = rs.getInt(1);
                // 创建采购清单
                String sql2 = "insert into purchase_list (contract_id) values (?)";
                pstmt2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                pstmt2.setInt(1, contractId);
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
                }
                connection.commit();
            }
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
        return null;
    }

    @Override
    public Contract findById(int id) {
        return null;
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
}
