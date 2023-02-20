package com.dgut.entity;


import java.sql.Date;

public class Payment {
    private int id;
    private int contractId;
    private int purchaseListId;
    private Double amount;
    private Date paymentDate;

    public Payment(int contractId, int purchaseListId, Double amount, Date paymentDate) {
        this.contractId = contractId;
        this.purchaseListId = purchaseListId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getPurchaseListId() {
        return purchaseListId;
    }

    public void setPurchaseListId(int purchaseListId) {
        this.purchaseListId = purchaseListId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}