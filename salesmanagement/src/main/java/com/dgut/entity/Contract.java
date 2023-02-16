package com.dgut.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public Contract(Integer id, Integer customerId, Integer salespersonId, Integer purchaseListId, Date startDate, Date endDate, Double amount, String status) {
        this.id = id;
        this.customerId = customerId;
        this.salespersonId = salespersonId;
        this.purchaseListId = purchaseListId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.status = status;
    }
    public Contract() {

    }

    public Contract(int id, int customerId, int salespersonId, int purchaseListId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal amount, String status) {

    }


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
