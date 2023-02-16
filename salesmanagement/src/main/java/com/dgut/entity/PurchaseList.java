package com.dgut.entity;

import java.util.List;

public class PurchaseList {
    private Integer id;

    public PurchaseList() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContract(Contract contract) {
    }

    public void setPurchaseListItems(List<PurchaseListItem> purchaseListItems) {
    }
}