package com.dgut.dao;

import com.dgut.entity.Contract;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;
import com.dgut.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface ContractDao {
    List<Contract> findAll();
    List<Contract> findByUser(User user);

    int save(Contract contract);
    int update(Contract contract);
    int deleteById(int id);
    void createContractAndPurchaseList(Contract contract, PurchaseList purchaseList, List<PurchaseListItem> purchaseListItems) throws SQLException;
}