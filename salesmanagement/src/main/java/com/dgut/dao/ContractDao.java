package com.dgut.dao;

import com.dgut.entity.Contract;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;

import java.sql.SQLException;
import java.util.List;

public interface ContractDao {
    List<Contract> findAll();
    Contract findById(int id);
    int save(Contract contract);
    int update(Contract contract);
    int deleteById(int id);
    void createContractAndPurchaseList(Contract contract, PurchaseList purchaseList, List<PurchaseListItem> purchaseListItems) throws SQLException;
}