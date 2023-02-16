package com.dgut.entity;

import java.util.Date;

public class Contract {
    public static final String STATUS_SIGNED = "Signed";
    public static final String STATUS_IN_PROGRESS = "InProgress";
    public static final String STATUS_COMPLETED = "Completed";
    private Integer id;
    private Integer customerId;
    private Integer salespersonId;
    private Integer purchaseListId;
    private Date startDate;
    private Date endDate;
    private Double amount;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(Integer salespersonId) {
        this.salespersonId = salespersonId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        if (status.equals(STATUS_SIGNED) ||
                status.equals(STATUS_IN_PROGRESS) ||
                status.equals(STATUS_COMPLETED)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }
    }

    public Integer getPurchaseListId() {
        return purchaseListId;
    }

    public void setPurchaseListId(Integer purchaseListId) {
        this.purchaseListId = purchaseListId;
    }

}
